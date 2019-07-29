/**
 * Created on 2016-4-20
 */
package com.zyl.designpattern.proxy;

/**
 * @author iaskbear <a href="mailto:iaskbear@gmail.com">iaskbear</a>
 * @version $Id$
 */
public class LogPrintProxy implements LogPrint {
    private LogPrintImpl logPrintImpl;
    
    public LogPrintProxy(LogPrintImpl logPrintImpl) {
        this.logPrintImpl = logPrintImpl;
    }

    @Override
    public void printLogInfo(String logInfo) {
        System.out.println("Do something before print the log info");
        logPrintImpl.printLogInfo(logInfo);
        System.out.println("Do something after print the log info");
    }
}