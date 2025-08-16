public class MetadataManager {
    public void loadMetadata(String file) {
        System.out.println("Loading metadata for file: " + file);
    }

    public void getMetadata(String file) {
        System.out.println("Retrieving metadata for file: " + file);
    }

    public void updateMetadata(String file, String metadata) {
        System.out.println("Updating metadata for file: " + file + " with " + metadata);
    }
}