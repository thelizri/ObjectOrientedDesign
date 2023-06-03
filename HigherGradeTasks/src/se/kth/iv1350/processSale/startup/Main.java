package se.kth.iv1350.processSale.startup;

import se.kth.iv1350.processSale.controller.Controller;
import se.kth.iv1350.processSale.integration.*;
import se.kth.iv1350.processSale.utils.ExceptionLogger;
import se.kth.iv1350.processSale.view.View;

import java.util.logging.Level;


public class Main {
    public static void main(String[] args) {

        try {
            DiscountDatabase discDb = new DiscountDatabase();
            ExternalAccountingSystem accSys = ExternalAccountingSystem.getInstance();
            ExternalInventorySystem invSys = new ExternalInventorySystem();
            Printer printer = new Printer();
            TotalRevenueFileOutput revenueFileOutput = new TotalRevenueFileOutput();
            Controller controller = new Controller(invSys, accSys, printer, discDb, revenueFileOutput);
            View view = new View(controller);
            view.run();
        } catch (Exception exception) {
            ExceptionLogger.logException(exception, Level.SEVERE, "An unknown error has disrupted the program.");
        }

    }
}