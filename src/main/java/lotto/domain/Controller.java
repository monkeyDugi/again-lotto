package lotto.domain;

import lotto.UI.InputView;
import lotto.UI.ResultView;

public class Controller {

    public static void run() {
        int purchaseAmount = InputView.requirePurchaseAmount();
        int manualLottoTicketCount = InputView.requireManualLottoTicketCount();
        ManualLottoTickets manualLottoTickets = InputView.requireManualLottoTickets(manualLottoTicketCount);

        LottoGame lottoGame = new LottoGame(purchaseAmount, manualLottoTickets);
        ResultView.confirmPurchaseCount(purchaseAmount, lottoGame.get(), manualLottoTicketCount);

        LottoTicket winningNumbers = new LottoTicket(InputView.requireWinningNumber());
        LottoNumber bonusNumber = new LottoNumber(InputView.requireBonusNumber());
        WinningStatistics winningStatistics = new WinningStatistics(lottoGame.get(), winningNumbers, bonusNumber);
        ResultView.printWinningStats(winningStatistics);
    }
}
