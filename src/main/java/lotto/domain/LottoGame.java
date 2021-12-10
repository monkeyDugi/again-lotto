package lotto.domain;

public class LottoGame {

    private final LottoTickets lottoTickets;

    public LottoGame(int purchaseAmount) {
        lottoTickets = new LottoTickets(purchaseAmount);
    }

    public LottoTickets get() {
        return lottoTickets;
    }
}
