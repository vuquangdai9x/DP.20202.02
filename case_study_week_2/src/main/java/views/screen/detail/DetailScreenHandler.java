package views.screen.detail;

import java.io.IOException;

import controller.HomeController;
import entity.media.Book;
import entity.media.CD;
import entity.media.DVD;
import entity.media.Media;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.stage.Stage;
import views.screen.BaseScreenHandler;

public class DetailScreenHandler extends BaseScreenHandler {
	private Media media;
	
	@FXML
    protected Spinner<Integer> spinnerChangeNumber;

    @FXML
    protected Button addToCartBtn;
    
	protected DetailScreenHandler(Stage stage, String screenPath, Object obj) throws IOException {
		super(stage, screenPath, obj);
	}
	
	@Override
	protected void setupFunctionality() throws Exception {
		addToCartBtn.setOnMouseClicked( e -> {
			updateCart();
		});
		super.setupFunctionality();
	}

	private void updateCart() {
		// get quantity of media from spinner and update to cart
	}

	public void setMedia(Media media) {
		this.media = ((HomeController)getBaseController()).getMediaDetail(media.getId());
		updateQuantity();
		showMediaInfo();
	}
	
	private void showMediaInfo() {
		MediaInfoHandler mediaInfo = null;
		try {
			if (media instanceof Book) {
				mediaInfo = new BookInfoHandler("screenPath");
			}else if (media instanceof CD) {
				mediaInfo = new CDInfoHandler("screenPath");
			}else if (media instanceof DVD) {
				mediaInfo = new DVDInfoHandler("screenPath");		
			}else {
				mediaInfo = new MediaInfoHandler("screenPath"); // fall back
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		if (mediaInfo != null) mediaInfo.showInfo(media);
	}

	private void updateQuantity() {
		// get quantity of media if already in cart and update to spinner
		// otherwise set spinner value to 0  
	}
}
