/**
 * Created on 2016-4-20
 */
package com.zyl.designpattern.proxy;

/**
 * @author zhuyl<a href="mailto:zhuyl@chsi.com.cn">zhu Youliang</a>
 * @version $Id$
 */
public class TestProxyPattern {
    public static void main(String[] args) {
        LogPrintImpl logPrintImpl = new LogPrintImpl();
        LogPrintProxy proxy = new LogPrintProxy(logPrintImpl);
        proxy.printLogInfo("I am the proxy pattern test class!");
    }
}