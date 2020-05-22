import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Barry
 * @date 03.06.2020
 * */
public class BankAccountTest {

    BankAccount bankAccount = new BankAccount("b1");

    @Test
    public void getAccount() {
        assertEquals(bankAccount.getAccount(), "b1");
    }

    @Test
    public void setAccount() {
        bankAccount.setAccount("s2");
        assertEquals(bankAccount.getAccount(), "s2");
    }

    @Test
    public void getBalance() {
        bankAccount.deposit(10.5);
        assertEquals(10.5, bankAccount.getBalance(), 0.00000001);
    }

    @Test
    public void deposit() {
        Assert.assertTrue(bankAccount.deposit(10));
        Assert.assertFalse(bankAccount.deposit(-1));
        assertEquals(10, bankAccount.getBalance(),0.00000001);
    }

    @Test
    public void withDraw() {
        bankAccount.deposit(10);
        Assert.assertTrue(bankAccount.withDraw(5));
        Assert.assertFalse(bankAccount.withDraw(15));
        assertEquals(bankAccount.getBalance(), 5, 0.0000001);
    }

    @Test
    public void update() {
    }

    @Test
    public void testToString() {

    }
}