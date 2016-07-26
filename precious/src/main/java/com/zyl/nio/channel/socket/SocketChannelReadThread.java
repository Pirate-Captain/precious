package com.zyl.nio.channel.socket;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SocketChannelReadThread implements Runnable {
    private Logger log = LoggerFactory.getLogger(this.getClass());
    private Selector selector;
    
    public SocketChannelReadThread(Selector selector) {
        this.selector = selector;
    }

    @Override
    public void run() {
        while ( true ) {
            try {
                if ( !selector.isOpen() ) {
                    break;
                }
                if ( selector.select(3000) == 0 ) {
                    continue;
                }
                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                while ( iterator.hasNext() ) {
                    SelectionKey selectionKey = iterator.next();
                    if ( selectionKey.isReadable() ) {
                        SocketChannel socketChannel = (SocketChannel)selectionKey.channel();
                        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                        
                        StringBuffer messageBuffer = new StringBuffer();
                        
                        while ( socketChannel.read(byteBuffer) != -1 ) {
                            byteBuffer.flip();
                            messageBuffer.append(Charset.forName("UTF-8").newDecoder().decode(byteBuffer).toString());
                            byteBuffer.clear();
                        }
                        
                        System.out.println("Receive data:" + messageBuffer.toString());
                        selectionKey.interestOps(SelectionKey.OP_READ);
                        iterator.remove();
                    }
                }
            } catch ( IOException e ) {
                log.error("读取数据异常：" + e);
            }
        }
    }
}