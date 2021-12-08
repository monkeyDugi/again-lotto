import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PositiveTest {

    @DisplayName("양수 배열을 반환한다.")
    @Test
    void parseArrayInt() {
        // given
        String[] text = {"1", "2", "3"};

        // when
        int[] numbers = Positive.parseArrayInt(text);

        // then
        int[] expected = {1, 2, 3};
        assertThat(numbers).isEqualTo(expected);
    }

    @DisplayName("음수가 있으면 예외 발생")
    @Test
    void validate_negative() {
        // given
        String[] text = {"1", "2", "-3"};

        // when
        assertThatThrownBy(() -> Positive.parseArrayInt(text))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("음수는 사용할 수 없습니다.");
    }
}