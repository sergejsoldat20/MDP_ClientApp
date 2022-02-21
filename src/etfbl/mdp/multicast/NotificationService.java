package etfbl.mdp.multicast;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

import application.MainPageController;



public class NotificationService {
	public static final String ADDRESS = "224.0.0.11";
	public static final int PORT = 20000;
	
	public static MulticastSocket socket = null;
	public static boolean received = true;
	public MainPageController mainPageController;
	
	public NotificationService() {
        try {
            socket = new MulticastSocket(PORT);
            InetAddress address = InetAddress.getByName(ADDRESS);
            socket.joinGroup(address);
            NotificationThread notificationThread = new NotificationThread(socket);
            notificationThread.notificaionService = this;
            notificationThread.start();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
	
	
	
	public static void sendNotification(String username, String content) {
		String message = username + "-" + content;
		byte [] buffer = message.getBytes();
		InetAddress address;
		try {
			address = InetAddress.getByName(ADDRESS);
			DatagramPacket packet = new DatagramPacket(buffer, buffer.length ,address ,PORT);
			NotificationService.received = false;
			socket.send(packet);
			Thread.sleep(1000);
			NotificationService.received = true;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	} 
}
