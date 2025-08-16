public class DispenseState implements IVendingMachineState{

    private final VendingMachine vendingMachine;

    public DispenseState(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }

    @Override
    public void render() {
        System.out.println("Product ready to dispense, cannot go back");

    }

    @Override
    public void selectProduct(Product product) {

        System.out.println("Product already select and ready to dispense");
    }

    @Override
    public void payment(int amount) {
        System.out.println("Payment already done,wait for dispense");
    }

    @Override
    public void dispenseProduct() {
        System.out.println("Please collect your product below:: "+vendingMachine.getSelectedProduct().getId());
        vendingMachine.setState(vendingMachine.getRenderState());
        vendingMachine.getInventory().getProduct(vendingMachine.getSelectedProduct().getId()).decrementQuantity(vendingMachine.getSelectedProduct().getQty());
    }
}
