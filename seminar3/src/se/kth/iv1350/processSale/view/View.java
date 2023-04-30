package se.kth.iv1350.processSale.view;

import se.kth.iv1350.processSale.controller.Controller;
import java.util.Scanner;

/**
 * This class represents the interface the cashier uses when they take the customer's order.
 * It's a simple command line interface.
 */
public class View {
    private Controller controller;
    private Scanner scanner;

    /**
     * Creates a new instance of the View class with the specified controller.
     * @param controller The controller to use for the view.
     */
    public View(Controller controller) {
        this.controller = controller;
        scanner = new Scanner(System.in);
        run();
    }

    /**
     * Runs the point of sale system until the user exits.
     */
    public void run() {
        System.out.println("Welcome to ProcessSale. Type a command in the terminal.");
        while (true) {
            String command = scanner.nextLine();
            String[] tokens = command.split(" ");
            switch (tokens[0]) {
                case "startSale":
                    startNewSale();
                    break;
                case "exit":
                    exitProgram();
                    return;
                case "addItem":
                    addItem(tokens);
                    break;
                case "discount":
                    requestDiscount(tokens);
                    break;
                case "pay":
                    makePayment(tokens);
                    break;
                case "closeSale":
                    closeSale();
                    break;
                case "help":
                    displayHelp();
                    break;
                default:
                    System.out.println("Invalid command. Type 'help' if you're stuck.");
                    break;
            }
        }
    }

    private void startNewSale() {
        controller.createNewSale();
        System.out.println("Created new sale");
    }

    private void exitProgram() {
        System.out.println("Exiting program");
    }

    private void addItem(String[] tokens) {
        if (tokens.length == 3) {
            String itemID = tokens[1];
            int quantity = Integer.parseInt(tokens[2]);
            System.out.println(controller.addItem(itemID, quantity));
        } else {
            System.out.println("Invalid command");
            System.out.println("Syntax: addItem <itemID> <quantity>");
        }
    }

    private void requestDiscount(String[] tokens) {
        if (tokens.length == 2) {
            String customerID = tokens[1];
            float discount = controller.requestDiscount(customerID);
            if (discount <= 0) {
                System.out.println("No discounts available.");
            } else {
                System.out.printf("Applied discount: %.2f Kr\n", discount);
                float remaining = controller.getRemainingAmount();
                System.out.printf("Remaining Total: %.2f Kr\n", remaining);
            }
        } else {
            System.out.println("Invalid command");
            System.out.println("Syntax: discount <customerID>");
        }
    }

    private void makePayment(String[] tokens) {
        if (tokens.length == 2) {
            float amount = Float.parseFloat(tokens[1]);
            float remaining = controller.pay(amount);
            System.out.printf("Remaining: %.2f Kr\n", remaining);
        } else {
            System.out.println("Invalid command");
            System.out.println("Syntax: pay <amount>");
        }
    }

    private void closeSale() {
        if (!controller.closeSale()) {
            System.out.println("You must finish paying before you can close the sale.");
            float remaining = controller.getRemainingAmount();
            System.out.printf("Remaining Total: %.2f Kr\n", remaining);
        }
    }

    private void displayHelp() {
        System.out.println("startSale                       - starts a new sale");
        System.out.println("exit                            - closes program");
        System.out.println("addItem <itemID> <quantity>     - adds item to current sale");
        System.out.println("discount <customerID>           - request a discount");
        System.out.println("pay <amount>                    - pays for purchase");
        System.out.println("closeSale                       - closes the sale. prints receipt. updates external systems.");
    }
}
