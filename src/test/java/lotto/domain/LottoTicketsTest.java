package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @DisplayName("로또 순위별 당첨 개수를 조회한다.")
    @Test
    void getByRankCountOfMatches() {
        // given
        List<LottoNumber> lottoLottoNumbers1 =
                new ArrayList<>(Arrays.asList(new LottoNumber(1)
                        , new LottoNumber(2)
                        , new LottoNumber(3)
                        , new LottoNumber(4)
                        , new LottoNumber(5)
                        , new LottoNumber(6)));
        List<LottoNumber> lottoLottoNumbers2 =
                new ArrayList<>(Arrays.asList(new LottoNumber(1)
                        , new LottoNumber(2)
                        , new LottoNumber(3)
                        , new LottoNumber(4)
                        , new LottoNumber(5)
                        , new LottoNumber(7)));
        List<LottoNumber> winningLottoNumbers =
                new ArrayList<>(Arrays.asList(new LottoNumber(1)
                        , new LottoNumber(2)
                        , new LottoNumber(3)
                        , new LottoNumber(10)
                        , new LottoNumber(11)
                        , new LottoNumber(12)));

        LottoTicket lottoTicket1 = new LottoTicket(lottoLottoNumbers1);
        LottoTicket lottoTicket2 = new LottoTicket(lottoLottoNumbers2);
        List<LottoTicket> lottoTicketList = new ArrayList<>();
        lottoTicketList.add(lottoTicket1);
        lottoTicketList.add(lottoTicket2);

        int purchaseAmount = 2000;
        LottoTickets lottoTickets = new LottoTickets(lottoTicketList, purchaseAmount);

        // when
        Map<Rank, Integer> byRankCountOfMatches = lottoTickets.getByRankCountOfMatches(winningLottoNumbers);

        // then
        Map<Rank, Integer> expectedByRankCountOfMatches = new HashMap<>();
        expectedByRankCountOfMatches.put(Rank.FIRST_PRICE, 0);
        expectedByRankCountOfMatches.put(Rank.SECOND_PRICE, 0);
        expectedByRankCountOfMatches.put(Rank.THIRD_PRICE, 0);
        expectedByRankCountOfMatches.put(Rank.FOUR_PRICE, 2);

        assertThat(byRankCountOfMatches).isEqualTo(expectedByRankCountOfMatches);
    }
}