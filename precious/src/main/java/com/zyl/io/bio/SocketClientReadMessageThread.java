/**
 * chsi
 * Created on 2017-09-29
 */
package com.zyl.io.bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * @author zhuyl<a href="mailto:zhuyl@chsi.com.cn">zhu Youliang</a>
 * @version $Id$
 */
public class SocketClientReadMessageThread implements Runnable {
    private Socket socket;

    public SocketClientReadMessageThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        BufferedReader reader;
        String message;
        while ( true ) {
            if ( !socket.isConnected() ) {
                break;
            }
            if ( socket.isClosed() || socket.isInputShutdown() || socket.isOutputShutdown() ) {
                break;
            }

            try {
                socket.sendUrgentData(0xFF);
            } catch ( IOException e ) {
                break;
            }
            try {
                reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                message = reader.readLine();
                System.out.println(message);
            } catch ( IOException e ) {
                e.printStackTrace();
            }
        }
    }
}