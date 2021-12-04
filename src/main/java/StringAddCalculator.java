import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringAddCalculator {

    private static final String DEFAULT_DELIMITER = ",|:";

    private static final int CUSTOM_DELIMITER_INDEX = 1;
    private static final int CUSTOM_DELIMITER_VALUE_INDEX = 2;
    private static final String CUSTOM_DELIMITER_PATTERN = "//(.)\n(.*)";

    public static int sum(String text) {
        int result = 0;

        int[] numbers = splitToInt(text);
        for (int number : numbers) {
            result += number;
        }

        return result;
    }

    private static int[] splitToInt(String text) {
        String defaultText = defaultString(text);
        Matcher matcher = Pattern.compile(CUSTOM_DELIMITER_PATTERN).matcher(defaultText);
        if (matcher.find()) {
            String customDelimiter = matcher.group(CUSTOM_DELIMITER_INDEX);
            return parseArrayInt(matcher.group(CUSTOM_DELIMITER_VALUE_INDEX).split(customDelimiter));
        }

        return parseArrayInt(defaultText.split(DEFAULT_DELIMITER));
    }

    private static String defaultString(String text) {
        if (text == null || text.isEmpty()) {
            return "0";
        }

        return text;
    }

    private static int[] parseArrayInt(String[] text) {
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
