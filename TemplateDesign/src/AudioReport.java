public class AudioReport extends IReport{
    @Override
    public void loadFile(String file) {
        System.out.println("loading audio file "+file);
    }

    @Override
    public void analyzeContent(String contentType) {
        System.out.println("analyzing "+contentType);
    }

    @Override
    public void generateStats() {
        System.out.println("generating audio stats");
    }

    @Override
    public void saveFile() {
        System.out.println("Saving audio report");
    }
}
