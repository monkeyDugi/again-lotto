package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class LottoGameTest {

    @DisplayName("로또 당첨 수익율을 계산한다.")
    @Test
    void calculateYields() {
        // given
        List<LottoNumber> lottoLottoNumbers =
                new ArrayList<>(Arrays.asList(new LottoNumber(1)
                        , new LottoNumber(2)
                        , new LottoNumber(3)
                        , new LottoNumber(4)
                        , new LottoNumber(5)
                        , new LottoNumber(6)));
        List<LottoNumber> winningLottoNumbers =
                new ArrayList<>(Arrays.asList(new LottoNumber(1)
                        , new LottoNumber(2)
                        , new LottoNumber(3)
                        , new LottoNumber(10)
                        , new LottoNumber(11)
                        , new LottoNumber(12)));

        LottoTicket lottoTicket = new LottoTicket(lottoLottoNumbers);
        List<LottoTicket> lottoTicketList = new ArrayList<>();
        lottoTicketList.add(lottoTicket);

        LottoTickets lottoTickets = new LottoTickets(lottoTicketList);

        int purchaseAmount = 14000;
        LottoGame lottoGame = new LottoGame(lottoTickets, purchaseAmount);


        // when
        double yields = lottoGame.calculateYields(winningLottoNumbers);

        // then
        assertThat(yields).isEqualTo(0.36);
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

        LottoTickets lottoTickets = new LottoTickets(lottoTicketList);

        int purchaseAmount = 2000;
        LottoGame lottoGame = new LottoGame(lottoTickets, purchaseAmount);

        // when
        Map<Rank, Integer> byRankCountOfMatches = lottoGame.getByRankCountOfMatches(winningLottoNumbers);

        // then
        Map<Rank, Integer> expectedByRankCountOfMatches = new HashMap<>();
        expectedByRankCountOfMatches.put(Rank.FIRST_PRICE, 0);
        expectedByRankCountOfMatches.put(Rank.SECOND_PRICE, 0);
        expectedByRankCountOfMatches.put(Rank.THIRD_PRICE, 0);
        expectedByRankCountOfMatches.put(Rank.FOUR_PRICE, 2);

        assertThat(byRankCountOfMatches).isEqualTo(expectedByRankCountOfMatches);
    }
}
