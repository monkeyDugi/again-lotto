package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class NumberTest {

    @DisplayName("로또 번호는 1이상이어야 합니다.")
    @Test
    void validateMinimumNumber() {
        // given
        int number = 0;

        // when
        assertThatThrownBy(() -> new LottoNumber(number))
                // then
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또 번호는 1이상이어야 합니다.");
    }

    @DisplayName("로또 번호는 45이하이어야 합니다.")
    @Test
    void validateMaximumNumber() {
        // given
        int number = 46;

        // when
        assertThatThrownBy(() -> new LottoNumber(number))
                // then
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또 번호는 45이하이어야 합니다.");
    }
}