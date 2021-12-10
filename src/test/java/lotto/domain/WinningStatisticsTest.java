package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class WinningStatisticsTest {

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

        int purchaseAmount = 14000;
        LottoTickets lottoTickets = new LottoTickets(lottoTicketList, purchaseAmount);

        WinningStatistics winningStatistics = new WinningStatistics(lottoTickets, winningLottoNumbers);

        // when
        double yields = winningStatistics.calculateYields();

        // then
        assertThat(yields).isEqualTo(0.36);
    }
}