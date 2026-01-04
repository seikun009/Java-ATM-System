import java.math.BigDecimal;

public class InsufficientBalanceException extends Exception {
    private BigDecimal currentBalance;

    public InsufficientBalanceException(BigDecimal balance, String message){
        super(message);
        this.currentBalance = balance;
    }
    public BigDecimal getCurrentBalance(){
        return currentBalance;
    }
}
