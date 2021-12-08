package stringAddCalculator;

/**
 * 양수를 배열을 생성하는 역할
 */
public class Positive {

    private Positive() {}

    public static int[] parseArrayInt(String[] text) {
        int textLength = text.length;
        int[] numbers = new int[textLength];

        for (int i = 0; i < textLength; i++) {
            numbers[i] = Integer.parseInt(text[i]);
            validateNegative(numbers[i]);
        }

        return numbers;
    }

    private static void validateNegative(int number) {
        if (number < 0) {
            throw new IllegalArgumentException("음수는 사용할 수 없습니다.");
        }
    }
}
