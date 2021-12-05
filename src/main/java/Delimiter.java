import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 문자열을 구분자로 분리하여 문자열 배열로 반환하는 역할
 */
public class Delimiter {

    private static final String DEFAULT_DELIMITER = ",|:";

    private static final int CUSTOM_DELIMITER_INDEX = 1;
    private static final int CUSTOM_DELIMITER_VALUE_INDEX = 2;
    private static final String CUSTOM_DELIMITER_PATTERN = "//(.)\n(.*)";

    private Delimiter() {}

    public static String[] splitString(String text) {
        String defaultText = defaultString(text);
        Matcher matcher = Pattern.compile(CUSTOM_DELIMITER_PATTERN).matcher(defaultText);
        if (matcher.find()) {
            String customDelimiter = matcher.group(CUSTOM_DELIMITER_INDEX);
            return matcher.group(CUSTOM_DELIMITER_VALUE_INDEX).split(customDelimiter);
        }

        return defaultText.split(DEFAULT_DELIMITER);
    }

    private static String defaultString(String text) {
        if (text == null || text.isEmpty()) {
            return "0";
        }

        return text;
    }
}
