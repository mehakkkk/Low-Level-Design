public class EncryptionDecorator extends Decorator{

    public EncryptionDecorator(Notification notification) {
        super(notification);
    }

    private String encrypt(String message)
    {
        //logic to encrypt message and then send
        return new StringBuilder(message).reverse().toString();
    }

    @Override
    public void sendNotification(String message) {
        message = encrypt(message);
        System.out.println("Encrypting the message:: "+message);
        super.sendNotification(message);
    }
}
