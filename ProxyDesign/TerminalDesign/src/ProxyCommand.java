public class ProxyCommand implements Icommand{

    private Icommand linuxCommand;
    private boolean isRootUser;
    public ProxyCommand(String user)
    {
        this.isRootUser = user.equals("root");
        linuxCommand = new LinuxCommand();
    }

    @Override
    public void execute(String command) {
        if (command.startsWith("rm")) {
            if (isRootUser) {
                linuxCommand.execute(command);
            } else {
                System.out.println(
                        String.format("You have no permission executing the command %s", command));
            }
        } else {
            linuxCommand.execute(command);
        }
    }
}
