package userinterface;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeMap;

//import carrierManagementSystem;
//import mediator;

public class UserInterface {
	
    public static void main(String[] args) {
    	String option;
    	String ruName;
    	//ConcreteMediator mediator = ConcreteMediator.getInstance();
    	
        System.out.println("Welcome!");
        Scanner input = new Scanner(System.in);
        do {
        	option = "-1";
        	try {
        		String menu = "\nPlease enter the number corresponding to the option you would like to choose:\n"
        				+ "0.	Exit Program\n"
        				+ "1.	Create Radio Unit\n"
        				+ "2.	Create Carrier on Radio Unit\n"
        				+ "3.	Create Carrier and Radio Unit\n"
        				+ "4.	List carriers on RU\n"
        				+ "5.	List RU suported RAT type\n"
        				+ "6.	List Radio Unit vendor\n"
        				+ "7.	List RU alarm status level\n"
        				+ "8.	List registerd Radio Units\n";
        		System.out.println(menu);
        		option = input.next();
        		switch (option) {
        			case "0":
        				System.out.println("Goodbye!\n");
        				break;
        			case "1":
        				// 1.Create Radio Unit     				
        				// get vendor
        				System.out.println("Please enter a name for the Radio Unit:\n"
                                + "For example: LTE#1\n"
                                + "Enter 'Back' to go back to the menu\n");
        				ruName = input.next();
        				if (ruName.toUpperCase().equals("BACK")) {
        					break;
        				}
        				String vendor = chooseVendor(input);
        				String ratType = chooseRATtype(input);
        				//mediator.createRU(ruName, vendor, ratType);
        				System.out.println("Radio Unit " + "'" + ruName + "'" + " has been successfully created");
        				break;
        			case "2":
        				// 2.Create Carrier on Radio Unit
        				System.out.println("To create carrier on Radio Unit, you need to have a Radio Unit ready first\n"
        						+ "Please enter the Radio Unit name:\n"
        						+ "Enter 'Back' to go back to the menu\n");
        				ruName = input.next();
        				if (ruName.toUpperCase().equals("BACK")) {
        					break;
        				}
        				createCarrierOnRu(ruName, input);
        				System.out.println("Carrier has been successfully created on " + "Radio Unit " + "'" + ruName + "'");
        				break;
        			case "3":
        				//3.Create Carrier and Radio Unit
        				System.out.println("Please enter a new Radio Unit name\n"
                                + "For example: LTE#1\n"
        						+ "Enter 'Back' to go back to the menu\n");
        				ruName = input.next();
        				if (ruName.toUpperCase().equals("BACK")) {
        					break;
        				}
        				createCarrierAndRu(ruName, input);
        				break;
        			case "4":
        				//4.List carriers on RU
        				System.out.println("Please enter a new Radio Unit name:\n"
                                + "For example: LTE#1\n"
                                + "Enter 'Back' to go back to the menu\n");        				
        				ruName = input.next();
        				if (ruName.toUpperCase().equals("BACK")) {
        					break;
        				}
        				//mediator.displayCarrierOnRu(ruName);
        				break;
        			case "5":
        				//5.Display RU supported RAT types
        				System.out.println("Please enter the name of the Radio Unit you want to list the supported RAT type of:\n"
                                + "For example: LTE#1\n"
                                + "Enter 'Back' to go back to the menu\n");
        				ruName = input.next();
        				if (ruName.toUpperCase().equals("BACK")) {
        					break;
        				}        				
        				//mediator.printRatType(ruName);
        				break;
        			case "6":
        				//6.List Radio Unit vendor
	    				System.out.println("Please enter the name of the Radio Unit you want to list the vendor of:\n"
	                            + "For example: LTE#1\n"
	                            + "Enter 'Back' to go back to the menu\n");
	    				ruName = input.next();
	    				if (ruName.toUpperCase().equals("BACK")) {
	    					break;
	    				}
	    				//mediator.printVendor(ruName);
	    				break;
        			case "7":
        				//7.List RU alarm status level
	    				System.out.println("Please enter the name of the Radio Unit you want to list the valarm status level of:\n"
	                            + "For example: LTE#1\n"
	                            + "Enter 'Back' to go back to the menu\n");
	    				ruName = input.next();
	    				if (ruName.toUpperCase().equals("BACK")) {
	    					break;
	    				}
	    				//mediator.printAlarmStatus(ruName);
	    				break;
        			case "8":
        				//8.List registerd Radio Units
        				//mediator.printRegisteredRadioUnits();
        				break;
	    			default:
                        System.out.println("Unsupported option, please try again!");
                        break;
       				
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
	 * Helper method that create a carrier on existing RU based on user-choose
	 * RAT typre, RF Ports, Frequency band and transmitting power
	 * 
	 * @param  mediator    	ConcreateMediator instance to send request
	 *    					to mediator
	 * @param  ruName       name of the radio unit to create carrier on
	 * @return void  	
	 */
	
	private static void createCarrierOnRu(String ruName, Scanner input) {
		String ratType;
		String[] rfPorts;
		String freqBand;
		double transPower;

		// get RAT type
		ratType = chooseRATtype(input);
				
		//get RF Ports
		rfPorts = chooseRfPorts(ratType, input);
		
		//get Frequency band
		freqBand = chooseFreqBand(ratType, input);
		
		//get transmitting power
		transPower = getTransPower(input);

		//mediator.createCarrier(rfPorts, freqBand, transPower, ruName);


	}

	
	
	/**
	 * Helper method that create a carrier on existing RU based on user-choose
	 * RAT typre, RF Ports, Frequency band and transmitting power
	 * 
	 * @param  mediator    	ConcreateMediator instance to send request
	 *    					to mediator
	 * @param  ruName       name of the radio unit to create carrier on
	 * @return void  	
	 */
	
	private static void createCarrierAndRu(String ruName, Scanner input) {
		String vendor;
		String ratType;
		String[] rfPorts;
		String freqBand;
		double transPower;

		//get vendor for radio unit
		vendor = chooseVendor(input);
		
		//get RAT type
        ratType = chooseRATtype(input);
	
		//get RF Ports
		rfPorts = chooseRfPorts(ratType, input);			
		
		//get Frequency band
		freqBand = chooseFreqBand(ratType, input);
			
		//get transmitting power
		transPower = getTransPower(input);

		//mediator.createCarrierAndRu(rfPorts, freqBand, transPower, ruName, vendor, ratType);

	}
	
	/**
	 * Helper method that get the transmitting power for carrier from user
	 * Returns the transmitting power user entered
	 * 
	 * @param  input    Scanner instance to get user input
	 * @return double   transmitting power
	 */	
	private static double getTransPower(Scanner input) {
		double transPower;
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
		return transPower;
	}
	
	
	/**
	 * Helper method that get the vendor from user
	 * Returns the vendor user choose
	 * 
	 * @param  input    Scanner instance to get user input
	 * @return String   vendor
	 */	
	private static String chooseVendor(Scanner input) {
		String vendor = null;
		System.out.println("Please choose a Vendor for Radio Unit:\n"
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
		System.out.println("\nPlease choose a RAT type:\n"
				+ "1.	LTE\n"
				+ "2.	WCDM\n");
		do {
			String subOption = input.next();		
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
	private static String[] chooseRfPorts(String ratType, Scanner input) {
		int noOfRfPorts;
		if (ratType.equals("LTE")) {
			noOfRfPorts = RF_PORT_NUMBER.LTE_RF_PORTS_NUMBER.getValue();
		} else {
			noOfRfPorts = RF_PORT_NUMBER.WCDMA_RF_PORTS_NUMBER.getValue();
		}		
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
