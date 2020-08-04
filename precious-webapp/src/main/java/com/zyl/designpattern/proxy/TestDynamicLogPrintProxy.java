/**
 * Created on 2016-4-20
 */
package com.zyl.designpattern.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * @author iaskbear <a href="mailto:iaskbear@gmail.com">iaskbear</a>
 * @version $Id$
 */
public class TestDynamicLogPrintProxy {
    public static void main(String[] args) {
        LogPrint logPrint = new LogPrintImpl();
        InvocationHandler handler = new DynamicLogPrintProxy(logPrint);

        System.getProperties().setProperty("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        LogPrint proxyObject = (LogPrint)Proxy.newProxyInstance(logPrint.getClass().getClassLoader(), logPrint.getClass().getInterfaces(), handler);
        proxyObject.printLogInfo("I am dyncmic log print proxy");
    }
}