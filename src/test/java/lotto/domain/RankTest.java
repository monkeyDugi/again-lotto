package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class RankTest {

    @DisplayName("당첨 개수에 맞는 Rank를 반환한다.")
    @Test
    void valueOfCountOfMathc() {
        // given
        int firstPriceCountOfMatch = 6;
        int secondPriceCountOfMatch = 5;
        int thirdPriceCountOfMatch = 4;
        int fourPriceCountOfMatch = 3;
        int missPriceCountOfMatch = 0;

        // when
        Rank firstRank = Rank.valueOf(firstPriceCountOfMatch);
        Rank secondRank = Rank.valueOf(secondPriceCountOfMatch);
        Rank thirdRank = Rank.valueOf(thirdPriceCountOfMatch);
        Rank fourRank = Rank.valueOf(fourPriceCountOfMatch);
        Rank missRank = Rank.valueOf(missPriceCountOfMatch);

        // then
        assertThat(firstRank).isEqualTo(Rank.FIRST_PRICE);
        assertThat(secondRank).isEqualTo(Rank.SECOND_PRICE);
        assertThat(thirdRank).isEqualTo(Rank.THIRD_PRICE);
        assertThat(fourRank).isEqualTo(Rank.FOUR_PRICE);
        assertThat(missRank).isEqualTo(Rank.MISS);
    }

    @DisplayName("맞춘 개수는 0보다 작을 수 없고, 6보다 클 수 없다.")
    @Test
    void validateCountOfMath() {
        // given
        int minimumCountOfMatch = -1;
        int maximumCountOfMatch = 7;

        // when
        assertThatThrownBy(() -> Rank.valueOf(minimumCountOfMatch))
                // then
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("맞춘 개수는 0보다 작을 수 없습니다.");

        // when
        assertThatThrownBy(() -> Rank.valueOf(maximumCountOfMatch))
                // then
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("맞춘 개수는 6보다 클 수 없습니다.");
    }
}