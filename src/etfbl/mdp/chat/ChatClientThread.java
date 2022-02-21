package etfbl.mdp.chat;
import application.Main;
import application.MainPageController;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Base64;








import javafx.application.Platform;


import java.io.*;

public class ChatClientThread extends Thread {
	private ObjectInputStream objectInputStream;
	private MainPageController mainPageController;
	private ArrayList<String> onlineUsers = new ArrayList<String>();
	private ChatClient chatClient;

	public ChatClientThread(ObjectInputStream objectInputStream, MainPageController mainPageController,ChatClient chatClient) {
		this.objectInputStream = objectInputStream;
		this.mainPageController = mainPageController;
		this.chatClient = chatClient;
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void run() {
		while(true) {
			try {
				if(!chatClient.getSocket().isClosed()) {
					String message = (String) objectInputStream.readObject();
					String[] parsedMessage = message.split("#");
					
					if(parsedMessage[0].equals("MESSAGE_RESPONSE")) {
						String senderUsername = parsedMessage[1];
						String mess = parsedMessage[2];
						Platform.runLater(() ->	mainPageController.receiveMessage(mess, senderUsername));
					} else if("LOGIN_RESPONSE".equals(parsedMessage[0])) {
						onlineUsers.add(parsedMessage[1]);
					} else if(parsedMessage[0].equals("LOGOUT_RESPONSE")) {
						String logoutUser = parsedMessage[1];
						onlineUsers.remove(logoutUser);
					}
					
					Thread.sleep(1000);				
					
				}
				
			} catch(IOException ex) {
				ex.printStackTrace();
				break;
			}
			catch(Exception ex) {
				ex.printStackTrace();
			}
		}
	}
}
