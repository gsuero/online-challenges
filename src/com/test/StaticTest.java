package com.test;

public class StaticTest {

    public static void main(String[] args) {
        System.out.println(3 % 0);

    }

}

class A {
    static String name;
    A() {
        if (name == null) {
            System.out.println("Called now");
            name = "ClassA";
        }
        
    }
}

class B extends A {
   
}
class C extends A {
}

class D extends A {
}