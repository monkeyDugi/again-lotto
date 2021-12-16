package lotto.domain;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class Ticket {

    public static final int TICKET_SIZE = 6;

    private final Set<TicketNumber> ticket;

    private Ticket(Set<TicketNumber> ticket) {
        validate(ticket);
        this.ticket = ticket;
    }

    public static Ticket of(List<Integer> numbers) {
        Set<TicketNumber> ticket = new HashSet<>();
        for (Integer number : numbers) {
            ticket.add(TicketNumber.of(number));
        }
        return new Ticket(ticket);
    }

    public int match(Ticket target) {
        int count = 0;
        for (TicketNumber ticketNumber : ticket) {
            count += target.increment(ticketNumber);
        }
        return count;
    }

    public boolean contains(TicketNumber ticketNumber) {
        return ticket.contains(ticketNumber);
    }

    private int increment(TicketNumber ticketNumber) {
        if (contains(ticketNumber)) {
            return 1;
        }
        return 0;
    }

    private void validate(Set<TicketNumber> ticket) {
        if (ticket.size() != TICKET_SIZE) {
            throw new IllegalArgumentException("티켓 번호는 중복될 수 없으며 6개이어야 합니다.");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket1 = (Ticket) o;
        return Objects.equals(ticket, ticket1.ticket);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ticket);
    }
}
