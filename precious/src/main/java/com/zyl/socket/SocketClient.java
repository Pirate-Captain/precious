package com.zyl.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetSocketAddress;
import java.net.Socket;

public class SocketClient {
    private static String host = "127.0.0.1";
    private static int port = 8999;
    
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket();
        socket.connect(new InetSocketAddress(host, port));
        
        new Thread(new SocketReadThread(socket)).start();
        
        while ( true ) {
            if ( socket.isClosed() ) {
                break;
            }
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String line = reader.readLine();
            PrintStream printStream = new PrintStream(socket.getOutputStream());
            printStream.println(line);
            printStream.flush();
        }
    }
    
}

class SocketReadThread implements Runnable {
    private Socket socket;
    
    public SocketReadThread(Socket socket) {
        this.socket = socket;
    }
    
    @Override
    public void run() {
        while ( true ) {
            if ( socket.isClosed() ) {
                break;
            }
            try {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                System.out.println(bufferedReader.readLine());
            } catch ( IOException e ) {
                e.printStackTrace();
            }
        }
    }
}