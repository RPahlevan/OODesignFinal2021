package userinterface;

import common.FrequencyBand;
import common.RatType;
import common.RfPort;
import common.Vendor;
import mediator.ConcreteMediator;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserInterface {
    static ConcreteMediator mediator;

    public static void main(String[] args) {
        String option;
        String ruName;

        mediator = ConcreteMediator.getInstance();

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
                        if (ruName.equalsIgnoreCase("BACK")) {
                            break;
                        }
                        Vendor vendor = chooseVendor(input);
                        RatType ratType = chooseRATtype(input);
                        mediator.createRu(ruName, vendor, ratType);
                        System.out.println("Radio Unit " + "'" + ruName + "'" + " has been successfully created");
                        break;
                    case "2":
                        // 2.Create Carrier on Radio Unit
                        System.out.println("To create carrier on Radio Unit, you need to have a Radio Unit ready first\n"
                                + "Please enter the Radio Unit name:\n"
                                + "Enter 'Back' to go back to the menu\n");
                        ruName = input.next();
                        if (ruName.equalsIgnoreCase("BACK")) {
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
                        if (ruName.equalsIgnoreCase("BACK")) {
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
                        if (ruName.equalsIgnoreCase("BACK")) {
                            break;
                        }
                        mediator.displayCarrierOnRu(ruName);
                        break;
                    case "5":
                        //5.Display RU supported RAT types
                        System.out.println("Please enter the name of the Radio Unit you want to list the supported RAT type of:\n"
                                + "For example: LTE#1\n"
                                + "Enter 'Back' to go back to the menu\n");
                        ruName = input.next();
                        if (ruName.equalsIgnoreCase("BACK")) {
                            break;
                        }
                        mediator.printRatType(ruName);
                        break;
                    case "6":
                        //6.List Radio Unit vendor
                        System.out.println("Please enter the name of the Radio Unit you want to list the vendor of:\n"
                                + "For example: LTE#1\n"
                                + "Enter 'Back' to go back to the menu\n");
                        ruName = input.next();
                        if (ruName.equalsIgnoreCase("BACK")) {
                            break;
                        }
                        mediator.printVendor(ruName);
                        break;
                    case "7":
                        //7.List RU alarm status level
                        System.out.println("Please enter the name of the Radio Unit you want to list the valarm status level of:\n"
                                + "For example: LTE#1\n"
                                + "Enter 'Back' to go back to the menu\n");
                        ruName = input.next();
                        if (ruName.equalsIgnoreCase("BACK")) {
                            break;
                        }
                        mediator.printAlarmStatus(ruName);
                        break;
                    case "8":
                        //8.List registerd Radio Units
                        mediator.printRegisteredRaidoUnits();
                        break;
                    default:
                        System.out.println("Unsupported option, please try again!");
                        break;

                }

            } catch (Exception e) {
                System.out.println("Invalid input, please try again!");
                continue;
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
     * RAT typre, RF Ports, Frequency band and transmitting power
     *
     * @param ruName   name of the radio unit to create carrier on
     * @return void
     */

    private static void createCarrierOnRu(String ruName, Scanner input) {
        RatType ratType;
        List<RfPort> rfPorts;
        FrequencyBand freqBand;
        double transPower;

        // get RAT type
        ratType = chooseRATtype(input);

        //get RF Ports
        rfPorts = chooseRfPorts(ratType, input);

        //get Frequency band
        freqBand = chooseFreqBand(ratType, input);

        //get transmitting power
        transPower = getTransPower(input);

        mediator.createCarrier(rfPorts, freqBand, transPower, ruName);

    }


    /**
     * Helper method that create a carrier on existing RU based on user-choose
     * RAT type, RF ports, frequency band and transmitting power.
     *
     * @param ruName name of the radio unit to create carrier on
     * @return void
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
     * @param input Scanner instance to get user input
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
            } catch (NumberFormatException e) {
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
     * @param input Scanner instance to get user input
     * @return Vendor   vendor
     */
    private static Vendor chooseVendor(Scanner input) {
        Vendor vendor = null;
        System.out.println("Please choose a Vendor for Radio Unit:\n"
                + "1.	Ericsson\n"
                + "2.	Nokia\n");

        do {
            String subOption = input.next();
            switch (subOption) {
                case "1":
                    vendor = Vendor.ERICSSON;
                    break;
                case "2":
                    vendor = Vendor.NOKIA;
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
     * @param input Scanner instance to get user input
     * @return RatType   the RAT type
     */
    private static RatType chooseRATtype(Scanner input) {
        RatType rat = null;
        System.out.println("\nPlease choose a RAT type:\n"
                + "1.	LTE\n"
                + "2.	WCDM\n");
        do {
            String subOption = input.next();
            switch (subOption) {
                case "1":
                    rat = RatType.LTE;
                    break;
                case "2":
                    rat = RatType.WCDMA;
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
     * Returns a list of RF Ports.
     *
     * @param input Scanner instance to get user input
     * @return RfPorts[]   an array of RF ports
     */
    private static List<RfPort> chooseRfPorts(RatType ratType, Scanner input) {
        int noOfRfPorts;
        if (ratType.equals("LTE")) {
            noOfRfPorts = RF_PORT_NUMBER.LTE_RF_PORTS_NUMBER.getValue();
        } else {
            noOfRfPorts = RF_PORT_NUMBER.WCDMA_RF_PORTS_NUMBER.getValue();
        }
        List<RfPort> ports = new ArrayList<>();
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
                    ports.add(RfPort.RF_0);
                    count++;
                    break;
                case "2":
                    ports.add(RfPort.RF_1);
                    count++;
                    break;
                case "3":
                    ports.add(RfPort.RF_2);
                    count++;
                    break;
                case "4":
                    ports.add(RfPort.RF_3);
                    count++;
                    break;
                case "5":
                    ports.add(RfPort.RF_4);
                    count++;
                    break;
                case "6":
                    ports.add(RfPort.RF_5);
                    count++;
                    break;
                case "7":
                    ports.add(RfPort.RF_6);
                    count++;
                    break;
                case "8":
                    ports.add(RfPort.RF_7);
                    count++;
                    break;
                default:
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
     * helper method that get the frequency band from user
     * returns the frequency band user choose.
     *
     * @param input Scanner instance to get user input
     * @return CarrierFrequencies   Carrier frequency band
     */
    private static FrequencyBand chooseFreqBand(RatType ratType, Scanner input) {
        FrequencyBand freqBand = null;
        System.out.println("Please choose Carrier Frequency Band:");
        if (ratType.equals(RatType.LTE)) {
            System.out.println(
                    "1.	LTE_BAND_1  = 1920 MHz\n"
                            + "2.	LTE_BAND_2  = 1850 MHz\n"
                            + "3.	LTE_BAND_3  = 1710 MHz\n"
                            + "4.	LTE_BAND_4  = 1755 MHz\n"
                            + "5.	LTE_BAND_5  = 824 MHz\n"
                            + "6.	LTE_BAND_6  = 830 MHz\n"
                            + "7.	LTE_BAND_7  = 2500 MHz\n"
                            + "8.	LTE_BAND_8 = 880 MHz\n");
        } else {
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
                    if (ratType.equals(RatType.LTE)) {
                        freqBand = FrequencyBand.LTE_BAND_1;
                    } else {
                        freqBand = FrequencyBand.WCDMA_BAND_1;
                    }
                    break;
                case "2":
                    if (ratType.equals(RatType.LTE)) {
                        freqBand = FrequencyBand.LTE_BAND_2;
                    } else {
                        freqBand = FrequencyBand.WCDMA_BAND_2;
                    }
                    break;
                case "3":
                    if (ratType.equals(RatType.LTE)) {
                        freqBand = FrequencyBand.LTE_BAND_3;
                    } else {
                        freqBand = FrequencyBand.WCDMA_BAND_3;
                    }
                    break;
                case "4":
                    if (ratType.equals(RatType.LTE)) {
                        freqBand = FrequencyBand.LTE_BAND_4;
                    } else {
                        freqBand = FrequencyBand.WCDMA_BAND_4;
                    }
                    break;
                case "5":
                    if (ratType.equals(RatType.LTE)) {
                        freqBand = FrequencyBand.LTE_BAND_5;
                    } else {
                        freqBand = FrequencyBand.WCDMA_BAND_5;
                    }
                    break;
                case "6":
                    if (ratType.equals(RatType.LTE)) {
                        freqBand = FrequencyBand.LTE_BAND_6;
                    } else {
                        freqBand = FrequencyBand.WCDMA_BAND_6;
                    }
                    break;
                case "7":
                    if (ratType.equals(RatType.LTE)) {
                        freqBand = FrequencyBand.LTE_BAND_7;
                    } else {
                        freqBand = FrequencyBand.WCDMA_BAND_7;
                    }
                    break;
                case "8":
                    if (ratType.equals(RatType.LTE)) {
                        freqBand = FrequencyBand.LTE_BAND_8;
                    } else {
                        freqBand = FrequencyBand.WCDMA_BAND_8;
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
