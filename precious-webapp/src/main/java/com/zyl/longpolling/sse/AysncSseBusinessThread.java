/**
 * chsi
 * Created on 2017-10-26
 */
package com.zyl.longpolling.sse;

import net.sf.json.JSONObject;

import javax.servlet.AsyncContext;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @author iaskbear <a href="mailto:iaskbear@gmail.com">iaskbear</a>
 * @version $Id$
 */
public class AysncSseBusinessThread implements Runnable {
    private AsyncContext asyncContext;
    private String[] names = new String[]{"Jack", "Sara", "Mark", "Sam", "Janifo"};
    private String[] sexs = new String[]{"male", "female", "other"};

    public AysncSseBusinessThread(AsyncContext asyncContext) {
        this.asyncContext = asyncContext;
    }

    @Override
    public void run() {
        while ( true ) {
            try {
                Map<String, String> resultMap = new HashMap<String, String>(3);
                resultMap.put("xm", names[new Random().nextInt(names.length)]);
                resultMap.put("age", String.valueOf(new Random().nextInt(100)));
                resultMap.put("sex", sexs[new Random().nextInt(sexs.length)]);
                asyncContext.getResponse().getWriter().write("retry: 10000\n");
                asyncContext.getResponse().getWriter().write("data: " + JSONObject.fromObject(resultMap).toString() + " \n\n");
                if ( asyncContext.getResponse().getWriter().checkError() ) {
                }

                Thread.sleep(1000);
            } catch ( IOException e ) {
                e.printStackTrace();
            } catch ( InterruptedException e ) {
                e.printStackTrace();
            }
        }
    }
}