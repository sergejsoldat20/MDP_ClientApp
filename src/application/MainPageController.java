package application;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.rmi.RemoteException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.json.JsonArray;
import javax.json.JsonValue;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.Response.Status;
import javax.xml.rpc.ServiceException;

import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import etfbl.mdp.chat.ChatClient;
import etfbl.mdp.model.User;
import etfbl.mdp.rmi.server.RMIService;
import etfbl.soap.SOAPService;
import etfbl.soap.SOAPServiceServiceLocator;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class MainPageController {
	
	@FXML
	Button updateStations;
	
	@FXML
	Button uploadButton;
	
	@FXML
	Button logoutButton;
	
	@FXML
	Button sendNotificationButton;
	
	@FXML
	ComboBox stationsBox;
	
	@FXML
	ComboBox usersBox;
	
	@FXML
	TextArea chatArea;
	
	@FXML
	TextField messageField;
	
	@FXML
	Button uploadReportButton;
	
	@FXML
	Button chatButton;
	
	@FXML
	Button updateTimeButton;
	
	@FXML
	Button showLinesButton;
	
	@FXML
	TextArea lines;
	
	@FXML
	TextField lineID;
	
	@FXML
	Label stationID;
	
	public HashMap<String, TextArea> chatHashMap = new HashMap<String, TextArea>();
	private String username;
	private String station;
	
	
	@FXML
	void initialize() {
		stationID.setText(station);
		Main.service.mainPageController = this;
		Main.chatService = new ChatClient("127.0.0.1", 1500, this);
    	Main.chatService.start();
		
		for(String s : getAllStations()) {
			stationsBox.getItems().add(s);
		}
	}
	
	public MainPageController() {
		this.username = MainController.users.get(0);
		this.station = MainController.stations.get(0);
	}
	
	public static URI getBaseURI() {
		return UriBuilder.fromUri(
				"http://localhost:8080/CentralApplication").build();
	}
	
	//preko REST se dobija raspored voznje 
	@FXML
	public void showLines(MouseEvent event){
		String text = "";
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(getBaseURI()).path("api").path("lines").path(station);
		Response response = target.request(MediaType.APPLICATION_JSON).get();
		if(response.getStatus() == Status.OK.getStatusCode()) {
			JsonArray jsonArray = response.readEntity(JsonArray.class);
			for(int i = 0; i < jsonArray.size(); i++) {
				JsonValue value = jsonArray.get(i);
				//System.out.println(value.toString());
				//JSONObject jObj = new JSONObject(value.toString());
				JSONObject json = new JSONObject(value.toString());
				String line = (String) json.get("line");
				text += line + "\n";
			}
			lines.setText(text);
			//System.out.println(text);
		}
	}
	
	//za izabrani ID linije azurira se vrijeme ako se stanice poklapaju
	@FXML
	public void updateTime(MouseEvent event) {
		String id = lineID.getText();
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(getBaseURI()).path("api").path("lines").path(id).path(station);
		Response response = target.request().put(Entity.entity(LocalTime.now().toString(), MediaType.APPLICATION_JSON));
		//System.out.println(response.getStatus());
	}
	
	@FXML
	public void logout(MouseEvent event) {
		SOAPServiceServiceLocator locator = new SOAPServiceServiceLocator();
	//	System.out.println("logout");
		try {
			SOAPService service = locator.getSOAPService();
			if(service.logout(username)) {
				removeUser(username);
				Platform.exit();
			}
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	//gui za upload izvjestaja
	@FXML
	public void uploadReport(MouseEvent event) {
		Stage fileChooserStage = new Stage();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose resource file");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("FILES:", "*.pdf"));
        fileChooser.setInitialDirectory(new File("resources"));
        File selectedFile = fileChooser.showOpenDialog(fileChooserStage);
        if(selectedFile != null) {
        	 try {
             	
     			FileInputStream fileInputStream = new FileInputStream(selectedFile);
     			long size = fileInputStream.getChannel().size();
     			byte bytes[] = new byte[(int) size];
     			fileInputStream.read(bytes);
     			String filename = username + " - " + station;
     			RMIService.uploadReport(username, station, size, LocalTime.now(), filename, bytes);
     		} catch (Exception ex) {
     			ex.printStackTrace();
     		}
     	}
	}
	
	//ukloni korisnika iz liste online korisnika
	public void removeUser(String username) {
		Iterator<User> iterator = MainController.onlineUsers.iterator();
		while(iterator.hasNext()) {
			User next = iterator.next();
			if(next.getUsername().equals(username)) {
				iterator.remove();
			}
		}
	}
	
	
	//prikazi objavestenje
	public void showNotification(String content, String sender) {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("ShowNotificationView.fxml"));	
		Parent rootParent;
		try {
			rootParent = loader.load();
			ShowNotificationController showNotificationController = loader.getController();
			Platform.runLater(() -> {
				showNotificationController.setContent(content);
				showNotificationController.setSender(sender);
				Scene scene = new Scene(rootParent);
				Stage stage = new Stage();
				stage.setScene(scene);
				stage.show();
			});
		} catch (Exception ex) {
			System.out.println(ex);
		}
		
    }
    
	@FXML
	public void sendNotification(MouseEvent event) {
		try {
            Stage newStage = new Stage();
            newStage.initModality(Modality.APPLICATION_MODAL);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("CreateNotificationView.fxml"));
            //MainPageController mainPageController = new MainPageController();
            CreateNotificationController controller = new CreateNotificationController();
            Parent root = loader.load();
            Scene scene = new Scene(root);
            newStage.setScene(scene);
            newStage.show();
        }catch (IOException ex){
           ex.printStackTrace();
        }
	}
	
	
	public ArrayList<String> getUsersByStation(String station) {
		SOAPServiceServiceLocator locator = new SOAPServiceServiceLocator();
		//System.out.println("logout");
		try {
			SOAPService service = locator.getSOAPService();
			String[] allUsers = service.loggedUsers();
			ArrayList<String> result = new ArrayList<String>();
			for(String s : allUsers) {
				String []parsed = s.split("#");
				if(parsed[1].equals(station)) {
					result.add(parsed[0]);
					System.out.println(parsed[0]);
				}
			}
			return result;
			
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	@FXML
	public void testButton(MouseEvent event) {
		getUsersByStation("A");
	}
	
	public ArrayList<String> getAllStations() {
		SOAPServiceServiceLocator locator = new SOAPServiceServiceLocator();
		
		try {
			System.out.println("-----------------------------------");
			SOAPService service = locator.getSOAPService();
			String[] allUsers = service.loggedUsers();
			ArrayList<String> result = new ArrayList<String>();
			for(String s : allUsers) {
				String []parsed = s.split("#");
				if(!result.contains(parsed[1])) {
					result.add(parsed[1]);
				}
			}
			return result;
			
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	@FXML
    void setUsersBox(ActionEvent event) {
    	if(stationsBox.getSelectionModel().getSelectedItem() != null) {
    		String selectedStation = (String) stationsBox.getSelectionModel().getSelectedItem();
    		if(selectedStation != null) {
    			for(String s : getUsersByStation(selectedStation)) {
    				usersBox.getItems().add(s);
    			}
    		}
    	}
    }
	
	@FXML
	void chooseUserForChat(ActionEvent event) {
		if(usersBox.getSelectionModel().getSelectedItem() != null) {
			String selectedUser = (String)usersBox.getSelectionModel().getSelectedItem();
			if(selectedUser != null) {
				System.out.println("otvoriti cet sa korisnikom - " + selectedUser);
			}
			
		}
	}
	
	@FXML
    void sendMessage(ActionEvent event) {
    	String receiverString = (String) usersBox.getSelectionModel().getSelectedItem();
    	if(receiverString != null) {
    		String contentString = messageField.getText();
        	if(contentString != null) {
        		Main.chatService.sendMessage("TEXT"+ "#" + Main.activeUser + "#" 
        		+ receiverString + "#" + contentString);
        		
        		chatHashMap.get(receiverString).appendText("me -> "+ contentString + "\n");
        		chatArea.setText(chatHashMap.get(receiverString).getText());
        	}
    	}
    }
    
    public void receiveMessage(String messageContent, String receiver) {
    	usersBox.setValue(receiver);
    	openChatWithUser(receiver);
    	chatHashMap.get(receiver).appendText(receiver +  " -> " + messageContent + "\n");
    	chatArea.setText(chatHashMap.get(receiver).getText());
    }
    
    public void openChatWithUser(String receiver) {
    	if(chatHashMap.containsKey(receiver)) {
    		chatArea.appendText(chatHashMap.get(receiver).getText());
    	} else {
    		chatHashMap.put(receiver, new TextArea());
    	}
    }
}
