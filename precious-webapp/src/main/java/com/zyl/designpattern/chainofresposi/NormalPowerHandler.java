package com.zyl.designpattern.chainofresposi;

public class NormalPowerHandler extends Handler {

    @Override
    void handleRequest(RequestData requestData) {
        if ( requestData.getAmount() >= 20 && requestData.getAmount() < 50 ) {
            System.out.println(this.getClass().getName() + " handler the request!");
        } else {
            if ( null != successor ) {
                successor.handleRequest(requestData);
            }
        }
    }
}