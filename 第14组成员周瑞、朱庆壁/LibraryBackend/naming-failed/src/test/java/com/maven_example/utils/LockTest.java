package com.maven_example.utils;

import java.util.concurrent.locks.ReentrantLock;

public class LockTest{
    public static final ReentrantLock lock = new ReentrantLock();
    public static Integer num = 0;

    public static void add(){
        ++ num;
    }
    public static Integer getNum(){
        return num;
    }

    public static void lock(){
        lock.lock();
    }
    public static void unlock(){
        lock.unlock();
    }
}
