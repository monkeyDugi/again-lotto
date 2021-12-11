package lotto.domain;

import java.util.Collections;
import java.util.List;

public class ManualLottoTickets {

    private final List<LottoTicket> manualLottoTickets;

    public ManualLottoTickets(List<LottoTicket> manualLottoTickets) {
        this.manualLottoTickets = manualLottoTickets;
    }

    public List<LottoTicket> get() {
        return Collections.unmodifiableList(manualLottoTickets);
    }

    public int size() {
        return manualLottoTickets.size();
    }
}
