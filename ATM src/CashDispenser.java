public class CashDispenser {
    // the default initial number of bills in the cash dispenser
    private final static int INITIAL_COUNT = 1000;
    private int count; // number of Rs.500 bills remaining
   
    // no-argument CashDispenser constructor initializes count to default
    public CashDispenser() {
        count = INITIAL_COUNT; // set count attribute to default
    }
   
    
    // simulates dispensing of specified amount of cash
    public void dispenseCash(int amount) {
        int billsRequired = amount / 500; // number of Rs.500 bills required
        count -= billsRequired; // update the count of bills
    }


    // indicates whether cash dispenser can dispense desired amount
    public boolean isSufficientCashAvailable(int amount) {
        int billsRequired = amount / 500; // number of Rs.500 bills required

        if (count >= billsRequired) {
            return true; // enough bills available
        }
        else {
            return false; // not enough bills available
        }
    }
} 