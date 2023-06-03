package se.kth.iv1350.processSale.integration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PrinterTest {
    private final ByteArrayOutputStream outStreamCaptor = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outStreamCaptor));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void testPrintReceipt() {
        Printer printer = new Printer();
        String receipt = "Receipt Content";
        printer.printReceipt(receipt);

        // Append newline character because println() appends a newline at the end
        assertEquals(receipt + System.lineSeparator(), outStreamCaptor.toString());
    }
}
