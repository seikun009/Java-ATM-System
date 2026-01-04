import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;

class AccountTest {
    @Test
    void 預入れテスト(){
        Account account = new Account("Seikun", "1234", new BigDecimal("1000"));
        account.deposit(new BigDecimal("500"));
        assertEquals(new BigDecimal("1500"), account.getBalance());
    }
    @Test
    void 残高不足テスト(){
        Account account = new Account("Seikun", "1234", new BigDecimal("1000"));
        assertThrows(InsufficientBalanceException.class, () ->{
            account.withdraw(new BigDecimal("2000"));
        });
    }
}