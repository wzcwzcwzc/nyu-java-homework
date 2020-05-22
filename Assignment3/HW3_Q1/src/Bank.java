import java.util.List;

/**
 * @author Barry
 * @date 03.05.2020
 * */
public class Bank {

    private List<BankAccount> bankAccounts;
    private double dividend;

    Bank(List<BankAccount> bankAccounts, double dividend){
        this.bankAccounts = bankAccounts;
        this.dividend = dividend;
    }

    public List<BankAccount> getBankAccounts() {
        return bankAccounts;
    }

    public void setBankAccounts(List<BankAccount> bankAccounts) {
        this.bankAccounts = bankAccounts;
    }

    public void open(BankAccount bankAccount){
        bankAccounts.add(bankAccount);
    }

    public void close(BankAccount bankAccount){
        if(bankAccounts.size() == 0){
            System.out.println("there is no bank account can close");
            return;
        }

        for(int i = 0; i < bankAccounts.size(); i++){
            if(bankAccount.getAccount().equals(bankAccounts.get(i).getAccount())){
                bankAccounts.remove(i);
            }
        }
    }

    public double getDividend() {
        return dividend;
    }

    public void setDividend(double dividend) {
        this.dividend = dividend;
    }

    public void update(List<BankAccount> bankAccounts){
        for(BankAccount bankAccount : bankAccounts){
            bankAccount.update();
        }
    }

    public void pay(double dividend){
        for(BankAccount bankAccount : bankAccounts){
            bankAccount.deposit(dividend);
        }
    }

    @Override
    public String toString(){
        return "the number of bank accounts: " + bankAccounts.size() + " the dividend is: " + getDividend();
    }
}