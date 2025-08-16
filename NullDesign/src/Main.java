public class Main {
    static NotificationSystem getNoificationObject(String type){
        if(type.equalsIgnoreCase("whatsapp"))
        {
            return new WhatsAppNotification();
        }
        return new NullNotification();
    }
    public static void main(String[] args) {
        getNoificationObject("whatsapp").sendMessage("A whatsapp message received");
        getNoificationObject("sms").sendMessage("sms notification");
    }
}
