package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MoneyTest {

    @Test
    void 구매금액에_대한_티켓_개수() {
        // given
        int purchaseAmount = 10000;

        // when
        Money money = new Money(purchaseAmount);

        // then
        assertThat(money.calculateCountOfBuyingTicket()).isEqualTo(10);
    }
    
    @DisplayName("구매금액과 자동 티켓의 개수를 제외한 것이 수동 티켓이다.")
    @Test
    void 수동_티켓_금액_생성() {
        // given
        Money money = new Money(10000);
        int autoCountOfBuyingTicket = 3;
        
        // when
        Money manualMoney = money.buyManual(autoCountOfBuyingTicket);
        
        // then
        assertThat(manualMoney).isEqualTo(new Money(7000));
    }

    @DisplayName("순위별 당첨금 합산을 위해 이전 당첨금 + 새로운 당첨금을 합산한다.")
    @Test
    void 당첨금_총합() {
        // given
        Money prePrizeMoney = new Money(10000); // 이전 당첨금
        Money newPrizeMoney = new Money(5000); // 신규 당첨금

        // when
        Money totalMoney = prePrizeMoney.sum(newPrizeMoney);

        // then
        assertThat(totalMoney).isEqualTo(new Money(15000));
    }

    @Test
    void 수익률_계산() {
        // given
        Money prize = new Money(2000);
        Money purchaseMoney = new Money(10000);

        // when
        double profitRate = purchaseMoney.calculateProfitRate(prize);

        // then
        assertThat(profitRate).isEqualTo(20);
    }
}