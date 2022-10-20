package com.luv2code.junitdemo;

import org.junit.jupiter.api.*;


import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

class DemoUtilsTests {

    private static final Logger logger = Logger.getLogger(DemoUtilsTests.class.getName());

    DemoUtils demoUtils;

    @BeforeEach
    void setupBeforeEach()
    {
         demoUtils = new DemoUtils();
        logger.info("@BeforeEach: executes before the execution of each test method");

    }


    @AfterEach
    void teardownAfterEach()
    {
        logger.info("Running @AfterEach Method");
    }



    @BeforeAll
    static //executed only once, before all test methods
    void setupBeforeEachClass()
    {
        logger.info("@BeforeAll executes only before all test methods");

    }


    @AfterAll
    static //executed only once, after all test methods
    void tearDownAfterAll()
    {
        logger.info("@AfterAll executes only After all test methods");

    }


     @Test
     void testEqualsAndNotEquals()
     {


         logger.info("Running test:  testEqualsAndNotEquals");

      //   DemoUtils demoUtils = new DemoUtils();

         assertEquals(6, demoUtils.add(2,4), "2 + 4 must be 6");
         assertNotEquals(6, demoUtils.add(1,9), "1+9 must not be 6");

     }


     @Test
     void testNullAndNotNull()
     {

         logger.info("Running test:  testNullAndNotNull");

        // DemoUtils demoUtils = new DemoUtils();


         String str1 = null;
         String str2 = "luv2code";

         assertNull(demoUtils.checkNull(str1), "Object should be null");
         assertNotNull(demoUtils.checkNull(str2), "Object should not be null");
     }
}
