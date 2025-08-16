public class TextNotifier implements Notification{
    @Override
    public void sendNotification(String message) {
        //logic to send text notification
        System.out.println("Notification sent via text: "+ message);
    }
}
