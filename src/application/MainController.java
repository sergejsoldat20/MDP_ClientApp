package application;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import javax.xml.rpc.ServiceException;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import etfbl.mdp.model.User;
import etfbl.soap.*;
public class MainController {
	
	@FXML
	TextField usernameField;
	
	@FXML
	PasswordField passwordField;
	
	@FXML
	Button loginButton;
	
	public static ArrayList<String> users = new ArrayList<String>();
	
	public static ArrayList<String> stations = new ArrayList<>();
	
	public static ArrayList<User> onlineUsers = new ArrayList<>();
	
	@FXML
	public void loginAction(ActionEvent ev) {
		String username = usernameField.getText();
		String password = passwordField.getText();
		
		SOAPServiceServiceLocator locator = new SOAPServiceServiceLocator();
		try {
			SOAPService service = locator.getSOAPService();
			User user = service.loginUser(username, password);
		
			if(user != null) {
				Main.activeUser = user.getUsername();
				onlineUsers.add(user);
				users.add(username);
				stations.add(user.getRailwayStation().getLocation());
				System.out.println(user.getUsername());
				showNewScene();
			}
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
	}
	
	public void showNewScene() {
		try {
            Stage noviProzor = new Stage();
            noviProzor.initModality(Modality.APPLICATION_MODAL);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("MainPage.fxml"));
            MainPageController mainPageController = new MainPageController();
            Parent root = loader.load();
            Scene scene = new Scene(root);
            noviProzor.setTitle("Main Page");
            noviProzor.setScene(scene);
            noviProzor.show();
        }catch (IOException ex){
           ex.printStackTrace();
        }
	}
	
	public static void main(String args[]) {
		
	}
}
