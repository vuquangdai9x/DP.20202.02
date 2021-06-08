package views.screen.detail;

import java.io.IOException;

import entity.media.Book;
import entity.media.Media;

public class BookInfoHandler extends MediaInfoHandler {
	public BookInfoHandler(String screenPath) throws IOException {
		super(screenPath);
	}

	@Override
	public void showInfo(Media media) {
		super.showInfo(media);
		Book book = (Book) media;
		// show book-only info
	}
}
