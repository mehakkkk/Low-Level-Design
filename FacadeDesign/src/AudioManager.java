public class AudioManager {
    public void playAudio(String file)
    {
        System.out.println("Playing file..."+file);
    }
    public void stopAudio()
    {
        System.out.println("Stopping Audio...");
    }
    public void setVolume(int level)
    {
        System.out.println("Volume set to level "+level);
    }
}
