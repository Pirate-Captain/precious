package com.zyl.rpc;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class RPC {
    /**
     * @param clazz 是服务接口，便于服务端检验是否提供了此服务
     * @param host port 是host地址和端口号
     * @return 代理对象
     **/
    @SuppressWarnings("unchecked")
    public static <T> T getProxy(final Class<T> clazz, String host, int port) {
        final Client client = new Client(host, port);
        InvocationHandler handler = new InvocationHandler() {
            
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                Call call = new Call();
                call.setInterfaces(clazz);
                call.setMethodName(method.getName());
                call.setParams(args);
                call.setParamTypes(method.getParameterTypes());
                client.invoke(call);
                return call.getResult();
            }
        };
        
        return (T)Proxy.newProxyInstance(RPC.class.getClassLoader(), new Class[]{clazz}, handler);
    }
}