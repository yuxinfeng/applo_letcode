package sanjiaotie.com.goodcooder.dynamicdemo;

import java.lang.reflect.Proxy;

/**
 * author：yuxinfeng on 2019-10-15 23:52
 * email：yuxinfeng@corp.netease.com
 */
public class DynamicProxy {

    public static void main(String args[]) {
        // Pen pencial = (Pen) new ProxyInvoke().asbind(new Pencial());
        // pencial.draw();
        ProxyInvoke proxyInvoke = new ProxyInvoke();
        Object a = Proxy.newProxyInstance(Pencial.class.getClassLoader(), Pencial.class.getInterfaces(), proxyInvoke);
        System.out.println(a.getClass().getName());


    }
}
