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
//@WebServlet(asyncSupported = true, urlPatterns = "/asyncsevlt", loadOnStartup = 0)
public class AsyncLongPollingServlet extends HttpServlet{
    private static final long serialVersionUID = -2465170326829870381L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AsyncContext context = req.startAsync(req, resp);
        context.addListener(new AsyncLongPollingListener());
        context.start(new AsyncBusinessThread(context));
    }
}