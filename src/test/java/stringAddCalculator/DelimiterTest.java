package stringAddCalculator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DelimiterTest {

    @DisplayName("문자열을 쉼표 구분자로 분리한다.")
    @Test
    void split_string_comma() {
        // given
        String text = "1,2,3";

        // when
        String[] splitString = Delimiter.splitString(text);

        // then
        String[] expected = {"1", "2", "3"};
        assertThat(splitString).isEqualTo(expected);
    }

    @DisplayName("문자열을 쉼표 또는 콜론 구분자로 분리한다.")
    @Test
    void split_string_comma_or_colon() {
        // given
        String text = "1,2:3";

        // when
        String[] splitString = Delimiter.splitString(text);

        // then
        String[] expected = {"1", "2", "3"};
        assertThat(splitString).isEqualTo(expected);
    }

    @DisplayName("문자열을 커스텀 구분자로 분리한다.")
    @Test
    void split_string_custom() {
        // given
        String text = "//;\n1;2;3";

        // when
        String[] splitString = Delimiter.splitString(text);

        // then
        String[] expected = {"1", "2", "3"};
        assertThat(splitString).isEqualTo(expected);
    }

    @DisplayName("문자열이 null이거나 빈 값이면 문자열 0을 반환한다.")
    @Test
    void default_string_zero() {
        // given
        String nullText = null;
        String emptyText = "";

        // when
        String[] splitNullString = Delimiter.splitString(nullText);
        String[] splitEmptyString = Delimiter.splitString(emptyText);

        // then
        String[] expected = {"0"};
        assertThat(splitNullString).isEqualTo(expected);
        assertThat(splitEmptyString).isEqualTo(expected);
    }
}