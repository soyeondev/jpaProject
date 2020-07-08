package com.example.demo.def;

public enum ConferenceStatus {
    PREPARING(1),
    OPEN(2),
    CLOSED(3);

    private short status;

    private ConferenceStatus(int status) {
        this.status = (short)status;
    }

    public static ConferenceStatus from(short code){
        if(code == 1){
            return PREPARING;
        }else if(code == 2){
            return OPEN;
        }else if(code == 3){
            return CLOSED;
        }
        return null;
    }

    public short code(){
        return status;
    }
}
