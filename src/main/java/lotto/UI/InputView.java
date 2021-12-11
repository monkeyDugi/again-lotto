package lotto.UI;

import lotto.domain.LottoNumber;
import lotto.domain.LottoTicket;
import lotto.domain.ManualLottoTickets;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class InputView {

    public static int requirePurchaseAmount() {
        System.out.println("구입금액을 입력해 주세요");
        Scanner sc = new Scanner(System.in);
        return sc.nextInt();
    }

    public static int requireManualLottoTicketCount() {
        System.out.println();
        System.out.println("수동으로 구매할 로또 수를 입력해 주세요");
        Scanner sc = new Scanner(System.in);

        return sc.nextInt();
    }

    public static ManualLottoTickets requireManualLottoTickets(int manualLottoTicketCount) {
        System.out.println();
        System.out.println("수동으로 구매할 번호를 입력해 주세요.");

        List<LottoTicket> manualLottoTickets = new ArrayList<>();
        for (int i = 0; i < manualLottoTicketCount; i++) {
            Scanner sc = new Scanner(System.in);
            String[] inputManualLottoNumbers = sc.nextLine().split(",");
            LottoTicket manualLottoTicket = new LottoTicket(Arrays.asList(inputManualLottoNumbers));
            manualLottoTickets.add(manualLottoTicket);
        }

        return new ManualLottoTickets(manualLottoTickets);
    }

    public static Set<LottoNumber> requireWinningNumber() {
        System.out.println();
        System.out.println("지난 주 당첨 번호를 입력해 주세요");
        Scanner sc = new Scanner(System.in);

        Set<LottoNumber> winningNumbers = new HashSet<>();
        String[] winningNumberArr = sc.nextLine().split(",");
        for (String winningNumber : winningNumberArr) {
            winningNumbers.add(new LottoNumber(Integer.parseInt(winningNumber)));
        }

        return winningNumbers;
    }

    public static int requireBonusNumber() {
        System.out.println();
        System.out.println("보너스 번호를 입력해 주세요.");
        Scanner sc = new Scanner(System.in);
        return sc.nextInt();
    }
}
