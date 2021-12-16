package lotto.UI;

import lotto.domain.LottoGame;
import lotto.domain.LottoResult;
import lotto.domain.Money;
import lotto.domain.Ticket;
import lotto.domain.WinningTicket;
import lotto.generator.DefaultGenerator;

public class Controller {

    public static void run() {
        Money purchaseMoney = new Money(InputView.requirePurchaseAmount());
        int manualTicketCount = InputView.requirePurchaseManualTicketCount();
        String manualLottoTickets = InputView.requireManualLottoTickets(manualTicketCount);
        LottoGame lottoGame = new LottoGame(purchaseMoney, new DefaultGenerator(manualLottoTickets, manualTicketCount));

        ResultView.confirmPurchaseTicket(lottoGame.get(), purchaseMoney);

        Ticket winningTicket = InputView.requireWinningTicket();
        int bonusNumber = InputView.requireBonusNumber();
        LottoResult lottoResult = lottoGame.match(new WinningTicket(winningTicket, bonusNumber));

        ResultView.printWinningStats(lottoResult, purchaseMoney);
    }
}
