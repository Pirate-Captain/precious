package com.zyl.designpattern.chainofresposi;

public class HanlderClient {
    public static void main(String[] args) {
        Handler lowerPowerHandler = new LowerPowerHandler();
        Handler normalPowerHandler = new NormalPowerHandler();
        Handler highPowerHandler = new HighPowerHandler();
        
        lowerPowerHandler.setSuccessor(normalPowerHandler);
        normalPowerHandler.setSuccessor(highPowerHandler);
        
        lowerPowerHandler.handleRequest(new RequestData(10));
        lowerPowerHandler.handleRequest(new RequestData(30));
        lowerPowerHandler.handleRequest(new RequestData(70));
    }
}