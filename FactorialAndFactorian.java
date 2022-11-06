package com.company;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner input = new Scanner( System.in );
        boolean quit = false;
        while (!quit)
        {
            System.out.print("Enter a number> ");
            if (input.hasNextInt())
            {
                int number = input.nextInt();
                if (isFactorian(number))
                    System.out.printf("%d is a factorian!\n", number);
                else
                    System.out.printf("%d is not a factorian.\n", number);
            }
            else if (input.hasNext())
            {
                String isQuitAnswer = input.next();
                quit = isQuitAnswer.equalsIgnoreCase("quit");
            }
            else
                System.out.print("Error.");
        }

    }

    public static int computeFactorial(int number) {
        int factorial = number;
        int count = number - 1;
        while (count > 1)
        {
            factorial *= count;
            count --;
        }
        return factorial;
    }

    public static boolean isFactorian(int number) {
        int factorian = 0;
        int remainder = 1;
        int quotient = number;
        while (quotient != 0)
        {
            remainder = quotient % 10;
            quotient /= 10;
            factorian += computeFactorial(remainder);
        }
        return (factorian == number);
    }

}
