/**
 * chsi
 * Created on 2017-10-26
 */
package com.zyl.longpolling.sse;

import com.zyl.longpolling.servlet3.async.AsyncLongPollingListener;

import javax.servlet.AsyncContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author iaskbear <a href="mailto:iaskbear@gmail.com">iaskbear</a>
 * @version $Id$
 */
@WebServlet(asyncSupported = true, urlPatterns = "/sse.do", loadOnStartup = 1)
public class AysncLongPollingSseServlet extends HttpServlet {
    private static final long serialVersionUID = 3983064262779081148L;
    private ExecutorService service = Executors.newCachedThreadPool();

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) {
        res.setContentType("text/event-stream");
        res.setCharacterEncoding("UTF-8");
        res.setHeader("Connection", "keep-alive");
        res.setHeader("Cache-Control", "no-cache");

        AsyncContext asyncContext = req.startAsync();
        asyncContext.addListener(new AsyncLongPollingListener());
        asyncContext.setTimeout(60000);
        service.execute(new AysncSseBusinessThread(asyncContext));
    }
}