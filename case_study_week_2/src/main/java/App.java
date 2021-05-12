import java.io.IOException;

import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import subsystem.InterbankSubsystem;
import views.screen.BaseScreenHandler;
import views.screen.ViewsConfig;
import views.screen.home.*;
import views.screen.intro.IntroScreenHandler;

public class App extends Application {

	@FXML
	ImageView logo;
	
	private static final float FADE_IN_DURATION = 2;
	private static final float FADE_OUT_DURATION = 1;
	
	private static final float UNVISIBLE_VALUE = 0;
	private static final float VISIBLE_VALUE = 1;

	/**
	 * <p>Vu Quang Dai</p>
	 * <p>Coicidental Cohesion</p>
	 * <p>Init, fade and load content process are not relate to each other</p>
	 */
	@Override
	public void start(Stage primaryStage) {
		try {

			// initialize the scene
			BaseScreenHandler introScreen = new IntroScreenHandler(primaryStage, ViewsConfig.INTRO_SCREEN_PATH);
			introScreen.show();

			// Load splash screen with fade in effect
			FadeTransition fadeIn = new FadeTransition(Duration.seconds(FADE_IN_DURATION), introScreen.getContent());
			fadeIn.setFromValue(UNVISIBLE_VALUE);
			fadeIn.setToValue(VISIBLE_VALUE);
			fadeIn.setCycleCount(1);

			// Finish splash with fade out effect
			FadeTransition fadeOut = new FadeTransition(Duration.seconds(FADE_OUT_DURATION), introScreen.getContent());
			fadeOut.setFromValue(VISIBLE_VALUE);
			fadeOut.setToValue(UNVISIBLE_VALUE);
			fadeOut.setCycleCount(1);

			// After fade in, start fade out
			fadeIn.play();
			fadeIn.setOnFinished((e) -> {
				fadeOut.play();
			});

			// After fade out, load actual content
			fadeOut.setOnFinished((e) -> {
				loadContent(primaryStage);
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void loadContent(Stage primaryStage) {
		try {
			HomeScreenHandler homeHandler = new HomeScreenHandler(primaryStage, ViewsConfig.HOME_PATH);
			homeHandler.setScreenTitle("Home Screen");
			homeHandler.setImage();
			homeHandler.show();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
