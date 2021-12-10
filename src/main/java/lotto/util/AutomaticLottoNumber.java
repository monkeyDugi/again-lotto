package lotto.util;

import lotto.domain.LottoNumber;
import lotto.domain.LottoTicket;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class AutomaticLottoNumber {

    private static final List<LottoNumber> numbers = new ArrayList<>();

    private AutomaticLottoNumber() {}

    static {
        createTotalNumbers();
    }

    public static Set<LottoNumber> createNumbers() {
        Collections.shuffle(numbers);

        Set<LottoNumber> lottoNumbers = new HashSet<>();
        Iterator<LottoNumber> iterator = numbers.iterator();
        while (lottoNumbers.size() < LottoTicket.LOTTO_NUMBERS_SIZE) {
            lottoNumbers.add(iterator.next());
        }

        return lottoNumbers;
    }

    private static void createTotalNumbers() {
        for (int i = LottoTicket.LOTTO_MINIMUM_NUMBER; i <= LottoTicket.LOTTO_MAXIMUM_NUMBER; i++) {
            numbers.add(new LottoNumber(i));
        }
    }
}
