public class TransactState implements IVendingMachineState{
    private final VendingMachine vendingMachine;

    public TransactState(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }


    @Override
    public void render() {
        vendingMachine.setState(vendingMachine.getRenderState());
        vendingMachine.getCurrentState().render();
    }

    @Override
    public void selectProduct(Product product) {
        System.out.println("Product already selected");

    }

    @Override
    public void payment(int amount) {
        int totalPayment = vendingMachine.getInventory()
                .getProduct(vendingMachine.getSelectedProduct().getId())
                .getPrice()*vendingMachine.getSelectedProduct().getQty();
        try {
            if (amount < totalPayment) {
                throw new Exception("Insufficient amount paid, Cancelling");

            }
            vendingMachine.setState(vendingMachine.getDispenseState());
        }
        catch(Exception ex)
        {
            System.out.println(ex.getMessage());
            vendingMachine.setState(vendingMachine.getRenderState());
        }

    }

    @Override
    public void dispenseProduct() {
        System.out.println("Make payment to dispense");
    }

}
