package lotto.UI;

import lotto.domain.LottoTicket;

import java.util.List;
import java.util.Scanner;

public class InputView {

    private static final int TICKET_PRICE = 1_000;

    private static final Scanner sc = new Scanner(System.in);

    public static int requirePurchaseAmount() {
        System.out.println("구입금액을 입력해 주세요");
        return sc.nextInt();
    }

    public static void confirmPurchaseCount(int purchaseAmount, List<LottoTicket> lottoTickets) {
        System.out.println(purchaseAmount / TICKET_PRICE + "개를 구매했습니다.");

        for (LottoTicket lottoTicket : lottoTickets) {
            System.out.println(lottoTicket);
        }
    }
}
