package com.zyl.rpc;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class RPCServer implements Server {
    private int port;
    private RPCListener listener;
    private Map<String, Object> serviceMap = new HashMap<String, Object>();
    private boolean running = false;
    
    public RPCServer(int port) {
        this.port = port;
    }

    @Override
    public void start() {
        this.running = true;
        listener = new RPCListener(this);
        new Thread(listener).start();
    }

    @Override
    public void stop() {
        this.running = false;
    }

    @SuppressWarnings("rawtypes")
    @Override
    /**
     * 个人认为serverImp直接传实例比较好
     */
    public void register(Class serviceInterface, Class serviceImp) {
        if ( serviceMap.containsKey(serviceInterface.getName()) ) {
            return;
        }
        try {
            serviceMap.put(serviceInterface.getName(), serviceImp.newInstance());
        } catch ( InstantiationException e ) {
            e.printStackTrace();
        } catch ( IllegalAccessException e ) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public void call(Call call) {
        Object object = serviceMap.get(call.getInterfaces().getName());
        if ( null == object ) {
            System.out.println("服务端为提供该服务！");
        }
        
        Method method = null;
        try {
            method = object.getClass().getMethod(call.getMethodName(), call.getParamTypes());
            Object result = method.invoke(object, call.getParams());
            call.setResult(result);
        } catch ( SecurityException e ) {
            e.printStackTrace();
        } catch ( NoSuchMethodException e ) {
            e.printStackTrace();
        } catch ( IllegalArgumentException e ) {
            e.printStackTrace();
        } catch ( IllegalAccessException e ) {
            e.printStackTrace();
        } catch ( InvocationTargetException e ) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean isRunning() {
        return running;
    }

    @Override
    public int getPort() {
        return port;
    }
}