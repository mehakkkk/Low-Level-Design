import java.util.List;

public class RenderState implements IVendingMachineState{
    private final VendingMachine vendingMachine;

    public RenderState(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }


    @Override
    public void render() {
        List<Product> productList = vendingMachine.getInventory().getAllProducts();
        for (Product product : productList) {
            System.out.println(product);
        }
        vendingMachine.resetSelectedProduct();
        vendingMachine.setState(vendingMachine.getReadyState());
    }

    @Override
    public void selectProduct(Product product) {

        System.out.println("select a product first!!");
    }

    @Override
    public void payment(int amount) {
        System.out.println("select a product first!!");
    }

    @Override
    public void dispenseProduct() {

        System.out.println("select a product first!!");
    }
}
