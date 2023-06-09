package se.kth.iv1350.processSale.startup;

import se.kth.iv1350.processSale.controller.Controller;
import se.kth.iv1350.processSale.integration.DiscountDatabase;
import se.kth.iv1350.processSale.integration.ExternalAccountingSystem;
import se.kth.iv1350.processSale.integration.ExternalInventorySystem;
import se.kth.iv1350.processSale.integration.Printer;
import se.kth.iv1350.processSale.view.View;

public class Main {
    public static void main(String[] args) {
        DiscountDatabase disDb = new DiscountDatabase();
        ExternalAccountingSystem accSys = new ExternalAccountingSystem();
        ExternalInventorySystem invSys = new ExternalInventorySystem();
        Printer printer = new Printer();
        Controller controller = new Controller(invSys, accSys, printer, disDb);
        View view = new View(controller);
        view.run();
    }
}