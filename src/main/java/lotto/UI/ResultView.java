package lotto.UI;

import lotto.domain.LottoResult;
import lotto.domain.Money;
import lotto.domain.Rank;
import lotto.domain.Ticket;

import java.util.List;

public class ResultView {

    public static void confirmPurchaseTicket(List<Ticket> tickets, Money purchaseMoney) {
        System.out.println(purchaseMoney.calculateCountOfBuyingTicket() + "개를 구매했습니다.");
        for (Ticket ticket : tickets) {
            System.out.println(ticket);
        }
    }

    public static void printWinningStats(LottoResult lottoResult, Money purchaseMoney) {
        System.out.println("당첨 통계");
        System.out.println("=============");

        Rank[] ranks = Rank.values();
        for (Rank rank : ranks) {
            System.out.println(
                    rank.getCountOfMatch() +
                            "개 일치 (" +
                            rank.getWinningMoney() +
                            "원) - " +
                            lottoResult.getValue(rank) +
                            "개"
            );
        }
        System.out.println("총 수익률 = " + purchaseMoney.calculateProfitRate(lottoResult.getPrize()));
    }
}
