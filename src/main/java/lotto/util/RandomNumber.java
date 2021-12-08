package lotto.util;

import java.util.Random;

public class RandomNumber {

    private static final int TO_NUMBER = 45;
    private static final int FROM_NUMBER = 1;

    private static final Random random = new Random();

    public static int createRandomNumber() {
        return random.nextInt(TO_NUMBER) + FROM_NUMBER;
    }
}
