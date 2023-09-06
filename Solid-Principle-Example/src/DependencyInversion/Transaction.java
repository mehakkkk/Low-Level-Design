package DependencyInversion;

public class Transaction {
	
	public void doTransact(long amount)
	{
		// here debitcard object is tightly coupled with Transaction class
		//but here for transaction we can use any type of card
		DebitCard debitCard = new DebitCard();
		debitCard.doTransaction(amount);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Transaction transaction = new Transaction();
		transaction.doTransact(100000);
	}

}
