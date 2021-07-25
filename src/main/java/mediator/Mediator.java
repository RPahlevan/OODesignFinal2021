package mediator;

import carriermanagementsystem.CarrierManagementSystemDirector;
import common.*;
import radiounit.DemoOneRadioUnit;
import radiounit.ManagedRadioUnit;
import radiounit.RadioUnitState;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.*;

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
 * Mediators would result in ample race conditions to occur.
 *
 * @author ebreojh
 */
public class Mediator implements PropertyChangeListener, MediatorIf {
    private static volatile Mediator UNIQUE_INSTANCE;
    private final PropertyChangeSupport support;
    private final CarrierManagementSystemDirector carrierManagement;
    private final AbstractManagedRadioUnitRegistry radioUnitRegistry;

    /**
     * Constructor for the Mediator class.
     * Required to be private to ensure the Singleton Pattern is followed.
     */
    private Mediator() {
        support = new PropertyChangeSupport(this);
        carrierManagement = CarrierManagementSystemDirector.getInstance();
        radioUnitRegistry = new ManagedRadioUnitRegistry();
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
     * Prints a formatted list of RUs currently registered with the mediator.
     */
    private void printRegisteredRadioUnits() {
        List<ManagedRadioUnit> radioUnits = ManagedRadioUnit.getAllRadios();
        radioUnits.forEach(System.out::println);
        if (radioUnits.size() == 0) {
            System.out.println("[ERROR] No RUs have been registered with the system.");
        } else {
            radioUnits.forEach(System.out::println);
        }
    }

    /**
     * Create a radio unit that will be used in the system. This radio
     * unit will be stored and managed by the RadioUnitRegistry class.
     *
     * @param name    The name of the RU.
     * @param vendor  The vendor for the RU.
     * @param ratType The RAT type for the RU.
     */
    private synchronized void createRu(String name, Vendor vendor, RatType ratType) {
        Random r = new Random();
        StringBuffer ip = new StringBuffer();
        ip.append(r.nextInt(256)).append(".").append(r.nextInt(256)).append(".").append(r.nextInt(256)).append(".").append(r.nextInt(256));
        radioUnitRegistry.addRadioUnit(ip.toString(), name, vendor, ratType);
    }

    /**
     * Attempt to remove an radio unit from the radio unit registry.
     *
     * @param ip The IP associated with the radio unit that will be removed.
     */
    private void removeRu(String ip) {
        radioUnitRegistry.removeRadioUnit(ip);
    }

    /**
     * Create a Carrier and add it to an existing RU.
     *
     * @param ip                 The IP address of the RU this carrier will be added to.
     * @param rfPorts            The RF Ports that will be used with this carrier.
     * @param carrierFrequencies The frequencies that will be used with this carrier.
     * @param transmittingPower  The transmitting power of the carrier.
     */
    private synchronized void createCarrierOnRu(String ip, List<RfPort> rfPorts, FrequencyBand carrierFrequencies,
                                                Double transmittingPower) {
        ManagedRadioUnit ru = radioUnitRegistry.getByIpAddress(ip);
        ru.setupCarrier(createCarrier(rfPorts, carrierFrequencies, transmittingPower, ru.getRatType()));
    }

    /**
     * Return a radio unit specified by a given IP address.
     *
     * @param ip The IP of the RU, as a String.
     * @return The radio unit associated with that specific ip
     */
    private ManagedRadioUnit getRadioUnit(String ip) {
        return radioUnitRegistry.getByIpAddress(ip);
    }

    /**
     * Create a carrier that will then be registered with the system. The carrier will have its type
     * determined based on the RAT type of the radio unit the carrier is being added to.
     *
     * @param rfPorts            A list of RF ports that will be used for this carrier.
     * @param carrierFrequencies The frequency band that will be used with this carrier.
     * @param transmittingPower  The transmitting power for this carrier.
     * @param ratType            The RAT type of the radio unit that this carrier will be associated with.
     * @return The created Carrier based on the RAT type of the radio unit it will be associated with.
     */
    private synchronized Carrier createCarrier(List<RfPort> rfPorts, FrequencyBand carrierFrequencies, Double transmittingPower, RatType ratType) {
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
                System.out.print("[ERROR] Invalid RAT type detected, failed to create a carrier.");
                return null;
            }
        }
    }

    /**
     * List any registered radio units that contain the parameter being passed.
     *
     * @param obj The parameter that will be used to query for radio units. Examples
     *            include a RatType, a RadioUnitState, a FrequencyBand, or a String name.
     */
    private void listRuByParam(Object obj) {
        // Most of this won't exist once the RadioUnitRegistry is complete.
        if (obj instanceof RatType) {
            List<ManagedRadioUnit> radioUnits = radioUnitRegistry.getByRatType((RatType) obj);
            radioUnits.forEach(System.out::println);
        } else if (obj instanceof RadioUnitState) {
            List<ManagedRadioUnit> radioUnits = radioUnitRegistry.getByState((RadioUnitState) obj);
            radioUnits.forEach(System.out::println);
        } else if (obj instanceof FrequencyBand) {
            List<ManagedRadioUnit> radioUnits = radioUnitRegistry.getByBand((FrequencyBand) obj);
            radioUnits.forEach(System.out::println);
        } else if (obj instanceof String) {
            List<ManagedRadioUnit> radioUnits = radioUnitRegistry.getByName((String) obj);
            radioUnits.forEach(System.out::println);
        }
    }

    /**
     * Notify listeners of the RAT Type for a specific
     *
     * @param evt The event that contains the necessary information to return alongside the RAT Type.
     */
    private void getRatType(PropertyChangeEvent evt) {
        List<Object> params = ((ArrayList<Object>) evt.getNewValue());
        List<Object> newParams = new ArrayList<>(Arrays.asList(params.get(0), Objects.requireNonNull(getRadioUnit((String) params.get(0))).getRatType()));
        support.firePropertyChange(evt.getPropertyName(), newParams, params.get(1));
    }

    /**
     * Print all alarms currently raised in the network.
     */
    private void printNetworkAlarms() {
        Set<AlarmStatusLevel> alarms = new HashSet<>();
        List<ManagedRadioUnit> radioUnits = ManagedRadioUnit.getAllRadios();
        radioUnits.forEach(ru -> alarms.add(ru.getAlarmStatus()));
        alarms.forEach(System.out::println);
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
                    case RAT_TYPE -> getRatType(evt);
                    case SETUP -> Objects.requireNonNull(getRadioUnit((String) evt.getNewValue())).setup();
                    case ACTIVATE -> Objects.requireNonNull(getRadioUnit((String) evt.getNewValue())).activate();
                    case SCALING -> Objects.requireNonNull(getRadioUnit((String) evt.getNewValue())).signalScaling();
                    case POST -> Objects.requireNonNull(getRadioUnit((String) evt.getNewValue())).postActivation();
                    case DIAGNOSTIC -> Objects.requireNonNull(getRadioUnit((String) evt.getNewValue())).performSelfDiagnostics();
                }
            }
            case DECOMMISSION -> {
                switch ((ProcedureOptions) evt.getOldValue()) {
                    case RAT_TYPE -> getRatType(evt);
                    case RELEASE -> Objects.requireNonNull(getRadioUnit((String) evt.getNewValue())).release();
                    case DEACTIVATE -> Objects.requireNonNull(getRadioUnit((String) evt.getNewValue())).deactivate();
                    case CARRIER -> Objects.requireNonNull(getRadioUnit((String) evt.getNewValue())).removeAllCarriers();
                }
            }
            case CREATE -> {
                switch ((ProcedureOptions) evt.getOldValue()) {
                    case CARRIER -> {
                        List<Object> params = ((ArrayList<Object>) evt.getNewValue());
                        createCarrierOnRu((String) params.get(0), (List<RfPort>) params.get(1), (FrequencyBand) params.get(2), (Double) params.get(3));
                    }
                    case RU -> {
                        List<Object> params = ((ArrayList<Object>) evt.getNewValue());
                        createRu((String) params.get(0), (Vendor) params.get(1), (RatType) params.get(2));
                    }
                    case ALARM -> {
                        List<Object> params = ((ArrayList<Object>) evt.getNewValue());
                        Objects.requireNonNull(getRadioUnit((String) params.get(0))).raiseAlarm((AlarmStatusLevel) params.get(1));
                    }
                }
            }
            case DELETE -> {
                switch ((ProcedureOptions) evt.getOldValue()) {
                    case CARRIER -> {
                        List<Object> params = ((ArrayList<Object>) evt.getNewValue());
                        Objects.requireNonNull(getRadioUnit((String) params.get(0))).removeCarrier((int) params.get(1));
                    }
                    case RU -> removeRu((String) evt.getNewValue());
                }
            }
            case LIST -> {
                switch ((ProcedureOptions) evt.getOldValue()) {
                    case FULL -> printRegisteredRadioUnits();
                    case PARAM -> listRuByParam(evt.getNewValue());
                    case RU -> System.out.println(getRadioUnit((String) evt.getNewValue()));
                    case ALARM -> printNetworkAlarms();
                }
            }
            case MODIFY -> {
                switch ((ProcedureOptions) evt.getOldValue()) {
                    case CARRIER -> {
                        List<Object> params = ((ArrayList<Object>) evt.getNewValue());
                        Objects.requireNonNull(getRadioUnit((String) params.get(0))).modifyCarrier((int) params.get(1), (FrequencyBand) params.get(2));
                    }
                }
            }
            case ACKNOWLEDGE -> {
                switch ((ProcedureOptions) evt.getOldValue()) {
                    case ALARM -> Objects.requireNonNull(getRadioUnit((String) evt.getNewValue())).acknowledgeAlarm();
                }
            }
            case GET -> {
                switch ((ProcedureOptions) evt.getOldValue()) {
                    case RAT_TYPE -> support.firePropertyChange(Procedure.GET.getDesc(), ProcedureOptions.RAT_TYPE,
                            Objects.requireNonNull(getRadioUnit((String) evt.getNewValue())).getRatType());
                }
            }
        }
    }

    /**
     * Add listeners to this class.
     *
     * @param pcl A property change listener
     */
    @Override
    public void addPropertyChangeListener(PropertyChangeListener pcl) {
        support.addPropertyChangeListener(pcl);
    }
}
