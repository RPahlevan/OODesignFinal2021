package mediator;

import carriermanagementsystem.CarrierManagementSystemDirector;
import common.*;
import radiounit.ManagedRadioUnit;
import radiounit.AbstractManagedRadioUnitRegistry;
import radiounit.ManagedRadioUnitRegistry;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.*;
import java.util.stream.Collectors;

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
		List<ManagedRadioUnit> radioUnits = radioUnitRegistry.getAllRadios();
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
	private synchronized void createRu(String ip, String name, Vendor vendor, RatType ratType) {
		radioUnitRegistry.addRadioUnit(ip, name, vendor, ratType);
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
		ManagedRadioUnit ru = getRadioUnit(ip);
		if (ru == null)
		{
			System.out.println("Unable to create carrier on Radio Unit: Radio Unit not found!");
			return;
		}
		ru.setupCarrier(createCarrier(rfPorts, carrierFrequencies, transmittingPower, ru.getRatType()));
	}

	/**
	 * Return a radio unit specified by a given IP address.
	 *
	 * @param ip The IP of the RU, as a String.
	 * @return The radio unit associated with that specific ip.
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
		case WCDMA:
		{
			try {
				return carrierManagement.createWcdmaCarrier(rfPorts, carrierFrequencies, transmittingPower);
			} catch (ArrayIndexOutOfBoundsException ex) {
				return null;
			}
		}
		case LTE:
		{
			try {
				return carrierManagement.createLteCarrier(rfPorts, carrierFrequencies, transmittingPower);
			} catch (ArrayIndexOutOfBoundsException ex) {
				return null;
			}
		}
		default:
		{
			System.out.print("[ERROR] Invalid RAT type detected, failed to create a carrier.");
			return null;
		}
		}
	}

	/**
	 * List any registered radio units that contain the parameter being passed.
	 *
	 * @param param The parameter that will be used to query for radio units. Examples
	 *              include a RatType, a RadioUnitState, a FrequencyBand, or a String name.
	 */
	private void listRuByParam(Object param) {
		if (param instanceof RatType) {
			List<ManagedRadioUnit> radioUnits = radioUnitRegistry.getByRatType((RatType) param);
			radioUnits.forEach(System.out::println);
		} else if (param instanceof RadioUnitStateE) {
			List<ManagedRadioUnit> radioUnits = radioUnitRegistry.getByState((RadioUnitStateE) param);
			radioUnits.forEach(System.out::println);
		} else if (param instanceof FrequencyBand) {
			List<ManagedRadioUnit> radioUnits = radioUnitRegistry.getByBand((FrequencyBand) param);
			radioUnits.forEach(System.out::println);
		} else if (param instanceof String) {
			List<ManagedRadioUnit> radioUnits = radioUnitRegistry.getByName((String) param);
			radioUnits.forEach(System.out::println);
		}
	}

	/**
	 * Notify listeners of the RAT Type for a specific radio unit.
	 *
	 * @param evt The event that contains the necessary information to return alongside the RAT Type.
	 */
	@SuppressWarnings("unchecked")
	private void messageRatType(PropertyChangeEvent evt) {
		List<Object> params = ((ArrayList<Object>) evt.getNewValue());
		ManagedRadioUnit radio = getRadioUnit((String) params.get(0));
		if (radio == null)
		{
			System.out.println("Unable to message RAT type listeners: Radio Unit not found!");
			return;
		}

		List<Object> newParams = new ArrayList<>(Arrays.asList(params.get(0), radio.getRatType()));
		support.firePropertyChange(evt.getPropertyName(), params.get(1), newParams);
	}

	/**
	 * Print all alarms currently raised in the network.
	 */
	private void printNetworkAlarms() {
		List<ManagedRadioUnit> radioUnits = radioUnitRegistry.getAllRadios();

		radioUnits = radioUnits.stream()
		.filter(ru -> ru.getAlarmStatus() != AlarmStatusLevel.NO_ALARM)
		.collect(Collectors.toList());
		
		if (radioUnits.size() > 0)
		{
			radioUnits.forEach(ru -> System.out.println(ru.getIpAddress() + ": " + ru.getAlarmStatus()));
		}
		else
		{
			System.out.println("No radios are currently alarmed!");
		}
	}

	/**
	 * This method gets called when a bound property is changed.
	 *
	 * @param evt A PropertyChangeEvent object describing the event source
	 *            and the property that has changed.
	 */
	@Override
	@SuppressWarnings("unchecked")
	public void propertyChange(PropertyChangeEvent evt) {
		Procedure procedure = null;
		for (Procedure currProcedure : Procedure.values()) {
			if (currProcedure.getDesc().equals(evt.getPropertyName())) {
				procedure = currProcedure;
			}
		}

		ManagedRadioUnit radio;
		List<Object> params;

		switch (Objects.requireNonNull(procedure)) {
		case COMMISSION:
			
			switch ((ProcedureOptions) evt.getOldValue()) {
			case RAT_TYPE:
				messageRatType(evt);
				break;
			case SETUP:
				radio = getRadioUnit((String) evt.getNewValue());
				if (radio == null)
				{
					System.out.println("Cannot process COMMISION event: Radio Unit not found!");
					return;
				}
				radio.setup();
				break;
			case ACTIVATE:
				radio = getRadioUnit((String) evt.getNewValue());
				if (radio == null)
				{
					System.out.println("Cannot process COMMISION event: Radio Unit not found!");
					return;
				}
				radio.activate();
				break;
			case SCALING:
				radio = getRadioUnit((String) evt.getNewValue());
				if (radio == null)
				{
					System.out.println("Cannot process COMMISION event: Radio Unit not found!");
					return;
				}
				radio.signalScaling();
				break;
			case POST:
				radio = getRadioUnit((String) evt.getNewValue());
				if (radio == null)
				{
					System.out.println("Cannot process COMMISION event: Radio Unit not found!");
					return;
				}
				radio.postActivation();
				break;
			case DIAGNOSTIC:
				radio = getRadioUnit((String) evt.getNewValue());
				if (radio == null)
				{
					System.out.println("Cannot process COMMISION event: Radio Unit not found!");
					return;
				}
				radio.performSelfDiagnostics();
				break;
			}
			break;
		case DECOMMISSION:
			switch ((ProcedureOptions) evt.getOldValue()) {
			case RAT_TYPE:
				messageRatType(evt);
				break;

			case RELEASE:
				radio = getRadioUnit((String) evt.getNewValue());
				if (radio == null)
				{
					System.out.println("Unable to RELEASE Radio Unit: Radio Unit not found!");
					return;
				}

				radio.release();
				break;

			case DEACTIVATE:
				radio = getRadioUnit((String) evt.getNewValue());
				if (radio == null)
				{
					System.out.println("Unbale to DEACTIVATE Radio Unit: Radio Unit not found!");
					return;
				}

				radio.deactivate();
				break;

			case CARRIER:
				radio = getRadioUnit((String) evt.getNewValue());
				if (radio == null)
				{
					System.out.println("Unable to remove all carriers from Radio Unit: Radio Unit not found!");
					return;
				}

				radio.removeAllCarriers();
				break;
			}
			break;
		case CREATE:
			switch ((ProcedureOptions) evt.getOldValue()) {
			case CARRIER:
				params = ((ArrayList<Object>) evt.getNewValue());
				createCarrierOnRu((String) params.get(0), (List<RfPort>) params.get(1), (FrequencyBand) params.get(2), (Double) params.get(3));
				break;
			case RU:
				params = ((ArrayList<Object>) evt.getNewValue());
				createRu((String) params.get(0), (String) params.get(1), (Vendor) params.get(2), (RatType) params.get(3));
				break;
			case ALARM:
				params = ((ArrayList<Object>) evt.getNewValue());
				radio = getRadioUnit((String) params.get(0));
				if (radio == null)
				{
					System.out.println("Unable to raise alarm on radio: Radio Unit not found!");
					return;
				}

				radio.raiseAlarm((AlarmStatusLevel) params.get(1));
				break;
			}
			break;
		case DELETE:
			switch ((ProcedureOptions) evt.getOldValue()) {
			case CARRIER:
				params = ((ArrayList<Object>) evt.getNewValue());
				radio = getRadioUnit((String) params.get(0));
				if (radio == null)
				{
					System.out.println("Unable to remove carrier from Radio Unit: Radio Unit not found!");
					return;
				}

				radio.removeCarrier((int) params.get(1));
				break;
			case RU:
				removeRu((String) evt.getNewValue());
				break;
			}
			break;
		case LIST:
			switch ((ProcedureOptions) evt.getOldValue()) {
			case FULL:
				printRegisteredRadioUnits();
				break;
			case PARAM:
				listRuByParam(evt.getNewValue());
				break;
			case RU:
				radio = getRadioUnit((String) evt.getNewValue());
				if (radio == null)
				{
					System.out.println("Unable to list Radio Unit details: Radio Unit not found!");
					return;
				}

				System.out.println(radio);
				break;
			case ALARM:
				printNetworkAlarms();
				break;
			}
			break;
		case MODIFY:
			switch ((ProcedureOptions) evt.getOldValue()) {
			case CARRIER:
				params = ((ArrayList<Object>) evt.getNewValue());

				radio = getRadioUnit((String) params.get(0));
				if (radio == null)
				{
					System.out.println("Unable to modify carrier on Radio Unit: Radio Unit not found!");
					return;
				}

				radio.modifyCarrier((int) params.get(1), (FrequencyBand) params.get(2));
				break;
			}
			break;
		case ACKNOWLEDGE:
			switch ((ProcedureOptions) evt.getOldValue()) {
			case ALARM:
				radio = getRadioUnit((String) evt.getNewValue());
				if (radio == null)
				{
					System.out.println("Unable to acknowledge alarm on Radio Unit: Radio Unit not found!");
					return;
				}

				radio.acknowledgeAlarm();
				break;
			}
			break;
		case GET:
			switch ((ProcedureOptions) evt.getOldValue()) {
			case RAT_TYPE:
				radio = getRadioUnit((String) evt.getNewValue());
				if (radio == null)
				{
					System.out.println("Unable to get RAT type for Radio Unit: Radio Unit not found!");
					return;
				}

				support.firePropertyChange(Procedure.GET.getDesc(), ProcedureOptions.RAT_TYPE, radio.getRatType());
				break;
			}
			break;
		}
	}

	/**
	 * Add listeners to this class.
	 *
	 * @param pcl A property change listener.
	 */
	@Override
	public void addPropertyChangeListener(PropertyChangeListener pcl) {
		support.addPropertyChangeListener(pcl);
	}
}
