public class LoogingDecorator extends Decorator{
    public LoogingDecorator(Notification notification) {
        super(notification);
    }

    @Override
    public void sendNotification(String message) {
        System.out.println("Logging the message for furture:: "+message);
        super.sendNotification(message);
    }
}
