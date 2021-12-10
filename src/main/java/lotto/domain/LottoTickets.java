package lotto.domain;

import lotto.util.AutomaticLottoNumber;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class LottoTickets {

    private static final int TICKET_PRICE = 1000;

    private List<LottoTicket> lottoTickets;
    private final int purchaseAmount;

    public LottoTickets(int purchaseAmount) {
        validatePrice(purchaseAmount);
        createLottoTickets(purchaseAmount);

        this.purchaseAmount = purchaseAmount;
    }

    public LottoTickets(List<LottoTicket> lottoTickets, int purchaseAmount) {
        this.lottoTickets = lottoTickets;
        this.purchaseAmount = purchaseAmount;
    }

    private void validatePrice(int purchaseAmount) {
        if (purchaseAmount < TICKET_PRICE) {
            throw new IllegalArgumentException("최소 구매 금액은 " + TICKET_PRICE + "원 입니다.");
        }

        if (purchaseAmount % TICKET_PRICE != 0) {
            throw new IllegalArgumentException("구매 금액은 " + TICKET_PRICE + "원 단위만 가능합니다.");
        }
    }

    private void createLottoTickets(int purchaseAmount) {
        lottoTickets = new ArrayList<>();
        int ticketCount = purchaseAmount / TICKET_PRICE;

        for (int i = 0; i < ticketCount; i++) {
            lottoTickets.add(new LottoTicket(AutomaticLottoNumber.createNumbers()));
        }
    }

    public Map<Rank, Integer> getByRankCountOfMatches(List<LottoNumber> winningNumbers) {
        Map<Rank, Integer> ranks = Rank.getDefaultRanks();
        for (LottoTicket lottoTicket : lottoTickets) {
            int countOfMatch = lottoTicket.getCountOfMatch(winningNumbers);
            Rank rank = Rank.valueOf(countOfMatch);

            ranks.merge(rank, 1, Integer::sum);
        }

        return ranks;
    }

    public List<LottoTicket> get() {
        return Collections.unmodifiableList(lottoTickets);
    }

    public int getPurchaseAmount() {
        return purchaseAmount;
    }

    public int size() {
        return lottoTickets.size();
    }

}
