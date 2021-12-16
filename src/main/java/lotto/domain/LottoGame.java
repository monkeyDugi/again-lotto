package lotto.domain;

import java.util.List;
import java.util.Objects;

public class LottoGame {

    private final List<Ticket> tickets;

    public LottoGame(Money money, LottoGenerator lottoGenerator) {
        this.tickets = lottoGenerator.generate(money);
    }

    public LottoResult match(WinningTicket winningTicket) {
        LottoResult lottoResult = new LottoResult();
        for (Ticket ticket : tickets) {
            lottoResult.putRank(winningTicket.match(ticket));
        }
        return lottoResult;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LottoGame lottoGame = (LottoGame) o;
        return Objects.equals(tickets, lottoGame.tickets);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tickets);
    }
}
