package lotto.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class TicketNumberTest {

    @Test
    void 번호가_1보다_작으면_예외() {
        // given
        int number = 0;


        // when
        assertThatThrownBy(() -> TicketNumber.of(number))
                // then
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("유효 범위를 벗어난 번호입니다.");
    }

    @Test
    void 번호가_45보다_크면_예외() {
        // given
        int number = 46;


        // when
        assertThatThrownBy(() -> TicketNumber.of(number))
                // then
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("유효 범위를 벗어난 번호입니다.");
    }
}