import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        TextEditor textEditor = new TextEditor();
        System.out.println("New text Editor\n\n");
        while(true)
        {
            System.out.println("1.write to file\n2.Undo write\n3.rollback to a saved version\n4.show article\nselect:");
            Scanner scanner = new Scanner(System.in);
            int opt = scanner.nextInt();
            switch (opt)
            {
                case 1:
                    scanner.nextLine();
                    String line = scanner.nextLine();
                    textEditor.writeToEditor(line);
                    break;

                case 2:
                    String text = textEditor.undo();
                    System.out.println(text);
                    break;
                case 3:
                    int index = scanner.nextInt();
                    text = textEditor.rollBack(index);
                    System.out.println(text);
                    break;
                default: System.exit(0);
            }

        }
    }
}
