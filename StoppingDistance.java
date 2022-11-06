/*  SELF ASSESSMENT of whether my code is easy to understand.
   1. Did I use easy-to-understand meaningful and properly formatted variable names and constants?
       Mark out of 5: 5
       Comment: Yes, I did.
    2. Did I indent the code appropriately?
       Mark out of 5: 5
       Comment: Yes, according to the standard.
   3. Did I make use of the functions appropriately within main and the other functions?
       Mark out of 5: 5
       Comment: Yes.
   4. Have I avoided inappropriate duplication of code?
       Mark out of 5: 5
       Comment: Yes.
     Total Mark out of  20 (Add all the previous marks): 20
*/

package src.com.company;
import java.util.Scanner;

public class StoppingDistance3 {

    public static final double DRY_COEFFICIENT = 0.8;
    public static final double WET_COEFFICIENT = 0.4;
    public static final double ICY_COEFFICIENT = 0.1;
    public static final double METERS_TO_KILOMETERS_FACTOR = 3.6;
    public static final int FRICTION_COEFFICIENT_MULTIPLIER = 250;

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        double vehicleSpeed = 0;
        boolean isValid = false;
        while (!isValid)
        {
            System.out.print("Enter vehicle speed in km/hr> ");
            if (input.hasNextDouble())
            {
                vehicleSpeed = input.nextDouble();
                isValid = true;
            }
            else
            {
                System.out.print("Error: Enter a positive number for the speed of the vehicle.\n");
            }
            input.nextLine();
        }

        int age = 0;
        isValid = false;
        while (!isValid)
        {
            System.out.print("Enter age in years> ");
            if (input.hasNextInt())
            {
                age = input.nextInt();
                isValid = true;
            }
            else
            {
                System.out.print("Error: Enter a positive whole number for the age of the driver.\n");
            }
            input.nextLine();
        }

        boolean roadIsNotWet;
        boolean roadIsWet;
        do
        {
            System.out.print("Is the road wet (Yes/No)? ");
            String isWetAnswer = input.next();
            roadIsWet = isWetAnswer.equalsIgnoreCase("Yes");
            roadIsNotWet = isWetAnswer.equalsIgnoreCase("No");
            if (!roadIsNotWet && !roadIsWet)
                System.out.print("Error: You must enter either Yes or No.\n");
        }
        while (!roadIsNotWet && !roadIsWet);

        boolean roadIsIcy;
        boolean roadIsNotIcy;
        do
        {
            System.out.print("Is the road icy (Yes/No)? ");
            String isIcyAnswer = input.next();
            roadIsIcy = isIcyAnswer.equalsIgnoreCase("Yes");
            roadIsNotIcy = isIcyAnswer.equalsIgnoreCase("No");
            if (!roadIsNotIcy && !roadIsIcy)
                System.out.print("Error: You must enter either Yes or No.\n");
        }
        while (!roadIsIcy && !roadIsNotIcy);

        double coefficientOfFriction = computeCoefficientOfFriction(roadIsIcy, roadIsWet);
        double brakingDistance = computeBrakingDistance(vehicleSpeed, roadIsIcy, roadIsWet);
        double reactionDistance = computeReactionDistance(vehicleSpeed, age);
        int stoppingDistance = computeStoppingDistance(vehicleSpeed, age, roadIsIcy, roadIsWet);

        System.out.print("As the road is ");
        if (roadIsIcy && roadIsWet)
            System.out.print("icy and wet");
        else if (roadIsNotWet && roadIsIcy)
            System.out.print("icy and dry");
        else if (roadIsWet)
            System.out.print("wet");
        else
            System.out.print("dry");
        System.out.printf(", the coefficient of friction is %s\n", coefficientOfFriction);
        System.out.printf("At a speed of %.1fkm/hr, a driver who is %d years old, the reaction distance is %.0fm, " +
                        "the braking distance is %.0fm, and so the stopping distance is %dm", vehicleSpeed, age,
                reactionDistance, brakingDistance, stoppingDistance);

        input.close();

    }

    public static double computeCoefficientOfFriction(boolean roadIsIcy, boolean roadIsWet) {
        if (roadIsIcy)
            return ICY_COEFFICIENT;
        else if (roadIsWet)
            return WET_COEFFICIENT;
        else
            return DRY_COEFFICIENT;
    }

    public static double computeBrakingDistance(double vehicleSpeed, boolean roadIsIcy, boolean roadIsWet) {
        return (vehicleSpeed * vehicleSpeed) / (FRICTION_COEFFICIENT_MULTIPLIER *
                computeCoefficientOfFriction(roadIsIcy, roadIsWet));
    }

    public static double computeReactionDistance(double vehicleSpeed, int age) {
        double reactionTime = 1.0 + (Math.abs(age - 24) * 0.05);          // numbers from reactionTime formula
        vehicleSpeed /= METERS_TO_KILOMETERS_FACTOR;
        return vehicleSpeed * reactionTime;
    }

    public static int computeStoppingDistance(double vehicleSpeed, int age, boolean roadIsIcy, boolean roadIsWet) {
        return (int) Math.round( (computeReactionDistance(vehicleSpeed, age) +
                computeBrakingDistance(vehicleSpeed, roadIsIcy, roadIsWet)) );
    }
}
