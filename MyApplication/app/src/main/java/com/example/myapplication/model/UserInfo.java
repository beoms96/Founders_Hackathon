package com.example.myapplication.model;

public class UserInfo {

    private String seedHash;
    private String address;
    private String balance;
    private Review reviewToWrite;

    public UserInfo() {

    }

    public static UserInfo getInstance() {
        return LazyHolder.INSTANCE;
    }

    public String getSeedHash() {
        return seedHash;
    }

    public void setSeedHash(String seedHash) {
        this.seedHash = seedHash;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public Review getReviewToWrite() {
        return reviewToWrite;
    }

    private static class LazyHolder {
        private static final UserInfo INSTANCE = new UserInfo();
    }
}