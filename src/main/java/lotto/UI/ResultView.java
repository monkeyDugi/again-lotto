package lotto.UI;

import lotto.domain.LottoGame;
import lotto.domain.LottoNumber;
import lotto.domain.Rank;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class ResultView {

    private static final Scanner sc = new Scanner(System.in);

    public static List<LottoNumber> requireWinningNumber() {
        System.out.println();
        System.out.println("지난 주 당첨 번호를 입력해 주세요");

        List<LottoNumber> lottoNumbers = new ArrayList<>();
        String[] winningNumbers = sc.nextLine().split(",");
        for (String winningNumber : winningNumbers) {
            lottoNumbers.add(new LottoNumber(Integer.parseInt(winningNumber)));
        }

        return lottoNumbers;
    }

    public static void printWinningStats(LottoGame lottoGame, List<LottoNumber> winningNumbers) {
        Map<Rank, Integer> byRankCountOfMatches = lottoGame.getByRankCountOfMatches(winningNumbers);
        for (Rank rank : byRankCountOfMatches.keySet()) {
            int byRankCountOfMatch = byRankCountOfMatches.get(rank);
            int winningAmount = rank.getWinningAmount();
            int countOfMatch = rank.getCountOfMatch();

            System.out.println(countOfMatch + "개 일치 (" + winningAmount + "원) - " + byRankCountOfMatch + "개");
        }

        System.out.println("총 수익률을 " + lottoGame.calculateYields(winningNumbers) + "입니다.(기준이 1이기 때문에 결과적으로 손해라는 의미임)");
    }
}
