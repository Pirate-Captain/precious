/**
 * chsi
 * Created on 2017-09-29
 */
package com.zyl.io.bio;

/**
 * @author zhuyl<a href="mailto:zhuyl@chsi.com.cn">zhu Youliang</a>
 * @version $Id$
 */
public class BIOClientTest {
    public static void main(String[] args) {
        BIOSocketClient client = new BIOSocketClient();
        client.connectServer();
    }
}