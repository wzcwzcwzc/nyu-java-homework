import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;
/**
 * @author Barry
 * @date 03.06.2020
 * */
public class SavingsAccountTest {
    SavingsAccount savingsAccount = new SavingsAccount("s1", 0.05, 1000);

    @Test
    public void getBalance() {
        Assert.assertEquals(1000, savingsAccount.getBalance(), 0.0000001);
    }

    @Test
    public void getInterest() {
        Assert.assertEquals(0.05, savingsAccount.getInterest(), 0.000001);
    }

    @Test
    public void setInterest() {
        savingsAccount.setInterest(0.01);
        Assert.assertEquals(0.01, savingsAccount.getInterest(), 0.0000001);
    }

    @Test
    public void deposit() {
        Assert.assertTrue(savingsAccount.deposit(500));
        Assert.assertEquals(1500, savingsAccount.getBalance(), 0.0000001);
        Assert.assertFalse(savingsAccount.deposit(-200));
    }

    @Test
    public void withDraw() {
        savingsAccount.deposit(200);
        Assert.assertTrue(savingsAccount.withDraw(1190));
        Assert.assertFalse(savingsAccount.withDraw(-10));
        Assert.assertFalse(savingsAccount.withDraw(1210));
    }

    @Test
    public void update() {
        savingsAccount.update();
        Assert.assertEquals(1050, savingsAccount.getBalance(), 0.000001);
    }

    @Test
    public void testToString() {
    }
}