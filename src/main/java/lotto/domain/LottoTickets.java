package lotto.domain;

import lotto.util.AutomaticLottoNumber;

import java.util.ArrayList;
import java.util.List;

public class LottoTickets {

    private static final int TICKET_PRICE = 1000;

    private final List<LottoTicket> lottoTickets = new ArrayList<>();

    public LottoTickets(int price) {
        validatePrice(price);
        createLottoTickets(price);
    }

    private void validatePrice(int price) {
        if (price < TICKET_PRICE) {
            throw new IllegalArgumentException("최소 구매 금액은 " + TICKET_PRICE + "원 입니다.");
        }

        if (price % TICKET_PRICE != 0) {
            throw new IllegalArgumentException("구매 금액은 " + TICKET_PRICE + "원 단위만 가능합니다.");
        }
    }

    private void createLottoTickets(int price) {
        int ticketCount = price / TICKET_PRICE;

        for (int i = 0; i < ticketCount; i++) {
            lottoTickets.add(new LottoTicket(AutomaticLottoNumber.createNumbers()));
        }
    }

    public int size() {
        return lottoTickets.size();
    }
}