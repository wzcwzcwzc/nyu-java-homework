/**
 * @author Barry
 * @date 03.05.2020
 * */
public class CurrentAccount extends BankAccount{

    private double overLimit;
    private double balance;

    CurrentAccount(String account, double overLimit, double balance) {
        super(account);
        this.overLimit = overLimit;
        this.balance = balance + overLimit;
    }

    private void setBalance(double balance){
        this.balance = balance;
    }

    @Override
    public boolean deposit(double amount){
        if(amount < 0){
            return false;
        }else{
            setBalance(getBalance() + amount);
            return true;
        }
    }

    @Override
    public boolean withDraw(double amount){
        if(amount < 0){
            return false;
        }else{
            setBalance(getBalance() - amount);
            return true;
        }
    }

    @Override
    public double getBalance(){
        return balance;
    }

    public double getOverLimit() {
        return overLimit;
    }

    public void setOverLimit(double overLimit) {
        this.overLimit = overLimit;
    }

    @Override
    public void update(){
        if(getBalance() < 0){
            System.out.println("letter of over draft of " + getAccount());
        }
    }

    @Override
    public String toString(){
        return "Current Account: " + getAccount() + " Over Limit: " + getOverLimit();
    }
}