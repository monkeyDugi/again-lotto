package lotto.domain;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;

public class WinningStatistics {

    private final LottoTickets lottoTickets;
    private final List<LottoNumber> winningNumbers;

    public WinningStatistics(LottoTickets lottoTickets, List<LottoNumber> winningNumbers) {
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
}
