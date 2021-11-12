public class BankDatabase {
    private Account[] accounts; // array of Accounts

    public BankDatabase() {
        accounts = new Account[4];     // just 4 accounts for testing
        accounts[0] = new Account(1234, 5432, 11000.0, 12000.0);
        accounts[1] = new Account(8765, 5678, 39000.0, 40000.0);  
        accounts[2] = new Account(1111, 1111, 17000.0, 18000.0);  
        accounts[3] = new Account(1212, 8989, 5000.0, 6000.0);  
    }
   
    private Account getAccount(int accountNumber) {
        for (Account currentAccount : accounts)
        {
            // return current account if match found
            if (currentAccount.getAccountNumber() == accountNumber)
                return currentAccount;
        } // end for

        return null;
    //      return null; // if no matching account was found, return null
    } 

    public boolean findNumberAccount(int userAccountNumber){
        Account userAccount = getAccount(userAccountNumber);

        // if account exists, return result of Account method validatePIN
        if (userAccount != null) {
            return true;
        }else {
            return false; // account number not found, so return false
        }
    }
    
    public boolean authenticateUser(int userAccountNumber, int userPIN) {
        // attempt to retrieve the account with the account number
        Account userAccount = getAccount(userAccountNumber);

        // if account exists, return result of Account method validatePIN
        if (userAccount != null) {
            return userAccount.validatePIN(userPIN);
        }
        else {
            return false; // account number not found, so return false
        }
    } 

    public double getAvailableBalance(int userAccountNumber) {
        return getAccount(userAccountNumber).getAvailableBalance();
    } 

    public double getTotalBalance(int userAccountNumber) {
        return getAccount(userAccountNumber).getTotalBalance();
    } 

    public void credit(int userAccountNumber, double amount) {
        getAccount(userAccountNumber).credit(amount);
    }

    public void debit(int userAccountNumber, double amount) {
        getAccount(userAccountNumber).debit(amount);
    } 
   
} 