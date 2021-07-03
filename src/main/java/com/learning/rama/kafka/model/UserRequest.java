package com.learning.rama.kafka.model;

import java.util.List;

public class UserRequest {
    private String uuid;
    private List<String> accounts;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public List<String> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<String> accounts) {
        this.accounts = accounts;
    }
}
