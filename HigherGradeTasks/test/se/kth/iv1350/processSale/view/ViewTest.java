package se.kth.iv1350.processSale.view;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.processSale.controller.Controller;
import se.kth.iv1350.processSale.integration.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ViewTest {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    private View view;
    private Controller controllerMock;
    private TotalRevenueView totalRevenueViewMock;

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
        DiscountDatabase discDb = new DiscountDatabase();
        ExternalAccountingSystem accSys = ExternalAccountingSystem.getInstance();
        ExternalInventorySystem invSys = new ExternalInventorySystem();
        Printer printer = new Printer();
        TotalRevenueFileOutput revenueFileOutput = new TotalRevenueFileOutput();
        Controller controller = new Controller(invSys, accSys, printer, discDb, revenueFileOutput);
        view = new View(controller);
    }

    @Test
    public void testStartNewSale() {
        view.startNewSale();
        String expected = "Created new sale" + System.lineSeparator();
        assertEquals(expected, outputStreamCaptor.toString(), "It should print correct start new sale message");
    }

    @Test
    public void testExitProgram() {
        view.exitProgram();
        String expected = "Exiting program" + System.lineSeparator();
        assertEquals(expected, outputStreamCaptor.toString(), "It should print correct exit program message");
    }

    @Test
    public void testDisplayHelp() {
        view.displayHelp();
        assertTrue(outputStreamCaptor.toString().contains("startSale"), "It should print help message");
        assertTrue(outputStreamCaptor.toString().contains("exit"), "It should print help message");
        // Continue assertions for other commands
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }
}
