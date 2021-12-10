package lotto.domain;

import java.util.Objects;

public class LottoNumber {

    private static final int LOTTO_MINIMUM_NUMBER = 1;
    private static final int LOTTO_MAXIMUM_NUMBER = 45;

    private final int number;

    public LottoNumber(int number) {
        validateMinimumNumber(number);
        validateMaximumNumber(number);

        this.number = number;
    }

    private void validateMinimumNumber(int number) {
        if (LOTTO_MINIMUM_NUMBER > number) {
            throw new IllegalArgumentException("로또 번호는 1이상이어야 합니다.");
        }
    }

    private void validateMaximumNumber(int number) {
        if (LOTTO_MAXIMUM_NUMBER < number) {
            throw new IllegalArgumentException("로또 번호는 45이하이어야 합니다.");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LottoNumber that = (LottoNumber) o;
        return number == that.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }

    @Override
    public String toString() {
        return "" + number;
    }
}
