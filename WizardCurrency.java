package com.company;
import java.util.Scanner;

public class WizardCurrency {

    public static final double PENCE_KNUT_RATE = 1.627254509;
    public static final String GALLEONS_PROMPT = "Enter the number of Galleons > ";
    public static final String SICKLES_PROMPT = "Enter the number of Sickles > ";
    public static final String KNUTS_PROMPT = "Enter the number of Knuts > ";
    public static final String POUNDS_AND_PENCE_PROMPT = "Enter the number of British pounds and pence (e.g. 1.24) > ";

    public static void main(String[] args) {

        String errorMessage = "";
        int numGalleons = 0;
        int numSickles = 0;
        int numKnuts = 0;
        double poundsAndPence = 0;
        Scanner input = new Scanner( System.in );
        System.out.print("1. Convert Wizarding Currency To GBP\n" +
                "2. Convert Knuts To Wizarding Currency and GBP\n" +
                "3. Convert GBP to Wizarding Currency\n" +
                "4. Quit\n" +
                "Select function> ");
        if (input.hasNextInt())
        {
            int programNumber = input.nextInt();
            if (programNumber > 0 && programNumber < 5)
            {
                if (programNumber == 1)
                {
                    errorMessage = "Error: The number of Galleons should be an integer (e.g. 3)";
                    numGalleons = (int) getNumberFromUser(GALLEONS_PROMPT, errorMessage, new Scanner( System.in ), true);
                    errorMessage = "Error: The number of Sickles should be an integer (e.g. 5)";
                    numSickles = (int) getNumberFromUser(SICKLES_PROMPT, errorMessage, new Scanner( System.in ), true);
                    errorMessage = "Error: The number of Knuts should be an integer (e.g. 12)";
                    numKnuts = (int) getNumberFromUser(KNUTS_PROMPT, errorMessage, new Scanner( System.in ), true);
                    int totalKnuts = convertWizardingCurrencyToKnuts(numGalleons, numSickles, numKnuts);
                    double pence = convertWizardingCurrencyToPence(numGalleons, numSickles, numKnuts);
                    double pounds = pence / 100;
                    System.out.printf("In British Pounds %s is GBP%.2f", convertKnutsToWizardingCurrency(totalKnuts), pounds);
                }
                else if (programNumber == 2)
                {
                    errorMessage = "Error: The number of Knuts should be an integer (e.g. 56)";
                    numKnuts = (int) getNumberFromUser(KNUTS_PROMPT, errorMessage, new Scanner( System.in ), true);
                    double pence = convertWizardingCurrencyToPence(numGalleons, numSickles, numKnuts);
                    double pounds = pence / 100;
                    System.out.printf("%d knuts is HP%s is equal to GBP%.2f", numKnuts, convertKnutsToWizardingCurrency(numKnuts), pounds);
                }
                else if (programNumber == 3)
                {
                    errorMessage = "Error: Invalid number of British pounds and pence";
                    poundsAndPence = (int) getNumberFromUser(POUNDS_AND_PENCE_PROMPT, errorMessage, new Scanner( System.in ), true);
                    int pounds = (int)poundsAndPence;
                    int pence = (int) ((poundsAndPence * 100) - pounds);
                    System.out.printf("In wizarding currency GBP%s is HP%s", poundsAndPence, convertGBPToWizardingCurrency(pounds, pence));
                }
            }
            else
                System.out.print("Error: Invalid selection.");
        }
    }

    public static int convertGBPToKnuts(int pounds, int pence) {
        pence += 100 * pounds;
        return (int) (PENCE_KNUT_RATE * pence);
    }

    public static String convertKnutsToWizardingCurrency(int totalKnuts) {
        int sickles = 0;
        int knuts = 0;
        int galleons = 0;
        int remainder = 0;
        if (totalKnuts >= 493)
        {
            galleons = totalKnuts / 493;
            remainder = totalKnuts % 493;
        }
        if (remainder >= 29)
        {
            sickles = remainder / 29;
            remainder %= 29;
        }
        if (remainder > 0)
            knuts = remainder;
        return "HP" + galleons + ":" + sickles + ":" + knuts;
    }

    public static String convertGBPToWizardingCurrency(int pounds, int pence) {
        int totalKnuts = convertGBPToKnuts(pounds, pence);
        return convertKnutsToWizardingCurrency(totalKnuts);
    }

    public static int convertWizardingCurrencyToKnuts(int numGalleons, int numSickles, int numKnuts) {
        return (numGalleons * 493) + (numSickles * 29) + numKnuts;
    }

    public static int convertWizardingCurrencyToPence(int numGalleons, int numSickles, int numKnuts) {
        int knuts = convertWizardingCurrencyToKnuts(numGalleons, numSickles, numKnuts);
        return (int) (Math.round(knuts / PENCE_KNUT_RATE));
    }

    public static double getNumberFromUser(String prompt, String errorMessage, Scanner input, boolean integerRequired) {
        double number = 0;
        while (integerRequired)
        {
            System.out.print(prompt);
            if (input.hasNextDouble())
            {
                number = input.nextDouble();
                integerRequired = false;
            }
            else
                System.out.print(errorMessage + "\n");
            input.nextLine();
        }
        return number;
    }
}
