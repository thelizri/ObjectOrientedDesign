package se.kth.iv1350.processSale.utils;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.logging.Level;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ExceptionLoggerTest {

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    public void testLogException() {
        Exception testException = new RuntimeException("Test exception");
        String messageToUser = "An exception occurred";
        ExceptionLogger.logException(testException, Level.SEVERE, messageToUser);

        // Append newline character because println() appends a newline at the end
        assertEquals(messageToUser + System.lineSeparator(), outputStreamCaptor.toString());
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }
}