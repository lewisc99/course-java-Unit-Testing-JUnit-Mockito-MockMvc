package com.luv2code.tdd;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class FizzBuzzTest {



    // if number is divisible by 3, print Fizz
    @Test
    @DisplayName("Divisible by three")
    @Order(1)
    void testForDivisibleByThree()
    {
       String expected = "Fizz";

       assertEquals(expected, FizzBuzz.compute(3),"Should return Fizz");
    }

    // if number is divisible by 5, print buzz
    @Test
    @DisplayName("Divisible by five")
    @Order(2)
    void testForDivisibleByFive()
    {
        String expected = "Buzz";

        assertEquals(expected, FizzBuzz.compute(5),"Should return Buzz");
    }

    //if number is divisible by 3 and 5. print FizzBuzz
    @Test
    @DisplayName("Divisible by 3 and 5")
    @Order(3)
    void testForDivisibleByThreeAndFive()
    {
        String expected = "FizzBuzz";

        assertEquals(expected, FizzBuzz.compute(15),"Should return FizzBuzz");
    }


    //if number is NOT divisible by 3 and 5, then print the number

    @Test
    @DisplayName("Not Divisible by 3 or 5")
    @Order(4)
    void testForNotDivisibleByThreeOrFive()
    {
        String expected = "1";

        assertEquals(expected, FizzBuzz.compute(1),"Should return 1");
    }


}
