package com.zyl.designpattern.chainofresposi;

public abstract class Handler {
    protected Handler successor;
    
    abstract void handleRequest(RequestData requestData);

    public void setSuccessor(Handler successor) {
        this.successor = successor;
    }
}