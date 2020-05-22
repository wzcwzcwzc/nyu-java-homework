import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Barry
 * @date 03.06.2020
 * */
public class CurrentAccountTest {

    public static final double DELTA = 0.0000001;

    CurrentAccount currentAccount = new CurrentAccount("c1", 500, 100);

    @Test
    public void deposit() {
        Assert.assertTrue(currentAccount.deposit(100));
        Assert.assertFalse(currentAccount.deposit(-100));
        Assert.assertEquals(700, currentAccount.getBalance(), DELTA);
    }

    @Test
    public void withDraw() {
        currentAccount.deposit(100);
        Assert.assertTrue(currentAccount.withDraw(100));
        Assert.assertFalse(currentAccount.withDraw(-100));
        Assert.assertEquals(600, currentAccount.getBalance(), DELTA);
    }

    @Test
    public void getBalance() {
        Assert.assertEquals(600, currentAccount.getBalance(), DELTA);
        currentAccount.deposit(100);
        Assert.assertEquals(700, currentAccount.getBalance(), DELTA);
    }

    @Test
    public void getOverLimit() {
        Assert.assertEquals(500, currentAccount.getOverLimit(), DELTA);
        currentAccount.setOverLimit(600);
        Assert.assertEquals(600, currentAccount.getOverLimit(), DELTA);
    }

    @Test
    public void setOverLimit() {
        currentAccount.setOverLimit(800);
        Assert.assertEquals(800, currentAccount.getOverLimit(), DELTA);
    }

    @Test
    public void update() {
        currentAccount.withDraw(800);
        Assert.assertEquals(-200, currentAccount.getBalance(), DELTA);
    }

    @Test
    public void testToString() {
    }
}