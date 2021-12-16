package lotto.domain;

import java.util.Objects;

public class TicketNumber {

    public static final int MINIMUM_NUMBER = 1;
    public static final int MAXIMUM_NUMBER = 45;

    private final int number;

    private TicketNumber(int number) {
        validate(number);
        this.number = number;
    }

    public static TicketNumber of(int number) {
        return new TicketNumber(number);
    }

    private void validate(int number) {
        if (number < MINIMUM_NUMBER || number > MAXIMUM_NUMBER) {
            throw new IllegalArgumentException("유효 범위를 벗어난 번호입니다.(" + number + ")");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TicketNumber that = (TicketNumber) o;
        return number == that.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }

    @Override
    public String toString() {
        return number + "";
    }
}
