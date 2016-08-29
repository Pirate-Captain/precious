package com.zyl.rpc;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
    private Socket socket;
    private ObjectOutputStream oos;
    private ObjectInputStream ois;
    private String host;
    private int port;
    
    public Client(String host, int port) {
        this.host = host;
        this.port = port;
    }
    
    private void initClient() throws UnknownHostException, IOException {
        socket = new Socket(host, port);
    }
    
    public void invoke(Call call) throws UnknownHostException, IOException, ClassNotFoundException {
        initClient();
        
        //发送call对象
        oos = new ObjectOutputStream(socket.getOutputStream());
        oos.writeObject(call);
        oos.flush();
        
        //等待服务器发送结果
        ois = new ObjectInputStream(socket.getInputStream());
        Call callResult = (Call)ois.readObject();
        System.out.println(callResult.getResult() + "******************");
        call.setResult(callResult.getResult());
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public ObjectOutputStream getOos() {
        return oos;
    }

    public void setOos(ObjectOutputStream oos) {
        this.oos = oos;
    }

    public ObjectInputStream getOis() {
        return ois;
    }

    public void setOis(ObjectInputStream ois) {
        this.ois = ois;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}