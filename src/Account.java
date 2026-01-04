import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Account {
    private String accountHolder;
    private String pin;
    private BigDecimal balance;
    private List<Transaction> transactions = new ArrayList<>();

    public Account(String accountHolder, String pin, BigDecimal initialBalance) {
        this.accountHolder = accountHolder;
        this.pin = pin;
        this.balance = initialBalance;
    }

    public BigDecimal getBalance() {
        return this.balance;
    }

    public boolean authenticate(String inputPin) {
        return this.pin.equals(inputPin);
    }

    public String getName() {
        return accountHolder;
    }

    public void deposit(BigDecimal amount) {
        if (amount != null && amount.compareTo(BigDecimal.ZERO) > 0) {
            this.balance = this.balance.add(amount);
            this.transactions.add(new Transaction("預入れ", amount));
            System.out.println(amount + "円を預け入れました。");
        }
    }

    public void withdraw(BigDecimal amount) throws InsufficientBalanceException { //呼び出し側にtry-catchを強制できる
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("引き出し額は0より大きい必要があります。");
        }
        if (this.balance.compareTo(amount) < 0) {
            throw new InsufficientBalanceException(this.balance, "残高が不足しています。必要額：" + amount + "円");
        }
        this.balance = this.balance.subtract(amount);
        this.transactions.add(new Transaction("引き出し", amount));
        System.out.println(amount + "円を引き出しました。");
    }

    public void printTransactionHistory(){
        System.out.println("\n===== 取引履歴 =====");
        if(transactions.isEmpty()){
            System.out.println("取引履歴はありません。");
        }else{
            for(Transaction t : transactions){
                System.out.println(t.getDetails());
            }
        }
        System.out.println("=====================");
    }
}
