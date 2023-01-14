package com.mavenexample;

import org.junit.Test;

public class HelloMavenTest{
	@Test
	public void testSay(){
		HelloMaven helloMaven = new HelloMaven();
		helloMaven.say("maven");
	}
}