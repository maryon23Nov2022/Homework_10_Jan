package com.maven_example.utils;

import java.util.concurrent.locks.ReentrantLock;

public class BookStatus{
    public static final ReentrantLock lock = new ReentrantLock();

    public static Integer getRemain(){
        lock.lock();
        try{
            System.out.printf("queryOperatioin\n");
            return 0;
        } finally{
            lock.unlock();
        }
    }
}
