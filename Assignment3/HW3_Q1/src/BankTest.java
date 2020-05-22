import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
/**
 * @author Barry
 * @date 03.05.2020
 * */
public class BankTest {

    public static final double DELTA = 0.0000001;

    SavingsAccount s1 = new SavingsAccount("s1", 0.2, 1000);
    SavingsAccount s2 = new SavingsAccount("s2", 0.3, 2000);
    CurrentAccount c1 = new CurrentAccount("c1", 100, 0);
    CurrentAccount c2 = new CurrentAccount("c2", 200, 2000);
    List<BankAccount> bankAccounts = new ArrayList<>();
    Bank bank = new Bank(bankAccounts, 0.02);

    @Test
    public void getBankAccounts() {
    }

    @Test
    public void setBankAccounts() {
        bankAccounts.add(c2);
        bankAccounts.add(s1);
        bankAccounts.add(s2);
        bankAccounts.add(c1);
    }

    @Test
    public void open() {
        SavingsAccount newSaving = new SavingsAccount("s3", 0.01, 100);
        bank.open(newSaving);
        boolean flag = false;
        for(int i = 0; i < bankAccounts.size(); i++){
            if(newSaving.equals(bankAccounts.get(i))){
                flag = true;
            }
        }
        System.out.println(flag);
    }

    @Test
    public void close() {
        bankAccounts.add(s2);
        for(int i = 0; i < bankAccounts.size(); i++){
            if(s2.equals(bankAccounts.get(i))){
                System.out.println("true");
            }
        }

        bank.close(s2);
        boolean flag = false;
        for(int i = 0; i < bankAccounts.size(); i++){
            if(s2.equals(bankAccounts.get(i))){
                flag = true;
            }
        }
        System.out.println(flag);
    }

    @Test
    public void getDividend() {
        Assert.assertEquals(bank.getDividend(), 0.02, DELTA);
    }

    @Test
    public void setDividend() {
        bank.setDividend(0.01);
        Assert.assertEquals(bank.getDividend(), 0.01, DELTA);
    }

    @Test
    public void update() {
        bankAccounts.add(s1);
        bankAccounts.add(s2);
        bankAccounts.add(c1);
        bankAccounts.add(c2);

        // create balance < 0 in c1
        c1.withDraw(102);

        bank.update(bankAccounts);
        // print send letter in c1
        Assert.assertEquals(s1.getBalance(), 1200, DELTA);
        Assert.assertEquals(s2.getBalance(), 2600, DELTA);
    }

    @Test
    public void pay() {
        bankAccounts.add(c2);
        bankAccounts.add(s1);
        bankAccounts.add(s2);
        bankAccounts.add(c1);
        bank.pay(100);
        Assert.assertEquals(s1.getBalance(), 1100, DELTA);
        Assert.assertEquals(s2.getBalance(), 2100, DELTA);
        c1.withDraw(50);
        Assert.assertEquals(c1.getBalance(), 150, DELTA);
        Assert.assertEquals(c2.getBalance(), 2300, DELTA);
    }

    @Test
    public void testToString() {
    }
}