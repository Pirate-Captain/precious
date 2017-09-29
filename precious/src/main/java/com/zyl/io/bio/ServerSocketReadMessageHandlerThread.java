/**
 * chsi
 * Created on 2017-09-29
 */
package com.zyl.io.bio;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;

/**
 * @author zhuyl<a href="mailto:zhuyl@chsi.com.cn">zhu Youliang</a>
 * @version $Id$
 */
public class ServerSocketReadMessageHandlerThread implements Runnable {
    private Socket socket;
    private List<Socket> clientSocketList;

    public ServerSocketReadMessageHandlerThread(Socket socket, List<Socket> clientSocketList) {
        this.socket = socket;
        this.clientSocketList = clientSocketList;
    }

    @Override
    public void run() {
        sayHelloMessage();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch ( IOException e ) {
            e.printStackTrace();
        }
        String message;
        while ( true ) {
            if ( !socket.isConnected() ) {
                clientSocketList.remove(socket);
                break;
            }
            if ( socket.isClosed() || socket.isInputShutdown() || socket.isOutputShutdown() ) {
                clientSocketList.remove(socket);
                break;
            }

            try {
                socket.sendUrgentData(0xFF);
            } catch ( IOException e ) {
                clientSocketList.remove(socket);
                break;
            }
            try {
                message = br.readLine();
                if ( StringUtils.equals("quite", message) ) {
                    break;
                }
                spreadMessage(message);
            } catch ( IOException e ) {
                e.printStackTrace();
            } catch ( Exception e ) {
            }
        }
        IOUtils.closeQuietly(br);
    }

    private void sayHelloMessage() {
        StringBuilder messageBuilder = new StringBuilder();
        messageBuilder.append("欢迎登录，目前在线人数：");
        messageBuilder.append(clientSocketList.size());

        try {
            PrintWriter os = new PrintWriter(socket.getOutputStream(), true);
            os.println(messageBuilder.toString());
            os.flush();
        } catch ( IOException e ) {
            e.printStackTrace();
        }
    }

    private void spreadMessage(String message) {
        if ( StringUtils.isBlank(message) ) {
            return;
        }
        for ( Socket tmpSocket : clientSocketList ) {
            if ( socket == tmpSocket ) {
                continue;
            }
            try {
                PrintWriter os = new PrintWriter(tmpSocket.getOutputStream(), true);
                os.println(socket.toString() + message);
                os.flush();
            } catch ( IOException e ) {
                e.printStackTrace();
            }
        }
    }
}