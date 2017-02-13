package com.zyl.rpc;

public class TestRPCClient {
    private static String host = "172.16.10.36";
    private static int port = 2202;
    
    public static void main(String[] args) {
        RPCService rpcService = RPC.getProxy(RPCService.class, host, port);
        rpcService.show("ZhangSan", "2002");
    }
}