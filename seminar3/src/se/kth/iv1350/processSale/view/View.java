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
                    System.out.println("Created new sale");
                    break;
                case "exit":
                    System.out.println("Exiting program");
                    return;
                case "addItem":
                    if (tokens.length == 3) {
                        String itemID = tokens[1];
                        int quantity = Integer.parseInt(tokens[2]);
                        controller.addItem(itemID, quantity);
                        printTotal();
                    } else {
                        System.out.println("Invalid command");
                        System.out.println("Syntax: addItem <itemID> <quantity>");
                    }
                    break;
                case "endSale":
                    float total = controller.endSale();
                    System.out.printf("You need to pay: %.2f Kr\n", total);
                    break;
                case "discount":
                    if (tokens.length == 2) {
                        String customerID = tokens[1];
                        float discount = controller.requestDiscount(customerID);
                        if (discount <= 0){
                            System.out.println("No discounts available.");
                        }
                        else{
                           System.out.printf("Applied discount: %.2f Kr\n", discount);
                        }
                    } else {
                        System.out.println("Invalid command");
                        System.out.println("Syntax: discount <customerID>");
                    }
                    break;
                case "pay":
                    if (tokens.length == 2) {
                        float amount = Float.parseFloat(tokens[1]);
                        float remaining = controller.pay(amount);
                        if (remaining > 0){
                            System.out.printf("Remaining: %.2f Kr\n", remaining);
                        }
                        else{
                            System.out.printf("Remaining: 0 kr\n");
                        }
                    } else {
                        System.out.println("Invalid command");
                        System.out.println("Syntax: pay <amount>");
                    }
                    break;
                case "closeSale":
                    if (!controller.closeSale()){
                        System.out.println("You must finish paying before you can close the sale.");
                    }
                    break;
                case "help":
                    System.out.println("startSale                       - starts a new sale");
                    System.out.println("exit                            - closes program");
                    System.out.println("addItem <itemID> <quantity>     - adds item to current sale");
                    System.out.println("endSale                         - starting payment process");
                    System.out.println("discount <customerID>           - request a discount");
                    System.out.println("pay <amount>                    - pays for purchase");
                    System.out.println("closeSale                       - closes the sale. prints receipt. updates external systems.");
                    break;
                default:
                    System.out.println("Invalid command. Type 'help' if you're stuck.");
                    break;
            }
        }
    }

    private void printTotal(){
        System.out.printf("Total Amount: %.2f Kr\n", controller.getTotal());
    }
}

