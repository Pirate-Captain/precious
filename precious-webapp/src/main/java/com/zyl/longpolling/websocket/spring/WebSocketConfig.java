/**
 * chsi
 * Created on 2017-10-27
 */
package com.zyl.longpolling.websocket.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/**
 * @author iaskbear <a href="mailto:iaskbear@gmail.com">iaskbear</a>
 * @version $Id$
 */
//@Configuration
//@EnableWebMvc
//@EnableWebSocket
public class WebSocketConfig extends WebMvcConfigurerAdapter implements WebSocketConfigurer {

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
//        registry.addHandler(messageHandler(), "/springmvc/ws").setAllowedOrigins("*").addInterceptors(handShakeInterceptor());
    }
//
//    @Bean
//    public WebSocketMessageHandler messageHandler() {
//        return new WebSocketMessageHandler();
//    }
//
//    @Bean
//    public WebSocketHandShakeInterceptor handShakeInterceptor() {
//        return new WebSocketHandShakeInterceptor();
//    }
}