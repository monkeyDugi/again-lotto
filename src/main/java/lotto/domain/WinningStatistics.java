package lotto.domain;

import java.text.DecimalFormat;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class WinningStatistics {

    private final LottoTickets lottoTickets;
    private final Set<LottoNumber> winningNumbers;

    public WinningStatistics(LottoTickets lottoTickets, Set<LottoNumber> winningNumbers) {
        this.lottoTickets = lottoTickets;
        this.winningNumbers = winningNumbers;
    }

    public double calculateYields() {
        int totalRankAmount = 0;

        Map<Rank, Integer> ranks = getByRankCountOfMatches();
        for (Rank rank : ranks.keySet()) {
            totalRankAmount += rank.getWinningAmount() * ranks.get(rank);
        }

        return calculateYields(totalRankAmount);
    }

    public Map<Rank, Integer> getByRankCountOfMatches() {
        return lottoTickets.getByRankCountOfMatches(winningNumbers);
    }

    private double calculateYields(int totalRankAmount) {
        DecimalFormat df = new DecimalFormat("#.##");
        return Double.parseDouble(df.format((double) totalRankAmount / (double) lottoTickets.getPurchaseAmount()));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WinningStatistics that = (WinningStatistics) o;
        return Objects.equals(lottoTickets, that.lottoTickets) && Objects.equals(winningNumbers, that.winningNumbers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lottoTickets, winningNumbers);
    }
}
