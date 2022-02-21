package etfbl.mdp.chat;

import application.Main;
import application.MainPageController;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;



public class ChatClient {
	
	public static final String KEY_STORE_PATH = "keystore.jks";
	public static final String KEY_STORE_PASS = "securemdp";
	private MainPageController mainPageController;
	private ObjectOutputStream objectOutputStream;
	private ObjectInputStream objectInputStream;
	private SSLSocket socket;
	private String serverName;
	private int port;
	
	
	public ChatClient (String serverName, int port,MainPageController mainPageController) {
		super();
		this.serverName = serverName;
		this.port = port;
		this.mainPageController = mainPageController;
	}
	
	public boolean start() {
		try {
			System.setProperty("javax.net.ssl.trustStore",KEY_STORE_PATH);
			System.setProperty("javax.net.ssl.trustStorePassword",KEY_STORE_PASS);
			SSLSocketFactory sslSocketFactory = (SSLSocketFactory)SSLSocketFactory.getDefault();
			socket = (SSLSocket) sslSocketFactory.createSocket(serverName,port);
			try {
				objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
				objectInputStream = new ObjectInputStream(socket.getInputStream());
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			new ChatClientThread(objectInputStream,mainPageController,this).start();
			objectOutputStream.writeObject(Main.activeUser);
			return true;
		} catch (IOException ex) {
			ex.printStackTrace();
			disconnect();
			return false;
		}
		
		
	}
	
	public SSLSocket getSocket() {
		return socket;
	}
	
	public void sendMessage(String msg) {
		try {
			objectOutputStream.writeObject(msg);
		} catch(IOException ex) {
			ex.printStackTrace();
		}
	}
	
	private void disconnect() {
		try { 
			if(objectInputStream != null) {
				objectInputStream.close();
			}
			
			if(objectOutputStream != null) {
				objectOutputStream.flush();
				objectOutputStream.close();
			}
			
			if(socket != null) {
				socket.close();
			}
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
}
