/**
 * 
 */
package alarmmonitoringsystem;

import java.rmi.Naming;
import java.util.regex.Pattern;
import java.util.Scanner;

import networkmanagementsystem.NetworkManagementClient;
import networkmanagementsystem.NetworkManagementSystemIf;

/**
 * @author edavleu
 *
 */
public class AlarmMonitoringClient {
	
	static final String MENU = "\nPlease enter the number corresponding to the option you would like to choose:\n"
			+ "0.	Exit Program\n"
			+ "1.	Get Network Alarms\n"
			+ "2.	Acknowledge Alarm on Radio Unit";

	static final Pattern IP_PATTERN = Pattern.compile("^([0-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5])\\."
			+ "([0-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5])\\."
			+ "([0-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5])\\."
			+ "([0-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5])$");

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String networkMgmtId = NetworkManagementClient.NETWORK_MGMT_ID;
		
		AlarmMonitoringSystem alMonitoringSys;
		
		try {
			NetworkManagementSystemIf networkMgmtSys = (NetworkManagementSystemIf) Naming.lookup(networkMgmtId);
			alMonitoringSys = new AlarmMonitoringSystem(networkMgmtSys);
			
			System.out.println("Welcome!");
			Scanner input = new Scanner(System.in);
			String option = "-1";
			String ip = "";

			while(!option.equals("0"))
			{
				System.out.println(MENU);
				option = input.next();
				
				switch (option) {
				case "0":
					System.out.println("Goodbye!\n");
					break;
				case "1":
					alMonitoringSys.getNetAlarms();
					break;
				case "2":
					ip = getIpAddress(input);
					alMonitoringSys.acknowledgeAlarmOnRu(ip);
					break;
				default:
					System.out.println("Unsupported option, please try again!!");
					break;
				}
			}
		}
		catch (Exception e)
		{
			System.out.println("Error connecting to network management system");
			e.printStackTrace();
			System.exit(0);
		}
	}

	/**
	 * Helper method to get the IP address of the radio unit from user
	 * Returns the IP address user entered
	 *
	 * @param input Scanner instance to get user input.
	 * @return IP address, as a String.
	 */
	private static String getIpAddress(Scanner input) {
		String IP;
		do {
			System.out.println("Please enter the IP address of the Radio Unit you want to operate on:");
			IP = input.next();
			if (IP_PATTERN.matcher(IP).matches()) {
				System.out.println("IP address is fine.");
				break;
			}
			System.out.println("Invalid IP address format, please try again: " + IP);
		} while (true);

		return IP;
	}

}
