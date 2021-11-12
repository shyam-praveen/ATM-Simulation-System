// Withdrawal.java
// Represents a withdrawal ATM transaction

public class Withdrawal extends Transaction implements Runnable {
    private int amount; // amount to withdraw
    private Keypad keypad; // reference to keypad
    private CashDispenser cashDispenser; // reference to cash dispenser
    private BankDatabase bankDatabase;

    // constant corresponding to menu option to cancel
    private final static int CANCELED = 0;

    // Withdrawal constructor
    public Withdrawal(int userAccountNumber, Screen atmScreen, BankDatabase atmBankDatabase, Keypad atmKeypad,
            CashDispenser atmCashDispenser) {

        // initialize superclass variables
        super(userAccountNumber, atmScreen, atmBankDatabase);

        keypad = new Keypad();
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
        amount = displayMenuOfAmounts();
        Screen screen = getScreen();

        if (amount != CANCELED) {
         cashDispenser = new CashDispenser();
            double availableBalance = bankDatabase.getAvailableBalance(getAccountNumber());
            if (cashDispenser.isSufficientCashAvailable(amount) && availableBalance>=amount) {
                cashDispenser.dispenseCash(amount);
                bankDatabase.credit(getAccountNumber(), amount);
                try {
                    Thread.sleep(3000);
                } catch (Exception e) {
                    System.out.println(e);
                }
                screen.displayMessageLine("You can take your cash now.");
                try {
                    Thread.sleep(2000);
                } catch (Exception e) {
                    System.out.println(e);
                }

            } else {
                if(!cashDispenser.isSufficientCashAvailable(amount))
                {
                    screen.displayMessageLine("Currently That Much cash is not available in ATM");
                    screen.displayMessageLine("Sorry For the Inconvineance");
                }else if(availableBalance<amount)
                {
                    screen.displayMessageLine("Insufficient Balance in your Account");
                }
                try {
                    Thread.sleep(2000);
                } catch (Exception e) {
                    System.out.println(e);
                }
            }

        } else {
            screen.displayMessageLine("Canceling transaction...");
        }
    }

    // display a menu of withdrawal amounts and the option to cancel;
    // return the chosen amount or 0 if the user chooses to cancel
    private int displayMenuOfAmounts() {
        int userChoice = 0; // local variable to store return value

        Screen screen = getScreen(); // get screen reference

        // array of amounts to correspond to menu numbers
        int[] amounts = { 0, 500, 1000, 1500, 2000, 3000 };

        // loop while no valid choice has been made
        while (userChoice == 0) {
            // display the withdrawal menu
            screen.displayMessageLine("\nWithdrawal Menu:");
            screen.displayMessageLine("1 - Rs500");
            screen.displayMessageLine("2 - Rs.1000");
            screen.displayMessageLine("3 - Rs.1500");
            screen.displayMessageLine("4 - Rs.2000");
            screen.displayMessageLine("5 - Rs.3000");
            screen.displayMessageLine("0 - Cancel transaction");
            screen.displayMessage("\nChoose a withdrawal amount: ");

            int input = keypad.getInput(); // get user input through keypad

            // determine how to proceed based on the input value
            // if the user choose a withdrawal amount
            // (i.e., chose option 1, 2, 3, 4 or 5), return the
            // corresponding amount from amounts array
            switch (input) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
                userChoice = amounts[input]; // save user's choice
                break;
            case CANCELED: // the user chose to cancel
                userChoice = CANCELED; // save user's choice
                break;
            default: // the user did not enter a value from 1-6
                screen.displayMessageLine("\nInvalid selection. Try again.");
            }
        }

        return userChoice; // return withdrawal amount or CANCELED
    }
}