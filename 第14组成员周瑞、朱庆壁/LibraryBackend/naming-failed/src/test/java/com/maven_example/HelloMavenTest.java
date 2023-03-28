package com.maven_example;

import org.junit.Assert;
import org.junit.Test;

public class HelloMavenTest{
    @Test
    public void testSay(){
        HelloMaven helloMaven = new HelloMaven();
        String res = helloMaven.say("Maven");
        Assert.assertEquals("Hello Maven", res);
    }
}
