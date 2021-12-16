package lotto.generator;

import lotto.domain.LottoGenerator;
import lotto.domain.Money;
import lotto.domain.Ticket;
import lotto.domain.TicketNumber;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AutoLottoGenerator implements LottoGenerator {

    @Override
    public List<Ticket> generate(Money money) {
        List<Ticket> tickets = new ArrayList<>();
        int countOfBuyingTicket = money.calculateCountOfBuyingTicket();
        for (int i = 0; i < countOfBuyingTicket; i++) {
            tickets.add(generateAuto());
        }
        return tickets;
    }

    private static Ticket generateAuto() {
        List<Integer> seed = createSeed();
        Collections.shuffle(seed);
        return Ticket.of(seed.subList(0, 6));
    }

    private static List<Integer> createSeed() {
        List<Integer> seed = new ArrayList<>();
        for (int i = TicketNumber.MINIMUM_NUMBER; i < TicketNumber.MAXIMUM_NUMBER; i++) {
            seed.add(i);
        }
        return seed;
    }
}
