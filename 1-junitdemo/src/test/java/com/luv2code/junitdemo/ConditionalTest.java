package com.luv2code.junitdemo;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;

class ConditionalTest {



    @Test
    @Disabled("Dont run this test until we fixed #JIRA 123 ")
    void basicTest()
    {
        //execute method and performe asserts
    }

    @Test
    @EnabledOnOs(OS.WINDOWS)
    void testForWindowsOnly()
    {
        //execute method and performe asserts
    }


     @Test
     @EnabledOnOs(OS.LINUX)
     void testForLinuxsOnly()
     {
         //execute method and performe asserts
     }

    @Test
    @EnabledOnOs(OS.MAC)
    void testForMacOnly()
    {
        //execute method and performe asserts
    }

    @EnabledOnOs({OS.MAC, OS.WINDOWS})
    @Test
    void testForMacAndWindowsOnly()
    {
        //execute method and performe asserts
    }



}
