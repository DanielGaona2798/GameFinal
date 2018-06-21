package models;

import java.io.File;
import java.io.FileNotFoundException;

import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class MusicManager {
	@SuppressWarnings("unused")
	private JFXPanel fx;

	public MusicManager() {
		fx = new JFXPanel();
	}

	public void playSong(String path) throws FileNotFoundException{
		Media m = new Media(new File(path).toURI().toString());
		MediaPlayer media = new MediaPlayer(m);
		media.play();
	}
}
