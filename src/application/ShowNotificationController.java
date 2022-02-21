package application;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ShowNotificationController { 
	
	@FXML
	private TextArea contentTextArea;

	@FXML
	private TextField senderTextField;

	public void setContent(String content) {
		contentTextArea.setText(content);
	   
	}
	public void setSender(String sender) {
		senderTextField.setText(sender);
	}
}
