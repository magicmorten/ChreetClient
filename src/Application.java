import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import server.ChreetApi;

public class Application extends javafx.application.Application{

	public static ChreetApi api;
	
	public static void main(String[] args) throws URISyntaxException {
		api = new ChreetApi(new URI("ws://localhost:8080/stream"));
		api.startUp();
		
		launch(args);
		
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
	  	Parent p = null;
			File n = new File("login.fxml");
			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
				Scene sc = new Scene(loader.load());
				sc.getStylesheets().add("chreetTheme.css");
				primaryStage.setScene(sc);
				primaryStage.show();
			} catch (IOException e) {
				e.printStackTrace();
			}
	}

}
