/**
 * chsi
 * Created on 2017-09-29
 */
package com.zyl.io.bio;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * @author iaskbear <a href="mailto:iaskbear@gmail.com">iaskbear</a>
 * @version $Id$
 */
public class BIOServerSocket {
    private Logger log = LoggerFactory.getLogger(this.getClass());
    private static int SERVER_PORT = 8669;
    private List<Socket> clientSocketList = new ArrayList<Socket>();

    public void startServer() {
        ServerSocket serverSocket;
        try {
            serverSocket = new ServerSocket(SERVER_PORT);
        } catch ( IOException e ) {
            log.error("创建server socket失败：{}", e);
            return;
        }
        while ( true ) {
            try {
                Socket socket = serverSocket.accept();
                log.info("有新的链接连入！");
                if ( socket.isConnected() ) {
                    clientSocketList.add(socket);
                    new Thread(new ServerSocketReadMessageHandlerThread(socket, clientSocketList)).start();
                }
            } catch ( IOException e ) {
                log.error("监听新的链接异常：{}", e);
            }
        }
    }
}