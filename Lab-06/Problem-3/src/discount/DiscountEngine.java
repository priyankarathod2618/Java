package discount;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class DiscountEngine {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        List<Double> prices = Arrays.asList(
                1000.0,
                2000.0,
                500.0,
                1500.0
        );

        System.out.println("===== DISCOUNT ENGINE =====");
        System.out.println();

        System.out.println("Original Prices:");

        for (double price : prices) {
            System.out.printf("₹%.2f%n", price);
        }

        System.out.println();

        System.out.println("Choose a discount rule:");
        System.out.println("1. 10% Discount");
        System.out.println("2. 20% Discount");
        System.out.println("3. Flat ₹100 Discount");

        System.out.print("Enter your choice: ");

        int choice = scanner.nextInt();

        DiscountRule rule;

        switch (choice) {

            case 1:
                rule = price -> price * 0.90;
                break;

            case 2:
                rule = price -> price * 0.80;
                break;

            case 3:
                rule = price -> price - 100;
                break;

            default:
                System.out.println("Invalid choice!");
                scanner.close();
                return;
        }

        System.out.println();
        System.out.println("Prices After Discount:");
        System.out.println();

        for (double price : prices) {

            double discountedPrice = rule.apply(price);

            System.out.printf(
                    "Original: ₹%.2f -> Discounted: ₹%.2f%n",
                    price,
                    discountedPrice
            );
        }

        scanner.close();
    }
}