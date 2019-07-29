/**
 * chsi
 * Created on 2017-10-27
 */
package com.zyl.longpolling.websocket.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;

/**
 * @author iaskbear <a href="mailto:iaskbear@gmail.com">iaskbear</a>
 * @version $Id$
 */
public class WebSocketMessageHandler extends TextWebSocketHandler {
    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        log.info("springmvc-websocket-连接已建立");
        final WebSocketSession session1 = session;
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                while ( true ) {
                    try {
                        Thread.sleep(5000);
                        TextMessage textMessage = new TextMessage("我有话说");
                        session1.sendMessage(textMessage);
                    } catch ( InterruptedException e ) {
                        e.printStackTrace();
                    } catch ( IOException e ) {
                        e.printStackTrace();
                    }
                }
            }
        };
        new Thread(runnable).start();
    }

    @Override
    protected void handleTextMessage(final WebSocketSession session, TextMessage message) throws Exception {
        log.info("springmvc-websocket-接收新的消息：" + message.getPayload());
        session.sendMessage(message);

//        super.handleTextMessage(session, message);
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        log.info("springmvc-websocket-数据传输失败：sessionId：{}，exception：{}", session.getId(), exception);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        log.info("springmvc-websocket-连接已断开！");
    }
}
