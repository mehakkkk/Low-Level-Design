public class ConsoleTarget implements ILogTarget{
    @Override
    public void appendMessage(Message message) {
        System.out.println("["+message.logLevel+"], "+message.getText());
    }
}
