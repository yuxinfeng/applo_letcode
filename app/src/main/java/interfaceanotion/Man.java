package interfaceanotion;

import sanjiaotie.com.interface_apt.AutoInterface;

// import sanjiaotie.com.interface_apt.AutoInterface;

public class Man {

    @AutoInterface("Hello")
    public void eat() {
        System.out.println("Eat");
    }

    public static void main(String args[]) {

    }
}
