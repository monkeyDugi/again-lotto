import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 문자열을 구분자로 분리하여 문자열 배열로 반환하는 역할
 */
public class Delimiter {

    private static final String DEFAULT_DELIMITERS = ",|:";

    private static final String CUSTOM_DELIMITER_REGEX = "//(.)\n(.*)";
    private static final Integer MATCH_GROUP_DELIMITER = 1;
    private static final Integer MATCH_GROUP_EXPRESSION = 2;

    private Delimiter() {}

    public static String[] splitString(String text) {
        String defaultText = defaultString(text);
        Matcher matcher = Pattern.compile(CUSTOM_DELIMITER_REGEX).matcher(defaultText);
        if (matcher.find()) {
            String customDelimiter = matcher.group(MATCH_GROUP_DELIMITER);
            return matcher.group(MATCH_GROUP_EXPRESSION).split(customDelimiter);
        }

        return defaultText.split(DEFAULT_DELIMITERS);
    }

    private static String defaultString(String text) {
        if (text == null || text.isEmpty()) {
            return "0";
        }

        return text;
    }
}
