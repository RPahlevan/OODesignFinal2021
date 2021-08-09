package networkmanagementsystem;

import common.*;
import mediator.Mediator;
import mediator.MediatorIf;

import java.beans.PropertyChangeListener;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.*;
import java.util.regex.Pattern;

/**
 * @author enuyhza
 */
public class NetworkManagementClient {
    public static final String NETWORK_MGMT_ID = "networkMgmtId";
    public static final String BACK_TO_MENU = "Enter 'Back' to go back to the menu.\n";
    public static final String INVALID_INPUT = "Invalid input, please try again.";
    public static final String BREAK_CHECK = "BACK";

    private static NetworkManagementSystemIf networkManagementSys;
    //define constants
    private static final String INPUT_PROMPT = "> ";
    private static final int LTE_RF_PORTS_NUMBER = 4;
    private static final int WCDMA_RF_PORTS_NUMBER = 2;
    private static final Pattern IP_PATTERN = Pattern.compile("^([0-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5])\\." +
            "([0-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5])\\." +
            "([0-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5])\\." +
            "([0-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5])$");

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

        System.out.println(
                "#############################################################\n" +
                        "##               NETWORK MANAGEMENT CLIENT                 ##\n" +
                        "##                        TEAM A.2                         ##\n" +
                        "##               ERICSSON OOP: SUMMER 2021                 ##\n" +
                        "#############################################################\n");
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
                        + "17.	List RUs details by standard (RAT type)\n"
                        + "18.	List RUs details by state\n"
                        + "19.	List RUs details by band\n"
                        + "20.	List Radio Unit by IP address\n"
                        + "21.	Set Alarm on Radio Unit\n"
                        + "22.	List all Network Alarms\n"
                        + "23.	Acknowledge Alarm on Radio Unit\n";

                System.out.print(menu);
                System.out.print(INPUT_PROMPT);
                option = input.next().trim();

                FrequencyBand freqBand;
                RatType ratType;

                switch (option) {
                    case "0":
                        System.out.println("\nGoodbye!");
                        input.close();
                        option = "0";
                        break;
                    case "1":
                        ip = getIpAddress(input);
                        if (ip.equalsIgnoreCase(BREAK_CHECK)) {
                            break;
                        }
                        networkManagementSys.commissionRu(ip);
                        returnToMenu();
                        break;
                    case "2":
                        ip = getIpAddress(input);
                        if (ip.equalsIgnoreCase(BREAK_CHECK)) {
                            break;
                        }
                        networkManagementSys.decommissionRu(ip);
                        returnToMenu();
                        break;
                    case "3":
                        // 3.Add(create) Radio Unit
                        // get vendor
                        System.out.println("\nPlease enter a name for the Radio Unit.\n"
                                + "For example: LTE#1\n"
                                + "Enter 'Back' to go back to the menu");
                        System.out.print(INPUT_PROMPT);
                        input.nextLine();
                        ruName = input.nextLine().trim();
                        if (ruName.equalsIgnoreCase(BREAK_CHECK)) {
                            break;
                        }
                        Vendor vendor = chooseVendor(input);
                        if (vendor == null) {
                            break;
                        }
                        ratType = chooseRatType(input);
                        if (ratType == null) {
                            break;
                        }
                        Random r = new Random();
                        ip = r.nextInt(256) + "." + r.nextInt(256) + "." + r.nextInt(256) + "." + r.nextInt(256);
                        networkManagementSys.addRadioUnit(ip, ruName, vendor, ratType);
                        returnToMenu();
                        break;
                    case "4":
                        // 4.Remove(delete) Radio Unit
                        ip = getIpAddress(input);
                        if (ip.equalsIgnoreCase(BREAK_CHECK)) {
                            break;
                        }
                        networkManagementSys.removeRadioUnit(ip);
                        returnToMenu();
                        break;
                    case "5":
                        //5.Setup Radio Unit
                        ip = getIpAddress(input);
                        if (ip.equalsIgnoreCase(BREAK_CHECK)) {
                            break;
                        }
                        networkManagementSys.setupRu(ip);
                        returnToMenu();
                        break;
                    case "6":
                        //6.Release Radio Unit
                        ip = getIpAddress(input);
                        if (ip.equalsIgnoreCase(BREAK_CHECK)) {
                            break;
                        }
                        networkManagementSys.releaseRu(ip);
                        returnToMenu();
                        break;
                    case "7":
                        //7.Activate Radio Unit
                        ip = getIpAddress(input);
                        if (ip.equalsIgnoreCase(BREAK_CHECK)) {
                            break;
                        }
                        networkManagementSys.activateRu(ip);
                        returnToMenu();
                        break;
                    case "8":
                        //8.Deactivate Radio Unit
                        ip = getIpAddress(input);
                        if (ip.equalsIgnoreCase(BREAK_CHECK)) {
                            break;
                        }
                        networkManagementSys.deactivateRu(ip);
                        returnToMenu();
                        break;
                    case "9":
                        // 9.Setup Carrier on Radio Unit
                        System.out.println("To setup a carrier on Radio Unit, you need to have a Radio Unit ready first");
                        ip = getIpAddress(input);
                        if (ip.equalsIgnoreCase(BREAK_CHECK)) {
                            break;
                        }
                        if (createCarrierOnRu(ip, input)) {
                            returnToMenu();
                        }
                        break;
                    case "10":
                        // 10.Modify Carrier on Radio Unit
                        ip = getIpAddress(input);
                        if (ip.equalsIgnoreCase("BACK")) {
                            break;
                        }
                        carrierId = getCarrierId(input);
                        if (carrierId == -1) {
                            break;
                        }
                        freqBand = chooseFreqBand(networkManagementSys.getRuRatType(ip), input);
                        if (freqBand == null) {
                            break;
                        }
                        networkManagementSys.modifyCarrierOnRu(ip, carrierId, freqBand);
                        returnToMenu();
                        break;
                    case "11":
                        //11.Remove Carrier on Radio Unit
                        ip = getIpAddress(input);
                        if (ip.equalsIgnoreCase(BREAK_CHECK)) {
                            break;
                        }
                        carrierId = getCarrierId(input);
                        if (carrierId == -1) {
                            break;
                        }
                        System.out.println();
                        networkManagementSys.removeCarrierOnRu(ip, carrierId);
                        returnToMenu();
                        break;
                    case "12":
                        //12.Remove all Carriers on Radio Unit
                        ip = getIpAddress(input);
                        if (ip.equalsIgnoreCase(BREAK_CHECK)) {
                            break;
                        }
                        System.out.println();
                        networkManagementSys.removeAllCarriersOnRu(ip);
                        returnToMenu();
                        break;
                    case "13":
                        //13.Signal scaling on Radio Unit
                        ip = getIpAddress(input);
                        if (ip.equalsIgnoreCase(BREAK_CHECK)) {
                            break;
                        }
                        System.out.println();
                        networkManagementSys.signalScalingOnRu(ip);
                        returnToMenu();
                        break;
                    case "14":
                        //14.Post activation
                        ip = getIpAddress(input);
                        if (ip.equalsIgnoreCase(BREAK_CHECK)) {
                            break;
                        }
                        System.out.println();
                        networkManagementSys.postActivation(ip);
                        returnToMenu();
                        break;
                    case "15":
                        //15.Perform self diagnostics
                        ip = getIpAddress(input);
                        if (ip.equalsIgnoreCase(BREAK_CHECK)) {
                            break;
                        }
                        System.out.println();
                        networkManagementSys.performSelfDiagnostics(ip);
                        returnToMenu();
                        break;
                    case "16":
                        //16.List network Inventory
                        System.out.println();
                        networkManagementSys.listNetworkInventory();
                        returnToMenu();
                        break;
                    case "17":
                        //17.List RUs by standard
                        input.nextLine();
                        ratType = chooseRatType(input);
                        if (ratType == null) {
                            break;
                        }
                        System.out.println();
                        networkManagementSys.listRuByParam(ratType);
                        returnToMenu();
                        break;
                    case "18":
                        //18.List RUs by state
                        RadioUnitStateE state = chooseRuState(input);
                        if (state == null) {
                            break;
                        }
                        System.out.println();
                        networkManagementSys.listRuByParam(state);
                        returnToMenu();
                        break;
                    case "19":
                        //19.List RUs by Band
                        ratType = chooseRatType(input);
                        if (ratType == null) {
                            break;
                        }
                        freqBand = chooseFreqBand(ratType, input);
                        if (freqBand == null) {
                            break;
                        }
                        System.out.println();
                        networkManagementSys.listRuByParam(freqBand);
                        returnToMenu();
                        break;
                    case "20":
                        //20.List Radio Unit details
                        ip = getIpAddress(input);
                        if (ip.equalsIgnoreCase(BREAK_CHECK)) {
                            break;
                        }
                        System.out.println();
                        networkManagementSys.listRadioUnitDetails(ip);
                        returnToMenu();
                        break;
                    case "21":
                        //21.Set Alarm on RU
                        ip = getIpAddress(input);
                        if (ip.equalsIgnoreCase(BREAK_CHECK)) {
                            break;
                        }
                        AlarmStatusLevel alarm = chooseAlarmStatusLevel(input);
                        if (alarm == null) {
                            break;
                        }
                        System.out.println();
                        networkManagementSys.setAlarmOnRu(ip, alarm);
                        returnToMenu();
                        break;
                    case "22":
                        //22.List all Network Alarms
                        System.out.println(networkManagementSys.getNetworkAlarms());
                        returnToMenu();
                        break;
                    case "23":
                        //23.Acknowledge alarm on RU
                        ip = getIpAddress(input);
                        if (ip.equalsIgnoreCase(BREAK_CHECK)) {
                            break;
                        }
                        System.out.println();
                        if (networkManagementSys.acknowledgeAlarm(ip)) {
                            System.out.printf("Successfully acknowledged alarm on the Radio Unit with the IP address \"%s\"", ip);
                        } else {
                            System.out.printf("Failed to acknowledge alarm on the Radio Unit with the IP address \"%s\"", ip);
                        }
                        returnToMenu();
                        break;
                    default:
                        System.out.println("Unsupported option, please try again!");
                        break;
                }

            } catch (Exception e) {
                e.printStackTrace();
                System.out.println(INVALID_INPUT);
            }

        } while (!option.equals("0"));
        System.exit(0);
    } // main

    private static void returnToMenu() {
        System.out.print("\nPlease press the Enter key to return to the main menu.");
        try {
            System.in.read();
        } catch (Exception e) {
        }
    }

    /**
     * Helper method that get the alarm from user.
     * Returns the alarm user selected.
     *
     * @param input Scanner instance to get user input.
     * @return An alarm from the AlarmStatusLevel enum.
     */
    private static AlarmStatusLevel chooseAlarmStatusLevel(Scanner input) {
        AlarmStatusLevel alarm = null;
        int i;

        do {
            i = 0;
            System.out.println("Please choose an Alarm for the Radio Unit.");
            for (AlarmStatusLevel currAlarm : AlarmStatusLevel.values()) {
                System.out.println("	" + ++i + ".	" + currAlarm.toString());
                if (i == 3) {
                    break;
                }
            }
            System.out.println(BACK_TO_MENU);
            System.out.print(INPUT_PROMPT);
            String subOption = input.nextLine().trim();
            if (subOption.equalsIgnoreCase(BREAK_CHECK)) {
                break;
            }
            try {
                if (i < 4) {
                    alarm = AlarmStatusLevel.values()[Integer.parseInt(subOption) - 1];
                } else {
                    throw new NumberFormatException();
                }
            } catch (ArrayIndexOutOfBoundsException | NumberFormatException ex) {
                System.out.println(INVALID_INPUT);
            }
        } while (alarm == null);

        return alarm;
    }

    /**
     * Prepare Observer relations between classes.
     * Currently, these include the Commission/Decommission classes, the
     * NetworkManagementSystem, the Mediator, and the NetworkManagementClient.
     */
    private static void setupObserverRelations() {
        // Setup Observer relations
        MediatorIf mediator = Mediator.getInstance();
        networkManagementSys = NetworkManagementSystem.getInstance();

        try {
            Naming.rebind(NETWORK_MGMT_ID, networkManagementSys);

            networkManagementSys.addPropertyChangeListener((PropertyChangeListener) mediator);
            mediator.addPropertyChangeListener((PropertyChangeListener) networkManagementSys);

            for (Map.Entry<RatType, CommissionRadioUnit> entry : networkManagementSys.getCommissioners().entrySet()) {
                entry.getValue().addPropertyChangeListener((PropertyChangeListener) mediator);
            }

            for (Map.Entry<RatType, DecommissionRadioUnit> entry : networkManagementSys.getDecommissioners().entrySet()) {
                entry.getValue().addPropertyChangeListener((PropertyChangeListener) mediator);
            }
        } catch (RemoteException | MalformedURLException ex) {
            System.out.println("Failed to connect to remote server");
            ex.printStackTrace();
            System.exit(0);
        }
    }


    /**
     * Helper method that create a carrier on existing RU based on user-choose
     * RAT type, RF Ports, Frequency band and transmitting power.
     * The option also exists to create a stand-alone carrier that will
     * not be added to an RU on creation.
     *
     * @param ip    The name of the radio unit to create carrier on.
     *              Pass as null to create carrier that isn't associated
     *              with an RU.
     * @param input Scanner instance to get user input.
     * @return True if a Carrier was created, false otherwise.
     */

    private static boolean createCarrierOnRu(String ip, Scanner input) {
        List<RfPort> rfPorts;
        FrequencyBand freqBand;
        double transPower;

        try {
            // Get the RatType of the RU we are currently looking at.
            RatType currRatType = networkManagementSys.getRuRatType(ip);

            if (currRatType == null) {
                return false;
            }

            System.out.println();

            //get RF Ports
            int noOfRfPorts;
            if (currRatType.equals(RatType.LTE)) {
                noOfRfPorts = LTE_RF_PORTS_NUMBER;
            } else {
                noOfRfPorts = WCDMA_RF_PORTS_NUMBER;
            }
            rfPorts = chooseRfPorts(noOfRfPorts, input);
            if (rfPorts.size() < noOfRfPorts) {
                return false;
            }

            System.out.println();

            //get Frequency band
            freqBand = chooseFreqBand(currRatType, input);
            if (freqBand == null) {
                return false;
            }

            System.out.println();

            //get transmitting power
            transPower = getTransPower(input);
            if (transPower == -99999) {
                return false;
            }

            System.out.println();
            networkManagementSys.setupCarrierOnRu(ip, rfPorts, freqBand, transPower);
        } catch (RemoteException ex) {
            System.out.println("Failed to connect to remote server");
            ex.printStackTrace();
            System.exit(0);
        }
        return true;
    }

    /**
     * Helper method to get the Carrier ID from user
     * Returns the Carrier ID user entered
     *
     * @param input Scanner instance to get user input.
     * @return Radio Unit State Enum.
     */
    private static RadioUnitStateE chooseRuState(Scanner input) {
        RadioUnitStateE state = null;
        int i;

        do {
            i = 0;
            System.out.println("Please choose the Radio Unit State.");
            for (RadioUnitStateE ruState : RadioUnitStateE.values()) {
                System.out.println("	" + ++i + ".	" + ruState.getRuState());
            }
            System.out.println(BACK_TO_MENU);
            System.out.print(INPUT_PROMPT);
            String subOption = input.nextLine().trim();
            if (subOption.equalsIgnoreCase(BREAK_CHECK)) {
                break;
            }
            try {
                state = RadioUnitStateE.values()[Integer.parseInt(subOption) - 1];
            } catch (ArrayIndexOutOfBoundsException | NumberFormatException ex) {
                System.out.println(INVALID_INPUT);
            }
        } while (state == null);

        return state;
    }

    /**
     * Helper method to get the Carrier ID from user
     * Returns the Carrier ID user entered
     *
     * @param input Scanner instance to get user input.
     * @return Carrier ID, as an int.
     */
    private static int getCarrierId(Scanner input) {
        int carrierId;
        do {
            System.out.print("Please enter the Carrier ID as a number.\n"
                    + BACK_TO_MENU);
            System.out.print(INPUT_PROMPT);
            String id = input.nextLine().trim();
            if (id.equalsIgnoreCase(BREAK_CHECK)) {
                return -1;
            }
            try {
                carrierId = Integer.parseInt(id);
                break;
            } catch (NumberFormatException e) {
                System.out.println(INVALID_INPUT);
            }
        } while (true);

        return carrierId;
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
            System.out.print("\nPlease enter the IP address of the Radio Unit you want to operate on.\n" +
                    "For example: 123.42.234.122\n"
                    + BACK_TO_MENU);
            System.out.print(INPUT_PROMPT);
            ip = input.nextLine().trim();
            if (ip.equalsIgnoreCase(BREAK_CHECK)) {
                break;
            }
            if (IP_PATTERN.matcher(ip).matches()) {
                break;
            }
            System.out.println(INVALID_INPUT);
        } while (true);

        return ip;
    }

    /**
     * Helper method that get the transmitting power for carrier from user
     * Returns the transmitting power user entered
     *
     * @param input Scanner instance to get user input.
     * @return The transmitting power, as a double.
     */
    private static double getTransPower(Scanner input) {
        double transPower = -99999;
        do {
            System.out.println("\nPlease enter a number for the transmitting power.\n"
                    + BACK_TO_MENU);
            System.out.print(INPUT_PROMPT);
            String transPowerText = input.next();
            if (transPowerText.equalsIgnoreCase(BREAK_CHECK)) {
                break;
            }
            try {
                transPower = Double.parseDouble(transPowerText);
                break;
            } catch (NumberFormatException e) {
                System.out.println(INVALID_INPUT);
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
        int i;

        do {
            i = 0;
            System.out.println("\nPlease choose a Vendor for the Radio Unit.");
            for (Vendor currVendor : Vendor.values()) {
                System.out.println("	" + ++i + ".	" + currVendor.getLabel());
            }
            System.out.println(BACK_TO_MENU);
            System.out.print(INPUT_PROMPT);
            String subOption = input.nextLine().trim();
            if (subOption.equalsIgnoreCase(BREAK_CHECK)) {
                break;
            }
            try {
                vendor = Vendor.values()[Integer.parseInt(subOption) - 1];
            } catch (ArrayIndexOutOfBoundsException | NumberFormatException ex) {
                System.out.println(INVALID_INPUT);
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
        int i;
        do {
            i = 0;
            System.out.println("\nPlease choose a RAT type for the Radio Unit.");
            for (RatType currRatType : RatType.values()) {
                System.out.println("	" + ++i + ".	" + currRatType.getLabel());
            }
            System.out.println(BACK_TO_MENU);
            System.out.print(INPUT_PROMPT);
            String subOption = input.nextLine().trim();
            if (subOption.equalsIgnoreCase(BREAK_CHECK)) {
                break;
            }
            try {
                rat = RatType.values()[Integer.parseInt(subOption) - 1];
            } catch (ArrayIndexOutOfBoundsException | NumberFormatException ex) {
                System.out.println(INVALID_INPUT);
            }
        } while (rat == null);

        return rat;
    }


    /**
     * Helper method that get the RF Ports from user.
     * Returns a list of RF Ports.
     *
     * @param input Scanner instance to get user input.
     * @return The RfPorts that were selected by the user, as a List.
     */
    private static List<RfPort> chooseRfPorts(int noOfRfPorts, Scanner input) {
        List<RfPort> ports = new ArrayList<>();
        int count = 0;
        int i = 0;
        Set<Integer> choices = new HashSet<>();

        System.out.printf("Please choose %d RF ports\n", noOfRfPorts);
        for (RfPort currRfPort : RfPort.values()) {
            System.out.println("	" + ++i + ".	" + currRfPort.getRfPort());
        }
        System.out.println(BACK_TO_MENU);
        System.out.print(INPUT_PROMPT);
        do {
            String subOption = input.next();
            if (subOption.equalsIgnoreCase(BREAK_CHECK)) {
                break;
            }
            try {
                if (choices.contains(Integer.parseInt(subOption))) {
                    System.out.println("This RF port has already been picked, please try again.\nEnter the next choice.");
                    System.out.print(INPUT_PROMPT);
                    continue;
                }
                choices.add(Integer.parseInt(subOption));
                ports.add(RfPort.values()[Integer.parseInt(subOption) - 1]);
                count++;
            } catch (ArrayIndexOutOfBoundsException | NumberFormatException ex) {
                System.out.println(INVALID_INPUT);
                continue;
            }
            if (count != noOfRfPorts) {
                System.out.println("Enter the next choice.");
                System.out.print(INPUT_PROMPT);
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
        int i;
        do {
            i = 0;
            System.out.println("Please choose the Carrier Frequency Band.");
            if (!ratType.equals(RatType.LTE)) {
                for (WcdmaFrequencyBand currWcdmaFreq : WcdmaFrequencyBand.values()) {
                    System.out.println("	" + ++i + ".	" + currWcdmaFreq.getBand());
                }
            } else {
                for (LteFrequencyBand currLteFreq : LteFrequencyBand.values()) {
                    System.out.println("	" + ++i + ".	" + currLteFreq.getBand());
                }
            }
            System.out.println(BACK_TO_MENU);
            System.out.print(INPUT_PROMPT);
            String subOption = input.next();
            if (subOption.equalsIgnoreCase(BREAK_CHECK)) {
                break;
            }
            try {
                if (ratType.equals(RatType.LTE)) {
                    freqBand = LteFrequencyBand.values()[Integer.parseInt(subOption) - 1];
                } else {
                    freqBand = WcdmaFrequencyBand.values()[Integer.parseInt(subOption) - 1];
                }
            } catch (ArrayIndexOutOfBoundsException | NumberFormatException ex) {
                System.out.println(INVALID_INPUT);
            }
        } while (freqBand == null);

        return freqBand;
    }
}
