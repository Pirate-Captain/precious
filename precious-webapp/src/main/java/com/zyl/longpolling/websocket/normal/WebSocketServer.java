/**
 * chsi
 * Created on 2017-10-27
 */
package com.zyl.longpolling.websocket.normal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

/**
 * @author iaskbear <a href="mailto:iaskbear@gmail.com">iaskbear</a>
 * @version $Id$
 */
@ServerEndpoint("/websocket/{user}")
public class WebSocketServer {
    private Logger log = LoggerFactory.getLogger(this.getClass());
    private String currentUser;

    @OnOpen
    public void onOpen(@PathParam("user") String user, Session session) {
        this.currentUser = user;
        log.info("websocket-connect-登录的用户：{}", currentUser);
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        log.info("websocket-onMessage-user:{} say: {}", currentUser, message);
        //异步发送消息
//        session.getAsyncRemote().sendText(currentUser + " say：" + message);
        try {
            //同步发送消息
            session.getBasicRemote().sendText(currentUser + " say: " + message);
        } catch ( IOException e ) {
            e.printStackTrace();
        }
    }

    @OnClose
    public void onClose(Session session, CloseReason closeReason) {
        log.info("websocket-onclose-user:{},sessionid:{} close by {}", currentUser, session.getId(), closeReason);
    }

    @OnError
    public void onError(Throwable exception) {
        log.info("websocket-onerror-exception:{}", exception);
    }
}