package mediator;

import radiounit.RadioUnit;

import java.util.ArrayList;
import java.util.List;

/**
 * The ConcreteMediator class is responsible for handling control/communication
 * logic between various system throughout the Wireless Network Management System.
 * <p>
 * In the current iteration, the mediator handles the references to various RUs
 * and manages communication between these RUs and the CarrierManagementSystem.
 * <p>
 * In future iterations, the mediator will also handle communication between
 * the Network Management System while maintaining a list of references to
 * built RUs.
 * <p>
 * This class acts a Singleton to maintain data-consistency given the requirement
 * that the system should be able to handle multiple users at once. Having two
 * ConcreteMediators would result in two distinct lists of RUs, which would cause
 * a communication breakdown between the various systems as they try to determine
 * (and fail to do so) which mediator contains the RU they need to interface with.
 */
public class ConcreteMediator implements Mediator {
    private static volatile ConcreteMediator UNIQUE_INSTANCE = new ConcreteMediator();
    private List<RadioUnit> radioUnits;
    private CarrierManagementIf carrierManagement;

    /**
     * Constructor for the ConcreteMediator class.
     * Required to be private to ensure the Singleton Pattern is followed.
     */
    private ConcreteMediator() {
        radioUnits = new ArrayList<>();
        carrierManagement = new CarrierManagementSystemDirector();
    }

    /**
     * Get the unique ConcreteMediator instance.
     *
     * @return The Singleton instance of the ConcreteMediator class.
     */
    public static ConcreteMediator getInstance() {
        return UNIQUE_INSTANCE;
    }

    /**
     * Registers an RU with the mediator. Only RUs that are registered can
     * be interacted with via other classes.
     *
     * @param radioUnit The RU that will be registered with the mediator.
     */
    private synchronized void register(RadioUnit radioUnit) {
        if (!radioUnits.contains(radioUnit)) {
            radioUnits.add(radioUnit);
        }
    }

    /**
     * Returns a list of RUs currently registered with the mediator.
     *
     * @return A list of registered RUs.
     */
    @Override
    public List<RadioUnit> getRegisteredRadioUnits() {
        return radioUnits;
    }

    /**
     * Prints a formatted list of RUs currently registered with the mediator.
     */
    @Override
    public void printRegistereredRaidoUnits() {
        radioUnits.forEach(ru -> ru.print());
    }

    /**
     * Print the RAT type for a RU associated with the specified name.
     *
     * @param name The name associated with the targeted RU.
     */
    @Override
    public void printRatType(String name) {
        radioUnits.forEach(ru -> {
            if (ru.getRadioUnitName().equals(name)) {
                System.out.println(ru.getRatType());
                return;
            }
        });
        System.out.printf("[ERROR] No RUs exist with the name: %s%n", name);
    }

    /**
     * Print the vendor for a RU associated with the specified name.
     *
     * @param name The name associated with the targeted RU.
     */
    @Override
    public void printVendor(String name) {
        radioUnits.forEach(ru -> {
            if (ru.getRadioUnitName().equals(name)) {
                System.out.println(ru.getVendorType());
                return;
            }
        });
        System.out.printf("[ERROR] No RUs exist with the name: %s%n", name);
    }

    /**
     * Print the alarm for a RU associated with the specified name.
     *
     * @param name The name associated with the targeted RU.
     */
    @Override
    public void printAlarmStatus(String name) {
        radioUnits.forEach(ru -> {
            if (ru.getRadioUnitName().equals(name)) {
                System.out.println(ru.getAlarmStatus());
                return;
            }
        });
        System.out.printf("[ERROR] No RUs exist with the name: %s%n", name);
    }

    /**
     * Create a bare bones RU. In the future this method will handle the
     * control logic associated with which RU is to be created. For now,
     * We just create a generic RU with the passed parameters.
     *
     * @param name    The name of the RU.
     * @param vendor  The vendor for the RU.
     * @param ratType The RAT type for the RU.
     */
    @Override
    public void createRu(String name, Vendor vendor, RatType ratType) {
        /* We are making a barebones RU, so we *technically* don't care
         * about figuring out what kind of RU is being made.
         * If we do, this will be updated to call the appropriate
         * constructor via switch statements.
         */
        this.register(new RadioUnit(name, vendor, ratType));
    }

    public void createCarrierAndRu(List<RfPorts> rfPorts, CarrierFrequencies carrierFrequencies,
                                   Double transmittingPower, String name, Vendor vendor, RatType ratType) {
        createRu(name, vendor, ratType);
        createCarrier(rfPorts, carrierFrequencies, transmittingPower, name);
    }

    public void createCarrier(List<RfPorts> rfPorts, CarrierFrequencies carrierFrequencies,
                              Double transmittingPower, String ruName) {
        radioUnits.forEach(ru -> {
            if (ru.getRadioUnitName().equals(ruName)) {
                switch(rfPorts.size()) {
                    case 2:
                        ru.setupCarrier(carrierManagement.createWcdmaCarrier(rfPorts, carrierFrequencies, transmittingPower));
                        return;
                    case 4:
                        ru.setupCarrier(carrierManagement.createLteCarrier(rfPorts, carrierFrequencies, transmittingPower));
                        return;
                    default:
                        System.out.printf(
                                "[ERROR] Invalid number of RF ports. Requires 2 or 4, but got %s%n", rfPorts.size());
                        return;
                }
            }
        });
        System.out.printf(
                "[ERROR] No RU with that name %s is registered with the system%n", ruName);
    }

    /**
     * Displays the carrier associated with the ID supplied.
     *
     * @param carrierId The ID number for the carrier that will be displayed.
     */
    @Override
    public void displayCarrier(int carrierId) {
        radioUnits.forEach(ru -> ru.getCarriers().forEach(carrier -> {
            if (carrier.getCarrierId() == carrierId) {
                carrier.print();
                return;
            }
        }));
        System.out.printf(
                "[ERROR] No carriers with the ID %d have been registered with any registered RUs.%n", carrierId);
    }

}
