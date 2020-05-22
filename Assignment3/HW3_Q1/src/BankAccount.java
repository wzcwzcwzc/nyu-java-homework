/**
 * @author Barry
 * @date 03.05.2020
 * */
public class BankAccount {

    private String account;
    private double balance;

    BankAccount(String account){
        this.account = account;
        this.balance = 0;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public double getBalance() {
        return balance;
    }

    private void setBalance(double balance) {
        this.balance = balance;
    }

    public boolean deposit(double amount){
        if(amount < 0){
            System.out.println("deposit number can not be negative");
            return false;
        }else{
            setBalance(amount + getBalance());
            return true;
        }
    }

    public boolean withDraw(double amount){
        if(amount < 0){
            System.out.println("withDraw number can not be negative");
            return false;
        }else{
            if(getBalance() - amount < 0){
                System.out.println("cannot withdraw because the money is not enough");
                return false;
            }else{
                setBalance(getBalance() - amount);
                return true;
            }
        }
    }

    // empty method, need to override

    public void update(){
        System.out.println("need override depend on specific account");
    }

    @Override
    public String toString(){
        return "account number: " + account + " , balance: " + balance;
    }
}