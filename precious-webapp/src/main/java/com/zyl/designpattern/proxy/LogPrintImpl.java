/**
 * Created on 2016-4-20
 */
package com.zyl.designpattern.proxy;

/**
 * @author iaskbear <a href="mailto:iaskbear@gmail.com">iaskbear</a>
 * @version $Id$
 */
public class LogPrintImpl implements LogPrint {

    @Override
    public void printLogInfo(String logInfo) {
        System.out.println("The LogInfo is : " + logInfo);
    }
}