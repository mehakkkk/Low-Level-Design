import java.util.Scanner;

public class VendingMachineDemo {

    public static void main(String[] args) {
        Inventory inventory = new Inventory();

        inventory.addProduct("kurkure",5,10);
        inventory.addProduct("lays",5,15);
        inventory.addProduct("sprite",3,20);
        inventory.addProduct("pepsi",3,20);

        VendingMachine vendingMachine = VendingMachine.getInstance(inventory);

        vendingMachine.showProducts();

        Thread t1 = new Thread(()->{
            vendingMachine.showProducts();
            int id=0;
            synchronized (vendingMachine)
            {
                System.out.println("select product for "+Thread.currentThread().getName());
                Scanner in = new Scanner(System.in);
                id = in.nextInt();
            }

            vendingMachine.selectProduct(new Product(id,"",2,0));
            vendingMachine.makePayment(30); //buy lays
            vendingMachine.dispenseProduct();
        });

        Thread t2 = new Thread(()->{
            vendingMachine.showProducts();

            int id=0;
            synchronized (vendingMachine)
            {
                System.out.println("select product for "+Thread.currentThread().getName());
                Scanner in = new Scanner(System.in);
                id = in.nextInt();
            }
            vendingMachine.selectProduct(new Product(id,"",3,0));
            vendingMachine.makePayment(30); //buy kurkure
            vendingMachine.dispenseProduct();
        });

        t1.start();
        t2.start();

        vendingMachine.showProducts();
    }
}
