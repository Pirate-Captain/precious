package com.zyl.rpc;

public class TestRPCServer {
    private static int port = 2202;
    
    public static void main(String[] args) {
        Server server = new RPCServer(port);
        
        server.register(RPCService.class, RPCServiceImpl.class);
        
        server.start();
    }
}