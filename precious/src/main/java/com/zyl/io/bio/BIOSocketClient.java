/**
 * chsi
 * Created on 2017-09-29
 */
package com.zyl.io.bio;

import java.io.IOException;
import java.net.Socket;

/**
 * @author iaskbear <a href="mailto:iaskbear@gmail.com">iaskbear</a>
 * @version $Id$
 */
public class BIOSocketClient {
    private static String HOST = "localhost";
    private static int port = 8669;

    public void connectServer() {
        try {
            Socket socket = new Socket(HOST, port);
            new Thread(new SocketClientWriteMessageThread(socket)).start();
            new Thread(new SocketClientReadMessageThread(socket)).start();
        } catch ( IOException e ) {
            e.printStackTrace();
        }
    }
}