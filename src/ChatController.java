


import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import server.ChreetListener;
import server.data.ChatMessage;
import server.data.User;
import server.data.User.AuthStatus;

public class ChatController implements ChreetListener {

    @FXML
    private TextField txtfield_Message;

    @FXML
    private Button btn_SendMessage;

    @FXML
    private Button btn_Menu;

    @FXML
    private ListView<?> List_Chat;

    @FXML
    private Button btn_Profile;
    
    public ChatController(){
    	Application.api.addListener(this);
    }

    @FXML
    void OpenMenu(ActionEvent event) {

    }

    @FXML
    void OpenProfile(ActionEvent event) {

    }

    @FXML
    void SendMessage(ActionEvent event) {
    	String msg = txtfield_Message.getText();
    	Application.api.sendChatMessage(msg);
    }

    @FXML
    void ShowComment(ActionEvent event) {

    }

	@Override
	public void onConnectionOpened() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onConnectionClosed() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onAuthChanged(AuthStatus status, User currentUser) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onMessageReceived(ChatMessage msg) {
		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				System.out.println(msg.getSender().getUsername() + ": " + msg.getContent());
				
			}
			
		});
	}

}