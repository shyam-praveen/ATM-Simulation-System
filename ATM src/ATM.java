
public class ATM{
    private boolean userAuthenticated; // whether user is authenticated
    private int currentAccountNumber; // current user's account number
    private Screen screen; // ATM's screen
    private Keypad keypad; // ATM's keypad
    private CashDispenser cashDispenser; // ATM's cash dispenser
    public  DepositSlot atmDepositSlot; //ATM's deposit slot //own//
    private BankDatabase bankDatabase; // account information database

    // constants corresponding to main menu options
    private static final int BALANCE_INQUIRY = 1;
    private static final int WITHDRAWAL = 2;
    private static final int DEPOSIT = 3;
    private static final int TRANSFER = 4;
    private static final int CHAT = 5;
    private static final int EXIT = 0;

    // no-argument ATM constructor initializes instance variables
    public ATM() {
        userAuthenticated = false; // user is not authenticated to start
        currentAccountNumber = 0; // no current account number to start
        screen = new Screen(); // create screen
        keypad = new Keypad(); // create keypad 
        cashDispenser = new CashDispenser(); // create cash dispenser
        bankDatabase = new BankDatabase(); // create acct info database
        atmDepositSlot = new DepositSlot();
        
    }

   // start ATM 
    public void run() {
        // welcome and authenticate user; perform transactions
        while (true) {
            // loop while user is not yet authenticated
            while (!userAuthenticated) {
                screen.displayMessageLine("\nWelcome!");       
                authenticateUser(); // authenticate user

            }

            performTransactions(); // user is now authenticated
            userAuthenticated = false; 
            currentAccountNumber = 0; // reset before next ATM session
            screen.displayMessageLine("\nThank you! Goodbye!");
        }
   }

   // attempts to authenticate user against database
   private void authenticateUser() {
        screen.displayMessage("\nPlease enter your account number: ");
        int accountNumber = keypad.getInput(); // input account number
        
        if(!bankDatabase.findNumberAccount(accountNumber))
        {
            screen.displayMessageLine("Invalid account number. Please try again.");
        }
        else
        {
        screen.displayMessage("\nEnter your PIN: "); // prompt for PIN
        int pin = keypad.getInput(); // input PIN

        // set userAuthenticated to boolean value returned by database
        userAuthenticated = 
            bankDatabase.authenticateUser(accountNumber, pin);

        // check whether authentication succeeded
        if (userAuthenticated) {
            currentAccountNumber = accountNumber; // save user's account 
        } 
        else {
            screen.displayMessageLine(
                "Wrong PIN. Please try again.");
        } 
    }
   } 

   // display the main menu and perform transactions
   private void performTransactions() {
        boolean userExited = false; // user has not chosen to exit

        // loop while user has not chosen option to exit system
        while (!userExited) {
            // show main menu and get user selection
            int mainMenuSelection = displayMainMenu();

            // decide how to proceed based on user's menu selection
            switch (mainMenuSelection) {
               // user chose to perform one of three transaction types
                case BALANCE_INQUIRY:
                    BalanceInquiry BIrun = new BalanceInquiry(currentAccountNumber, screen, bankDatabase);
                    Thread BIThread = new Thread(BIrun);
                    BIThread.start();
                    try{
                        BIThread.join();
                    }catch(Exception e)
                    {
                        System.out.println(e);
                    }
                    break;
                case WITHDRAWAL:
                Withdrawal Wrun = new Withdrawal(
                    currentAccountNumber, screen, bankDatabase, 
                        keypad, cashDispenser);
                        Thread WThread = new Thread(Wrun);
                    WThread.start();
                    try{
                        WThread.join();
                    }catch(Exception e)
                    {
                        System.out.println(e);
                    }
                    break;
                case DEPOSIT:
                Deposit D = new Deposit(
                    currentAccountNumber, screen, bankDatabase, 
                        keypad, atmDepositSlot);
                        Thread DThread = new Thread(D);
                    DThread.start();
                    try{
                        DThread.join();
                    }catch(Exception e)
                    {
                        System.out.println(e);
                    }
                    break;
                case TRANSFER:
                Transfer T = new Transfer(
                    currentAccountNumber, screen, bankDatabase, 
                        keypad);
                        Thread TThread = new Thread(T);
                    TThread.start();
                    try{
                        TThread.join();
                    }catch(Exception e)
                    {
                        System.out.println(e);
                    }
                    break;
                    // // initialize as new object of chosen type
                case CHAT:
                    client MessageBOX = new client();
                    try{
                        MessageBOX.chat();
                    }catch(Exception e)
                    {
                        System.out.println(e);
                    }
                    
                case EXIT: // user chose to terminate session
                    screen.displayMessageLine("\nExiting the system...");
                    userExited = true; // this ATM session should end
                    break;
                default: // 
                    screen.displayMessageLine("\nYou did not enter a valid selection. Try again.");
                    break;
            }
      } 
   } 

    // display the main menu and return an input selection
    private int displayMainMenu() {
        screen.displayMessageLine("\nMain menu:");
        screen.displayMessageLine("1 - View my balance");
        screen.displayMessageLine("2 - Withdraw cash");
        screen.displayMessageLine("3 - Deposit funds");
        screen.displayMessageLine("4 - Transfer");
        screen.displayMessageLine("5 - Chat With Help Center");
        screen.displayMessageLine("0 - Exit\n");
        screen.displayMessage("Enter a choice: ");
        return keypad.getInput(); // return user's selection
    } 
         
}