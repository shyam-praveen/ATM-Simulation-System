public class Deposit extends Transaction implements Runnable {
    private double amount; // amount to deposit
    private Keypad keypad; // reference to keypad
    private DepositSlot depositSlot; // reference to deposit slot
    private final static int CANCELED = 0; // constant for cancel option
    // own//
    private BankDatabase bankDatabase;

    // Deposit constructor
    public Deposit(int userAccountNumber, Screen atmScreen, BankDatabase atmBankDatabase, Keypad atmKeypad,
            DepositSlot atmDepositSlot) {

        // initialize superclass variables
        super(userAccountNumber, atmScreen, atmBankDatabase);

        // own//
        keypad = new Keypad();
        depositSlot = atmDepositSlot;
        bankDatabase = atmBankDatabase;
    }

    public void run() {
        synchronized(this){
            this.execute();
         }
    }

    // perform transaction
    @Override
    public void execute() {
        amount = promptForDepositAmount();
        Screen screen = getScreen();

        // BankDatabase atmBankDatabase = super.getBankDatabase();
        if (amount != CANCELED) {
            screen.displayMessage("\nPlease insert a deposit envelope containing ");
            screen.displayRupeeAmount(amount);
            screen.displayMessageLine(" ");
            screen.displayMessageLine("...");
            try {
                Thread.sleep(3000);
            } catch (Exception e) {
                System.out.println(e);
            }
            if (depositSlot.isEnvelopeReceived()) {
                screen.displayMessageLine("Your envelope has been received.");

                bankDatabase.debit(getAccountNumber(), amount);
                try {
                    Thread.sleep(2000);
                } catch (Exception e) {
                    System.out.println(e);
                }
                // atmBankDatabase.getAccount(super.getAccountNumber()).debit(amount);
            }
        } else {
            screen.displayMessageLine("Canceling transaction...");
        }
    }

    // prompt user to enter a deposit amount in rupees
    private double promptForDepositAmount() {
        Screen screen = getScreen(); // get reference to screen

        // display the prompt
        screen.displayMessage("\nPlease enter a deposit amount in " + "Rupees (or 0 to cancel): ");
        int input = keypad.getInput(); // receive input of deposit amount

        // check whether the user canceled or entered a valid amount
        if (input == CANCELED) {
            return CANCELED;
        } else {
            return (double) input; // return rupees amount
        }
    }
}
