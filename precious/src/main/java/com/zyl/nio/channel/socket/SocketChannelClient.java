package com.zyl.nio.channel.socket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Scanner;


public class SocketChannelClient {
    private String host;
    private int port;
    private Selector selector;
    private SocketChannel socketChannel;
    private static SocketChannelClient client = null;
    private static boolean mFlag = true;
    
    public SocketChannelClient(String host, int port) throws IOException {
        this.host = host;
        this.port = port;
        
        socketChannel = SocketChannel.open(new InetSocketAddress(this.host, this.port));
        socketChannel.configureBlocking(false);
        
        selector = Selector.open();
        socketChannel.register(selector, SelectionKey.OP_READ);
        
        new Thread(new SocketChannelReadThread(selector)).start();
    }
    
    public void sendMessage(String message) throws IOException {
        ByteBuffer byteBuffer = ByteBuffer.wrap(message.getBytes("UTF-8"));
        System.out.println(socketChannel.write(byteBuffer));
    }
    
    public static void main(String[] args) throws IOException, InterruptedException {
        client = new SocketChannelClient("127.0.0.1", 8999);
        
        Thread.sleep(10000);
        
        new Thread() {
            @Override
            public void run() {
                if ( !client.socketChannel.isOpen() || !client.socketChannel.isConnected() ) {
                    super.run();
                }
                
                try {
                    client.sendMessage("你好!Nio!醉里挑灯看剑,梦回吹角连营");
                    while ( mFlag ) {
                        Scanner scanner = new Scanner(System.in);
                        String message = scanner.next();
                        client.sendMessage(message);
                    }
                } catch ( IOException e ) {
                    mFlag = false;
                } finally {
                    mFlag = false;
                }
                super.run();
            }
        }.start();
    }
}