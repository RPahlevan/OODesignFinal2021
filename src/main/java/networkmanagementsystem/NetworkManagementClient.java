/**
 * 
 */
package networkmanagementsystem;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import common.*;
import mediator.ConcreteMediator;
import mediator.Mediator;
import radiounit.ManagedRadioUnit;
import java.util.regex.Pattern;

/**
 * @author enuyhza
 *
 */
public class NetworkManagementClient {
	private static Mediator mediator;
	private static NetworkManagementSystem networkManagementSys;
	//define constants
    static final int LTE_RF_PORTS_NUMBER = 4;
    static final int WCDMA_RF_PORTS_NUMBER = 2;
    
    static final Pattern IpPattern = Pattern.compile("^([0-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5])\\."
    												+ "([0-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5])\\."
    												+ "([0-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5])\\."
    												+ "([0-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5])$");
    
	/**
	 * @param args
	 */
    public static void main(String[] args) {
    	networkManagementSys = new ConcreteNetworkManagementSystem();
    	mediator = ConcreteMediator.getInstance();
        String option;
        String ruName;
        String IP;
        int carrierId;

        System.out.println("Welcome!");
        Scanner input = new Scanner(System.in);
        do {
            option = "-1";
            try {
                String menu = "\nPlease enter the number corresponding to the option you would like to choose:\n"
                        + "0.	Exit Program\n"
                		+ "1.	Commission Radio Unit\n"
                        + "2.	Decommission Radio Unit\n"
                		+ "3.	Add Radio Unit\n"
                        + "4.	Setup Radio Unit\n"
                        + "5.	Release Radio Unit\n"
                        + "6.	Activate Radio Unit\n"
                        + "7.	Deactivate Radio Unit\n"
                        + "8.	Setup Carrier on Radio Unit\n"
                        + "9.	Modify Carrier on Radio Unit\n"
                        + "10.	Remove Carrier on Radio Unit\n"
                        + "11.	Remove all Carriers on Radio Unit\n"
                        + "12.	Signal scaling on Radio Unit\n"
                        + "13.	Post activation\n"
                        + "14.	Perform self diagnostics\n"
                        + "15.	List newwork Inventory\n"
                        + "16.	List RUs by standard(RAT type)\n"
                        + "17.	List RUs by state\n"
                        + "18.	List RUs by Band\n"
                        + "19.	List Radio Unit details";

                System.out.println(menu);
                option = input.next();
                switch (option) {
                    case "0" -> System.out.println("Goodbye!\n");
                    case "1" -> {
                    	IP = getIpAddress(input);
                    	networkManagementSys.commissionRu(IP);
                    }
                    case "2" -> {
                    	IP = getIpAddress(input);
                    	networkManagementSys.decommissionRu(IP);                    	
                    }
                    case "3" -> {
                        // 3.Add(create) Radio Unit
                        // get vendor
                        System.out.println("Please enter a name for the Radio Unit:\n"
                                + "For example: LTE#1\n"
                                + "Enter 'Back' to go back to the menu\n");
                        ruName = input.next();
                        if (ruName.equalsIgnoreCase("BACK")) {
                            break;
                        }
                        Vendor vendor = chooseVendor(input);
                        RatType ratType = chooseRatType(input);
                        networkManagementSys.addRadioUnit(ruName, vendor, ratType);
                        System.out.println("Radio Unit " + "'" + ruName + "'" + " has been successfully added");
                    }
                    case "4" -> {
                    	//4.Setup Radio Unit
                    	IP = getIpAddress(input);
                    	networkManagementSys.setupRu(IP);
                    }
                    case "5" -> {
                    	//5.Release Radio Unit
                    	IP = getIpAddress(input);
                    	networkManagementSys.releaseRu(IP);
                    }
                    case "6" -> {
                    	//6.Activate Radio Unit
                    	IP = getIpAddress(input);
                    	networkManagementSys.activateRu(IP);;
                    }
                    case "7" -> {
                    	//7.Deactivate Radio Unit
                    	IP = getIpAddress(input);
                    	networkManagementSys.deactivateRu(IP);;
                    }
                    case "8" -> {
                        // 8.Setup Carrier on Radio Unit
                    	//Todo: option 8 is broken for the moment, need to update getRadioUnit() method in mediator
                        System.out.println("To setup a carrier on Radio Unit, you need to have a Radio Unit ready first");
                        IP = getIpAddress(input);
                        createCarrierOnRu(IP, input);
                        System.out.println("Carrier has been successfully created on " + "Radio Unit with IP address: " + "'" + IP + "'");
                    }
                    case "9" -> {
                        // 9.Modify Carrier on Radio Unit
                        IP = getIpAddress(input);
                        carrierId = getCarrierId(input);
                      //Todo: option 9 is broken for the moment, need to update getRadioUnit() method in mediator
                        ManagedRadioUnit ru = mediator.getRadioUnit(IP);//Todo: call mediator directly?
                        FrequencyBand freqBand = chooseFreqBand(ru.getRatType(), input);
                        
                        networkManagementSys.modifyCarrierOnRu(IP, carrierId, freqBand);
                        System.out.println("Carrier has been successfully created on " + "Radio Unit with IP address: " + "'" + IP + "'");
                    }
                    case "10" -> {
                        //10.Remove Carrier on Radio Unit
                        IP = getIpAddress(input);
                        carrierId = getCarrierId(input);
                        networkManagementSys.removeCarrierOnRu(IP, carrierId);
                    }
                    case "11" -> {
                    	//11.Remove all Carriers on Radio Unit
                    	IP = getIpAddress(input);
                    	networkManagementSys.removeAllCarrierOnRu(IP);
                    }
                    case "12" -> {
                    	//12.Signal scaling on Radio Unit
                    	IP = getIpAddress(input);
                    	networkManagementSys.signalScalingOnRu(IP);
                    }
                    case "13" -> {
                    	//13.Post activation
                    	IP = getIpAddress(input);
                    	networkManagementSys.postActivation(IP);
                    }
                    case "14" -> {
                    	//14.Perform self diagnostics
                    	IP = getIpAddress(input);
                    	networkManagementSys.performSelfDiagnostics(IP);
                    }
                    case "15" -> {
                    	//15.List network Inventory
                    	networkManagementSys.listNetworkInventory();
                    }
                    case "16" -> {
                    	//16.List RUs by standard
                    	RatType ratType = chooseRatType(input);
                    	networkManagementSys.listRuByParam(ratType);
                    }
                    case "17" -> {
                    	//17.List RUs by state
                    	String state = chooseRuState(input);
                    	networkManagementSys.listRuByParam(state);                    	
                    }
                    case "18" -> {
                    	//18.List RUs by Band
                    	RatType ratType = chooseRatType(input);
                    	FrequencyBand freqBand = chooseFreqBand(ratType, input);
                    	networkManagementSys.listRuByParam(freqBand);                    	
                    }
                    case "19" -> {
                    	//19.List Radio Unit details
                    	IP = getIpAddress(input);
                    	networkManagementSys.listRadioUnitDetails(IP);
                    }
                    default -> System.out.println("Unsupported option, please try again!");
                }

            } catch (Exception e) {
                System.out.println("Invalid input, please try again!");
            }

        } while (!option.equals("0"));
    } // main


    /**
     * Helper method that create a carrier on existing RU based on user-choose
     * RAT type, RF Ports, Frequency band and transmitting power.
     * The option also exists to create a stand-alone carrier that will
     * not be added to an RU on creation.
     *
     * @param ruName The name of the radio unit to create carrier on.
     *               Pass as null to create carrier that isn't associated
     *               with an RU.
     * @param input  Scanner instance to get user input.
     */

    private static void createCarrierOnRu(String IP, Scanner input) {
        List<RfPort> rfPorts;
        FrequencyBand freqBand;
        double transPower;
        System.out.println("createCarrierOnRu reached");
        ManagedRadioUnit ru = mediator.getRadioUnit(IP);//Todo: update getRadioUnit() in mediator

        //get RF Ports
        rfPorts = chooseRfPorts(ru.getRatType(), input);

        //get Frequency band
        freqBand = chooseFreqBand(ru.getRatType(), input);

        //get transmitting power
        transPower = getTransPower(input);
        
        //create carrier
        Carrier carrier = mediator.createCarrier(rfPorts, freqBand, transPower, ru.getRatType());

        networkManagementSys.setupCarrierOnRu(IP, carrier);

    }

    /**
     * Helper method to get the Carrier ID from user
     * Returns the Carrier ID user entered
     *
     * @param input   Scanner instance to get user input.
     * @return Radio Unit State, as an String.
     */
    private static String chooseRuState(Scanner input) {
    	//Todo: suggest to create RU state enum
    	String option;
    	String state = null;
    	System.out.println("Please choose Radio Unit State:\n"
    			+ "1. Activated\n"
    			+ "2. Deactivated\n"
    			+ "3. Idle\n");  
    	do {      	
        	option = input.next();
        	switch (option) {
        		case ("1") -> state = "ACTIVATED";
        		case ("2") -> state = "DEACTIVATED";
        		case ("3") -> state = "IDLE";
        		default -> System.out.println("Unsupported option, please try again!");
        	}

    	} while (state == null);

        return state;
    }
        
    /**
     * Helper method to get the Carrier ID from user
     * Returns the Carrier ID user entered
     *
     * @param input   Scanner instance to get user input.
     * @return Carrier ID, as an int.
     */
    private static int getCarrierId(Scanner input) {
    	int ID;
    	do {
        	System.out.println("Please enter the Carrier ID:");
        	String id = input.next();
            try {
                ID = Integer.parseInt(id);
                break;
            } catch (NumberFormatException e) {
                System.out.println("'" + id + "'" + " is not a valid number, Try again!");
            }
    	} while (true);

        return ID;
    }
    
    
    /**
     * Helper method to get the IP address of the radio unit from user
     * Returns the IP address user entered
     *
     * @param input   Scanner instance to get user input.
     * @return IP address, as a String.
     */        
    private static String getIpAddress(Scanner input) {
    	String IP;
    	do {
        	System.out.println("Please enter the IP address of the Radio Unit you want to operate on:");
            IP = input.next();
            if (IpPattern.matcher(IP).matches()) {
            	break;
            }
            System.out.println("Invalid IP address format, please try again.");
    	} while (true);

        return IP;
    }
    /**
     * Helper method that get the transmitting power for carrier from user
     * Returns the transmitting power user entered
     *
     * @param input Scanner instance to get user input.
     * @return The transmitting power, as a double.
     */
    private static double getTransPower(Scanner input) {
        double transPower;
        System.out.println("Please enter a number for transmitting Power:");
        do {
            String transPowerText = input.next();
            try {
                transPower = Double.parseDouble(transPowerText);
                break;
            } catch (NumberFormatException e) {
                System.out.println("'" + transPowerText + "'" + " is not a valid number, Try again!");
            }
        } while (true);
        return transPower;
    }


    /**
     * Helper method that get the vendor from user.
     * Returns the vendor user selected.
     *
     * @param input Scanner instance to get user input.
     * @return A vendor from the Vendor enum.
     */
    private static Vendor chooseVendor(Scanner input) {
        Vendor vendor = null;
        int i = 0;
        System.out.println("Please choose a Vendor for Radio Unit:");
        for (Vendor currVendor : Vendor.values()) {
            System.out.println("	" + ++i + ".	" + currVendor.toString());
        }

        do {
            String subOption = input.next();
            try {
                vendor = Vendor.values()[Integer.parseInt(subOption)  - 1];
            } catch (ArrayIndexOutOfBoundsException | NumberFormatException ex) {
                System.out.println("Invalid input, please try again.");
            }
        } while (vendor == null);

        return vendor;
    }

    /**
     * Helper method that get the RAT type from user.
     * Returns the RAT type the user selected.
     *
     * @param input Scanner instance to get user input.
     * @return The RAT type selected by the user.
     */
    private static RatType chooseRatType(Scanner input) {
        RatType rat = null;
        int i = 0;
        System.out.println("\nPlease choose a RAT type:");
        for (RatType currRatType : RatType.values()) {
            System.out.println("	" + ++i + ".	" + currRatType.toString());
        }
        do {
            String subOption = input.next();
            try {
                rat = RatType.values()[Integer.parseInt(subOption) - 1];
            } catch (ArrayIndexOutOfBoundsException | NumberFormatException ex) {
                System.out.println("Invalid input, please try again.");
            }
        } while (rat == null);

        return rat;
    }


    /**
     * Helper method that get the RF Ports from user.
     * Returns a list of RF Ports.
     *
     * @param ratType The RAT type of the RF ports being requested.
     * @param input   Scanner instance to get user input.
     * @return The RfPorts that were selected by the user, as a List.
     */
    private static List<RfPort> chooseRfPorts(RatType ratType, Scanner input) {
        int noOfRfPorts;
        if (ratType.equals(RatType.LTE)) {
            noOfRfPorts = LTE_RF_PORTS_NUMBER;
        } else {
            noOfRfPorts = WCDMA_RF_PORTS_NUMBER;
        }
        List<RfPort> ports = new ArrayList<>();
        int count = 0;

        int i = 0;
        System.out.printf("Please choose %d RF ports\n", noOfRfPorts);
        for (RfPort currRfPort : RfPort.values()) {
            System.out.println("	" + ++i + ".	" + currRfPort.toString());
        }
        do {
            String subOption = input.next();
            try {
                ports.add(RfPort.values()[Integer.parseInt(subOption) - 1]);
                count++;
            } catch (ArrayIndexOutOfBoundsException | NumberFormatException ex) {
                System.out.println("Invalid input, please try again.");
                continue;
            }
            if (count != noOfRfPorts) {
                System.out.println("Enter next number:");
            }
        } while (count < noOfRfPorts);
        return ports;
    }

    /**
     * Helper method that get the frequency band from user.
     * Returns the frequency band user choose.
     *
     * @param ratType The RAT type of the frequency bands being requested.
     * @param input   Scanner instance to get user input.
     * @return The frequency band the user selected, as a FrequencyBand enum entry.
     */
    private static FrequencyBand chooseFreqBand(RatType ratType, Scanner input) {
        FrequencyBand freqBand = null;
        int i = 0;
        System.out.println("Please choose Carrier Frequency Band:");
        if (!ratType.equals(RatType.LTE)) {
            for (WcdmaFrequencyBand currWcdmaFreq : WcdmaFrequencyBand.values()) {
                System.out.println("	" + ++i + ".	" + currWcdmaFreq.toString());
            }
        } else {
            for (LteFrequencyBand currLteFreq : LteFrequencyBand.values()) {
                System.out.println("	" + ++i + ".	" + currLteFreq.toString());
            }
        }
        do {
            String subOption = input.next();
            try {
                if (ratType.equals(RatType.LTE)) {
                    freqBand = LteFrequencyBand.values()[Integer.parseInt(subOption) - 1];
                } else {
                    freqBand = WcdmaFrequencyBand.values()[Integer.parseInt(subOption) - 1];
                }
            } catch (ArrayIndexOutOfBoundsException | NumberFormatException ex) {
                System.out.println("Invalid input, please try again.");
            }
        } while (freqBand == null);

        return freqBand;
    }

}
