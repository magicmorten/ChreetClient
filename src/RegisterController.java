
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class RegisterController {

    @FXML
    private TextField txtfield_CreateUsername;

    @FXML
    private TextField txtfield_CreatePassword;

    @FXML
    private TextField txtfield_CheckPassword;

    @FXML
    private Button btn_back;

    public RegisterController(){
    	
    	
    }
    @FXML
    void BackToLogin(ActionEvent event) {
    	 Stage stage = (Stage) btn_back.getScene().getWindow();
 	    stage.close();
      
    }

    @FXML
    void PasswordCheck(ActionEvent event) {

    }

}