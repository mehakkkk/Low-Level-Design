public class MediaLibraryFacade {
    AudioManager audioManager;
    VideoManager videoManager;
    MetadataManager metadataManager;

    public MediaLibraryFacade(){
        audioManager = new AudioManager();
        videoManager = new VideoManager();
        metadataManager = new MetadataManager();
    }

    public void playMedia(String file, boolean isVideo) {
        if (isVideo) {
            videoManager.playVideo(file);
        } else {
            audioManager.playAudio(file);
        }
        metadataManager.loadMetadata(file);
    }

    public void stopMedia(boolean isVideo) {
        if (isVideo) {
            videoManager.pauseVideo();
        } else {
            audioManager.stopAudio();
        }
    }

    public void configureSettings(int volume, int resolutionWidth, int resolutionHeight) {
        audioManager.setVolume(volume);
        videoManager.setResolution(resolutionWidth, resolutionHeight);
    }

    public void manageMetadata(String file, String metadata) {
        metadataManager.updateMetadata(file, metadata);
    }

    public static void main(String[] args) {
        // Create a facade instance with subsystems
        MediaLibraryFacade facade = new MediaLibraryFacade();

        // Use the facade to play media
        facade.playMedia("song.mp3", false);  // Playing audio
        facade.configureSettings(50, 1920, 1080); // Setting volume and video resolution
        facade.manageMetadata("song.mp3", "Artist: Example Artist");

        // Use the facade to stop media
        facade.stopMedia(false);  // Stopping audio

        // Play video and manage metadata
        facade.playMedia("movie.mp4", true);  // Playing video
        facade.manageMetadata("movie.mp4", "Director: Example Director");

        // Stop video
        facade.stopMedia(true);  // Stopping video
    }
}
