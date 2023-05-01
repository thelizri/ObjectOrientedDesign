package se.kth.iv1350.processSale.utils;

import java.math.BigDecimal;

/**
 * Represents a monetary amount.
 */
public class Money {
    private final BigDecimal amount;

    /**
     * Creates a new instance of the Money class.
     *
     * @param amount The amount of money, as a BigDecimal.
     */
    public Money(BigDecimal amount) {
        this.amount = amount;
    }

    /**
     * Creates a new instance of the Money class.
     *
     * @param amount The amount of money, as a double.
     */
    public Money(double amount) {
        this.amount = new BigDecimal(amount);
    }

    /**
     * Creates a new instance of the Money class.
     *
     * @param amount The amount of money, as a string.
     */
    public Money(String amount) {
        this.amount = new BigDecimal(amount);
    }

    /**
     * Creates a new instance of the Money class.
     *
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
     *
     * @param other The other Money instance to add.
     * @return A new Money instance representing the sum of this instance and the other instance.
     */
    public Money add(Money other) {
        return new Money(this.amount.add(other.amount));
    }

    /**
     * Subtracts another Money instance from this instance and returns the result as a new Money instance.
     *
     * @param other The other Money instance to subtract.
     * @return A new Money instance representing the difference between this instance and the other instance.
     */
    public Money subtract(Money other) {
        return new Money(this.amount.subtract(other.amount));
    }

    /**
     * Multiplies this instance by a scalar value and returns the result as a new Money instance.
     *
     * @param scalar The scalar value to multiply by.
     * @return A new Money instance representing the product of this instance and the scalar value.
     */
    public Money multiply(int scalar) {
        return new Money(this.amount.multiply(new BigDecimal(scalar)));
    }

    /**
     * Multiplies this instance by a scalar value and returns the result as a new Money instance.
     *
     * @param scalar The scalar value to multiply by.
     * @return A new Money instance representing the product of this instance and the scalar value.
     */
    public Money multiply(Money scalar) {
        return new Money(this.amount.multiply(scalar.getAmount()));
    }

    /**
     * Returns a negative version of this instance as a new Money instance.
     *
     * @return A new Money instance representing the negative value of this instance.
     */
    public Money negate() {
        return new Money(this.amount.negate());
    }

    /**
     * Checks if this instance is greater than 0.
     *
     * @return true if this instance is greater than 0, false otherwise.
     */
    public boolean isGreaterThanZero() {
        return this.amount.compareTo(BigDecimal.ZERO) > 0;
    }

    /**
     * Gets the amount of this instance as a float.
     *
     * @return The amount of this instance as a float.
     */
    public float getAmountFloat() {
        return this.amount.floatValue();
    }

    /**
     * Gets the amount of this instance as a string.
     *
     * @return The amount of this instance as a string.
     */
    @Override
    public String toString() {
        return this.amount.toPlainString();
    }

    private BigDecimal getAmount() {
        return this.amount;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;

        Money money = (Money) object;

        return amount.equals(money.amount);
    }

    @Override
    public int hashCode() {
        return amount.hashCode();
    }
}
