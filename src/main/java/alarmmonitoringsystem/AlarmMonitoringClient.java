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
	
	private static final String MENU = "\nPlease enter the number corresponding to the option you would like to choose:\n"
			+ "0.	Exit Program\n"
			+ "1.	Get Network Alarms\n"
			+ "2.	Acknowledge Alarm on Radio Unit";

	private static final Pattern IP_PATTERN = Pattern.compile("^([0-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5])\\."
			+ "([0-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5])\\."
			+ "([0-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5])\\."
			+ "([0-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5])$");
	private static final String INPUT_PROMPT = "> ";

	/**
	 * Entry-point into the application.
	 *
	 * @param args The command-line arguments passed during initialization.
	 */
	public static void main(String[] args) {
		String networkMgmtId = NetworkManagementClient.NETWORK_MGMT_ID;
		
		AlarmMonitoringSystemIf alMonitoringSys;
		
		try {
			NetworkManagementSystemIf networkMgmtSys = (NetworkManagementSystemIf) Naming.lookup(networkMgmtId);
			alMonitoringSys = new AlarmMonitoringSystem(networkMgmtSys);

			System.out.println(
					"#############################################################\n" +
					"##                 ALARM MONITORING CLIENT                 ##\n" +
					"##                        TEAM A.2                         ##\n" +
					"##               ERICSSON OOP: SUMMER 2021                 ##\n" +
					"#############################################################\n");
			System.out.println("Welcome!");
			Scanner input = new Scanner(System.in);
			String option = "-1";
			String ip;

			while(!option.equals("0"))
			{
				System.out.print(MENU);
				System.out.print(INPUT_PROMPT);
				option = input.next().trim();
				
				switch (option) {
				case "0":
					input.close();
					System.out.println("\nGoodbye!");
					break;
				case "1":
					alMonitoringSys.getNetAlarms();
					returnToMenu();
					break;
				case "2":
					ip = getIpAddress(input);
					alMonitoringSys.acknowledgeAlarmOnRu(ip);
					returnToMenu();
					break;
				default:
					System.out.println("Unsupported option, please try again!");
					break;
				}
			}
		}
		catch (Exception e)
		{
			System.out.println("Error connecting to network management system.");
			e.printStackTrace();
			System.exit(0);
		}
		System.exit(0);
	}

	/**
	 * Helper method to get the IP address of the radio unit from user
	 * Returns the IP address user entered
	 *
	 * @param input Scanner instance to get user input.
	 * @return IP address, as a String.
	 */
	private static String getIpAddress(Scanner input) {
		String ip;
		input.nextLine();
		do {
			System.out.print("\nPlease enter the IP address of the Radio Unit you want to operate on.\n"
					+ "Enter 'Back' to go back to the menu\n");
			System.out.print(INPUT_PROMPT);
			ip = input.nextLine().trim();
			if (ip.equalsIgnoreCase("BACK")) {
				break;
			}
			if (IP_PATTERN.matcher(ip).matches()) {
				break;
			}
			System.out.println("Input is not a valid IP address: " + ip + "\nPlease try again.");
		} while (true);

		return ip;
	}

	private static void returnToMenu() {
		System.out.print("Please press the Enter key to return to the main menu.");
		try
		{
			System.in.read();
		}
		catch(Exception e)
		{}
	}
}
