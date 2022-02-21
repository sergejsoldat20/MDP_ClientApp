package etfbl.mdp.multicast;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

import application.Main;
import javafx.application.Platform;

public class NotificationThread extends Thread {
	
	private static final int PORT = 20000;
	private static final String HOST = "224.0.0.11";
	public NotificationService notificaionService;
	public MulticastSocket socket;
	
	public NotificationThread(MulticastSocket socket) {
		this.socket = socket;
	}
	@Override
	public void run() {
			byte buffer[] = new byte[256];
			InetAddress address;
			try {
				address = InetAddress.getByName(HOST);
				while (true) {
					DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
					if(!socket.isClosed()) {
					   	socket.receive(packet);
						String received = new String(packet.getData(), 0, packet.getLength());
			            String parsed[] = received.split("-");
			            String username = parsed[0];
			            String content = parsed[1];
			            if(!Main.activeUser.equals(username)) {
				       	   notificaionService.mainPageController.showNotification(content, username);
				        }
					} 
				} 
			} catch (Exception ex) {
				ex.printStackTrace();
			} 
			
		
	}
}
