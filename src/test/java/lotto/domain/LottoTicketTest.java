package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoTicketTest {

    @DisplayName("로또 번호는 6개만 가능하다.")
    @Test
    void validateSize() {
        // given
        List<LottoNumber> lottoLottoNumbers =
                new ArrayList<>(Arrays.asList(new LottoNumber(1)
                        , new LottoNumber(2)
                        , new LottoNumber(3)
                        , new LottoNumber(4)
                        , new LottoNumber(5)
                        , new LottoNumber(6)
                        , new LottoNumber(7)));

        // when
        assertThatThrownBy(() -> new LottoTicket(lottoLottoNumbers))
                // then
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또 번호는 6개만 가능합니다.");
    }

    @DisplayName("로또 번호들은 중복될 수 없다.")
    @Test
    void validateDuplicate() {
        // given
        List<LottoNumber> lottoLottoNumbers =
                new ArrayList<>(Arrays.asList(new LottoNumber(1)
                        , new LottoNumber(2)
                        , new LottoNumber(3)
                        , new LottoNumber(4)
                        , new LottoNumber(6)
                        , new LottoNumber(6)));

        // when
        assertThatThrownBy(() -> new LottoTicket(lottoLottoNumbers))
                // then
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또 번호들은 중복될 수 없습니다.");
    }

    @DisplayName("당첨된 번호의 개수를 구한다.")
    @Test
    void getLottoNumberOfWins() {
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
                        , new LottoNumber(4)
                        , new LottoNumber(5)
                        , new LottoNumber(7)));

        LottoTicket lottoTicket = new LottoTicket(lottoLottoNumbers);

        // when
        int numberOfWins = lottoTicket.getNumberOfWins(winningLottoNumbers);

        // then
        assertThat(numberOfWins).isEqualTo(5);
    }
}