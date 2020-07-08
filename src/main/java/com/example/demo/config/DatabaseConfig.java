package com.example.demo.config;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.LazyConnectionDataSourceProxy;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

/**
 *
 * DatabaseProperty로부터 설정값을 읽어와서 DataSource를 생성하고 ReplicationRoutinDataSource에 담는다.
 * Lazy load를 위해 LazyConnectionDataSourceProxy를 사용한다.
 *
 * 따라서, transaction start시점이 아닌 commit 시점에 Connection 객체가 리턴된다.
 *
 * */

@Configuration
public class DatabaseConfig {

    @Autowired
    private DatabaseProperty databaseProperty;

    // 데이터소스 생성
    public DataSource createDataSource(String url) {
        SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
        dataSource.setUrl(url);
        dataSource.setDriverClass(com.mysql.jdbc.Driver.class);
        dataSource.setUsername(databaseProperty.getUsername());
        dataSource.setPassword(databaseProperty.getPassword());

        return dataSource;
    }

    @Bean
    public DataSource routingDataSource() {
        ReplicationRoutingDataSource replicationRoutingDataSource = new ReplicationRoutingDataSource();

        // masterDB url가져와서 DataSource생성
        DataSource master = createDataSource(databaseProperty.getUrl());

        Map<Object, Object> dataSourceMap = new LinkedHashMap<>();
        // map에 masterDB정보 저장
        dataSourceMap.put("master", master);

        // slaveDB list가져와서 slaveDB 개수만큼 map에 저장 
        databaseProperty.getSlaveList().forEach(slave -> {
            dataSourceMap.put(slave.getName(), createDataSource(slave.getUrl()));
        });

        // masterDB, slaveDB 정보 저장된 것 넘겨주기
        replicationRoutingDataSource.setTargetDataSources(dataSourceMap);
        // masterDB default값으로 넘겨주기
        replicationRoutingDataSource.setDefaultTargetDataSource(master);

        return replicationRoutingDataSource;
    }

    @Bean
    public DataSource dataSource() {
        /*LazyConnectionDataSourceProxy 트랜잭션이 시작 되더라도 실제로 커넥션이 필요한 경우에만
          데이터 소스에서 커넥션을 반환한다.*/
        // LazyLoad를 위해 LazyConnectionDataSourceProxy로 감싼다.
        return new LazyConnectionDataSourceProxy(routingDataSource());
    }

    // Java에서 영속성 컨텍스트 설정
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        // LazyConnectionDataSourceProxy로 감싼 데이터소스를 set한다.
        entityManagerFactoryBean.setDataSource(dataSource());
        //import @Entity classes
        entityManagerFactoryBean.setPackagesToScan("com.example.demo");
        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        entityManagerFactoryBean.setJpaVendorAdapter(vendorAdapter);

        return entityManagerFactoryBean;
    }

    // Transaction 처리
    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
    // JpaTransactionManager를 사용하기 위해서는 entityManagerFactory가 필요
        JpaTransactionManager tm = new JpaTransactionManager();
        tm.setEntityManagerFactory(entityManagerFactory);

		return tm;
	}
}
