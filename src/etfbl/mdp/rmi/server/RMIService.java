package etfbl.mdp.rmi.server;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.time.LocalTime;
import java.util.ArrayList;

public class RMIService {
	
	public static void uploadReport(String user, String station, long fileLength, LocalTime time, String filename, byte[] data) {
		System.setProperty("java.security.policy", "client_policyfile.txt");
		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new SecurityManager());
		}
		
		try {
			Registry registry = LocateRegistry.getRegistry(1099);
			RMIReportInterface report = (RMIReportInterface) registry.lookup("RMIReport");
			
			//report.uploadReport("Sergej", "A", 0, LocalTime.now(), "Sergej - A" , "alooooo".getBytes());
			report.uploadReport(user, station, fileLength, time, filename, data);
			
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public static byte[] downloadReport(String filename) {
		System.setProperty("java.security.policy", "client_policyfile.txt");
		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new SecurityManager());
		}
		
		try {
			Registry registry = LocateRegistry.getRegistry(1099);
			RMIReportInterface report = (RMIReportInterface) registry.lookup("RMIReport");
			return report.downloadReport(filename);			
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
	
	public static ArrayList<String> getReportsInfo(){
		System.setProperty("java.security.policy", "client_policyfile.txt");
		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new SecurityManager());
		}
		
		try {
			Registry registry = LocateRegistry.getRegistry(1099);
			RMIReportInterface report = (RMIReportInterface) registry.lookup("RMIReport");
			return report.getReports();
			
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
}
