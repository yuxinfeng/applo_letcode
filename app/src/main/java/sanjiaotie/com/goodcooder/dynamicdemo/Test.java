package sanjiaotie.com.goodcooder.dynamicdemo;

/**
 * author：yuxinfeng on 2019-10-17 14:07
 * email：yuxinfeng@corp.netease.com
 */
public class Test {

    public static void main(String args[]) {
        Character a = (Character) null;
        System.out.println(a);
        Boolean boolValue = (Boolean) true; // 将这个 true 替换为 2 或者 3，再看看打印结果
        if (boolValue) System.out.println("Hello, Java!");
        if (boolValue == true) System.out.println("Hello, JVM!");
    }
}


