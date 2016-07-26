package com.zyl.nio.channel.socket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;

public class ServerSocketChannelTest {
    private static Selector selector;
    
    public static void main(String[] args) throws IOException {
        selector = Selector.open();
        
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.socket().bind(new InetSocketAddress(8999));
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        
        ServerSocketChannelSelector channelSelector = new ServerSocketChannelSelector();
        while ( true ) {
            if ( !selector.isOpen() || !serverSocketChannel.isOpen() ) {
                break;
            }
            if ( selector.select(3000) == 0 ) {
                continue;
            }
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while ( iterator.hasNext() ) {
                SelectionKey selectionKey = iterator.next();
                if ( selectionKey.isAcceptable() ) {
                    channelSelector.handAccept(selectionKey);
                } else if ( selectionKey.isConnectable() ) {
                    System.out.println("连接到服务器！");
                } else if (selectionKey.isReadable() ) {
                    channelSelector.handRead(selectionKey);
                }
                
                iterator.remove();
            }
        }
    }
}