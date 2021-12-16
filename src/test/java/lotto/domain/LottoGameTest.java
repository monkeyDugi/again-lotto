package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class LottoGameTest {

    @DisplayName("")
    @Test
    void match_2등_1장_3등_1장_4등1_5등_2장() {
        // given
        Money purchaseMoney = new Money(10000);
        List<Ticket> tickets = new ArrayList<>();
        tickets.add(Ticket.of(Arrays.asList(1, 2, 3, 4, 5, 7))); // 2등
        tickets.add(Ticket.of(Arrays.asList(1, 2, 3, 4, 5, 8))); // 3등
        tickets.add(Ticket.of(Arrays.asList(1, 2, 3, 4, 10, 11))); // 4등
        tickets.add(Ticket.of(Arrays.asList(1, 2, 3, 9, 10, 11))); // 5등
        LottoGame lottoGame = new LottoGame(purchaseMoney, money -> tickets);


        WinningTicket winningTicket = new WinningTicket(Ticket.of(Arrays.asList(1, 2, 3, 4, 5, 6)), 7);

        // when
        LottoResult lottoResult = lottoGame.match(winningTicket);

        // then
        assertThat(lottoResult.getValue(Rank.SECOND)).isEqualTo(1);
        assertThat(lottoResult.getValue(Rank.THIRD)).isEqualTo(1);
        assertThat(lottoResult.getValue(Rank.FOURTH)).isEqualTo(1);
        assertThat(lottoResult.getValue(Rank.FIFTH)).isEqualTo(1);
        System.out.println(lottoGame.toString());
//        assertThat(lottoResult.getPrize()).isEqualTo(new Money(4_555_000));
    }
}