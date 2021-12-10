package lotto.domain;

import lotto.util.AutomaticLottoNumber;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoTicketTest {

    @DisplayName("로또 번호는 6개 보다 클 수 없다.")
    @Test
    void validateMaximumSize() {
        // given
        Set<LottoNumber> lottoLottoNumbers =
                new HashSet<>(Arrays.asList(new LottoNumber(1)
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

    @DisplayName("로또 번호는 6개 보다 작을 수 없다.")
    @Test
    void validateMinimumSize() {
        // given
        Set<LottoNumber> lottoLottoNumbers =
                new HashSet<>(Arrays.asList(new LottoNumber(1)
                        , new LottoNumber(2)
                        , new LottoNumber(3)
                        , new LottoNumber(4)
                        , new LottoNumber(7)));

        // when
        assertThatThrownBy(() -> new LottoTicket(lottoLottoNumbers))
                // then
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또 번호는 6개만 가능합니다.");
    }

    @DisplayName("로또 번호들은 중복될 수 없다. 무조건 6개를 넘어도 6개가 생성된다.")
    @Test
    void validateDuplicate() {
        // given
        Set<LottoNumber> lottoLottoNumbers =
                new HashSet<>(Arrays.asList(new LottoNumber(1)
                        , new LottoNumber(2)
                        , new LottoNumber(3)
                        , new LottoNumber(4)
                        , new LottoNumber(5)
                        , new LottoNumber(6)
                        , new LottoNumber(6)));

        // when
        LottoTicket lottoTicket = new LottoTicket(lottoLottoNumbers);

        // then
        assertThat(lottoTicket.size()).isEqualTo(LottoTicket.LOTTO_NUMBERS_SIZE);
    }

    @DisplayName("당첨된 번호의 개수를 구한다.")
    @Test
    void getLottoNumberOfWins() {
        // given
        Set<LottoNumber> lottoLottoNumbers =
                new HashSet<>(Arrays.asList(new LottoNumber(1)
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
        int numberOfWins = lottoTicket.getCountOfMatch(winningLottoNumbers);

        // then
        assertThat(numberOfWins).isEqualTo(5);
    }

    @DisplayName("6개의 번호를 가진 티켓이 생성된다.")
    @Test
    void createLottoTicket() {
        // when
        Set<LottoNumber> lottoNumbers = AutomaticLottoNumber.createNumbers();

        // then
        assertThat(lottoNumbers.size()).isEqualTo(LottoTicket.LOTTO_NUMBERS_SIZE);
    }

    @DisplayName("보너스 번호를 맞추면 true, 아니면 false를 반환한다.")
    @Test
    void equalsMatchBonus() {
        // given
        Set<LottoNumber> lottoLottoNumbers =
                new HashSet<>(Arrays.asList(new LottoNumber(1)
                        , new LottoNumber(2)
                        , new LottoNumber(3)
                        , new LottoNumber(4)
                        , new LottoNumber(5)
                        , new LottoNumber(6)));

        int trueBonusNumber = 3;
        int falseBonusNumber = 7;

        LottoTicket lottoTicket = new LottoTicket(lottoLottoNumbers);

        // then
        assertThat(lottoTicket.equalsMatchBonus(trueBonusNumber)).isTrue();
        assertThat(lottoTicket.equalsMatchBonus(falseBonusNumber)).isFalse();
    }
}