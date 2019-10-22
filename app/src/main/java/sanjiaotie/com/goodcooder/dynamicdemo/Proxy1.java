package sanjiaotie.com.goodcooder.dynamicdemo;

/**
 * author：yuxinfeng on 2019-10-15 23:48
 * email：yuxinfeng@corp.netease.com
 *
 */
public class Proxy1 {

    /**
     * 静态代理
     * 由于需求变更，需要统计pencial调用draw方法前后的时间，可以使用静态代理
     */
    public void draw(Pencial pencial) {
        long t1 = System.currentTimeMillis();
        pencial.draw();
        System.out.println(System.currentTimeMillis() - t1);
    }

    public static void main(String args[]) {
        new Proxy1().draw(new Pencial());
    }
}
