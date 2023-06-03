package se.kth.iv1350.task2;

import java.util.Random;

/**
 * This class uses java.util.Random through composition and provides a method to generate a biased boolean.
 */
public class MyRandomWithComposition {

    private final Random random;

    /**
     * Constructor that initializes the random object.
     */
    public MyRandomWithComposition() {
        this.random = new Random();
    }

    /**
     * Returns a boolean that is true with a probability equal to the given bias.
     *
     * @param trueBias a float between 0 and 1 (inclusive) representing the bias towards true.
     * @return a boolean that's true with a probability equal to trueBias.
     * @throws IllegalArgumentException if trueBias is less than 0 or greater than 1.
     */
    public boolean nextBiasedBoolean(float trueBias) {
        if (trueBias < 0 || trueBias > 1) {
            throw new IllegalArgumentException("Bias must be between 0 and 1");
        }

        return random.nextFloat() < trueBias;
    }
}

