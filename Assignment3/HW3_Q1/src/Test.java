import java.util.ArrayList;
import java.util.List;
/**
 * @author Barry
 * @date 03.05.2020
 * */
public class Test {
    public static void main(String[] args) {
        // initialize the bank account list
        // the test of each method is in corresponding test file, such as BankAccountTest...etc.
        // use junit to do unit test
        List<BankAccount> bankAccounts = new ArrayList<>();
        SavingsAccount s1 = new SavingsAccount("s1", 0.2, 1000);
        SavingsAccount s2 = new SavingsAccount("s2", 0.3, 2000);
        CurrentAccount c1 = new CurrentAccount("c1", 100, 0);
        CurrentAccount c2 = new CurrentAccount("c2", 200, 2000);

        bankAccounts.add(s1);
        bankAccounts.add(s2);
        bankAccounts.add(c1);
        bankAccounts.add(c2);

        Bank bank = new Bank(bankAccounts, 100);
        bank.setDividend(10.5);
        System.out.println(c1.getBalance());
        c1.withDraw(10);
        System.out.println(c1.getBalance());
        System.out.println(s1.getBalance() + " " + s1.getAccount() + " ");
        System.out.println(c1.getBalance() + " " + c1.getAccount() + " ");
        bank.update(bankAccounts);
        bank.pay(bank.getDividend());
        System.out.println(s1.getBalance() + " " + s1.getAccount() + " ");
        System.out.println(c1.getBalance() + " " + c1.getAccount() + " ");
    }
}
