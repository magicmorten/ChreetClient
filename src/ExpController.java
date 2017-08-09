import java.io.File;
import java.io.IOException;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import server.ChreetListener;
import server.data.ChatMessage;
import server.data.User;
import server.data.User.AuthStatus;

public class ExpController implements ChreetListener {

    @FXML
    private TextField txtfield_Password;

    @FXML
    private Button btn_Login;

    @FXML
    private Text txt;

    @FXML
    private TextField txtfield_Username;

    @FXML
    private Button btn_Register;

    public ExpController(){
    	Application.api.addListener(this);
    }
    @FXML
    public void Register(ActionEvent event) {
    	Stage registerStage = new Stage();
    	Parent p = null;
		File n = new File("registration.fxml");
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("registration.fxml"));
			Scene sc = new Scene(loader.load());
			registerStage.setScene(sc);
			ExpController e = new ExpController();
			registerStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
     }

    
   
    
    @FXML
    public void login(ActionEvent event) {
    	String username = txtfield_Username.getText();
    	String password = txtfield_Password.getText();
     
    	Application.api.login(username, password);
    }
	@Override
	public void onConnectionOpened() {}
	
	@Override
	public void onConnectionClosed() {}
	
	@Override
	public void onAuthChanged(AuthStatus status, User currentUser) {
		Platform.runLater(new Runnable(){
			
		@Override
		public void run() {
			// TODO Auto-generated method stub
			if (status == AuthStatus.SUCCESS) {
				
				Stage chatStage = new Stage();
		    	Parent p = null;
				File n = new File("chat.fxml");
				ChatController e2 = new ChatController();
				try {
					FXMLLoader loader = new FXMLLoader(getClass().getResource("chat.fxml"));
					Scene sc = new Scene(loader.load());
					chatStage.setScene(sc);
					chatStage.show();
				} catch (IOException e) {
					e.printStackTrace();
				}
			    Stage stage = (Stage) txt.getScene().getWindow();
			    stage.close();
			} else {
				
			}
		}
		});
	}
	
	@Override
	public void onMessageReceived(ChatMessage msg) {}

}

