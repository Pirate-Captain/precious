package com.zyl.nio.pipe;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Pipe;
import java.nio.channels.Pipe.SinkChannel;
import java.nio.channels.Pipe.SourceChannel;

public class PipeTest {
    public static void main(String[] args) throws IOException {
        Pipe pipe = Pipe.open();
        SinkChannel sinkChannel = pipe.sink();
        
        String message = "Life is like a box of chocolate, you never know whick kind tast you will get next!";
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        byteBuffer.put(message.getBytes("utf-8"));
        
        byteBuffer.flip();
        
        while ( byteBuffer.hasRemaining() ) {
            sinkChannel.write(byteBuffer);
        }
        
        SourceChannel sourceChannel = pipe.source();
        ByteBuffer sourceBuffer = ByteBuffer.allocate(1024);
        
        int byteLength = sourceChannel.read(sourceBuffer);
        sourceBuffer.flip();
        byte[] bufferByte = new byte[message.getBytes().length];
        sourceBuffer.get(bufferByte, 0, byteLength);
        System.out.println(new String(bufferByte, "utf-8"));
        
        sourceChannel.close();
        sinkChannel.close();
    }
}