package com.zyl.rpc;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import org.apache.commons.io.IOUtils;

public class RPCListener implements Runnable {
    private Server server;
    private ServerSocket serverSocket;
    
    public RPCListener(Server server) {
        this.server = server;
    }
    
    @Override
    public void run() {
        // 首先定义服务器监听socket
        try {
            serverSocket = new ServerSocket(server.getPort());
        } catch ( IOException e ) {
            e.printStackTrace();
        }
        
        while ( server.isRunning() ) {
            ObjectOutputStream oos = null;
            ObjectInputStream ois = null;
            try {
                Socket clientSocket = serverSocket.accept();
                // 获取客户端的请求
                ois = new ObjectInputStream(clientSocket.getInputStream());
                Call call = (Call)ois.readObject();
                // 服务器处理
                server.call(call);
                
                // 再将结果返回给客户端
                oos = new ObjectOutputStream(clientSocket.getOutputStream());
                oos.writeObject(call);
            } catch ( IOException e ) {
                e.printStackTrace();
            } catch ( ClassNotFoundException e ) {
                e.printStackTrace();
            } finally {
                IOUtils.closeQuietly(oos);
                IOUtils.closeQuietly(ois);
            }
        }
    }
}