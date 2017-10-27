/**
 * chsi
 * Created on 2017-10-24
 */
package com.zyl.longpolling.servlet3.async;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet 3.0 异步Servlet
 * 1.需要设置asyncSupported = true
 * 2.需要创建一个AsyncListener 监听任务执行情况
 * 3.需要创建一个业务的线程，执行业务功能，并将结果返回，最后需要执行asyncContext.complete()结束本次任务
 *
 * @author zhuyl<a href="mailto:zhuyl@chsi.com.cn">zhu Youliang</a>
 * @version $Id$
 */
@WebServlet(asyncSupported = true, urlPatterns = "/asyncsevlt.do", loadOnStartup = 1)
public class AsyncLongPollingServlet extends HttpServlet {
    private static final long serialVersionUID = -2465170326829870381L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        AsyncContext context = req.startAsync(req, resp);
        context.addListener(new AsyncLongPollingListener());
        //这个地方可以使用线程池，不要自己创建线程执行
        new Thread(new AsyncBusinessThread(context)).start();
    }
}