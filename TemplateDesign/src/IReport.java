public abstract class IReport {

    public abstract void loadFile(String file);
    public abstract void analyzeContent(String contentType);
    public abstract void generateStats();
    public abstract void saveFile();

    public final void generateReport(String file,String contentType)
    {
        loadFile(file);
        analyzeContent(contentType);
        generateStats();
        saveFile();
    }
}
