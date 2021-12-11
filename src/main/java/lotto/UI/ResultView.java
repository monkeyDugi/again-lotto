package lotto.UI;

import lotto.domain.LottoTicket;
import lotto.domain.LottoTickets;
import lotto.domain.Rank;
import lotto.domain.WinningStatistics;

import java.util.Map;

public class ResultView {

    public static void confirmPurchaseCount(int purchaseAmount, LottoTickets lottoTickets, int manualLottoTicketCount) {
        int autoLottoTicketCount = purchaseAmount / LottoTicket.TICKET_PRICE - manualLottoTicketCount;

        StringBuilder sb = new StringBuilder();
        sb.append("수동으로 ")
                .append(manualLottoTicketCount)
                .append("장, ");
        sb.append("자동으로 ")
                .append(autoLottoTicketCount)
                .append("장을 구매했습니다.");

        System.out.println(sb);

        for (LottoTicket lottoTicket : lottoTickets.get()) {
            System.out.println(lottoTicket);
        }
    }

    public static void printWinningStats(WinningStatistics winningStatistics) {
        Map<Rank, Integer> byRankCountOfMatches = winningStatistics.getByRankCountOfMatches();
        for (Rank rank : byRankCountOfMatches.keySet()) {
            int byRankCountOfMatch = byRankCountOfMatches.get(rank);
            int winningAmount = rank.getWinningAmount();
            int countOfMatch = rank.getCountOfMatch();

            System.out.println(countOfMatch + "개 일치 (" + winningAmount + "원) - " + byRankCountOfMatch + "개");
        }

        System.out.println("총 수익률을 " + winningStatistics.calculateYields() + "입니다.");
    }
}
