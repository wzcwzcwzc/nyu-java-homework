/**
 * @author Barry
 * @date 03.05.2020
 * */
public class SavingsAccount extends BankAccount{

    private double interest;
    private double balance;

    SavingsAccount(String account, double interest, double balance){
        super(account);
        this.interest = interest;
        this.balance = balance;
    }

    private void setBalance(double balance){
        this.balance = balance;
    }

    @Override
    public double getBalance(){
        return balance;
    }

    public double getInterest() {
        return interest;
    }

    public void setInterest(double interest) {
        this.interest = interest;
    }

    @Override
    public boolean deposit(double amount){
        if(amount >= 0){
            setBalance(getBalance() + amount);
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean withDraw(double amount){
        if(super.withDraw(amount)){
            if(getBalance() < amount){
                System.out.println("not enough money in your Saving account");
                return false;
            }else{
                setBalance(getBalance() - amount);
                return true;
            }
        }else{
            return false;
        }
    }

    /**
     * this method is default use (1 + interest) * balance
     * to calculate the total balance.
     *
     * It can be changed based on different banks.
     * */
    @Override
    public void update(){
        double balance = getBalance();
        balance = (1 + interest) * balance;
        setBalance(balance);
    }

    @Override
    public String toString(){
        return "Saving Account: " + getAccount() + " Balance: "
                + getBalance() + " interest: " + getInterest();
    }
}