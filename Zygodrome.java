package com.company;
import java.util.Scanner;

public class ETest1 {

    public static final int QUIT_PARAMETER = -1;

    public static void main(String[] args) {

        Scanner input = new Scanner( System.in );
        boolean isQuit = false;
        int number = 0;
        while (!isQuit)
        {
            System.out.print("Enter the maximum positive whole number to be considered (or -1 to quit)> ");
            String myInput = input.next();
            Scanner inputNumber = new Scanner(myInput);
            if (inputNumber.hasNextInt())
                number = inputNumber.nextInt();
            if (number > 0) {
                String numbers = "";
                for (int possibleNumber = 10; number >= possibleNumber; possibleNumber++)
                {
                    if (isZygodrome(possibleNumber) && isNude(possibleNumber))
                        numbers += possibleNumber + ((possibleNumber == number) ? "": ", ");
                }
                if (numbers != "")
                    System.out.printf(" The numbers which are both zygodromes and nude up to %s are " + numbers + "\n", number);
                else
                    System.out.printf(" There are no numbers which are both zygodromes and nude up to %s" + "\n", number);
            }
            else if (number != QUIT_PARAMETER)
                System.out.printf(" Error: %s is not a positive whole number.\n", myInput);
            else
                isQuit = true;
        }
        input.close();
    }

    public static boolean isNude( int number ) {

        for (int digitNumber = getNumberOfDigits(number); digitNumber != 0; digitNumber--)
        {
            int digit = getDigit(number, digitNumber);
            if (digit == 0)
                return false;
            if (number % getDigit(number, digitNumber) != 0)
                return false;
        }
        return true;
    }

    public static int getNumberOfDigits( int number ) {

        int numberOfDigits = 0;
        while (number > 0)
        {
            number /= 10;
            numberOfDigits ++;
        }

        return numberOfDigits;
    }

    public static int getDigit( int number, int digitNumber ) {

        int remainder = 1;
        int quotient = 0;
        int count = getNumberOfDigits(number) - digitNumber + 1;
        int counter = 0;
        while (counter < count)
        {
            quotient = number / 10;
            remainder = number % 10;
            number = quotient;
            counter ++;
        }
        return remainder;
    }

    public static boolean isZygodrome( int number ) {
        int count = 0;
        for (int digitNumber = getNumberOfDigits(number); digitNumber != 0; )
        {
                if (getDigit(number, digitNumber) == getDigit(number, digitNumber - 1))
                    count = 0;
                else if (count > 0)
                    return false;
                else if (count == 0)
                    count++;
                digitNumber--;
        }
        return true;
    }
}
