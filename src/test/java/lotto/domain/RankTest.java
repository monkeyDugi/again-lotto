package lotto.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class RankTest {

    @Test
    void 미당첨() {
        // given
        int countOfMatch = 2;

        // when
        Rank rank = Rank.valueOf(countOfMatch, false);

        // then
        assertThat(rank).isEqualTo(Rank.MISS);
    }

    @Test
    void 당첨_2등() {
        // given
        int countOfMatch = 5;

        // when
        Rank rank = Rank.valueOf(countOfMatch, true);

        // then
        assertThat(rank).isEqualTo(Rank.SECOND);
    }

    @Test
    void 당첨_3등() {
        // given
        int countOfMatch = 5;

        // when
        Rank rank = Rank.valueOf(countOfMatch, false);

        // then
        assertThat(rank).isEqualTo(Rank.THIRD);
    }

    @Test
    void 유요하지_않는_당첨_개수_예외() {
        // given
        int countOfMatch = 7;

        // when
        assertThatThrownBy(() -> Rank.valueOf(countOfMatch, false))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(countOfMatch + "는 유효하지 않은 값입니다.");


    }
}