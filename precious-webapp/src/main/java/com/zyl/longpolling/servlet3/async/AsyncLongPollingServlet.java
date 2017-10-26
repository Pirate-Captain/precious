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
 * @author zhuyl<a href="mailto:zhuyl@chsi.com.cn">zhu Youliang</a>
 * @version $Id$
 */
@WebServlet(asyncSupported = true, urlPatterns = "/asyncsevlt.do", loadOnStartup = 1)
public class AsyncLongPollingServlet extends HttpServlet{
    private static final long serialVersionUID = -2465170326829870381L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        AsyncContext context = req.startAsync(req, resp);
        context.addListener(new AsyncLongPollingListener());
        new Thread(new AsyncBusinessThread(context)).start();
    }
}