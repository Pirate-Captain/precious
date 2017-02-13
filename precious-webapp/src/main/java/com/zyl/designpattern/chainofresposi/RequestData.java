package com.zyl.designpattern.chainofresposi;

public class RequestData {
    private int amount;
    
    public RequestData(int amount) {
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}