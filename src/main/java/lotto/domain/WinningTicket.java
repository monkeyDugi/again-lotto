package lotto.domain;

import java.util.Objects;

public class WinningTicket {

    private final Ticket winningTicket;
    private final TicketNumber bonusNumber;

    public WinningTicket(Ticket winningTicket, int bonusNumber) {
        this.bonusNumber = TicketNumber.of(bonusNumber);
        if (winningTicket.contains(this.bonusNumber)) {
            throw new IllegalArgumentException("당첨 번호와 보너스 번호는 중복될 수 없습니다.");
        }
        this.winningTicket = winningTicket;
    }

    public Rank match(Ticket userTicket) {
        int matchCount = winningTicket.match(userTicket);
        boolean matchBonus = userTicket.contains(bonusNumber);
        return Rank.valueOf(matchCount, matchBonus);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WinningTicket that = (WinningTicket) o;
        return Objects.equals(winningTicket, that.winningTicket) && Objects.equals(bonusNumber, that.bonusNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(winningTicket, bonusNumber);
    }
}
