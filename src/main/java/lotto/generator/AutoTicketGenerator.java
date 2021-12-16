package lotto.generator;

import lotto.domain.Money;
import lotto.domain.Ticket;
import lotto.domain.TicketGenerator;
import lotto.domain.TicketNumber;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AutoTicketGenerator implements TicketGenerator {

    @Override
    public List<Ticket> generate(Money purchaseMoney) {
        List<Ticket> tickets = new ArrayList<>();
        int countOfBuyingTicket = purchaseMoney.calculateCountOfBuyingTicket();
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
