package lotto.domain;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Stream;

/**
 * 당첨금 관리
 */
public enum Rank {

    FIRST_PRICE(6, 2_000_000_000),
    SECOND_PRICE(5, 1_500_000),
    THIRD_PRICE(4, 50_000),
    FOUR_PRICE(3, 5_000),
    MISS(0, 0);

    private static final int MINIMUM_COUNT_OF_MATCH = 0;
    private static final int MAXIMUM_COUNT_OF_MATCH = 6;

    private final int countOfMatch;
    private final int winningAmount;

    Rank(int countOfMatch, int winningAmount) {
        this.countOfMatch = countOfMatch;
        this.winningAmount = winningAmount;
    }

    public static Rank valueOf(int countOfMatch) {
        validateCountOfMatch(countOfMatch);

        Stream<Rank> stream = Arrays.stream(values());
        return stream.filter(rank -> rank.equalsCountOfMatch(countOfMatch))
                     .findFirst()
                     .orElse(MISS);
    }

    public static Map<Rank, Integer> getDefaultRanks() {
        Map<Rank, Integer> ranks = new LinkedHashMap<>();

        Rank[] rankArr = Rank.values();
        for (Rank rank : rankArr) {
            ranks.put(rank, 0);
        }

        ranks.remove(MISS);

        return ranks;
    }

    public int getWinningAmount() {
        return winningAmount;
    }

    public int getCountOfMatch() {
        return countOfMatch;
    }

    private static void validateCountOfMatch(int countOfMatch) {
        if (countOfMatch < MINIMUM_COUNT_OF_MATCH) {
            throw new IllegalArgumentException("맞춘 개수는 " + MINIMUM_COUNT_OF_MATCH + "보다 작을 수 없습니다.");
        }

        if (countOfMatch > MAXIMUM_COUNT_OF_MATCH) {
            throw new IllegalArgumentException("맞춘 개수는 " + MAXIMUM_COUNT_OF_MATCH + "보다 클 수 없습니다.");
        }
    }

    private boolean equalsCountOfMatch(int countOfMatch) {
        return this.countOfMatch == countOfMatch;
    }
}
