package lotto.UI;

import lotto.domain.Ticket;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {

//    private static final Scanner sc = new Scanner(System.in);

    public static int requirePurchaseAmount() {
        System.out.println("구입금액을 입력해 주세요");

        Scanner sc = new Scanner(System.in);
        return sc.nextInt();
    }

    public static int requirePurchaseManualTicketCount() {
        System.out.println();
        System.out.println("수동으로 구매할 로또 수를 입력해 주세요");
        Scanner sc = new Scanner(System.in);

        return sc.nextInt();
    }

    public static String requireManualLottoTickets(int manualTicketCount) {
        if (manualTicketCount > 0) {
            System.out.println();
            System.out.println("수동으로 구매할 번호를 입력해 주세요.");
        }

        StringBuilder sb = new StringBuilder();
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < manualTicketCount; i++) {
            sb.append(sc.nextLine());
            sb.append("\r\n");
        }
        return sb.toString();
    }

    public static Ticket requireWinningTicket() {
        System.out.println();
        System.out.println("지난 주 당첨 번호를 입력해 주세요");

        List<Integer> ticketNumbers = new ArrayList<>();
        Scanner sc = new Scanner(System.in); // 여기가 문제
        List<String> winningNumbers = Arrays.asList(sc.nextLine().split(","));
        for (String winningNumber : winningNumbers) {
            ticketNumbers.add(Integer.parseInt(winningNumber));
        }
        return Ticket.of(ticketNumbers);
    }

    public static int requireBonusNumber() {
        System.out.println();
        System.out.println("보너스 번호를 입력해 주세요.");

        Scanner sc = new Scanner(System.in);
        return sc.nextInt();
    }
}
