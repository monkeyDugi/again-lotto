package lotto.domain;

import lotto.UI.InputView;
import lotto.UI.ResultView;

import java.util.Set;

public class Controller {

    public static void run() {
        int purchaseAmount = InputView.requirePurchaseAmount();
        LottoGame lottoGame = new LottoGame(purchaseAmount);
        InputView.confirmPurchaseCount(purchaseAmount, lottoGame.get());

        Set<LottoNumber> winningNumbers = ResultView.requireWinningNumber();
        WinningStatistics winningStatistics = new WinningStatistics(lottoGame.get(), winningNumbers);
        ResultView.printWinningStats(winningStatistics);
    }
}
