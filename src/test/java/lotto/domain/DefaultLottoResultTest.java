package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DefaultLottoResultTest {

    @DisplayName("")
    @Test
    void 총_당첨금_반환() {
        // given
        LottoResult lottoResult = new LottoResult();
        lottoResult.putRank(Rank.FIFTH);
        lottoResult.putRank(Rank.FIFTH);
        lottoResult.putRank(Rank.FIFTH);

        // when
        Money totalPrize = lottoResult.getPrize();

        // then
        assertThat(totalPrize).isEqualTo(new Money(15_000));
    }
}