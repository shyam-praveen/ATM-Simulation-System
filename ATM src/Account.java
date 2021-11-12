public class Account {
    private int accountNumber; // account number
    private int pin; // PIN for authentication
    private double availableBalance; // funds available for withdrawal
    private double totalBalance; // funds available & pending deposits

    // Account constructor initializes attributes
    public Account(int theAccountNumber, int thePIN, 
        double theAvailableBalance, double theTotalBalance) {
        accountNumber = theAccountNumber;
        pin = thePIN;
        availableBalance = theAvailableBalance;
        totalBalance = theTotalBalance;   
    }

    // determines whether a user-specified PIN matches PIN in Account
   public boolean validatePIN(int userPIN) {
        if (userPIN == pin) {
          return true;
        }
        else {
          return false;
    }
    } 

    public double getAvailableBalance() {
        return availableBalance;
    } 

    public double getTotalBalance() {
        return totalBalance;
    } 

    public int getAccountNumber() {
        return accountNumber;  
    }

    public int getPin() {
        return pin;
    }

    public void setAvailableBalance(double availableBalance) {
        this.availableBalance = availableBalance;
    }

    public void setTotalBalance(double totalBalance) {
        this.totalBalance = totalBalance;
    }

    public void credit(double amount) {
        this.totalBalance -= amount;
        this.availableBalance -= amount;
    }

    public void debit(double amount) {
        this.totalBalance += amount;
        this.availableBalance += amount;
    }

} 