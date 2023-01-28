package com.maven_example.utils;

import com.maven_example.utils.class_locked.ClassA;
import com.maven_example.utils.class_locked.ClassB;
import org.junit.Test;

import java.util.concurrent.locks.ReentrantLock;

public class LockExecutor{
    @Test
    public void func() throws InterruptedException{
        ClassA classA = new ClassA();
        ClassB classB = new ClassB();

//        classA.say();
        classA.start();
        classB.start();
        classA.join();
        classB.join();
        System.out.printf("%d\n", LockTest.getNum());
    }
}
