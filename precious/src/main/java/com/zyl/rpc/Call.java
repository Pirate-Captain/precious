package com.zyl.rpc;

import java.io.Serializable;

@SuppressWarnings("rawtypes")
public class Call implements Serializable {
    private static final long serialVersionUID = 5376932663291969195L;
    private Class interfaces;
    private Object[] params;
    private Object result;
    private String methodName;
    private Class[] paramTypes;

    public Class getInterfaces() {
        return interfaces;
    }

    public void setInterfaces(Class interfaces) {
        this.interfaces = interfaces;
    }

    public Object[] getParams() {
        return params;
    }

    public void setParams(Object[] params) {
        this.params = params;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Class[] getParamTypes() {
        return paramTypes;
    }

    public void setParamTypes(Class[] paramTypes) {
        this.paramTypes = paramTypes;
    }
}