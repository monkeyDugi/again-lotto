package lotto.domain;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LottoTicket {

    private static final int LOTTO_NUMBERS_SIZE = 6;
    private static final int LOTTO_MINIMUM_NUMBER = 1;
    private static final int LOTTO_MAXIMUM_NUMBER = 45;

    private List<Integer> lottoNumbers;

    public LottoTicket(List<Integer> lottoNumbers) {
        validateSize(lottoNumbers);
        validateDuplicate(lottoNumbers);
        validateMinimumNumber(lottoNumbers);
        validateMaximumNumber(lottoNumbers);

        this.lottoNumbers = lottoNumbers;
    }

    private void validateSize(List<Integer> lottoNumbers) {
        if (lottoNumbers.size() != LOTTO_NUMBERS_SIZE) {
            throw new IllegalArgumentException("로또 번호는 6개만 가능합니다.");
        }
    }

    private void validateDuplicate(List<Integer> lottoNumbers) {
        Set<Integer> nonDuplicateNumbers = new HashSet<>(lottoNumbers);

        if (nonDuplicateNumbers.size() != LOTTO_NUMBERS_SIZE) {
            throw new IllegalArgumentException("로또 번호들은 중복될 수 없습니다.");
        }
    }

    private void validateMinimumNumber(List<Integer> lottoNumbers) {
        int minNumber = Collections.min(lottoNumbers);
        if (minNumber < LOTTO_MINIMUM_NUMBER) {
            throw new IllegalArgumentException("로또 번호는 1이상이어야 합니다.");
        }
    }

    private void validateMaximumNumber(List<Integer> lottoNumbers) {
        int maxNumber = Collections.max(lottoNumbers);
        if (maxNumber > LOTTO_MAXIMUM_NUMBER) {
            throw new IllegalArgumentException("로또 번호는 45이하이어야 합니다.");
        }
    }
}
