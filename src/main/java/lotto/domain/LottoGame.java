package lotto.domain;

public class LottoGame {

    private final LottoTickets lottoTickets;

    public LottoGame(int purchaseAmount, ManualLottoTickets manualLottoTickets) {
        lottoTickets = new LottoTickets(manualLottoTickets, purchaseAmount);
    }

    public LottoTickets get() {
        return lottoTickets;
    }
}
