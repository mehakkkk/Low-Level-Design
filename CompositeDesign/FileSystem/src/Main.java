public class Main {
    public static void main(String[] args) {
        Directory codeDirectory = new Directory("coding-problems");

        //add files to the directory
        codeDirectory.add(new File("code1"));
        codeDirectory.add(new File("code2"));

        // add a directory
        Directory codeJavaDirectory = new Directory("CodeJava");
        codeJavaDirectory.add(new File("codeJava1"));

        //add this directory to codeDirectory
        codeDirectory.add(codeJavaDirectory);

        codeDirectory.ls();
    }
}
