package lotto.domain;

import java.util.List;

public interface TicketGenerator {

    List<Ticket> generate(Money purchaseMoney);
}
