/**
 * chsi
 * Created on 2017-09-29
 */
package com.zyl.io.bio;

/**
 * @author zhuyl<a href="mailto:zhuyl@chsi.com.cn">zhu Youliang</a>
 * @version $Id$
 */
public class BIOServerTest {
    public static void main(String[] args) {
        BIOServerSocket serverSocket = new BIOServerSocket();
        serverSocket.startServer();
    }
}