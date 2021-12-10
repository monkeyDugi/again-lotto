package lotto.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class LottoTicket {

    public static final int LOTTO_NUMBERS_SIZE = 6;
    public static final int TICKET_PRICE = 1000;
    public static final int LOTTO_MINIMUM_NUMBER = 1;
    public static final int LOTTO_MAXIMUM_NUMBER = 45;

    private final Set<LottoNumber> lottoNumbers;

    public LottoTicket(Set<LottoNumber> lottoNumbers) {
        validateSize(lottoNumbers);
        this.lottoNumbers = lottoNumbers;
    }

    public int getCountOfMatch(List<LottoNumber> winningNumbers) {
        List<LottoNumber> copyWinningNumbers = new ArrayList<>(winningNumbers);
        copyWinningNumbers.retainAll(lottoNumbers);

        return copyWinningNumbers.size();
    }

    public boolean equalsMatchBonus(int bonusNumber) {
        return lottoNumbers.contains(new LottoNumber(bonusNumber));
    }

    private void validateSize(Set<LottoNumber> lottoNumbers) {
        if (lottoNumbers.size() != LOTTO_NUMBERS_SIZE) {
            throw new IllegalArgumentException("로또 번호는 6개만 가능합니다.");
        }
    }

    public int size() {
        return lottoNumbers.size();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LottoTicket that = (LottoTicket) o;
        return Objects.equals(lottoNumbers, that.lottoNumbers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lottoNumbers);
    }

    @Override
    public String toString() {
        return lottoNumbers.toString();
    }
}
