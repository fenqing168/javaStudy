package com.native1.test;

public class TestApp {

    static{
        System.load("D:\\testCode\\javaStudy\\fenqing-utils\\src\\Dll3.dll");
    }

    public static void main(String[] args) {
        Test test = new Test();
        int num = test.sysInit(1, 1);
        System.out.println(num);
    }

}
