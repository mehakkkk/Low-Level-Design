public class TextEditor {
    private String article;
    private Caretaker caretaker;

    public TextEditor() {
        article = "";
        caretaker = new Caretaker();
        caretaker.addSavedVersion(new Memento(article)); // Initialize with empty state
    }

    public void writeToEditor(String text) {
        article = new StringBuilder(article).append(text).toString();
        caretaker.addSavedVersion(new Memento(article));
        System.out.println("Saved Version " + caretaker.getSavedVersionsCount());
    }

    public String undo() {
        try {
            Memento m = caretaker.getLastSavedVersion();
            this.article = m.getArticle();
            System.out.println("Undo performed");
            return article;
        } catch (IndexOutOfBoundsException e) {
            System.out.println("No versions to undo.");
            return article;
        }
    }

    public String rollBack(int index) {
        if (index >= 1 && index <= caretaker.getSavedVersionsCount()) {
            Memento m = caretaker.getSavedVersion(index - 1); // Convert 1-based to 0-based
            this.article = m.getArticle();
            System.out.println("Rollback to " + index);
            return article;
        } else {
            System.out.println("Invalid index.");
            return article;
        }
    }
}