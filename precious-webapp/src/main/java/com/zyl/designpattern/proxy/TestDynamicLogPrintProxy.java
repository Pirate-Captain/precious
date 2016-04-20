/**
 * Created on 2016-4-20
 */
package com.zyl.designpattern.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * @author zhuyl<a href="mailto:zhuyl@chsi.com.cn">zhu Youliang</a>
 * @version $Id$
 */
public class TestDynamicLogPrintProxy {
    public static void main(String[] args) {
        LogPrint logPrint = new LogPrintImpl();
        InvocationHandler handler = new DynamicLogPrintProxy(logPrint);
        
        LogPrint proxyObject = (LogPrint)Proxy.newProxyInstance(logPrint.getClass().getClassLoader(), logPrint.getClass().getInterfaces(), handler);
        proxyObject.printLogInfo("I am dyncmic log print proxy");
    }
}