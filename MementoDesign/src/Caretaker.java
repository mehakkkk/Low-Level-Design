import java.util.ArrayList;
import java.util.List;

public class Caretaker {
    private List<Memento> savedVersions;

    public Caretaker() {
        savedVersions = new ArrayList<>();
    }

    public void addSavedVersion(Memento m) {
        savedVersions.add(m);
    }

    public Memento getSavedVersion(int index) {
        return savedVersions.get(index);
    }

    public Memento getLastSavedVersion() {
        if (savedVersions.isEmpty()) {
            throw new IndexOutOfBoundsException("No versions available");
        }
        return savedVersions.get(savedVersions.size() - 1);
    }

    public int getSavedVersionsCount() {
        return savedVersions.size();
    }
}
