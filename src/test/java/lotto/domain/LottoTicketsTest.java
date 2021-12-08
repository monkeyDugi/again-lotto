package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoTicketsTest {

    @DisplayName("천원 단위로 로또 티켓을 생성한다.")
    @Test
    void createLottoTicket() {
        // given
        int price = 2000;

        // when
        LottoTickets lottoTickets = new LottoTickets(price);

        // then
        assertThat(lottoTickets.size()).isEqualTo(2);
    }

    @DisplayName("최소 구매 금액은 1000원이다.")
    @Test
    void validateMinimumPrice() {
        // given
        int price = 900;

        // when
        assertThatThrownBy(() -> new LottoTickets(price))
                // then
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("최소 구매 금액은 1000원 입니다.");
    }

    @DisplayName("구매 금액은 1000원 단위로만 가능하다.")
    @Test
    void validateTicketPrice() {
        // given
        int price = 1100;

        // when
        assertThatThrownBy(() -> new LottoTickets(price))
                // then
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("구매 금액은 1000원 단위만 가능합니다.");
    }
}