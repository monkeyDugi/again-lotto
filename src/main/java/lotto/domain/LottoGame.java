package lotto.domain;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class LottoGame {

    private final List<Ticket> tickets;

    public LottoGame(Money purchaseMoney, TicketGenerator lottoGenerator) {
        this.tickets = lottoGenerator.generate(purchaseMoney);
    }

    public LottoResult match(WinningTicket winningTicket) {
        LottoResult lottoResult = new LottoResult();
        for (Ticket ticket : tickets) {
            lottoResult.putRank(winningTicket.match(ticket));
        }
        return lottoResult;
    }

    public List<Ticket> get() {
        return Collections.unmodifiableList(tickets);
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
