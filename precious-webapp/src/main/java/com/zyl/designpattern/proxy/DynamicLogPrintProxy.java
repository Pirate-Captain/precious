/**
 * Created on 2016-4-20
 */
package com.zyl.designpattern.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author iaskbear <a href="mailto:iaskbear@gmail.com">iaskbear</a>
 * @version $Id$
 */
public class DynamicLogPrintProxy implements InvocationHandler {
    private LogPrint logPrint;
    
    public DynamicLogPrintProxy(LogPrint logPrint) {
        this.logPrint = logPrint;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("Dynamic proxy do something before invoke");
//        Object result = method.invoke(logPrint, args); 如果这样调用会陷入死循环
        Object result = method.invoke(logPrint, args);
        System.out.println("Dynamic proxy do something after invoke");
        return result;
    }
}