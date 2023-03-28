package com.maven_example.utils.class_locked;

import com.maven_example.utils.LockTest;

public class ClassB extends Thread{

    @Override
    public void run() {
        for(int i = 0; i < 200000; ++ i){
            LockTest.lock();
            try{
                LockTest.add();
            } finally{
                LockTest.unlock();
            }
        }
        System.out.printf("%d ClassB executed.\n", LockTest.getNum());
    }
}
