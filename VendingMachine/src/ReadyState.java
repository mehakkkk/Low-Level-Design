public class ReadyState implements IVendingMachineState{

    private final VendingMachine vendingMachine;

    public ReadyState(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }
    @Override
    public void render() {
        vendingMachine.setState(vendingMachine.getRenderState());
        vendingMachine.getCurrentState().render();
    }

    @Override
    public void selectProduct(Product product) {
        if(vendingMachine.getInventory().isAvailable(product))
        {
            vendingMachine.setSelectedProduct(product);
            vendingMachine.setState(vendingMachine.getTransactState());
        }
    }

    @Override
    public void payment(int amount) {
        System.out.println("select a product to make payment");

    }

    @Override
    public void dispenseProduct() {
        System.out.println("select a product to dispense");

    }
}
