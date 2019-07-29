/**
 * chsi
 * Created on 2017-09-29
 */
package com.zyl.io.bio;

/**
 * @author iaskbear <a href="mailto:iaskbear@gmail.com">iaskbear</a>
 * @version $Id$
 */
public class BIOClientTest {
    public static void main(String[] args) {
        BIOSocketClient client = new BIOSocketClient();
        client.connectServer();
    }
}