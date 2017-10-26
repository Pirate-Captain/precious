/**
 * chsi
 * Created on 2017-10-24
 */
package com.zyl.longpolling.servlet3.async;

import net.sf.json.JSONObject;

import javax.servlet.AsyncContext;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhuyl<a href="mailto:zhuyl@chsi.com.cn">zhu Youliang</a>
 * @version $Id$
 */
public class AsyncBusinessThread implements Runnable {
    private AsyncContext asyncContext;

    public AsyncBusinessThread(AsyncContext asyncContext) {
        this.asyncContext = asyncContext;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(3000);
            Map<String, String> resultMap = new HashMap<String, String>();
            resultMap.put("xm", "张三");
            resultMap.put("sex", "male");
            resultMap.put("age", "20");

            asyncContext.getResponse().getWriter().write(JSONObject.fromObject(resultMap).toString());
            asyncContext.getResponse().getWriter().flush();
            asyncContext.complete();
        } catch ( InterruptedException e ) {
            e.printStackTrace();
        } catch ( IOException e ) {
            e.printStackTrace();
        }
    }
}