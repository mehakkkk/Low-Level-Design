package DependencyInversion.Solution;

public class DebitCard implements Card {
	
	public void doTransaction(long amount)
	{
		System.out.println("Transaction done by debitcard: "+amount);
	}

}
