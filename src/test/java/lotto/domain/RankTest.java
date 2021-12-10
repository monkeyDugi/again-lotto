package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static lotto.domain.Rank.MISS;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class RankTest {

    @DisplayName("당첨 개수에 맞는 Rank를 반환한다.")
    @Test
    void valueOfCountOfMatch() {
        // given
        int firstPriceCountOfMatch = 6;
        int secondPriceCountOfMatch = 4;
        int thirdPriceCountOfMatch = 5;
        int fourPriceCountOfMatch = 4;
        int fifthPriceCountOfMatch = 3;
        int missPriceCountOfMatch = 0;

        // when
        Rank firstRank = Rank.valueOf(firstPriceCountOfMatch, false);
        Rank secondRank = Rank.valueOf(secondPriceCountOfMatch, true);
        Rank thirdRank = Rank.valueOf(thirdPriceCountOfMatch, false);
        Rank fourRank = Rank.valueOf(fourPriceCountOfMatch, false);
        Rank fifthRank = Rank.valueOf(fifthPriceCountOfMatch, false);
        Rank missRank = Rank.valueOf(missPriceCountOfMatch, false);

        // then
        assertThat(firstRank).isEqualTo(Rank.FIRST_PRICE);
        assertThat(secondRank).isEqualTo(Rank.SECOND_PRICE);
        assertThat(thirdRank).isEqualTo(Rank.THIRD_PRICE);
        assertThat(fourRank).isEqualTo(Rank.FOUR_PRICE);
        assertThat(fifthRank).isEqualTo(Rank.FIFTH_PRICE);
        assertThat(missRank).isEqualTo(MISS);
    }

    @DisplayName("맞춘 개수는 0보다 작을 수 없고, 6보다 클 수 없다.")
    @Test
    void validateCountOfMath() {
        // given
        int minimumCountOfMatch = -1;
        int maximumCountOfMatch = 7;

        // when
        assertThatThrownBy(() -> Rank.valueOf(minimumCountOfMatch, false))
                // then
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("맞춘 개수는 0보다 작을 수 없습니다.");

        // when
        assertThatThrownBy(() -> Rank.valueOf(maximumCountOfMatch, false))
                // then
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("맞춘 개수는 6보다 클 수 없습니다.");
    }

    @DisplayName("MISS를 제외한 모든 value를 Map으로 반환한다. 각각 값은 0이다.")
    @Test
    void getDefaultRanks() {
        // given
        Map<Rank, Integer> expectedRanks = new LinkedHashMap<>();
        Rank[] rankArr = Rank.values();
        for (Rank rank : rankArr) {
            expectedRanks.put(rank, 0);
        }

        expectedRanks.remove(Rank.MISS);

        // when
        Map<Rank, Integer> defaultRanks = Rank.getDefaultRanks();

        // then
        assertThat(defaultRanks).isEqualTo(expectedRanks);
    }

    @DisplayName("")
    @Test
    void d() {
        // given
        Map<Rank, Integer> expectedRanks = new LinkedHashMap<>();
        Rank[] rankArr = Rank.values();
        for (Rank rank : rankArr) {
            expectedRanks.put(rank, 0);
        }

        expectedRanks.remove(Rank.MISS);

        // when
        Map<Rank, Integer> defaultRanks = Rank.getDefaultRanks();

        // then
        assertThat(defaultRanks).isEqualTo(expectedRanks);
    }
}