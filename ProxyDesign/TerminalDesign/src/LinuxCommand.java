public class LinuxCommand implements Icommand{
    @Override
    public void execute(String command) {
        System.out.println("command: "+command);
    }
}
