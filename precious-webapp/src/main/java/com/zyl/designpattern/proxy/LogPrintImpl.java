/**
 * Created on 2016-4-20
 */
package com.zyl.designpattern.proxy;

/**
 * @author zhuyl<a href="mailto:zhuyl@chsi.com.cn">zhu Youliang</a>
 * @version $Id$
 */
public class LogPrintImpl implements LogPrint {

    @Override
    public void printLogInfo(String logInfo) {
        System.out.println("The LogInfo is : " + logInfo);
    }
}