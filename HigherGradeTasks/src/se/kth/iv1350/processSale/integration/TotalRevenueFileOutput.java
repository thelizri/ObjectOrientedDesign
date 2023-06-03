package se.kth.iv1350.processSale.integration;

import se.kth.iv1350.processSale.utils.ExceptionLogger;
import se.kth.iv1350.processSale.utils.Money;
import se.kth.iv1350.processSale.utils.Observer;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;

/**
 * The TotalRevenueFileOutput class represents a concrete Observer that writes the total revenue to a file.
 * It updates the total revenue when notified by the observed Subject, following the Observer design pattern.
 * <p>
 * Each instance of this class tracks the total revenue as a Money object, which is updated every time the
 * calculateTotalIncome method is called. The total revenue is then written to a file.
 */
public class TotalRevenueFileOutput extends Observer {

    private final String filePath;
    private final LocalDateTime dateTime;
    private final DateTimeFormatter dateTimeFormatter;
    private Money totalRevenue;

    /**
     * Constructs a new instance of TotalRevenueFileOutput, initializing the total revenue to zero.
     * The file path is dynamically generated based on the current date, formatted according to the pattern "d-MMM-yyyy".
     * Each instance represents the total revenue as a Money object, which gets updated whenever the calculateTotalIncome method is called.
     */
    public TotalRevenueFileOutput() {
        totalRevenue = new Money();
        this.dateTime = LocalDateTime.now();
        this.dateTimeFormatter = DateTimeFormatter.ofPattern("d-MMM-yyyy");
        this.filePath = "Total-Revenue " + dateTime.format(dateTimeFormatter) + ".txt";
    }

    /**
     * This method writes the total revenue to a file.
     * Each line in the file represents an update and contains the updated total revenue, formatted as a floating-point number
     * with two decimal places, and the timestamp of the update.
     * The file is named "Total-Revenue [date].txt", where [date] is the date when the TotalRevenueFileOutput object was created.
     */
    private void outputTotalRevenueToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, false))) {
            writer.write(String.format("%-20s %10.2f kr %23s\n", "Total Revenue: ", totalRevenue.getAmountFloat(), dateTime.format(dateTimeFormatter)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Calculates the total income by adding the price of the sale that was just made to the total revenue.
     * Then writes the updated total revenue to the file.
     *
     * @param priceOfTheSaleThatWasJustMade The price of the sale that was just completed.
     */
    @Override
    protected void calculateTotalIncome(Money priceOfTheSaleThatWasJustMade) {
        totalRevenue = totalRevenue.add(priceOfTheSaleThatWasJustMade);
        outputTotalRevenueToFile();
    }

    /**
     * Writes the total income to a file, formatted as a floating-point number with two decimal places.
     * Each line in the file contains the total income and the timestamp of the update.
     * This method throws an IOException if an error occurs while attempting to write to the file.
     *
     * @throws IOException if an error occurs while attempting to write to the file.
     */
    @Override
    protected void doShowTotalIncome() throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, false))) {
            writer.write(String.format("%-20s %10.2f kr %23s\n", "Total Revenue: ", totalRevenue.getAmountFloat(), dateTime.format(dateTimeFormatter)));
        } catch (IOException exception) {
            throw exception;
        }
    }

    /**
     * Handles any errors that occur during the calculation or writing of the total income to a file.
     * The error is logged as a severe error.
     *
     * @param exception The exception that occurred during the calculation or writing of the total income.
     */
    @Override
    protected void handleErrors(Exception exception) {
        ExceptionLogger.logException(exception, Level.SEVERE, "Could not write to sale log file.");
    }
}


