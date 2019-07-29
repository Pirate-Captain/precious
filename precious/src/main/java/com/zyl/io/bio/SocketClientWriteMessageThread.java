/**
 * chsi
 * Created on 2017-09-29
 */
package com.zyl.io.bio;

import org.apache.commons.lang.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * @author iaskbear <a href="mailto:iaskbear@gmail.com">iaskbear</a>
 * @version $Id$
 */
public class SocketClientWriteMessageThread implements Runnable {
    private Socket socket;

    public SocketClientWriteMessageThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter os;
        String message;
        while ( true ) {
            if ( !socket.isConnected() ) {
                break;
            }
            try {
                message = reader.readLine();
                if ( StringUtils.isBlank(message) ) {
                    continue;
                }
                os = new PrintWriter(socket.getOutputStream(), true);
                os.println(message);
                os.flush();
            } catch ( IOException e ) {
                e.printStackTrace();
            }
        }
    }
}