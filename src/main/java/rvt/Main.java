package rvt;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        
        double totalSum = 0; 

        File file = new File("data\\orders.csv");

        try {
            Scanner scanner = new Scanner(file);

                scanner.nextLine();

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                
                String[] data = line.split(",");

                String orderId = data[0];
                String customer = data[1];
                String product = data[2];
                
                int quantity = Integer.parseInt(data[3].trim()); 
                double price = Double.parseDouble(data[4].trim());

                double orderTotal = quantity * price;
                totalSum += orderTotal;

                System.out.printf("Pasūtījums #%s: %s pasūtīja %d x %s (%.2f EUR) -> Kopā: %.2f EUR%n", 
                        orderId, customer, quantity, product, price, orderTotal);
                
            }
            scanner.close();
            System.out.printf("Kopējā pasūtījumu summa: %.2f EUR%n", totalSum);

        } catch (FileNotFoundException e) {
            System.out.println("Fails nav atrasts! Parbaudi, vai 'orders.csv' ir pareizaja mape - ~/data/orders.csv");
        }
    }
}