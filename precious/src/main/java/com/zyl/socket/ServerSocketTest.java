package com.zyl.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

import org.apache.commons.io.IOUtils;

public class ServerSocketTest {
    private static int port = 8999;
    
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket();
        serverSocket.bind(new InetSocketAddress(port));
        
        while ( true ) {
            Socket socket = serverSocket.accept();
            System.out.println("Connet the Server's host:" + socket.getInetAddress().getHostAddress());
            new Thread(new ServerSocketReadThread(socket)).start();
        }
    }
}

class ServerSocketReadThread implements Runnable {
    private Socket socket = null;
    
    public ServerSocketReadThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        if ( socket.isClosed() ) {
            return;
        }
        
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintStream printStream = new PrintStream(socket.getOutputStream());
            String line = null;
            boolean flag = true;
            while ( flag ) {
                line = reader.readLine();
                System.out.println(socket.getInetAddress().getHostAddress() + " Client say :" + line);
                printStream.println("echo：" + line);
                printStream.flush();
            }
            IOUtils.closeQuietly(reader);
            IOUtils.closeQuietly(printStream);
            socket.close();
        } catch ( IOException e ) {
            e.printStackTrace();
        }
    }
}