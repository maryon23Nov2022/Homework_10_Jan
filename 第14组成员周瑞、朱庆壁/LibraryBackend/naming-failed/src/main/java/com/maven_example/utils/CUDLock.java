package com.maven_example.utils;

import java.util.concurrent.locks.ReentrantLock;

public class CUDLock {
    public static final ReentrantLock lock = new ReentrantLock();

    public static void lock(){
        lock.lock();
    }

    public static void unlock(){
        lock.unlock();
    }
}
