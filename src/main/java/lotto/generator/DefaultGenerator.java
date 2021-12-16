package lotto.generator;

import lotto.domain.Money;
import lotto.domain.Ticket;
import lotto.domain.TicketGenerator;

import java.util.ArrayList;
import java.util.List;

public class DefaultGenerator implements TicketGenerator {

    private final TicketGenerator autoTicketGenerator = new AutoTicketGenerator();
    private final String inputManualTickets;
    private final int manualTicketCount;

    public DefaultGenerator(String inputManualTickets, int manualTicketCount) {
        this.inputManualTickets = inputManualTickets;
        this.manualTicketCount = manualTicketCount;
    }

    @Override
    public List<Ticket> generate(Money purchaseMoney) {
        List<Ticket> manualTickets = generateManualTickets();
        Money autoMoney = purchaseMoney.buyManual(manualTickets.size());
        List<Ticket> autoTickets = autoTicketGenerator.generate(autoMoney);
        manualTickets.addAll(autoTickets);
        return manualTickets;
    }

    private List<Ticket> generateManualTickets() {
        List<Ticket> manualTickets = new ArrayList<>();

        if ("".equals(inputManualTickets) || inputManualTickets == null) {
            return manualTickets;
        }

        String[] split = inputManualTickets.split("\r?\n");
        for (String s : split) {
            manualTickets.add(Ticket.ofComma(s));
        }
        validate(manualTickets);
        return manualTickets;
    }

    private void validate(List<Ticket> manualTickets) {
        if (manualTickets.size() != manualTicketCount) {
            throw new IllegalArgumentException("지불하신 금액보다 티켓 장수가 적거나 많습니다.");
        }
    }
}
