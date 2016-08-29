package com.zyl.rpc;

@SuppressWarnings("rawtypes")
public interface Server {
    void start();
    void stop();
    void register(Class serviceInterface, Class serviceImp);
    void call(Call call);
    boolean isRunning();
    int getPort();
}