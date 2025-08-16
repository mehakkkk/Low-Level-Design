public class Main {
    public static void main(String[] args) {
        IReport audioReport = new AudioReport();
        IReport videoReport = new VideoReport();

        audioReport.generateReport("audio.mpeg","AUDIO");
        videoReport.generateReport("video.mjpeg","VIDEO");
    }
}
