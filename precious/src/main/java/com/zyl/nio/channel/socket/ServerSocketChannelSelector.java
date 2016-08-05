package com.zyl.nio.channel.socket;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;

public class ServerSocketChannelSelector {
    
    public void handAccept(SelectionKey selectionKey) throws IOException {
        SocketChannel socketChannel = ((ServerSocketChannel)selectionKey.channel()).accept();
        socketChannel.configureBlocking(false);
        System.out.println("Connet to host's address：" + socketChannel.socket().getInetAddress().getHostAddress());
        socketChannel.register(selectionKey.selector(), SelectionKey.OP_READ, ByteBuffer.allocate(1024));
    }
    
    public void handRead(SelectionKey selectionKey) throws IOException {
        SocketChannel socketChannel = (SocketChannel)selectionKey.channel();
        ByteBuffer byteBuffer = (ByteBuffer)selectionKey.attachment();
        byteBuffer.clear();
        StringBuffer messageBuffer = new StringBuffer();
        if ( !socketChannel.isOpen() || !socketChannel.isConnected() ) {
            return;
        }
        socketChannel.read(byteBuffer);
        byteBuffer.flip();
        messageBuffer.append(Charset.forName("UTF-8").newDecoder().decode(byteBuffer).toString());
        byteBuffer.clear();
        System.out.println("Receive the message："+messageBuffer.toString()+" from " + socketChannel.socket().getInetAddress().getHostAddress());
        String replyMessage = "已收到您的信息：" + messageBuffer.toString();
        socketChannel.write(ByteBuffer.wrap(replyMessage.getBytes("UTF-8")));
        selectionKey.interestOps(SelectionKey.OP_READ|SelectionKey.OP_WRITE);
    }
}