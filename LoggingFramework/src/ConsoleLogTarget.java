public class ConsoleLogTarget implements ILogTarget{

    @Override
    public void writeLog(Message message) {
        System.out.println(message);
    }
}
