package lotto.generator;

import lotto.domain.Money;
import lotto.domain.Ticket;
import lotto.domain.TicketGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class DefaultGeneratorTest {

    @Test
    void 자동과_수동_티켓_생성() {
        // given
        String inputManualTickets = "1,2,3,4,5,6\r\n"
                                  + "1,2,3,4,5,6";
        int manualTicketCount = 2;
        TicketGenerator ticketGenerator = new DefaultGenerator(inputManualTickets, manualTicketCount);
        Money purchaseMoney = new Money(10000);

        // when
        List<Ticket> tickets = ticketGenerator.generate(purchaseMoney);

        // then
        assertThat(tickets.size()).isEqualTo(10);
    }

    @DisplayName("수동 구매가 포함될 시 구매 금액에 대해 총 티켓 장수가 일치하지 않으면 예외")
    @Test
    void 금액과_티켓_장수_불일치_예외() {
        // given
        String inputManualTickets = "1,2,3,4,5,6\r\n"
                + "1,2,3,4,5,6";
        int manualTicketCount = 3;
        TicketGenerator ticketGenerator = new DefaultGenerator(inputManualTickets, manualTicketCount);
        Money purchaseMoney = new Money(11000);

        // when
        assertThatThrownBy(() -> ticketGenerator.generate(purchaseMoney))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("지불하신 금액보다 티켓 장수가 적거나 많습니다.");
    }
}