package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoTicketsTest {

    @DisplayName("천원 단위로 로또 티켓을 자동 생성한다.(자동만 구매)")
    @Test
    void createAutoLottoTicket() {
        // given
        int price = 2000;

        // when
        LottoTickets lottoTickets = new LottoTickets(price);

        // then
        assertThat(lottoTickets.size()).isEqualTo(2);
    }

    @DisplayName("수동으로만 구매할 경우 수동 티켓만 발행된다.")
    @Test
    void createManualLottoTicket() {
        // given
        int purchaseAmount = 3000;

        List<String> manualLottoNumber1 = Arrays.asList("1", "2", "3", "4", "5", "6");
        List<String> manualLottoNumber2 = Arrays.asList("1", "2", "3", "4", "5", "7");
        List<String> manualLottoNumber3 = Arrays.asList("1", "2", "3", "4", "5", "8");

        LottoTicket manualLottoTicket1 = new LottoTicket(manualLottoNumber1);
        LottoTicket manualLottoTicket2 = new LottoTicket(manualLottoNumber2);
        LottoTicket manualLottoTicket3 = new LottoTicket(manualLottoNumber3);

        List<LottoTicket> manualLottoTickets = new ArrayList<>();
        manualLottoTickets.add(manualLottoTicket1);
        manualLottoTickets.add(manualLottoTicket2);
        manualLottoTickets.add(manualLottoTicket3);

        LottoTickets lottoTickets = new LottoTickets(new ManualLottoTickets(manualLottoTickets), purchaseAmount);

        // then
        assertThat(lottoTickets.size()).isEqualTo(3);
    }

    @DisplayName("수동, 자동 모두 구매할 경우 같이 구매된다.")
    @Test
    void createManualLottoTicketAndAutoLottoTicket() {
        // given
        int purchaseAmount = 5000;

        List<String> manualLottoNumber1 = Arrays.asList("1", "2", "3", "4", "5", "6");
        List<String> manualLottoNumber2 = Arrays.asList("1", "2", "3", "4", "5", "7");
        List<String> manualLottoNumber3 = Arrays.asList("1", "2", "3", "4", "5", "8");

        LottoTicket manualLottoTicket1 = new LottoTicket(manualLottoNumber1);
        LottoTicket manualLottoTicket2 = new LottoTicket(manualLottoNumber2);
        LottoTicket manualLottoTicket3 = new LottoTicket(manualLottoNumber3);

        List<LottoTicket> manualLottoTickets = new ArrayList<>();
        manualLottoTickets.add(manualLottoTicket1);
        manualLottoTickets.add(manualLottoTicket2);
        manualLottoTickets.add(manualLottoTicket3);

        LottoTickets lottoTickets = new LottoTickets(new ManualLottoTickets(manualLottoTickets), purchaseAmount);

        // then
        assertThat(lottoTickets.size()).isEqualTo(5);
    }

    @DisplayName("최소 구매 금액은 1000원이다.")
    @Test
    void validateMinimumPrice() {
        // given
        int purchaseAmount = 900;

        // when
        assertThatThrownBy(() -> new LottoTickets(purchaseAmount))
                // then
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("최소 구매 금액은 1000원 입니다.");
    }

    @DisplayName("구매 금액은 1000원 단위로만 가능하다.")
    @Test
    void validateTicketPrice() {
        // given
        int purchaseAmount = 1100;

        // when
        assertThatThrownBy(() -> new LottoTickets(purchaseAmount))
                // then
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("구매 금액은 1000원 단위만 가능합니다.");
    }

    @DisplayName("로또 순위별 당첨 개수를 조회한다.")
    @Test
    void getByRankCountOfMatches() {
        // given
        Set<LottoNumber> lottoLottoNumbers1 =
                new HashSet<>(Arrays.asList(new LottoNumber(1)
                        , new LottoNumber(2)
                        , new LottoNumber(3)
                        , new LottoNumber(4)
                        , new LottoNumber(5)
                        , new LottoNumber(6)));
        Set<LottoNumber> lottoLottoNumbers2 =
                new HashSet<>(Arrays.asList(new LottoNumber(1)
                        , new LottoNumber(2)
                        , new LottoNumber(3)
                        , new LottoNumber(4)
                        , new LottoNumber(5)
                        , new LottoNumber(7)));
        Set<LottoNumber> winningLottoNumbers =
                new HashSet<>(Arrays.asList(new LottoNumber(1)
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
        Map<Rank, Integer> byRankCountOfMatches = lottoTickets.getByRankCountOfMatches(new LottoTicket(winningLottoNumbers), new LottoNumber(30));

        // then
        Map<Rank, Integer> expectedByRankCountOfMatches = new HashMap<>();
        expectedByRankCountOfMatches.put(Rank.FIRST_PRICE, 0);
        expectedByRankCountOfMatches.put(Rank.SECOND_PRICE, 0);
        expectedByRankCountOfMatches.put(Rank.THIRD_PRICE, 0);
        expectedByRankCountOfMatches.put(Rank.FOUR_PRICE, 0);
        expectedByRankCountOfMatches.put(Rank.FIFTH_PRICE, 2);

        assertThat(byRankCountOfMatches).isEqualTo(expectedByRankCountOfMatches);
    }
}