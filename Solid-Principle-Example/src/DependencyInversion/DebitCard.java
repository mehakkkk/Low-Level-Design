package DependencyInversion;

public class DebitCard {
	
	public void doTransaction(long amount)
	{
		System.out.println("Transaction done by debitcard: "+amount);
	}

}
