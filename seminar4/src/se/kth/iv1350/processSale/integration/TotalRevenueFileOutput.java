package se.kth.iv1350.processSale.integration;

import se.kth.iv1350.processSale.utils.Money;
import se.kth.iv1350.processSale.utils.Observer;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * This class represents a concrete Observer called TotalRevenueFileOutput that writes the total revenue to a file.
 * The class follows the Observer design pattern and updates the total revenue whenever it is notified of a change by the observed Subject.
 * <p>
 * The TotalRevenueFileOutput class implements the Observer interface and overrides the update method defined by the interface.
 */
public class TotalRevenueFileOutput implements Observer {

    private final String filePath;
    private final LocalDateTime dateTime;
    private final DateTimeFormatter dateTimeFormatter;
    private Money totalRevenue;

    /**
     * Constructs a new instance of TotalRevenueFileOutput, initializing the total revenue to zero.
     * The file path is dynamically generated based on the current date, formatted according to the pattern "d-MMM-yyyy".
     * Each instance represents the total revenue as a Money object, which gets updated whenever the update method is called.
     */
    public TotalRevenueFileOutput() {
        totalRevenue = new Money();
        this.dateTime = LocalDateTime.now();
        this.dateTimeFormatter = DateTimeFormatter.ofPattern("d-MMM-yyyy");
        this.filePath = "Total-Revenue " + dateTime.format(dateTimeFormatter) + ".txt";
    }

    /**
     * This method is called by the observed Subject when a state change occurs.
     * The total revenue is updated with the amount contained in the passed Money object, which represents the new state.
     *
     * @param money The Money object containing the amount to be added to the total revenue.
     */
    @Override
    public void update(Money money) {
        totalRevenue = totalRevenue.add(money);
        outputTotalRevenueToFile();
    }

    /**
     * This method writes the total revenue to a file.
     * Each line in the file represents an update, and contains the updated total revenue (as a floating-point number with two decimal places)
     * and the timestamp of the update.
     * The file is named "Total-Revenue [date].txt", where [date] is the date when the TotalRevenueFileOutput object was created.
     */
    private void outputTotalRevenueToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, false))) {
            writer.write(String.format("%-20s %10.2f kr %23s\n", "Total Revenue: ", totalRevenue.getAmountFloat(), dateTime.format(dateTimeFormatter)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


