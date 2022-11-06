package com.company;
import java.util.Scanner;

public class Main {

    public static final String DEFICIENT = "Deficient";
    public static final String ABUNDANT = "Abundant";
    public static final String PERFECT = "Perfect";
    public static final int NO_DIVISOR_PARAMETER = -1;

    public static void main(String[] args) {

        Scanner input = new Scanner( System.in );
        boolean isQuit = false;
        int number = 0;
        while (!isQuit)
        {
            System.out.print("Enter the positive whole number to be considered (or 'quit')> ");
            String myInput = input.next();
            boolean isWord = true;
            isQuit = myInput.equalsIgnoreCase("Quit");
            Scanner inputNumber = new Scanner(myInput);
            if (inputNumber.hasNextInt())
            {
                number = inputNumber.nextInt();
                isWord = false;
            }
            if (number > 0 && !isWord)
                System.out.printf(" %s is %s and is " + (!isAdmirable(number) ? "not " : "") + "Admirable\n",
                        number, isDeficientPerfectOrAbundant(number));
            else if (!isQuit)
                System.out.printf(" Error: %s is not a positive whole number.\n", myInput);
        }


    }

    public static int computeNextProperDivisor( int number, int lastProperDivisor ) {

        int possibleDivisor = lastProperDivisor - 1;
        for (; possibleDivisor > 0; possibleDivisor--)
        {
            double remainder = number % possibleDivisor;
            if (remainder == 0)
                return possibleDivisor;
        }
        return NO_DIVISOR_PARAMETER;
    }

    public static int sumOfProperDivisors( int number ) {
        int sumOfDivisors = 0;
        int nextDivisor = computeNextProperDivisor(number, number);
        while (nextDivisor != NO_DIVISOR_PARAMETER)
        {
            sumOfDivisors += nextDivisor;
            nextDivisor = computeNextProperDivisor(number, nextDivisor);
        }
        return sumOfDivisors;
    }

    public static String isDeficientPerfectOrAbundant( int number ) {
        int sum = sumOfProperDivisors(number);
        if (sum < number)
            return DEFICIENT;
        else if (sum > number)
            return ABUNDANT;
        else
            return PERFECT;
    }

    public static boolean isAdmirable( int number ) {
        int nextDivisor = number;
        int sum = sumOfProperDivisors(number);
        do
        {
            nextDivisor = computeNextProperDivisor(number, nextDivisor);
            int divisor = nextDivisor * -2;
            if (sum + divisor == number)
                return true;
            nextDivisor--;
        }
        while (nextDivisor > 0);
        return false;
    }
}
