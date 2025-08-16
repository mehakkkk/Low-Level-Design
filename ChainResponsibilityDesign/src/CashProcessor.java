public abstract class CashProcessor {

    protected CashProcessor cashProcessor;
    protected int NOTE_DENOMINATION;
    protected int cashLeft;

    public CashProcessor(int cashLeft,CashProcessor cashProcessor)
    {
        this.cashProcessor = cashProcessor;
        this.cashLeft = cashLeft;
    }
    public void dispenseCash(int amount){

        // Calculate the maximum number of 500-unit notes required
        int numOfNotes = amount / NOTE_DENOMINATION;

        // Determine the number of notes we can actually use
        int notesToUse = Math.min(cashLeft, numOfNotes);

        // Calculate the amount dispensed
        int amountDispensed = notesToUse * NOTE_DENOMINATION;

        // Update the cashLeft
        cashLeft -= notesToUse;

        // Print results
        if (amountDispensed > 0) {
            System.out.println("Number of " + NOTE_DENOMINATION + "-unit notes used: " + notesToUse);
            System.out.println("Amount dispensed: " + amountDispensed);
        }

        if (amountDispensed < amount) {
            if(cashProcessor != null)
                cashProcessor.dispenseCash(amount-amountDispensed);
            else
                System.out.println("Remaining amount that could not be dispensed: " + (amount - amountDispensed));
        }
    }
}
