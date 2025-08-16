public class Main {
    public static void main(String[] args) {
        Icommand icommand = new ProxyCommand("mehak");
        icommand.execute("grep -ri 'hello'");
        icommand.execute("mkdir system-design");
        icommand.execute("rm -r system-design");
    }
}
