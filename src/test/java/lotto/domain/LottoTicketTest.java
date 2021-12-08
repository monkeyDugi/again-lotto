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
        List<Integer> lottoNumbers = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7));

        // when
        assertThatThrownBy(() -> new LottoTicket(lottoNumbers))
                // then
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또 번호는 6개만 가능합니다.");
    }

    @DisplayName("로또 번호들은 중복될 수 없다.")
    @Test
    void validateDuplicate() {
        // given
        List<Integer> lottoNumbers = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 6, 6));

        // when
        assertThatThrownBy(() -> new LottoTicket(lottoNumbers))
                // then
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또 번호들은 중복될 수 없습니다.");
    }

    @DisplayName("로또 번호는 1이상이어야 합니다.")
    @Test
    void validateMinimumNumber() {
        // given
        List<Integer> lottoNumbers = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 6, 0));

        // when
        assertThatThrownBy(() -> new LottoTicket(lottoNumbers))
                // then
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또 번호는 1이상이어야 합니다.");
    }

    @DisplayName("로또 번호는 45이하이어야 합니다.")
    @Test
    void validateMaximumNumber() {
        // given
        List<Integer> lottoNumbers = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 6, 46));

        // when
        assertThatThrownBy(() -> new LottoTicket(lottoNumbers))
                // then
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또 번호는 45이하이어야 합니다.");
    }

    @DisplayName("당첨된 번호의 개수를 구한다.")
    @Test
    void getNumberOfWins() {
        // given
        List<Integer> lottoNumbers = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6));
        List<Integer> winningNumbers = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 7));

        LottoTicket lottoTicket = new LottoTicket(lottoNumbers);

        // when
        int numberOfWins = lottoTicket.getNumberOfWins(winningNumbers);

        // then
        assertThat(numberOfWins).isEqualTo(5);
    }
}