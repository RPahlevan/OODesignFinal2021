package userinterface;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeMap;

//import carrierManagementSystem;

public class UserInterface {
	
    public static void main(String[] args) {
    	//ConcreteMediator mediator = new ConcreteMediator();
    	//private List<Carrier> carrierList = new ArrayList<>();
    	String option;
    	
    	//CarrierManagementSystemDirector carrierManagement = new CarrierManagementSystemDirector();
    	
        System.out.println("Hello, World!");
        Scanner input = new Scanner(System.in);
        do {
        	option = "-1";
        	try {
        		String menu = "\nPlease enter the number corresponding to the option you would like to choose:\n"
        				+ "0.	Exit Program\n"
        				+ "1.	Create Carrier\n"
        				+ "2.	Create Radio Unit\n"
        				+ "3.	List Carriers\n"
        				+ "4.	Display carriers on RU"
        				+ "5.	List suported RAT types\n"
        				+ "6.	List radio vendors\n"
        				+ "7.	List alarm status levels\n";
        		System.out.println(menu);
        		option = input.next();
        		switch (option) {
        			case "0":
        				System.out.println("Goodbye!\n");
        				break;
        			case "1":
        				// 1.Create Carrier
        				//carrierList.add(createCarrierHelper(carrierManagement));
        				break;
        			case "2":
        				// 2.Create Radio Unit     				
        				// get vendor
        				System.out.println("Please enter Radio Unit name:");
        				String ruName = input.next();
        				String vendor = chooseVendor(input);
        				String ratType = chooseRATtype(input);
        				//mediator.createRU(ruName, vendor, ratType);
        				
        				
        		}
        		
        	} catch (Exception e) {
        		System.out.println("Invalid input, please try again!");
        		continue;
        	}
        	
        	
        } while(!option.equals("0"));
    } // main

    
	/**
	 * This enum class contains values for number of RF Ports
	 * required for LTE and WCDMA.
	 */
	enum RF_PORT_NUMBER {
		LTE_RF_PORTS_NUMBER(4),
		WCDMA_RF_PORTS_NUMBER(2);
		
		private final int value;
		RF_PORT_NUMBER(int value) {
			this.value = value;
		}		
		public int getValue() {
			return value;
		}
		
	}
	/**
	 * Helper method that create a carrier based on user-choose
	 * RAT typre, RF Ports, Frequency band and transmitting power
	 * Returns the carrier created
	 * 
	 * @param  mediator    	ConcreateMediator instance to send request
	 *    					to mediator
	 * @return Carrier   	carrier
	 */
/*	
	private static Carrier createCarrierHelper(carrierManagement) {
		String ratType;
		String[] rfPorts;
		String freqBand;
		double transPower;
		Carrier carrier;
		do {
			// get RAT type
			ratType = chooseRATtype(input);
			if (ratType.equals("Back")) {
				break;
			}
			
			//get RF Ports
			int noOfRfPorts;
			if (ratType.equals("LTE")) {
				noOfRfPorts = RF_PORT_NUMBER.LTE_RF_PORTS_NUMBER.getValue();
			} else {
				noOfRfPorts = RF_PORT_NUMBER.WCDMA_RF_PORTS_NUMBER.getValue();
			}
			rfPorts = chooseRfPorts(noOfRfPorts, input);
			
			//get Frequency band
			freqBand = chooseFreqBand(ratType, input);
			
			//get transmitting power
			System.out.println("Please enter a number for transmitting Power:\n");
			do {
				String transPowerText = input.next();
				try {
					transPower = Double.valueOf(transPowerText);
					break;
				} catch (NumberFormatException  e) {
					System.out.println("'" + transPowerText + "'" + " is not a valid number, Try agian!");
					continue;
				}        						
			} while (true);			
			break;	
		} while (true);
		if (ratType.equals("LTE")){
			carrier = carrierManagement.creatLteCarrier(rfPorts); 
		} else {
			carrier = carrierManagement.creatWcdmaCarrier(rfPorts); 
		}		
		return carrier;
	}
*/
	
	
	/**
	 * Helper method that get the vendor from user
	 * Returns the vendor user choose
	 * 
	 * @param  input    Scanner instance to get user input
	 * @return String   vendor
	 */	
	private static String chooseVendor(Scanner input) {
		String vendor = null;
		System.out.println("Please choose Vendor:\n"
				+ "1.	Ericsson\n"
				+ "2.	Nokia\n");

		do {
			String subOption = input.next();
			switch (subOption) {
				case "1":
					vendor = "Ericsson";
					break;
				case "2":
					vendor = "Nokia";
					break;
				default:
					System.out.println("Invalid input, please try again.");
					continue;
			}
		} while (vendor == null);

		return vendor;
	}
	/**
	 * Helper method that get the RAT type from user.
	 * Returns the RAT type user choose.
	 * 
	 * @param input     Scanner instance to get user input
	 * @return String   the RAT type
	 */
	private static String chooseRATtype(Scanner input) {
		String rat = null;
		System.out.println("\nPlease choose the RAT type:\n"
				+ "1.	LTE\n"
				+ "2.	WCDM\n"
				+ "Enter 'Back' to go back to the menu\n");
		do {
			String subOption = input.next();
			if (subOption.toUpperCase().equals("BACK")) {
				return "Back";
			}			
			switch (subOption) {
				case "1":
					rat = "LTE";
					break;
				case "2":
					rat = "WCDMA";
					break;
				default:
					System.out.println("Invalid input, please try again.");
					continue;
			}		
		} while (rat == null);

		return rat;	
	}
	
	
	/**
	 * Helper method that get the RF Ports from user.
	 * Returns a list of RF Ports user choose.
	 * 
	 * @param input        Scanner instance to get user input
	 * @return RfPorts[]   an array of RF ports
	 */	
	//Temporary return a string[], need to change to RfPorts type
	private static String[] chooseRfPorts(int noOfRfPorts, Scanner input) {
		String[] ports = new String[noOfRfPorts];
		int count = 0;
		System.out.println(
				"Please choose " + noOfRfPorts + " RF ports\n"
				+ "1.	RF_0 = A\n"
				+ "2.	RF_1 = B\n"
				+ "3.	RF_2 = C\n"
				+ "4.	RF_3 = D\n"
				+ "5.	RF_4 = E\n"
				+ "6.	RF_5 = F\n"
				+ "7.	RF_6 = G\n"
				+ "8.	RF_7 = H\n");
		do {
			String subOption = input.next();
			switch (subOption) {
				case "1":
					ports[count++] = "RF_0 = A";
					break;
				case "2":
					ports[count++] = "RF_1 = B";
					break;
				case "3":
					ports[count++] = "RF_2 = C";
					break;
				case "4":
					ports[count++] = "RF_3 = D";
					break;
				case "5":
					ports[count++] = "RF_4 = E";
					break;
				case "6":
					ports[count++] = "RF_5 = F";
					break;
				case "7":
					ports[count++] = "RF_6 = G";
					break;
				case "8":
					ports[count++] = "RF_7 = H";
					break;
				default:
					System.out.println("Invalid input, please try again.");
					continue;		
			}
			if (count != noOfRfPorts) {
				System.out.println("Enter next number:");            						
			}
		} while(count < noOfRfPorts);
		return ports;
	}
	/**
	 * helper method that get the frequency band from user
	 * returns the frequency band user choose.
	 * 
	 * @param input                 Scanner instance to get user input
	 * @return CarrierFrequencies   Carrier frequency band
	 */
	//temporary return a String, should return CarrierFrequencies type
	private static String chooseFreqBand(String ratType, Scanner input) {
		String freqBand = null;
		System.out.println("Please choose Carrier Frequency Band:");
		if (ratType == "LTE") {
			System.out.println(
					  "1.	LTE_BAND_1  = 1920 MHz\n"
					+ "2.	LTE_BAND_2  = 1850 MHz\n"
					+ "3.	LTE_BAND_3  = 1710 MHz\n"
					+ "4.	LTE_BAND_4  = 1755 MHz\n"
					+ "5.	LTE_BAND_5  = 824 MHz\n"
					+ "6.	LTE_BAND_6  = 830 MHz\n"
					+ "7.	LTE_BAND_7  = 2500 MHz\n"
					+ "8.	LTE_BAND_8 = 880 MHz\n");
		}else {
			System.out.println(
					  "1.	WCDMA_BAND_1  = 2100 MHz\n"
					+ "2.	WCDMA_BAND_2  = 1900 MHz\n"
					+ "3.	WCDMA_BAND_3  = 1800 MHz\n"
					+ "4.	WCDMA_BAND_4  = 2100/1700 MHz\n"
					+ "5.	WCDMA_BAND_5  = 850 MHz - for the U.S.\n"
					+ "6.	WCDMA_BAND_6  = 850 MHz - for Japan\n"
					+ "7.	WCDMA_BAND_7  = 2500 MHz\n"
					+ "8.	WCDMA_BAND_8  = 900 MHz\n");			
		}
		do {
			String subOption = input.next();
			switch (subOption) {
				case "1":
					if (ratType == "LTE") {
						freqBand = "LTE_BAND_1  = 1920 MHz";
					} else {
						freqBand = "WCDMA_BAND_1  = 2100 MHz";
					}
					break;
				case "2":
					if (ratType == "LTE") {
						freqBand = "LTE_BAND_2  = 1850 MHz";
					} else {
						freqBand = "WCDMA_BAND_2  = 1900 MHz";
					}					
					break;
				case "3":
					if (ratType == "LTE") {
						freqBand = "LTE_BAND_3  = 1710 MHz";
					} else {
						freqBand = "WCDMA_BAND_3  = 1800 MHz";
					}					
					break;
				case "4":
					if (ratType == "LTE") {
						freqBand = "LTE_BAND_4  = 1755 MHz";
					} else {
						freqBand = "WCDMA_BAND_4  = 2100/1700 MHz";
					}
					break;
				case "5":
					if (ratType == "LTE") {
						freqBand = "LTE_BAND_5  = 824 MHz";
					} else {
						freqBand = "WCDMA_BAND_5  = 850 MHz - for the U.S.";
					}
					break;
				case "6":
					if (ratType == "LTE") {
						freqBand = "LTE_BAND_6  = 830 MHz";
					} else {
						freqBand = "WCDMA_BAND_6  = 850 MHz - for Japan";
					}
					break;
				case "7":
					if (ratType == "LTE") {
						freqBand = "LTE_BAND_7  = 2500 MHz";
					} else {
						freqBand = "WCDMA_BAND_7  = 2500 MHz";
					}
					break;
				case "8":
					if (ratType == "LTE") {
						freqBand = "LTE_BAND_8 = 880 MHz";
					} else {
						freqBand = "WCDMA_BAND_8  = 900 MHz";
					}
					break;
				default:
					System.out.println("Invalid input, please try again.");
					continue;					
			}
		} while (freqBand == null);
		
		return freqBand;
	}


} // UserInterface class
