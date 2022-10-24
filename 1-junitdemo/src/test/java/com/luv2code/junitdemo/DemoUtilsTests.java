package com.luv2code.junitdemo;

import org.junit.jupiter.api.*;


import java.util.List;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;



//@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class DemoUtilsTests {

    private static final Logger logger = Logger.getLogger(DemoUtilsTests.class.getName());

    DemoUtils demoUtils;


    @BeforeEach
    void setupBeforeEach()
    {
        demoUtils = new DemoUtils();
    }





     @Test
    @DisplayName("Equals and not Equals")
     void test_Equals_And_Not_Equals()
     {
         assertEquals(6, demoUtils.add(2,4), "2 + 4 must be 6");
         assertNotEquals(6, demoUtils.add(1,9), "1+9 must not be 6");
     }


     @Test
  @DisplayName("Null and not Null")
     void test_Null_And_Not_Null()
     {
         String str1 = null;
         String str2 = "luv2code";

         assertNull(demoUtils.checkNull(str1), "Object should be null");
         assertNotNull(demoUtils.checkNull(str2), "Object should not be null");
     }




     @DisplayName("Same and Not same")
     @Test
    void testSameAndNotSame()
    {
        String str = "luv2code";

        assertSame(demoUtils.getAcademy(), demoUtils.getAcademyDuplicate(), "Objects should refer to same object");
        assertNotSame(str, demoUtils.getAcademy(), "Objects should not refer to same object");

    }


    @DisplayName("True and False")
    @Test
    void testTrueFalse()
    {
        int gradeOne = 10;
        int gradeTwo = 5;

        assertTrue(demoUtils.isGreater(gradeOne, gradeTwo), "This should return True");
        assertFalse(demoUtils.isGreater(gradeTwo, gradeOne), "This should return False");


    }

    @DisplayName("Array Equals")
    @Test
    void testArrayEquals()
    {
        String[] stringArray = {"A","B","C"};

        assertArrayEquals(stringArray,demoUtils.getFirstThreeLettersOfAlphabet(), "Arrays should be the same");

    }


    @DisplayName("Iterable equals")
    @Test
    void testIterableEquals()
    {
        List<String> theList = List.of("luv","2","code");
        assertIterableEquals(theList, demoUtils.getAcademyInList(),"Expect list should be same as actual list");
    }

    @Test
    @DisplayName("Lines match")
    void testLinesMatch()
    {
        List<String> theList = List.of("luv","2","code");

        assertLinesMatch(theList, demoUtils.getAcademyInList(), "Lines should match");
    }


    @DisplayName("Throws and Does not Throw")
    @Test
    void testThrowsAndDoesNotThrow()
    {
       assertThrows(Exception.class, () -> {demoUtils.throwException(-1);}, "Should throw exception");
       assertDoesNotThrow( () -> {demoUtils.throwException(-1);}, "Should not throw exception");

    }



/*


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

    */

}
