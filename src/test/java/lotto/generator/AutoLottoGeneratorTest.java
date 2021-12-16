package lotto.generator;

import lotto.domain.LottoGenerator;
import lotto.domain.Money;
import lotto.domain.Ticket;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

class AutoLottoGeneratorTest {

    @DisplayName("금액에 따른 티켓 구매")
    @Test
    void 자동_티켓_생성() {
        Money purchaseAmount = new Money(10000);
        LottoGenerator lottoGenerator = new AutoLottoGenerator();

        // when
        List<Ticket> tickets = lottoGenerator.generate(purchaseAmount);

        // then
        Assertions.assertThat(tickets.size()).isEqualTo(10);
    }
}