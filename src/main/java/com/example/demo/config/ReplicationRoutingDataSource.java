package com.example.demo.config;

import com.example.demo.util.CircularList;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.util.Map;
import java.util.stream.Collectors;

public class ReplicationRoutingDataSource extends AbstractRoutingDataSource {

    private CircularList<String> dataSourceNameList;

    @Override
    public void setTargetDataSources(Map<Object, Object> targetDataSources) {
        // map으로 받는다.
        super.setTargetDataSources(targetDataSources);

        dataSourceNameList = new CircularList<>(
          // key들을 가져와서 list로 만들겠다.
          targetDataSources.keySet()
                .stream()
                .filter(key -> key.toString().contains("slave"))    // slave가 포함된 것들을 가져오겠다.
                .map(key -> key.toString())
                .collect(Collectors.toList())   // map을 list로 만들겠다.
        );
    }

    @Override
    protected Object determineCurrentLookupKey() {  // 사용되는 곳..?
        boolean isReadOnly = TransactionSynchronizationManager.isCurrentTransactionReadOnly();
        if (isReadOnly) {
            return dataSourceNameList.getOne();
        } else {
            return "master";
        }
    }
}
