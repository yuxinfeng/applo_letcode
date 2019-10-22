package sanjiaotie.com.goodcooder.dynamicdemo;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * author：yuxinfeng on 2019-10-15 23:52
 * email：yuxinfeng@corp.netease.com
 */
public class ProxyInvoke implements InvocationHandler {

    private Object object;

    public Object asbind(Object object) {
        this.object = object;
        return Proxy.newProxyInstance(object.getClass().getClassLoader(), object.getClass().getInterfaces(), this);
    }

    @Override
    public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
        Object a = null;
        long t1 = System.currentTimeMillis();
        System.out.println("start:"+ t1);
        a = method.invoke(object, objects);
        System.out.println("end:"+ (System.currentTimeMillis() - t1));

        return a;
    }
}
