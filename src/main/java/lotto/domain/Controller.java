package lotto.domain;

import lotto.UI.InputView;
import lotto.UI.ResultView;

import java.util.List;

public class Controller {

    public static void run() {
        int purchaseAmount = InputView.requirePurchaseAmount();
        LottoGame lottoGame = new LottoGame(purchaseAmount);
        InputView.confirmPurchaseCount(purchaseAmount, lottoGame.getLottoTickets());

        List<LottoNumber> winningNumbers = ResultView.requireWinningNumber();
        ResultView.printWinningStats(lottoGame, winningNumbers);
    }
}
