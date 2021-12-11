package lotto.domain;

import lotto.UI.InputView;
import lotto.UI.ResultView;

public class Controller {

    public static void run() {
        int purchaseAmount = InputView.requirePurchaseAmount();
        LottoGame lottoGame = new LottoGame(purchaseAmount);
        InputView.confirmPurchaseCount(purchaseAmount, lottoGame.get());

        LottoTicket winningNumbers = new LottoTicket(ResultView.requireWinningNumber());
        LottoNumber bonusNumber = new LottoNumber(ResultView.requireBonusNumber());
        WinningStatistics winningStatistics = new WinningStatistics(lottoGame.get(), winningNumbers, bonusNumber);
        ResultView.printWinningStats(winningStatistics);
    }
}
