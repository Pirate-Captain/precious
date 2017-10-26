/**
 * chsi
 * Created on 2017-10-24
 */
package com.zyl.longpolling.servlet3.async;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.AsyncEvent;
import javax.servlet.AsyncListener;
import java.io.IOException;

/**
 * @author zhuyl<a href="mailto:zhuyl@chsi.com.cn">zhu Youliang</a>
 * @version $Id$
 */
public class AsyncLongPollingListener implements AsyncListener {
    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Override
    public void onComplete(AsyncEvent asyncEvent) throws IOException {
        log.info("异步任务已完成！");
    }

    @Override
    public void onTimeout(AsyncEvent asyncEvent) throws IOException {
        log.info("任务超时");
    }

    @Override
    public void onError(AsyncEvent asyncEvent) throws IOException {
        log.info("任务异常" + asyncEvent.getThrowable());
    }

    @Override
    public void onStartAsync(AsyncEvent asyncEvent) throws IOException {
        log.info("异步任务启动");
    }
}
