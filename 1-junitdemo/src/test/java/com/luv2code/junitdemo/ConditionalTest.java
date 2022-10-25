package com.luv2code.junitdemo;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.*;

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



    @Test
    @EnabledOnJre(JRE.JAVA_17)
    void testForOnlyForJava17()
    {
        //execute method and performe asserts
    }

    @Test
    @EnabledOnJre(JRE.JAVA_13)
    void testOnlyForJava13()
    {
        //execute method and performe asserts
    }




    @Test
    @EnabledForJreRange(min=JRE.JAVA_13, max=JRE.JAVA_17)
    void testOnlyForJavaRange()
    {
        //execute method and performe asserts
    }

    @Test
    @EnabledForJreRange(min=JRE.JAVA_11)
    void testOnlyForJavaRangeMin()
    {
        //execute method and performe asserts
    }



    @Test
    @EnabledIfEnvironmentVariable(named = "LUV2CODE_ENV", matches = "DEV")
    void testOnlyForDevEnvironment()
    {
        //execute method and performe asserts
    }

    @Test
    @EnabledIfSystemProperty(named = "LUV2CODE_SYS_PROP", matches = "CI_CD_DEPLOY")
    void testOnlyForSystemProperty()
    {
        //execute method and performe asserts
    }


}
