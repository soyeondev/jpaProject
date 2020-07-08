package com.example.demo.def;

public enum RoleLevel {
    PROG_DIRECTOR(1),
    CONFER_ADMIN(5),
    MASTER_ADMIN(9);

    private short level;

    private RoleLevel(int level) {
        this.level = (short) level;
    }

    public short code(){
        return level;
    }
}
