package views.screen.detail;

import java.io.IOException;

import entity.media.CD;
import entity.media.Media;

public class CDInfoHandler extends MediaInfoHandler {
	public CDInfoHandler(String screenPath) throws IOException {
		super(screenPath);
	}

	@Override
	public void showInfo(Media media) {
		super.showInfo(media);
		CD cd = (CD) media;
		// show CD-only info
	}
}
