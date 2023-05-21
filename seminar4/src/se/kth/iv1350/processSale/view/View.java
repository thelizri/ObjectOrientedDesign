package se.kth.iv1350.processSale.view;

import se.kth.iv1350.processSale.controller.Controller;
import se.kth.iv1350.processSale.dto.ItemDTO;
import se.kth.iv1350.processSale.exception.DatabaseFailureException;
import se.kth.iv1350.processSale.exception.ItemDoesNotExistException;
import se.kth.iv1350.processSale.utils.ExceptionLogger;
import se.kth.iv1350.processSale.utils.Money;

import java.util.Scanner;
import java.util.logging.Level;

/**
 * This class represents the interface the cashier uses when they take the customer's order.
 * It's a simple command line interface.
 */
public class View {
    private final Controller controller;
    private final Scanner scanner;

    /**
     * Creates a new instance of the View class with the specified controller.
     *
     * @param controller The controller to use for the view.
     */
    public View(Controller controller) {
        this.controller = controller;
        scanner = new Scanner(System.in);
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
                case "startSale" -> startNewSale();
                case "exit" -> {
                    exitProgram();
                    return;
                }
                case "addItem" -> addItem(tokens);
                case "discount" -> requestDiscount(tokens);
                case "pay" -> makePayment(tokens);
                case "closeSale" -> closeSale();
                case "help" -> displayHelp();
                default -> System.out.println("Invalid command. Type 'help' if you're stuck.");
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
            int quantity;
            try {
                quantity = Integer.parseInt(tokens[2]);
            } catch (NumberFormatException exception) {
                ExceptionLogger.logException(exception, Level.INFO, "Error: Quantity must be an integer");
                return;
            }
            try {
                ItemDTO itemDTO = controller.addItem(itemID, quantity);
                System.out.println(itemDTO.getDescription() + " " + itemDTO.getQuantity());
                Money runningTotal = controller.getTotal();
                System.out.printf("Running total: %.2f Kr\n", runningTotal.getAmountFloat());
            }
            catch(ItemDoesNotExistException exception){
                ExceptionLogger.logException(exception, Level.WARNING, "Error while scanning barcode. Try again.");
            }
            catch(DatabaseFailureException exception){
                ExceptionLogger.logException(exception, Level.SEVERE, "Could not connect to database. We apologize for the inconvenience");
            }
        } else {
            System.out.println("Invalid command");
            System.out.println("Syntax: addItem <itemID> <quantity>");
        }
    }

    private void requestDiscount(String[] tokens) {
        if (tokens.length == 2) {
            String customerID = tokens[1];
            Money discount = controller.requestDiscount(customerID);
            if (discount.isGreaterThanZero()) {
                System.out.printf("Applied discount: %.2f Kr\n", discount.getAmountFloat());
                Money remaining = controller.getRemainingAmount();
                System.out.printf("Remaining total: %.2f Kr\n", remaining.getAmountFloat());
            } else {
                System.out.println("No discounts available.");
            }
        } else {
            System.out.println("Invalid command");
            System.out.println("Syntax: discount <customerID>");
        }
    }

    private void makePayment(String[] tokens) {
        if (tokens.length == 2) {
            Money amount = new Money(tokens[1]);
            Money remaining = controller.pay(amount);
            System.out.printf("Remaining total: %.2f Kr\n", remaining.getAmountFloat());
        } else {
            System.out.println("Invalid command");
            System.out.println("Syntax: pay <amount>");
        }
    }

    private void closeSale() {
        if (!controller.closeSale()) {
            System.out.println("You must finish paying before you can close the sale.");
            Money remaining = controller.getRemainingAmount();
            System.out.printf("Remaining total: %.2f Kr\n", remaining.getAmountFloat());
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
