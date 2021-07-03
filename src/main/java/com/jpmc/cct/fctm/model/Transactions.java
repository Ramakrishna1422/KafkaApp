package com.jpmc.cct.fctm.model;

public class Transactions {

    private String uuid;
    private String accId;
    private String transId;
    private String amount;

    public Transactions() {
    }

    public Transactions(String uuid, String accId, String transId, String amount) {
        this.uuid = uuid;
        this.accId = accId;
        this.transId = transId;
        this.amount = amount;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getAccId() {
        return accId;
    }

    public void setAccId(String accId) {
        this.accId = accId;
    }

    public String getTransId() {
        return transId;
    }

    public void setTransId(String transId) {
        this.transId = transId;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
