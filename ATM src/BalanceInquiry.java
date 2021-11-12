public class BalanceInquiry extends Transaction implements Runnable{
   // BalanceInquiry constructor
    public BalanceInquiry(int userAccountNumber, Screen atmScreen, 
        BankDatabase atmBankDatabase) {
        super(userAccountNumber, atmScreen, atmBankDatabase);
    } 

   public void run() {
      synchronized(this){
         this.execute();
      }
   }
   // performs the transaction
    @Override
    public void execute() {
        // get references to bank database and screen
        BankDatabase bankDatabase = getBankDatabase();
        Screen screen = getScreen();

        // get the available balance for the account involved
        double availableBalance = 
           bankDatabase.getAvailableBalance(getAccountNumber());

        // get the total balance for the account involved
        double totalBalance = 
           bankDatabase.getTotalBalance(getAccountNumber());

        // display the balance information on the screen
        screen.displayMessageLine("\nBalance Information:");
        try
        {
            Thread.sleep(3000);
        }catch(Exception e)
        {
           System.out.println(e);
        }
        screen.displayMessage(" - Available balance: "); 
        screen.displayRupeeAmount(availableBalance);
        screen.displayMessage("\n - Total balance:     ");
        screen.displayRupeeAmount(totalBalance);
        screen.displayMessageLine("");
        try
        {
            Thread.sleep(2000);
        }catch(Exception e)
        {
           System.out.println(e);
        }
   }
} 