/**
 * chsi
 * Created on 2017-09-29
 */
package com.zyl.io.bio;

/**
 * @author iaskbear <a href="mailto:iaskbear@gmail.com">iaskbear</a>
 * @version $Id$
 */
public class BIOServerTest {
    public static void main(String[] args) {
        BIOServerSocket serverSocket = new BIOServerSocket();
        serverSocket.startServer();
    }
}