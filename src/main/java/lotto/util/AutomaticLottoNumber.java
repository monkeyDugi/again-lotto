package lotto.util;

import lotto.domain.LottoNumber;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AutomaticLottoNumber {

    private static final int TO_NUMBER = 45;
    private static final int FROM_NUMBER = 1;
    private static final int LOTTO_NUMBERS_SIZE = 6;

    private static final List<LottoNumber> numbers = new ArrayList<>();

    private AutomaticLottoNumber() {}

    static {
        createTotalNumbers();
    }

    private static void createTotalNumbers() {
        for (int i = FROM_NUMBER; i <= TO_NUMBER; i++) {
            numbers.add(new LottoNumber(i));
        }
    }

    public static List<LottoNumber> createNumbers() {
        Collections.shuffle(numbers);

        List<LottoNumber> lottoNumbers = new ArrayList<>();
        for (int i = 0; i < LOTTO_NUMBERS_SIZE; i++) {
            lottoNumbers.add(numbers.get(i));
        }

        return lottoNumbers;
    }
}
