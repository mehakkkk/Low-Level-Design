package DependencyInversion.Solution;

public class Transaction {
	
	public void doTransact(long amount,Card card)
	{
		// here debitcard object is tightly coupled with Transaction class
		//but here for transaction we can use any type of card
		card.doTransaction(amount);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Transaction transaction = new Transaction();
		transaction.doTransact(100000,new CreditCard());
		transaction.doTransact(400000,new DebitCard());
	}

}
