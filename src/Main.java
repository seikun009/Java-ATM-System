import java.math.BigDecimal;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Account myAccount = new Account("Seikun", "1234", new BigDecimal("10000"));
        Scanner scanner = new Scanner(System.in);

        System.out.println("---ATMシステム起動---");
        int missCount = 0;
        boolean loggedIn = false;

        while (missCount < 3 && !loggedIn) {
            System.out.print("暗証番号を入力してください：");
            String inputPin = scanner.next();

            if (myAccount.authenticate(inputPin)) {
                loggedIn = true;
            } else {
                missCount++;

                    System.out.println("暗証番号が違います。あと" + (3 - missCount) + "回試せます。");
            }
        }
        if(!loggedIn) {
            System.out.println("3回間違えたため、口座をロックしました。窓口へお越しください。");
            scanner.close();
            return;
        }
        System.out.println("承認成功！ようこそ" + myAccount.getName() + "様");
        boolean running = true;
        while (running) {
            System.out.println("\n--------------------");
            System.out.println("1:残高照会|2:預け入れ|3:引き出し|4:取引履歴|5:終了");
            System.out.println("操作を選んでください（1-4）：");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1 -> {
                    System.out.println("現在の残高は" + myAccount.getBalance() + "円です。");
                }
                case 2 -> {
                    System.out.println("預入金額を入力してください：");
                    BigDecimal amount = scanner.nextBigDecimal();
                    myAccount.deposit(amount);
                }
                case 3 -> {
                    System.out.println("引き出し金額を入力してください：");
                    BigDecimal amount = scanner.nextBigDecimal();
                    try{
                        myAccount.withdraw(amount);
                    } catch (InsufficientBalanceException e) {
                        System.out.println("【残高不足エラー】" + e.getMessage());
                        System.out.println("現在の残高は" + e.getCurrentBalance() + "円です。");
                    }
                    catch(Exception e){
                        System.out.println(e.getMessage());
                    }

                }
                case 4 -> {
                    myAccount.printTransactionHistory();
                }
                case 5 ->{
                    System.out.println("ご利用ありがとうございました。");
                    running = false;
                }
                default -> System.out.println("1から5の番号を入力してください。");
            }
        }
        scanner.close();
    }
}