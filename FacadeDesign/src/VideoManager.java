public class VideoManager {
    public void playVideo(String file){
        System.out.println("Playing video..."+file);
    }

    public void pauseVideo(){
        System.out.println("Pausing video...");
    }

    public void setResolution(int width,int height)
    {
        System.out.println("resolution set to "+width+" px width and "+height+" px height");
    }
}
