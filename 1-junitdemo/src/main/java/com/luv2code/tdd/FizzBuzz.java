package com.luv2code.tdd;

public class FizzBuzz {

    // if number is divisible by 3, print Fizz
    // if number is divisible by 5, print buzz
    //if number is divisible by 3 and 5. print FizzBuzz
    //if number is not divisible by 3 and 5, then print the number
    public static String compute(int number) {

        if (number % 3 == 0 )
        {
            return "Fizz";
        }
        else if (number % 5 == 0)
        {
            return "Buzz";
        }
        return null;
    }
}
