package InterfaceSegregation;

public class PayTm implements UPIPayment {

	@Override
	public void payMoney() {
		// TODO Auto-generated method stub

	}

	@Override
	//but this i actually not required as paytm does not have group functionality
	public void createSplitGroup() {
		// TODO Auto-generated method stub

	}

	@Override
	public void getScratchCard() {
		// TODO Auto-generated method stub

	}

}
