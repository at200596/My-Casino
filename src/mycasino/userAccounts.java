
package mycasino;


public class userAccounts {
    private String name;
    private double balance;
    private int accountNumber;

    public userAccounts(String name, double balance, int accountNumber) {
        this.name = name;
        this.balance = balance;
        this.accountNumber = accountNumber;
    }

    @Override
    public String toString() {
        return "Account #" + accountNumber + ": " + name + " - " + "£" + balance;
    }
    
    

    public String getName() {
        return name;
    }

    public double getBalance() {
        return balance;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }
    
    
    
    
    
    
}
