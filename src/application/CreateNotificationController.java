package application;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class CreateNotificationController {
	
    @FXML
    private TextArea notificationText;

    @FXML
    private Button saveButton;
    
    @FXML
    void onClickSaveButton(ActionEvent event) {
    	byte[] text = notificationText.getText().getBytes();
    	if(notificationText.getText() != null) {
    		Main.service.sendNotification(Main.activeUser, notificationText.getText());
	    }
	}
}