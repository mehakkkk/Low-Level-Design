public class VendingMachine {
    private static VendingMachine instance;
    private Inventory inventory;
    private final IVendingMachineState renderState;
    private final IVendingMachineState transactState;
    private final IVendingMachineState dispenseState;
    private final IVendingMachineState readyState;

    private final ThreadLocal<IVendingMachineState> currentState;
    private final ThreadLocal<Product> selectedProduct = new ThreadLocal<>();


    private VendingMachine(Inventory inventory)
    {
        this.inventory = inventory;
        renderState = new RenderState(this);
        transactState = new TransactState(this);
        dispenseState = new DispenseState(this);
        readyState = new ReadyState(this);
        currentState = ThreadLocal.withInitial(() -> renderState);

    }
    public static synchronized VendingMachine getInstance(Inventory inventory) {
        if (instance == null) {
            instance = new VendingMachine(inventory);
        }
        return instance;
    }
    public Inventory getInventory()
    {
        return inventory;
    }
    public void showProducts()
    {
        synchronized (instance) {
            currentState.get().render();
        }
    }

    public void selectProduct(Product product) {
        currentState.get().selectProduct(product);
    }

    public void makePayment(int amount)
    {
        currentState.get().payment(amount);
    }

    public void dispenseProduct() {
        currentState.get().dispenseProduct();
    }

    void setState(IVendingMachineState state) {
        currentState.set(state);
    }

    IVendingMachineState getReadyState() {
        return readyState;
    }

    IVendingMachineState getTransactState(){
        return transactState;
    }

    IVendingMachineState getDispenseState() {
        return dispenseState;
    }

    Product getSelectedProduct() {
        return selectedProduct.get();
    }

    void setSelectedProduct(Product product) {
        selectedProduct.set(product);
    }

    void resetSelectedProduct() {
        selectedProduct.set(null);
    }

    public IVendingMachineState getRenderState() {
        return renderState;
    }

    public IVendingMachineState getCurrentState() {
        return currentState.get();
    }


}
