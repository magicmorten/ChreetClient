import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import javafx.util.Callback;
import server.ChreetApi;
import server.data.ChatMessage;

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
				wrapper(primaryStage);
				primaryStage.show();
			} catch (IOException e) {
				e.printStackTrace();
			}
	}

	public void wrapper (Stage primaryStage)	{
		ArrayList<ChatMessage> a = new ArrayList<>();
		
		primaryStage.setTitle("Chreet");;
		
		ListView<ChatMessage> listView = new ListView<>();
		ObservableList<ChatMessage> myObservableList = FXCollections.observableList(a);
		listView.setItems(myObservableList);
		
		listView.setCellFactory(new Callback<ListView<ChatMessage>, ListCell<ChatMessage>>(){
			
			@Override
			public ListCell<ChatMessage> call(ListView<ChatMessage> p) {
				
				ListCell<ChatMessage> cell = new ListCell<ChatMessage>(){
					
					@Override
					protected void updateItem(ChatMessage t, boolean bln) {
						super.updateItem(t, bln);
						if (t != null) {
							setText(t.getSender() + ": " + t.getContent() + " (" + t.getSendTime() + ")");
						}
					}
				};
				
				return cell;
			}
		});
	}
}
