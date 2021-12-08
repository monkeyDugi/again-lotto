/**
 * 문자열을 덧셈하는 역할
 */
public class StringAddCalculator {

    public static int sum(String text) {
        int result = 0;

        int[] numbers = Positive.parseArrayInt(Delimiter.splitString(text));
        for (int number : numbers) {
            result += number;
        }

        return result;
    }
}
