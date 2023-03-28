package com.maven_example.utils.class_locked;

import com.maven_example.utils.LockTest;

import java.util.concurrent.locks.ReentrantLock;

public class ClassA extends Thread{

    @Override
    public void run(){
        for(int i = 0; i < 200000; ++ i){
            LockTest.lock();
            try{
                LockTest.add();
            } finally{
                LockTest.unlock();
            }
        }
        System.out.printf("%d ClassA executed.\n", LockTest.getNum());
    }

    public void say(){
        System.out.printf("slkafsldf\n");
    }
}
