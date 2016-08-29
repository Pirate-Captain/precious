package com.zyl.rpc;

public class RPCServiceImpl implements RPCService {

    @Override
    public void show(String name, String code) {
        System.out.println("rpc invoke say：" + name + ", " + code);
    }
}