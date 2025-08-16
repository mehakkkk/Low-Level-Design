public interface IVendingMachineState {
    void render();

    void selectProduct(Product product);

    void payment(int amount);

    void dispenseProduct();

}
