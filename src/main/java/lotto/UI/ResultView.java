package lotto.UI;

import lotto.domain.LottoNumber;
import lotto.domain.Rank;
import lotto.domain.WinningStatistics;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class ResultView {

    private static final Scanner sc = new Scanner(System.in);

    public static Set<LottoNumber> requireWinningNumber() {
        System.out.println();
        System.out.println("지난 주 당첨 번호를 입력해 주세요");

        Set<LottoNumber> lottoNumbers = new HashSet<>();
        String[] winningNumbers = sc.nextLine().split(",");
        for (String winningNumber : winningNumbers) {
            lottoNumbers.add(new LottoNumber(Integer.parseInt(winningNumber)));
        }

        return lottoNumbers;
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
