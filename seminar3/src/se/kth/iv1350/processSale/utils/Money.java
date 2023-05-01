package se.kth.iv1350.processSale.utils;

import java.math.BigDecimal;

/**
 * Represents a monetary amount.
 */
public class Money {
    private final BigDecimal amount;

    /**
     * Creates a new instance of the Money class.
     * @param amount The amount of money, as a BigDecimal.
     */
    public Money(BigDecimal amount) {
        this.amount = amount;
    }

    /**
     * Creates a new instance of the Money class.
     * @param amount The amount of money, as a float.
     */
    public Money(float amount) {
        this.amount = new BigDecimal(amount);
    }

    /**
     * Creates a new instance of the Money class.
     * @param amount The amount of money, as a double.
     */
    public Money(double amount) {
        this.amount = new BigDecimal(amount);
    }

    /**
     * Creates a new instance of the Money class.
     * @param amount The amount of money, as a string.
     */
    public Money(String amount) {
        this.amount = new BigDecimal(amount);
    }

    /**
     * Creates a new instance of the Money class.
     * @param amount The amount of money, as an integer.
     */
    public Money(int amount) {
        this.amount = new BigDecimal(amount);
    }

    /**
     * Creates a new instance of the Money class with zero amount.
     */
    public Money() {
        this.amount = BigDecimal.ZERO;
    }

    /**
     * Adds another Money instance to this instance and returns the result as a new Money instance.
     * @param other The other Money instance to add.
     * @return A new Money instance representing the sum of this instance and the other instance.
     */
    public Money add(Money other) {
        return new Money(this.amount.add(other.amount));
    }

    /**
     * Subtracts another Money instance from this instance and returns the result as a new Money instance.
     * @param other The other Money instance to subtract.
     * @return A new Money instance representing the difference between this instance and the other instance.
     */
    public Money subtract(Money other) {
        return new Money(this.amount.subtract(other.amount));
    }

    /**
     * Multiplies this instance by a scalar value and returns the result as a new Money instance.
     * @param scalar The scalar value to multiply by.
     * @return A new Money instance representing the product of this instance and the scalar value.
     */
    public Money multiply(float scalar) {
        return new Money(this.amount.multiply(new BigDecimal(scalar)));
    }

    /**
     * Multiplies this instance by a scalar value and returns the result as a new Money instance.
     * @param scalar The scalar value to multiply by.
     * @return A new Money instance representing the product of this instance and the scalar value.
     */
    public Money multiply(Money scalar) {
        return new Money(this.amount.multiply(scalar.getAmount()));
    }

    /**
     * Returns a negative version of this instance as a new Money instance.
     * @return A new Money instance representing the negative value of this instance.
     */
    public Money negate() {
        return new Money(this.amount.negate());
    }

    /**
     * Checks if this instance is greater than another Money instance.
     * @param other The other Money instance to compare to.
     * @return true if this instance is greater than the other instance, false otherwise.
     */
    public boolean isGreaterThan(Money other) {
        return this.amount.compareTo(other.amount) > 0;
    }

    /**
     * Checks if this instance is less than another Money instance.
     * @param other The other Money instance to compare to.
     * @return true if this instance is less than the other instance, false otherwise.
     */
    public boolean isLessThan(Money other) {
        return this.amount.compareTo(other.amount) < 0;
    }

    /**
     * Compares this instance with another Money instance.
     * @param other The other Money instance to compare to.
     * @return A negative integer, zero, or a positive integer as this instance is less than, equal to, or greater than the specified object.
     */
    public int compareTo(Money other) {
        return this.amount.compareTo(other.amount);
    }

    /**
     * Gets the amount of this instance as a BigDecimal.
     * @return The amount of this instance as a BigDecimal.
     */
    public BigDecimal getAmount() {
        return this.amount;
    }

    /**
     * Gets the amount of this instance as a float.
     * @return The amount of this instance as a float.
     */
    public float getAmountFloat() {
        return this.amount.floatValue();
    }

    /**
     * Gets the amount of this instance as a string.
     * @return The amount of this instance as a string.
     */
    public String getAmountString() {
        return this.amount.toPlainString();
    }

}
