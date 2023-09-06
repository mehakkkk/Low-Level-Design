package DependencyInversion;

public class CreditCard {
	
	public void doTransaction(long amount)
	{
		System.out.println("Transaction done by creditcard: "+amount);
	}


}
