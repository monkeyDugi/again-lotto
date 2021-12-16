package lotto.domain;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class TicketTest {

    @Test
    void 번호_개수_6개미만_예외() {
        // given
        List<Integer> ticketNumbers = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));

        // when
        assertThatThrownBy(() -> Ticket.of(ticketNumbers))
                // then
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("티켓 번호는 중복될 수 없으며 6개이어야 합니다.");
    }

    @Test
    void 번호_개수_6개초과_예외() {
        // given
        List<Integer> ticketNumbers = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7));

        // when
        assertThatThrownBy(() -> Ticket.of(ticketNumbers))
                // then
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("티켓 번호는 중복될 수 없으며 6개이어야 합니다.");
    }

    @Test
    void 번호_중복_예외() {
        // given
        List<Integer> ticketNumbers = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 5));

        // when
        assertThatThrownBy(() -> Ticket.of(ticketNumbers))
                // then
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("티켓 번호는 중복될 수 없으며 6개이어야 합니다.");
    }

    @Test
    void 티켓에_해당_번호가_있으면_true() {
        // given
        Ticket ticket = Ticket.of(Arrays.asList(1, 2, 3, 4, 5, 6));
        int bonusNumber = 6;

        // when
        boolean contains = ticket.contains(TicketNumber.of(bonusNumber));

        // then
        assertThat(contains).isTrue();
    }

    @Test
    void 티켓에_해당_번호가_없으면_false() {
        // given
        Ticket ticket = Ticket.of(Arrays.asList(1, 2, 3, 4, 5, 6));
        int bonusNumber = 7;

        // when
        boolean contains = ticket.contains(TicketNumber.of(bonusNumber));

        // then
        assertThat(contains).isFalse();
    }

    @Test
    void 맞춘_개수_반환() {
        // given
        Ticket ticket = Ticket.of(Arrays.asList(1, 2, 3, 4, 5, 6));
        Ticket winningTicket = Ticket.of(Arrays.asList(1, 2, 3, 4, 5, 7));

        // then
        assertThat(winningTicket.match(ticket)).isEqualTo(5);
    }
}
