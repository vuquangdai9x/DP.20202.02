package views.screen.detail;

import java.io.IOException;

import entity.media.DVD;
import entity.media.Media;

public class DVDInfoHandler extends MediaInfoHandler {
	public DVDInfoHandler(String screenPath) throws IOException {
		super(screenPath);
	}

	@Override
	public void showInfo(Media media) {
		super.showInfo(media);
		DVD dvd = (DVD) media;
		// show DVD-only info
	}
}
