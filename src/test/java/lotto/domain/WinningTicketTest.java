package lotto.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class WinningTicketTest {

    @Test
    void 당첨_2등() {
        // given
        Ticket userTicket = Ticket.of(Arrays.asList(1, 2, 3, 4, 5, 7));
        int bonusNumber = 7;
        List<Integer> winningNumbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        WinningTicket winningTicket = new WinningTicket(Ticket.of(winningNumbers), bonusNumber);

        // when
        Rank rank = winningTicket.match(userTicket);

        // then
        assertThat(rank).isEqualTo(Rank.SECOND);
    }

    @Test
    void 당첨번호와_보너스_번호가_중복되면_예외() {
        // given
        Ticket userTicket = Ticket.of(Arrays.asList(1, 2, 3, 4, 5, 7));
        int bonusNumber = 7;
        List<Integer> winningNumbers = Arrays.asList(1, 2, 3, 4, 5, 7);

        assertThatThrownBy(() -> new WinningTicket(Ticket.of(winningNumbers), bonusNumber))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("당첨 번호와 보너스 번호는 중복될 수 없습니다.");
    }
}