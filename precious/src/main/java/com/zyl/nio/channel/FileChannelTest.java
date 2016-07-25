package com.zyl.nio.channel;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

import org.apache.commons.io.IOUtils;

public class FileChannelTest {
    public static void main(String[] args) throws IOException {
        copyFileWithByteBuffer();
        copyFileWithChannel();
    }
    
    private static void copyFileWithChannel() throws IOException {
        FileInputStream fis = new FileInputStream("e:/fileChannel.txt");
        FileOutputStream fos = new FileOutputStream("e:/fileChannel-copy.txt");
        
        FileChannel fromChannel = fis.getChannel();
        FileChannel toChannel = fos.getChannel();
        
        toChannel.transferFrom(fromChannel, 0, fromChannel.size());
        
        IOUtils.closeQuietly(fis);
        IOUtils.closeQuietly(fos);
    }
    
    /**
     * 使用ByteBuffer拷贝文件
     * @throws IOException
     */
    private static void copyFileWithByteBuffer() throws IOException {
        RandomAccessFile randomAccessFile = new RandomAccessFile("fileChannel.txt", "rw");
        FileChannel channel = randomAccessFile.getChannel();
        
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        byte[] outputByte = new byte[1024];
        int byteLength = 0;
        long totalCount = 0;
        long fileSize = channel.size();
        
        FileOutputStream fos = new FileOutputStream("fileChannel-copy.txt");
        FileChannel outputChannel = fos.getChannel();
        
        while ( (byteLength = channel.read(byteBuffer)) != -1 ) {
            byteBuffer.flip();
            byteBuffer.get(outputByte, 0, byteLength);
            totalCount += byteLength;
            System.out.println(totalCount + "/" + fileSize + "：" + String.format("%4.3f", ((float)totalCount / (float)fileSize)));
            byteBuffer.clear();
            
            ByteBuffer ouputBuffer = ByteBuffer.allocate(1024);
            ouputBuffer.put(outputByte);
            ouputBuffer.flip();
            outputChannel.write(ouputBuffer);
        }
        
        outputChannel.force(true);
        
        channel.close();
        IOUtils.closeQuietly(randomAccessFile);
        
        outputChannel.close();
        IOUtils.closeQuietly(fos);
    }
}