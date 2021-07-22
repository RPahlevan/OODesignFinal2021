package mediator;

import carriermanagementsystem.CarrierManagementSystemDirector;
import common.*;
import networkmanagementsystem.*;
import radiounit.DemoOneRadioUnit;
import radiounit.ManagedRadioUnit;
import radiounit.RadioUnitState;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

import static common.ProcedureOptions.RELEASE;
import static common.RatType.LTE;
import static common.RatType.WCDMA;

/**
 * The Mediator class is responsible for handling control/communication
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
 *
 * @author ebreojh
 */
public class Mediator implements PropertyChangeListener, MediatorIf {
    private static volatile Mediator UNIQUE_INSTANCE;
    private final PropertyChangeSupport support;
    private final List<ManagedRadioUnit> radioUnits;
    private final CarrierManagementSystemDirector carrierManagement;

    /**
     * Constructor for the Mediator class.
     * Required to be private to ensure the Singleton Pattern is followed.
     */
    private Mediator() {
        support = new PropertyChangeSupport(this);
        radioUnits = new ArrayList<>();
        carrierManagement = CarrierManagementSystemDirector.getInstance();
    }

    /**
     * Get the unique Mediator instance.
     *
     * @return The Singleton instance of the Mediator class.
     */
    public static Mediator getInstance() {
        if (UNIQUE_INSTANCE == null) {
            synchronized (Mediator.class) {
                if (UNIQUE_INSTANCE == null) {
                    UNIQUE_INSTANCE = new Mediator();
                }
            }
        }
        return UNIQUE_INSTANCE;
    }

    /**
     * Registers an RU with the mediator. Only RUs that are registered can
     * be interacted with via other classes.
     *
     * @param radioUnit The RU that will be registered with the mediator.
     */
    private synchronized void register(ManagedRadioUnit radioUnit) {
        if (!radioUnits.contains(radioUnit)) {
            radioUnits.forEach(ru -> {
                if (radioUnit.getIpAddress().equals(ru.getIpAddress())) {
                    System.out.println("[ERROR] A radio unit with that IP address has already been registered with the system.");
                    return;
                }
            });
            radioUnits.add(radioUnit);
        }
    }

    /**
     * Prints a formatted list of RUs currently registered with the mediator.
     */
    public void printRegisteredRadioUnits() {
        if (radioUnits.size() == 0) {
            System.out.println("[ERROR] No RUs have been registered with the system.");
        } else {
            radioUnits.forEach(ru -> System.out.println(ru.getIpAddress() + ":" + ru.getRadioUnitName()));
        }
    }

    /**
     * Print all created carriers that have been associated with any registered radio units.
     */
    public void printCreatedCarriers() {
        if (radioUnits.size() == 0) {
            System.out.println("[ERROR] No carriers have yet to be registered with the system.");
        } else {
            radioUnits.forEach(ru -> ru.getCarriers().forEach(carrier -> System.out.println(carrier.toString())));
        }
        // TODO Add a check to see if any carriers were actually printed, display a message if not.
    }

    /**
     * Print the RAT type for a RU associated with the specified name.
     *
     * @param ip The ip associated with the targeted RU.
     */
    public void printRatType(String ip) {
        AtomicBoolean flag = new AtomicBoolean(false);
        radioUnits.forEach(ru -> {
            if (ru.getIpAddress().equals(ip)) {
                flag.set(true);
                System.out.println(ru.getRatType());
                return;
            }
        });
        if (!flag.get()) {
            System.out.printf("[ERROR] No RUs exist with the ip: %s%n", ip);
        }
    }

    /**
     * Print the vendor for a RU associated with the specified name.
     *
     * @param ip The ip associated with the targeted RU.
     */
    public void printVendor(String ip) {
        AtomicBoolean flag = new AtomicBoolean(false);
        radioUnits.forEach(ru -> {
            if (ru.getIpAddress().equals(ip)) {
                flag.set(true);
                System.out.println(ru.getVendor());
                return;
            }
        });
        if (!flag.get()) {
            System.out.printf("[ERROR] No RUs exist with the ip: %s%n", ip);
        }
    }

    /**
     * Print the alarm for a RU associated with the specified name.
     *
     * @param ip The name associated with the targeted RU.
     */
    public void printAlarmStatus(String ip) {
        AtomicBoolean flag = new AtomicBoolean(false);
        radioUnits.forEach(ru -> {
            if (ru.getIpAddress().equals(ip)) {
                flag.set(true);
                System.out.println(ru.getAlarmStatus());
                return;
            }
        });
        if (!flag.get()) {
            System.out.printf("[ERROR] No RUs exist with the ip: %s%n", ip);
        }
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
    public synchronized void createRu(String name, Vendor vendor, RatType ratType) {
        /* We are making a barebones RU, so we *technically* don't care
         * about figuring out what kind of RU is being made.
         * If we do, this will be updated to call the appropriate
         * constructor via switch statements.
         */

        // Can this lead to repeats? Technically yes, it can. Just like how it's possible Dream didn't cheat his speedrun.
        Random r = new Random();
        this.register(new DemoOneRadioUnit(r.nextInt(256) + "." + r.nextInt(256) + "." + r.nextInt(256) + "." + r.nextInt(256), name, vendor, ratType) {
        });
    }

    /**
     * Attempts to remove an radio unit from the radio unit registry.
     *
     * @param ip The IP associated with the radio unit that will be removed.
     */
    public void removeRu(String ip) {
        // TODO Update this to point to the RU registry.
        radioUnits.remove(ip);
    }

    /**
     * Creates an RU and then add a newly created carrier to that existing RU.
     *
     * @param rfPorts            The RF Ports that will be used with this carrier.
     * @param carrierFrequencies The frequencies that will be used with this carrier.
     * @param transmittingPower  The transmitting power of the carrier.
     * @param ip                 The IP address of the RU this carrier will be added to.
     * @param vendor             The vendor for the newly created RU.
     * @param ratType            The RAT type for the newly created RU.
     */
    @Override
    public synchronized void createCarrierAndRu(List<RfPort> rfPorts, FrequencyBand carrierFrequencies,
                                                Double transmittingPower, String ip, Vendor vendor, RatType ratType) {
        createRu(ip, vendor, ratType);
        createCarrierOnRu(ip, rfPorts, carrierFrequencies, transmittingPower);
    }

    /**
     * Creates a Carrier and add it to an existing RU.
     *
     * @param ip                 The IP address of the RU this carrier will be added to.
     * @param rfPorts            The RF Ports that will be used with this carrier.
     * @param carrierFrequencies The frequencies that will be used with this carrier.
     * @param transmittingPower  The transmitting power of the carrier.
     */
    @Override
    public synchronized void createCarrierOnRu(String ip, List<RfPort> rfPorts, FrequencyBand carrierFrequencies,
                                               Double transmittingPower) {
        for (ManagedRadioUnit ru : radioUnits) {
            if (ru.getIpAddress().equals(ip)) {
                try {
                    ru.setupCarrier(createCarrier(rfPorts, carrierFrequencies, transmittingPower, ru.getRatType()));
                    return;
                } catch (NullPointerException ex) {
                    return;
                }
            }
        }
        System.out.printf("[ERROR] No RU with the IP %s is registered with the system%n", ip;
    }

    /**
     * Return a radio name specified by a given name.
     *
     * @param ip The IP of the RU, as a String.
     * @return The radio unit associated with that specific ip
     */
    @Override
    public ManagedRadioUnit getRadioUnit(String ip) {
        for (ManagedRadioUnit ru : radioUnits) {
            if (ru.getIpAddress().equals(ip)) {
                return ru;
            }
        }
        return null;
    }

    /**
     * Creates a carrier that will then be registered with the system. The carrier will have its type
     * determined based on the RAT type of the radio unit the carrier is being added to.
     *
     * @param rfPorts            A list of RF ports that will be used for this carrier.
     * @param carrierFrequencies The frequency band that will be used with this carrier.
     * @param transmittingPower  The transmitting power for this carrier.
     * @param ratType            The RAT type of the radio unit that this carrier will be associated with.
     * @return The created Carrier based on the RAT type of the radio unit it will be associated with.
     */
    public synchronized Carrier createCarrier(List<RfPort> rfPorts, FrequencyBand carrierFrequencies, Double transmittingPower, RatType ratType) {
        switch (ratType) {
            case WCDMA -> {
                try {
                    return carrierManagement.createWcdmaCarrier(rfPorts, carrierFrequencies, transmittingPower);
                } catch (ArrayIndexOutOfBoundsException ex) {
                    return null;
                }
            }
            case LTE -> {
                try {
                    return carrierManagement.createLteCarrier(rfPorts, carrierFrequencies, transmittingPower);
                } catch (ArrayIndexOutOfBoundsException ex) {
                    return null;
                }
            }
            default -> {
                System.out.printf("[ERROR] Invalid RAT type detected, failed to create a carrier.");
                return null;
            }
        }
    }

    /**
     * Displays the carriers associated with an RU.
     *
     * @param name The name of the RU for which the carriers will be displayed.
     */
    @Override
    public void displayCarrierOnRu(String name) {
        AtomicBoolean flag = new AtomicBoolean(false);
        radioUnits.forEach(ru -> {
            if (ru.getRadioUnitName().equals(name)) {
                flag.set(true);
                if (ru.getCarriers().size() == 0) {
                    System.out.printf(
                            "No carriers have been associated with the RU named %s%n", name);
                } else {
                    ru.getCarriers().forEach(carrier -> System.out.println(carrier.toString()));
                }
                return;
            }
        });
        if (!flag.get()) {
            System.out.printf(
                    "[ERROR] No RUs with the name %s have been registered with the system.%n", name);
        }
    }

    public void listRuByParam(Object obj) {
        // Most of this won't exist once the RadioUnitRegistry is complete.
        if (obj instanceof RatType) {
            RatType check = (RatType) obj;
            radioUnits.forEach(ru -> {
                if (ru.getRatType().equals(check)) {
                    System.out.println(ru);
                }
            });
        } else if (obj instanceof RadioUnitState) {
            RadioUnitState check = (RadioUnitState) obj;
            radioUnits.forEach(ru -> {
                if (ru.getCurrentState().equals(check)) {
                    System.out.println(ru);
                }
            });
        } else if (obj instanceof FrequencyBand) {
            FrequencyBand check = (FrequencyBand) obj;
            radioUnits.forEach(ru -> ru.getCarriers().forEach(carr -> {
                if (carr.getCarrierFrequencies().getBand().equals(check)) {
                    System.out.println(ru);
                }
            }));
        } else if (obj instanceof String) {
            String check = (String) obj;
            radioUnits.forEach(ru -> {
                if (ru.getIpAddress().equals(check)) {
                    System.out.println(ru);
                }
            });
        }
    }

    /**
     *
     * @param ip
     */
    private void commissionRu(String ip) {
        switch(getRadioUnit(ip).getRatType()) {
            case LTE -> commissionerLte.commissionRadioUnit(ip);
            case WCDMA -> commissionerWcdma.commissionRadioUnit(ip);
            default -> System.out.println("[ERROR] Unexpected RAT type encountered.");
        }
    }

    /**
     *
     * @param ip
     */
    private void decommissionRu(String ip, ProcedureOptions procedure) {
        switch(procedure) {
            case FULL -> {
                switch (getRadioUnit(ip).getRatType()) {
                    case LTE -> decommissionerLte.decommissionRadioUnit(ip);
                    case WCDMA -> decommissionerWcdma.decommissionRadioUnit(ip);
                    default -> System.out.println("[ERROR] Unexpected RAT type encountered.");
                }
            }
            case RELEASE -> getRadioUnit(ip).release();
        }
    }

    /**
     * This method gets called when a bound property is changed.
     *
     * @param evt A PropertyChangeEvent object describing the event source
     *            and the property that has changed.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        Procedure procedure = null;
        for (Procedure currProcedure : Procedure.values()) {
            if (currProcedure.getDesc().equals(evt.getPropertyName())) {
                procedure = currProcedure;
            }
        }

        switch (Objects.requireNonNull(procedure)) {
            case COMMISSION -> {
                switch ((ProcedureOptions) evt.getOldValue()) {
                    case RAT_TYPE -> {
                        List<Object> params = ((ArrayList<Object>) evt.getNewValue());
                        List<Object> newParams = new ArrayList<>(Arrays.asList(params.get(0), getRadioUnit((String) params.get(0)).getRatType()));
                        support.firePropertyChange(evt.getPropertyName(), newParams, params.get(1));
                    }
                    case SETUP -> getRadioUnit((String) evt.getNewValue()).setup();
                    case ACTIVATE -> getRadioUnit((String) evt.getNewValue()).activate();
                    case SCALING -> getRadioUnit((String) evt.getNewValue()).signalScaling();
                    case POST -> getRadioUnit((String) evt.getNewValue()).postActivation();
                    case DIAGNOSTIC -> getRadioUnit((String) evt.getNewValue()).performSelfDiagnostics();
                }
            }
            case DECOMMISSION -> {
                switch ((ProcedureOptions) evt.getOldValue()) {
                    case RAT_TYPE -> {
                        List<Object> params = ((ArrayList<Object>) evt.getNewValue());
                        List<Object> newParams = new ArrayList<>(Arrays.asList(params.get(0), getRadioUnit((String) params.get(0)).getRatType()));
                        support.firePropertyChange(evt.getPropertyName(), newParams, params.get(1));
                    }
                    case RELEASE -> getRadioUnit((String) evt.getNewValue()).release();
                    case DEACTIVATE -> getRadioUnit((String) evt.getNewValue()).deactivate();
                    case CARRIER -> getRadioUnit((String) evt.getNewValue()).removeAllCarriers();
                }
            }
            case CREATE -> {
                switch ((ProcedureOptions) evt.getOldValue()) {
                    case CARRIER -> {
                        List<Object> params = ((ArrayList<Object>) evt.getNewValue());
                        createCarrierOnRu((String) params.get(0), (List<RfPort>) params.get(1), (FrequencyBand) params.get(2), (Double) params.get(3));
                    }
                    case RU -> {}
                    case ALARM -> {}
                }
            }
            case DELETE -> {
                switch ((ProcedureOptions) evt.getOldValue()) {
                    case CARRIER -> {}
                    case RU -> {}
                }
            }
            case LIST -> {
                switch ((ProcedureOptions) evt.getOldValue()) {
                    case FULL -> {}
                    case PARAM-> {}
                    case RU -> {}
                    case ALARM -> {}
                }
            }
            case MODIFY -> {
                switch ((ProcedureOptions) evt.getOldValue()) {
                    case CARRIER -> {}
                }
            }
            case ACKNOWLEDGE -> {
                switch ((ProcedureOptions) evt.getOldValue()) {
                    case ALARM -> {}
                }
            }
        }
    }

    /**
     * Adds listeners to this class.
     *
     * @param pcl A property change listener
     */
    public void addPropertyChangeListener(PropertyChangeListener pcl) {
        support.addPropertyChangeListener(pcl);
    }
}
