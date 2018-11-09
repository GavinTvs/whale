package com.example.widget_ad;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author : gavin
 * @date 2018/11/9.
 */

public class AdProxy implements InvocationHandler {
    private Object object;

    public AdProxy(Object object) {
        this.object = object;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result = method.invoke(object,args);
        return result;
    }
}
