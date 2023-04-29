package se.kth.iv1350.processSale.view;

import se.kth.iv1350.processSale.controller.Controller;
import java.util.Scanner;

public class View {
    private Controller controller;
    private Scanner scanner;

    public View(Controller controller) {
        this.controller = controller;
        scanner = new Scanner(System.in);
        run();
    }

    public void run() {
        while (true) {
            String command = scanner.nextLine();
            String[] tokens = command.split(" ");
            switch (tokens[0]) {
                case "startSale":
                    controller.createNewSale();
                    break;
                case "exit":
                    return;
                case "addItem":
                    if (tokens.length == 3) {
                        String itemID = tokens[1];
                        int quantity = Integer.parseInt(tokens[2]);
                        controller.addItem(itemID, quantity);
                    } else {
                        System.out.println("Invalid command");
                    }
                    break;
                case "endSale":
                    float total = controller.endSale();
                    System.out.printf("Total Amount: %.2f Kr", total);
                    break;
                case "discount":
                    if (tokens.length == 2) {
                        String customerID = tokens[1];
                        controller.requestDiscount(customerID);
                    } else {
                        System.out.println("Invalid command");
                    }
                    break;
                case "pay":
                    if (tokens.length == 2) {
                        float amount = Float.parseFloat(tokens[1]);
                        float remaining = controller.pay(amount);
                        if (remaining > 0){
                            System.out.printf("Remaining: %.2f Kr", remaining);
                        }
                        else{
                            System.out.printf("Remaining: 0 kr");
                        }
                    } else {
                        System.out.println("Invalid command");
                    }
                    break;
                case "closeSale":
                    if (!controller.closeSale()){
                        System.out.println("You must finish paying before you can close the sale.");
                    }
                    break;
                default:
                    System.out.println("Invalid command");
                    break;
            }
        }
    }
}

