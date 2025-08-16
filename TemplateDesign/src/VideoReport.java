public class VideoReport extends IReport{
    @Override
    public void loadFile(String file) {
        System.out.println("loading video file "+file);
    }

    @Override
    public void analyzeContent(String contentType) {
        System.out.println("analyzing "+contentType);
    }

    @Override
    public void generateStats() {
        System.out.println("generating video stats");
    }

    @Override
    public void saveFile() {
        System.out.println("Saving video report");
    }
}
