package lotto.domain;

import java.text.DecimalFormat;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class LottoGame {

    private static final int TICKET_PRICE = 1_000;

    private final LottoTickets lottoTickets;
    private int purchaseAmount;

    public LottoGame(LottoTickets lottoTickets, int purchaseAmount) {
        this.lottoTickets = lottoTickets;
        this.purchaseAmount = purchaseAmount;
    }

    public LottoGame(int purchaseAmount) {
        lottoTickets = new LottoTickets(purchaseAmount);
        this.purchaseAmount = purchaseAmount;
    }

    public double calculateYields(List<LottoNumber> winningNumbers) {
        int totalRankAmount = 0;

        Map<Rank, Integer> ranks = getByRankCountOfMatches(winningNumbers);
        for (Rank rank : ranks.keySet()) {
            totalRankAmount += rank.getWinningAmount() * ranks.get(rank);
        }

        return calculateYields(totalRankAmount, purchaseAmount);
    }

    public Map<Rank, Integer> getByRankCountOfMatches(List<LottoNumber> winningNumbers) {
        Map<Rank, Integer> ranks = createDefaultRanks();
        calculateByRankCountOfMatches(winningNumbers, ranks);

        return ranks;
    }

    private Map<Rank, Integer> createDefaultRanks() {
        Map<Rank, Integer> ranks = new LinkedHashMap<>();

        Rank[] rankArr = Rank.values();
        for (Rank rank : rankArr) {
            ranks.put(rank, 0);
        }

        return ranks;
    }

    private void calculateByRankCountOfMatches(List<LottoNumber> winningNumbers, Map<Rank, Integer> ranks) {
        List<LottoTicket> lottoTickets = this.lottoTickets.get();
        for (LottoTicket lottoTicket : lottoTickets) {
            int countOfMatch = lottoTicket.getCountOfMatch(winningNumbers);
            Rank rank = Rank.valueOf(countOfMatch);

            ranks.merge(rank, 1, Integer::sum);
        }

        ranks.remove(Rank.MISS);
    }

    private double calculateYields(int totalRankAmount, int purchaseAmount) {
        DecimalFormat df = new DecimalFormat("#.##");
        return Double.parseDouble(df.format((double) totalRankAmount / (double) purchaseAmount));
    }

    public List<LottoTicket> getLottoTickets() {
        return Collections.unmodifiableList(lottoTickets.get());
    }
}
