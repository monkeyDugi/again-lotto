package lotto.domain;

import lotto.util.AutomaticLottoNumber;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class LottoTickets {

    private static final int DEFAULT_MANUAL_LOTTO_TICKET_COUNT = 0;

    private List<LottoTicket> lottoTickets = new ArrayList<>();
    private final int purchaseAmount;

    public LottoTickets(int purchaseAmount) {
        validatePrice(purchaseAmount);
        createAutoLottoTickets(purchaseAmount, DEFAULT_MANUAL_LOTTO_TICKET_COUNT);

        this.purchaseAmount = purchaseAmount;
    }

    // 테스트 용도
    public LottoTickets(List<LottoTicket> lottoTickets, int purchaseAmount) {
        validatePrice(purchaseAmount);

        this.lottoTickets = lottoTickets;
        this.purchaseAmount = purchaseAmount;
    }

    public LottoTickets(ManualLottoTickets manualLottoTickets, int purchaseAmount) {
        validatePrice(purchaseAmount);
        createManualLottoTickets(purchaseAmount, manualLottoTickets);

        this.purchaseAmount = purchaseAmount;
    }

    public Map<Rank, Integer> getByRankCountOfMatches(LottoTicket winningNumbers, LottoNumber bonusNumber) {
        Map<Rank, Integer> ranks = Rank.getDefaultRanks();
        for (LottoTicket lottoTicket : lottoTickets) {
            boolean isBonusNumber = lottoTicket.equalsMatchBonus(bonusNumber);
            int countOfMatch = lottoTicket.getCountOfMatch(winningNumbers);

            Rank rank = Rank.valueOf(countOfMatch, isBonusNumber);
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

    private void validatePrice(int purchaseAmount) {
        if (purchaseAmount < LottoTicket.TICKET_PRICE) {
            throw new IllegalArgumentException("최소 구매 금액은 " + LottoTicket.TICKET_PRICE + "원 입니다.");
        }

        if (purchaseAmount % LottoTicket.TICKET_PRICE != 0) {
            throw new IllegalArgumentException("구매 금액은 " + LottoTicket.TICKET_PRICE + "원 단위만 가능합니다.");
        }
    }

    private void createAutoLottoTickets(int purchaseAmount, int manualLottoTicketCount) {
        int ticketCount = purchaseAmount / LottoTicket.TICKET_PRICE - manualLottoTicketCount;

        for (int i = 0; i < ticketCount; i++) {
            lottoTickets.add(new LottoTicket(AutomaticLottoNumber.createNumbers()));
        }
    }

    private void createManualLottoTickets(int purchaseAmount, ManualLottoTickets manualLottoTickets) {
        int manualLottoTicketCount = manualLottoTickets.size();
        createAutoLottoTickets(purchaseAmount, manualLottoTickets.size());

        if (manualLottoTicketCount > 0) {
            lottoTickets.addAll(manualLottoTickets.get());
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LottoTickets that = (LottoTickets) o;
        return getPurchaseAmount() == that.getPurchaseAmount() && Objects.equals(lottoTickets, that.lottoTickets);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lottoTickets, getPurchaseAmount());
    }
}
