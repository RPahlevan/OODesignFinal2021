package userinterface;

import common.*;
import mediator.Mediator;
import radiounit.ManagedRadioUnit;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * The UserInterface class represents the current main access
 * point for demo 1 of the Wireless Network Management System.
 */
public class UserInterface {
    private static Mediator mediator;

    public static void main(String[] args) {
        String option;
        String ruName;

        mediator = Mediator.getInstance();

        System.out.println("Welcome!");
        Scanner input = new Scanner(System.in);
        do {
            option = "-1";
            try {
                String menu = "\nPlease enter the number corresponding to the option you would like to choose:\n"
                        + "0.   Exit Program\n"
                        + "1.   Create Radio Unit\n"
                        + "2.   Create Carrier on Radio Unit\n"
                        + "3.   Create Carrier and Radio Unit\n"
                        + "4.   List carriers on RU\n"
                        + "5.   List RU supported RAT type\n"
                        + "6.   List Radio Unit vendor\n"
                        + "7.   List RU alarm status level\n"
                        + "8.   List registered Radio Units\n"
                        + "9.   List all Carriers\n";

                System.out.println(menu);
                option = input.next();
                switch (option) {
                    case "0" -> System.out.println("Goodbye!\n");
                    case "1" -> {
                        // 1.Create Radio Unit
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
                        mediator.createRu(ruName, vendor, ratType);
                        System.out.println("Radio Unit " + "'" + ruName + "'" + " has been successfully created");
                    }
                    case "2" -> {
                        // 2.Create Carrier on Radio Unit
                        System.out.println("To create a carrier on Radio Unit, you need to have a Radio Unit ready first\n"
                                + "Please enter the Radio Unit name:\n"
                                + "Enter 'Back' to go back to the menu\n");
                        ruName = input.next();
                        if (ruName.equalsIgnoreCase("BACK")) {
                            break;
                        }
                        createCarrierOnRu(ruName, input);
                        System.out.println("Carrier has been successfully created on " + "Radio Unit " + "'" + ruName + "'");
                    }
                    case "3" -> {
                        //3.Create Carrier and Radio Unit
                        System.out.println("Please enter a new Radio Unit name\n"
                                + "For example: LTE#1\n"
                                + "Enter 'Back' to go back to the menu\n");
                        ruName = input.next();
                        if (ruName.equalsIgnoreCase("BACK")) {
                            break;
                        }
                        createCarrierAndRu(ruName, input);
                    }
                    case "4" -> {
                        //4.List carriers on RU
                        System.out.println("Please enter the name of the Radio Unit you want to list the carriers of:\n"
                                + "For example: LTE#1\n"
                                + "Enter 'Back' to go back to the menu\n");
                        ruName = input.next();
                        if (ruName.equalsIgnoreCase("BACK")) {
                            break;
                        }
                        mediator.displayCarrierOnRu(ruName);
                    }
                    case "5" -> {
                        //5.Display RU supported RAT types
                        System.out.println("Please enter the name of the Radio Unit you want to list the supported RAT type of:\n"
                                + "For example: LTE#1\n"
                                + "Enter 'Back' to go back to the menu\n");
                        ruName = input.next();
                        if (ruName.equalsIgnoreCase("BACK")) {
                            break;
                        }
                        mediator.printRatType(ruName);
                    }
                    case "6" -> {
                        //6.List Radio Unit vendor
                        System.out.println("Please enter the name of the Radio Unit you want to list the vendor of:\n"
                                + "For example: LTE#1\n"
                                + "Enter 'Back' to go back to the menu\n");
                        ruName = input.next();
                        if (ruName.equalsIgnoreCase("BACK")) {
                            break;
                        }
                        mediator.printVendor(ruName);
                    }
                    case "7" -> {
                        //7.List RU alarm status level
                        System.out.println("Please enter the name of the Radio Unit you want to list the alarm status level of:\n"
                                + "For example: LTE#1\n"
                                + "Enter 'Back' to go back to the menu\n");
                        ruName = input.next();
                        if (ruName.equalsIgnoreCase("BACK")) {
                            break;
                        }
                        mediator.printAlarmStatus(ruName);
                    }
                    case "8" ->
                            //8.List registered Radio Units
                            mediator.printRegisteredRadioUnits();
                    case "9" ->
                            //9.List all created carriers.
                            mediator.printCreatedCarriers();
                    default -> System.out.println("Unsupported option, please try again!");
                }

            } catch (Exception e) {
                System.out.println("Invalid input, please try again!");
            }

        } while (!option.equals("0"));
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
     * RAT type, RF Ports, Frequency band and transmitting power.
     * The option also exists to create a stand-alone carrier that will
     * not be added to an RU on creation.
     *
     * @param ruName The name of the radio unit to create carrier on.
     *               Pass as null to create carrier that isn't associated
     *               with an RU.
     * @param input  Scanner instance to get user input.
     */

    private static void createCarrierOnRu(String ruName, Scanner input) {
        List<RfPort> rfPorts;
        FrequencyBand freqBand;
        double transPower;

        ManagedRadioUnit ru = mediator.getRadioUnit(ruName);

        //get RF Ports
        rfPorts = chooseRfPorts(ru.getRatType(), input);

        //get Frequency band
        freqBand = chooseFreqBand(ru.getRatType(), input);

        //get transmitting power
        transPower = getTransPower(input);

        mediator.createCarrierOnRu(rfPorts, freqBand, transPower, ruName);

    }


    /**
     * Helper method that create a carrier on existing RU based on user-choose
     * RAT type, RF ports, frequency band and transmitting power.
     *
     * @param ruName The name of the radio unit to create carrier on.
     * @param input  Scanner instance to get user input.
     */

    private static void createCarrierAndRu(String ruName, Scanner input) {
        Vendor vendor;
        RatType ratType;
        List<RfPort> rfPorts;
        FrequencyBand freqBand;
        double transPower;

        //get vendor for radio unit
        vendor = chooseVendor(input);

        //get RAT type
        ratType = chooseRatType(input);

        //get RF Ports
        rfPorts = chooseRfPorts(ratType, input);

        //get Frequency band
        freqBand = chooseFreqBand(ratType, input);

        //get transmitting power
        transPower = getTransPower(input);

        mediator.createCarrierAndRu(rfPorts, freqBand, transPower, ruName, vendor, ratType);

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
            noOfRfPorts = RF_PORT_NUMBER.LTE_RF_PORTS_NUMBER.getValue();
        } else {
            noOfRfPorts = RF_PORT_NUMBER.WCDMA_RF_PORTS_NUMBER.getValue();
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
        if (ratType.equals(RatType.LTE)) {
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

} // UserInterface class
