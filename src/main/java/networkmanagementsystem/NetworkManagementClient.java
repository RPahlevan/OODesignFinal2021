package networkmanagementsystem;

import common.*;
import mediator.Mediator;
import mediator.MediatorIf;

import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * @author enuyhza
 *
 */
public class NetworkManagementClient {
    private static NetworkManagementSystemIf networkManagementSys;
    //define constants
    static final int LTE_RF_PORTS_NUMBER = 4;
    static final int WCDMA_RF_PORTS_NUMBER = 2;

    static final Pattern IpPattern = Pattern.compile("^([0-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5])\\."
            + "([0-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5])\\."
            + "([0-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5])\\."
            + "([0-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5])$");

    /**
     * Entry-point into the application.
     *
     * @param args The command-line arguments passed during initialization.
     */
    public static void main(String[] args) {
        setupObserverRelations();

        String option;
        String ruName;
        String ip;
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
                        + "4.	Remove Radio Unit\n"
                        + "5.	Setup Radio Unit\n"
                        + "6.	Release Radio Unit\n"
                        + "7.	Activate Radio Unit\n"
                        + "8.	Deactivate Radio Unit\n"
                        + "9.	Setup Carrier on Radio Unit\n"
                        + "10.	Modify Carrier on Radio Unit\n"
                        + "11.	Remove Carrier on Radio Unit\n"
                        + "12.	Remove all Carriers on Radio Unit\n"
                        + "13.	Signal scaling on Radio Unit\n"
                        + "14.	Post activation\n"
                        + "15.	Perform self diagnostics\n"
                        + "16.	List network Inventory\n"
                        + "17.	List RUs by standard(RAT type)\n"
                        + "18.	List RUs by state\n"
                        + "19.	List RUs by Band\n"
                        + "20.	Set alarm on Radio Unit\n"
                        + "21.	List all network alarms\n"
                        + "22.	Acknowledge alarm on Radio Unit\n"
                        + "23.	List Radio Unit details";

                System.out.println(menu);
                option = input.next();
                switch (option) {
                    case "0" -> System.out.println("Goodbye!\n");
                    case "1" -> {
                        ip = getIpAddress(input);
                        networkManagementSys.commissionRu(ip);
                    }
                    case "2" -> {
                        ip = getIpAddress(input);
                        networkManagementSys.decommissionRu(ip);
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
                        // 4.Remove(delete) Radio Unit
                        ip = getIpAddress(input);
                        networkManagementSys.removeRadioUnit(ip);
                    }
                    case "5" -> {
                        //5.Setup Radio Unit
                        ip = getIpAddress(input);
                        networkManagementSys.setupRu(ip);
                    }
                    case "6" -> {
                        //6.Release Radio Unit
                        ip = getIpAddress(input);
                        networkManagementSys.releaseRu(ip);
                    }
                    case "7" -> {
                        //7.Activate Radio Unit
                        ip = getIpAddress(input);
                        networkManagementSys.activateRu(ip);
                    }
                    case "8" -> {
                        //8.Deactivate Radio Unit
                        ip = getIpAddress(input);
                        networkManagementSys.deactivateRu(ip);
                    }
                    case "9" -> {
                        // 9.Setup Carrier on Radio Unit
                        System.out.println("To setup a carrier on Radio Unit, you need to have a Radio Unit ready first");
                        ip = getIpAddress(input);
                        createCarrierOnRu(ip, input);
                        System.out.println("Carrier has been successfully created on " + "Radio Unit with IP address: " + "'" + ip + "'");
                    }
                    case "10" -> {
                        // 10.Modify Carrier on Radio Unit
                        ip = getIpAddress(input);
                        carrierId = getCarrierId(input);
                        FrequencyBand freqBand = chooseFreqBand(networkManagementSys.getRuRatType(ip), input);
                        networkManagementSys.modifyCarrierOnRu(ip, carrierId, freqBand);
                        System.out.println("Carrier has been successfully created on " + "Radio Unit with IP address: " + "'" + ip + "'");
                    }
                    case "11" -> {
                        //11.Remove Carrier on Radio Unit
                        ip = getIpAddress(input);
                        carrierId = getCarrierId(input);
                        networkManagementSys.removeCarrierOnRu(ip, carrierId);
                    }
                    case "12" -> {
                        //12.Remove all Carriers on Radio Unit
                        ip = getIpAddress(input);
                        networkManagementSys.removeAllCarrierOnRu(ip);
                    }
                    case "13" -> {
                        //13.Signal scaling on Radio Unit
                        ip = getIpAddress(input);
                        networkManagementSys.signalScalingOnRu(ip);
                    }
                    case "14" -> {
                        //14.Post activation
                        ip = getIpAddress(input);
                        networkManagementSys.postActivation(ip);
                    }
                    case "15" -> {
                        //15.Perform self diagnostics
                        ip = getIpAddress(input);
                        networkManagementSys.performSelfDiagnostics(ip);
                    }
                    case "16" -> //16.List network Inventory
                            networkManagementSys.listNetworkInventory();
                    case "17" -> {
                        //17.List RUs by standard
                        RatType ratType = chooseRatType(input);
                        networkManagementSys.listRuByParam(ratType);
                    }
                    case "18" -> {
                        //18.List RUs by state
                        String state = chooseRuState(input);
                        networkManagementSys.listRuByParam(state);
                    }
                    case "19" -> {
                        //19.List RUs by Band
                        RatType ratType = chooseRatType(input);
                        FrequencyBand freqBand = chooseFreqBand(ratType, input);
                        networkManagementSys.listRuByParam(freqBand);
                    }
                    case "20" -> {
                        //20.List Radio Unit details
                        ip = getIpAddress(input);
                        networkManagementSys.listRadioUnitDetails(ip);
                    }
                    case "21" -> {
                        //21.Set Alarm on RU
                        ip = getIpAddress(input);
                        AlarmStatusLevel alarm = chooseAlarmStatusLevel(input);
                        networkManagementSys.setAlarmOnRu(ip, alarm);
                    }
                    case "22" -> //22.List all Network Alarms
                            networkManagementSys.getNetworkAlarms();
                    case "23" -> {
                        //23.Acknowledge alarm on RU
                        ip = getIpAddress(input);
                        networkManagementSys.acknowledgeAlarm(ip);
                    }
                    default -> System.out.println("Unsupported option, please try again!");
                }

            } catch (Exception e) {
                System.out.println("Invalid input, please try again!");
            }

        } while (!option.equals("0"));
    } // main

    /**
     * Helper method that get the alarm from user.
     * Returns the alarm user selected.
     *
     * @param input Scanner instance to get user input.
     * @return A alarm from the AlarmStatusLevel enum.
     */
    private static AlarmStatusLevel chooseAlarmStatusLevel(Scanner input) {
        AlarmStatusLevel alarm = null;
        int i = 0;
        System.out.println("Please choose an Alarm for Radio Unit:");
        for (AlarmStatusLevel currAlarm : AlarmStatusLevel.values()) {
            System.out.println("	" + ++i + ".	" + currAlarm.toString());
            if (i == 3) {
                break;
            }
        }

        do {
            String subOption = input.next();
            try {
                if (i < 4) {
                    alarm = AlarmStatusLevel.values()[Integer.parseInt(subOption) - 1];
                } else {
                    throw new NumberFormatException();
                }
            } catch (ArrayIndexOutOfBoundsException | NumberFormatException ex) {
                System.out.println("Invalid input, please try again.");
            }
        } while (alarm == null);

        return alarm;
    }

    /**
     * Prepare Observer relations between classes.
     * Currently these includes the Commission/Decommission classes, the
     * NetworkManagementSystem, the Mediator, and the NetworkManagementClient.
     */
    private static void setupObserverRelations() {
        // Setup Observer relations
        MediatorIf mediator = Mediator.getInstance();
        networkManagementSys = NetworkManagementSystem.getInstance();
        networkManagementSys.addPropertyChangeListener((PropertyChangeListener) mediator);
        mediator.addPropertyChangeListener((PropertyChangeListener) networkManagementSys);
        for (Map.Entry<RatType, CommissionRadioUnit> entry : networkManagementSys.getCommissioners().entrySet()) {
            entry.getValue().addPropertyChangeListener((PropertyChangeListener) mediator);
        }
        for (Map.Entry<RatType, DecommissionRadioUnit> entry : networkManagementSys.getDecommissioners().entrySet()) {
            entry.getValue().addPropertyChangeListener((PropertyChangeListener) mediator);
        }
    }


    /**
     * Helper method that create a carrier on existing RU based on user-choose
     * RAT type, RF Ports, Frequency band and transmitting power.
     * The option also exists to create a stand-alone carrier that will
     * not be added to an RU on creation.
     *
     * @param ip The name of the radio unit to create carrier on.
     *               Pass as null to create carrier that isn't associated
     *               with an RU.
     * @param input  Scanner instance to get user input.
     */

    private static void createCarrierOnRu(String ip, Scanner input) {
        List<RfPort> rfPorts;
        FrequencyBand freqBand;
        double transPower;
        System.out.println("createCarrierOnRu reached");
        // Get the RatType of the RU we are currently looking at.
        RatType currRatType = networkManagementSys.getRuRatType(ip);

        //get RF Ports
        rfPorts = chooseRfPorts(currRatType, input);

        //get Frequency band
        freqBand = chooseFreqBand(currRatType, input);

        //get transmitting power
        transPower = getTransPower(input);

        networkManagementSys.setupCarrierOnRu(ip, rfPorts, freqBand, transPower);

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
                vendor = Vendor.values()[Integer.parseInt(subOption) - 1];
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
