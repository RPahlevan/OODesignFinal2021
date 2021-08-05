package networkmanagementsystem;

import common.*;
import mediator.Mediator;
import radiounit.ManagedRadioUnit;
import radiounit.ManagedRadioUnitRegistry;
import radiounit.AbstractManagedRadioUnitRegistry;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.*;
import java.util.stream.Collectors;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import static common.ProcedureOptions.*;

/**
 * The NetworkManagementSystem is responsible for managing various
 * network related functions. These mostly deal with radio units. However,
 * the implementation currently in place allows for this class to manage
 * radio units without explicitly knowing/referencing radio units or the
 * mediator that controls the logic with the rest of the system.
 * <p>
 * Instead, the NetworkManagementSystem engages in a two-way
 * communication agreement with the mediator. The NetworkManagementSystem
 * does not know that the Mediator exists, yet it subscribes to its events
 * and handles them as needed.
 * <p>
 * The NetworkManagementSystem also operates as a Singleton. As this is
 * the entry point to the entire system, having multiple instances could
 * create undesired race conditions.
 *
 * @author ebreojh
 */
public class NetworkManagementSystem extends UnicastRemoteObject implements NetworkManagementSystemIf, PropertyChangeListener {
	private static volatile NetworkManagementSystem UNIQUE_INSTANCE;
	private final PropertyChangeSupport support;
	private final HashMap<RatType, CommissionRadioUnit> commissioners;
	private final HashMap<RatType, DecommissionRadioUnit> decommissioners;
	private final AbstractManagedRadioUnitRegistry radioUnitRegistry;
	private RatType currRatType;

	/**
	 * Constructor for the NetworkManagementSystem class.
	 * Required to be private to ensure the Singleton Pattern is followed.
	 */
	private NetworkManagementSystem() throws RemoteException {
		commissioners = new HashMap<>();
		commissioners.put(RatType.LTE, new CommissionLteRadioUnit());
		commissioners.put(RatType.WCDMA, new CommissionWcdmaRadioUnit());
		decommissioners = new HashMap<>();
		decommissioners.put(RatType.LTE, new DecommissionLteRadioUnit());
		decommissioners.put(RatType.WCDMA, new DecommissionWcdmaRadioUnit());
		support = new PropertyChangeSupport(this);
		radioUnitRegistry = ManagedRadioUnitRegistry.getInstance();
	}

	/**
	 * Get the unique NetworkManagementSystem instance.
	 *
	 * @return The Singleton instance of the NetworkManagementSystem class.
	 */
	public static NetworkManagementSystem getInstance() {
		if (UNIQUE_INSTANCE == null) {
			synchronized (Mediator.class) {
				if (UNIQUE_INSTANCE == null) {
					try {
						UNIQUE_INSTANCE = new NetworkManagementSystem();
					}
					catch (RemoteException e)
					{
						System.out.println("Error connecting to remote server");
						e.printStackTrace();
						System.exit(0);
					}
				}
			}
		}
		return UNIQUE_INSTANCE;
	}

	/**
	 * Handle the request to commission a radio unit.
	 *
	 * @param ip The IP address of the radio unit that is being commissioned.
	 */
	@Override
	public void commissionRu(String ip) {
		List<Object> params = new ArrayList<>(Arrays.asList(ip, ProcedureOptions.FULL));
		support.firePropertyChange(Procedure.COMMISSION.getDesc(), ProcedureOptions.RAT_TYPE, params);
	}

	/**
	 * Handle the request to decommission a radio unit.
	 *
	 * @param ip The IP address of the radio unit that is being decommissioned.
	 */
	@Override
	public void decommissionRu(String ip) {
		List<Object> params = new ArrayList<>(Arrays.asList(ip, ProcedureOptions.FULL));
		support.firePropertyChange(Procedure.DECOMMISSION.getDesc(), ProcedureOptions.RAT_TYPE, params);
	}

	/**
	 * Handle the request to add a radio unit.
	 * This radio unit is not initially activated. Normally upon creation,
	 * RUs should be commissioned via the commissionRu method.
	 *
	 * @param name    The name the radio unit can be identified by.
	 * @param vendor  The vendor of the radio unit.
	 * @param ratType The RAT type of the radio unit.
	 */
	@Override
	public void addRadioUnit(String ip, String name, Vendor vendor, RatType ratType) {
		List<Object> params = new ArrayList<>(Arrays.asList(ip, name, vendor, ratType));
		support.firePropertyChange(Procedure.CREATE.getDesc(), ProcedureOptions.RU, params);
	}

	/**
	 * Handle the request to remove a radio unit.
	 *
	 * @param ip The IP address of the radio unit that will be removed.
	 */
	@Override
	public void removeRadioUnit(String ip) {
		support.firePropertyChange(Procedure.DELETE.getDesc(), ProcedureOptions.RU, ip);
	}

	/**
	 * Handle the request to setup a radio unit.
	 *
	 * @param ip The IP address of the radio unit that will be setup.
	 */
	@Override
	public void setupRu(String ip) {
		List<Object> params = new ArrayList<>(Arrays.asList(ip, SETUP));
		support.firePropertyChange(Procedure.COMMISSION.getDesc(), ProcedureOptions.RAT_TYPE, params);
	}

	/**
	 * Handle the request to release a radio unit.
	 *
	 * @param ip The IP address of the radio unit that will be released.
	 */
	@Override
	public void releaseRu(String ip) {
		List<Object> params = new ArrayList<>(Arrays.asList(ip, ProcedureOptions.RELEASE));
		support.firePropertyChange(Procedure.DECOMMISSION.getDesc(), ProcedureOptions.RAT_TYPE, params);
	}

	/**
	 * Handle the request to activate a radio unit.
	 *
	 * @param ip The IP address of the radio unit that will be activated.
	 */
	@Override
	public void activateRu(String ip) {
		List<Object> params = new ArrayList<>(Arrays.asList(ip, ACTIVATE));
		support.firePropertyChange(Procedure.COMMISSION.getDesc(), ProcedureOptions.RAT_TYPE, params);
	}

	/**
	 * Handle the request to deactivate a radio unit.
	 *
	 * @param ip The IP address of the radio unit that will be deactivated.
	 */
	@Override
	public void deactivateRu(String ip) {
		List<Object> params = new ArrayList<>(Arrays.asList(ip, ProcedureOptions.DEACTIVATE));
		support.firePropertyChange(Procedure.DECOMMISSION.getDesc(), ProcedureOptions.RAT_TYPE, params);
	}

	/**
	 * Handle the request to setup a carrier on an existing radio unit.
	 *
	 * @param ip                The IP address of the radio unit that will have a carrier added to it.
	 * @param rfPorts           The RF ports for the carrier that is being created.
	 * @param freq              The frequency band for the carrier that is being created.
	 * @param transmittingPower The transmitting power for the carrier that is being created.
	 */
	@Override
	public void setupCarrierOnRu(String ip, List<RfPort> rfPorts, FrequencyBand freq, Double transmittingPower) {
		List<Object> params = new ArrayList<>(Arrays.asList(ip, rfPorts, freq, transmittingPower));
		support.firePropertyChange(Procedure.CREATE.getDesc(), ProcedureOptions.CARRIER, params);
	}

	/**
	 * Handle the request to modify a carrier on an existing radio unit.
	 *
	 * @param ip            The IP address of the radio unit that will have its carrier modified.
	 * @param id            The ID number of the carrier that is being modified on that radio unit.
	 * @param frequencyBand The frequency band that will be updated on the carrier.
	 */
	@Override
	public void modifyCarrierOnRu(String ip, int id, FrequencyBand frequencyBand) {
		List<Object> params = new ArrayList<>(Arrays.asList(ip, id, frequencyBand));
		support.firePropertyChange(Procedure.MODIFY.getDesc(), ProcedureOptions.CARRIER, params);
	}

	/**
	 * Handle the request to remove a carrier from an existing radio unit.
	 *
	 * @param ip The IP address of the radio unit that will have its carrier removed.
	 * @param id The ID number of the carrier that is being removed from that radio unit.
	 */
	@Override
	public void removeCarrierOnRu(String ip, int id) {
		List<Object> params = new ArrayList<>(Arrays.asList(ip, id));
		support.firePropertyChange(Procedure.DELETE.getDesc(), ProcedureOptions.CARRIER, params);
	}

	/**
	 * Handle the request to perform signal scaling on a radio unit.
	 *
	 * @param ip The IP address of the radio unit that will perform signal scaling.
	 */
	@Override
	public void signalScalingOnRu(String ip) {
		List<Object> params = new ArrayList<>(Arrays.asList(ip, SCALING));
		support.firePropertyChange(Procedure.COMMISSION.getDesc(), ProcedureOptions.RAT_TYPE, params);
	}

	/**
	 * Handle the request to perform a post activation on a radio unit.
	 *
	 * @param ip The IP address of the radio unit that will perform post activation.
	 */
	@Override
	public void postActivation(String ip) {
		List<Object> params = new ArrayList<>(Arrays.asList(ip, ProcedureOptions.POST));
		support.firePropertyChange(Procedure.COMMISSION.getDesc(), ProcedureOptions.RAT_TYPE, params);
	}

	/**
	 * Handle the request to perform self diagnostics on a radio unit.
	 *
	 * @param ip The IP address of the radio unit that will perform self diagnostics.
	 */
	@Override
	public void performSelfDiagnostics(String ip) {
		List<Object> params = new ArrayList<>(Arrays.asList(ip, ProcedureOptions.DIAGNOSTIC));
		support.firePropertyChange(Procedure.COMMISSION.getDesc(), ProcedureOptions.RAT_TYPE, params);
	}

	/**
	 * Handle the request to remove all carriers from a radio unit.
	 *
	 * @param ip The IP address of the radio unit that will have all of its carriers removed.
	 */
	@Override
	public void removeAllCarriersOnRu(String ip) {
		List<Object> params = new ArrayList<>(Arrays.asList(ip, ProcedureOptions.CARRIER));
		support.firePropertyChange(Procedure.DECOMMISSION.getDesc(), ProcedureOptions.RAT_TYPE, params);
	}

	/**
	 * Handle the request to list all created radio units.
	 */
	@Override
	public void listNetworkInventory() {
		support.firePropertyChange(Procedure.LIST.getDesc(), ProcedureOptions.FULL, "");
	}

	/**
	 * Handle the request to list all radio units that contain a certain parameter.
	 *
	 * @param param The parameter that will be used to query for radio units. Examples
	 *              include a RatType, a RadioUnitState, a FrequencyBand, or a String name.
	 */
	@Override
	public void listRuByParam(Object param) {
		support.firePropertyChange(Procedure.LIST.getDesc(), ProcedureOptions.PARAM, param);
	}

	/**
	 * Handle the request to list the details of a radio unit.
	 *
	 * @param ip The IP address of the radio unit that will have its details listed.
	 */
	@Override
	public void listRadioUnitDetails(String ip) {
		support.firePropertyChange(Procedure.LIST.getDesc(), ProcedureOptions.RU, ip);
	}

	/**
	 * Handle the request to set an alarm on a radio unit.
	 *
	 * @param ip    The IP address of the radio unit that will have an alarm raised.
	 * @param alarm The alarm that will be raised on the radio unit.
	 */
	@Override
	public void setAlarmOnRu(String ip, AlarmStatusLevel alarm) {
		List<Object> params = new ArrayList<>(Arrays.asList(ip, alarm));
		support.firePropertyChange(Procedure.CREATE.getDesc(), ProcedureOptions.ALARM, params);
	}

	/**
	 * Handle the request to list all current network alarms.
	 */
	@Override
	public String getNetworkAlarms() {
		String result = "No radios are currently alarmed!";
		
		List<ManagedRadioUnit> radioUnits = radioUnitRegistry.getAllRadios();

		radioUnits = radioUnits.stream()
		.filter(ru -> ru.getAlarmStatus() != AlarmStatusLevel.NO_ALARM)
		.collect(Collectors.toList());
		
		if (radioUnits.size() > 0)
		{
			for (ManagedRadioUnit ru : radioUnits)
			{
				result = ru.getIpAddress() + ": " + ru.getAlarmStatus() + "\n";
			}
		}
		else
		{
			System.out.println("No radios are currently alarmed!");
		}
		
		return result;
	}

	/**
	 * Handle the request to acknowledge a raised alarm on a radio unit.
	 *
	 * @param ip The IP address of the radio unit that will have an alarm acknowledged.
	 */
	@Override
	public boolean acknowledgeAlarm(String ip) {
		ManagedRadioUnit radio = radioUnitRegistry.getByIpAddress(ip);
		if (radio == null)
		{
			return false;
		}
		
		radio.acknowledgeAlarm();
		
		return true;
	}

	/**
	 * Get the map of all commissioner classes.
	 *
	 * @return A hashmap of all commissioner classes, keyed by RAT type.
	 */
	@Override
	public HashMap<RatType, CommissionRadioUnit> getCommissioners() {
		return commissioners;
	}

	/**
	 * Get the map of all decommissioner classes.
	 *
	 * @return A hashmap of all decommissioner classes, keyed by RAT type.
	 */
	@Override
	public HashMap<RatType, DecommissionRadioUnit> getDecommissioners() {
		return decommissioners;
	}

	/**
	 * Handle the request to get the RAT Type for a radio unit.
	 *
	 * @param ip The IP address of the radio unit that is having its RAT type request.
	 * @return The RAT type of the radio unit that was requested.
	 */
	@Override
	public RatType getRuRatType(String ip) {
		support.firePropertyChange(Procedure.GET.getDesc(), ProcedureOptions.RAT_TYPE, ip);
		return currRatType;
	}

	/**
	 * Adds listeners to this class.
	 *
	 * @param pcl A property change listener.
	 */
	public void addPropertyChangeListener(PropertyChangeListener pcl) {
		support.addPropertyChangeListener(pcl);
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
		// Determine which procedure we are working with.
		Procedure procedure = null;
		for (Procedure currProcedure : Procedure.values()) {
			if (currProcedure.getDesc().equals(evt.getPropertyName())) {
				procedure = currProcedure;
			}
		}
		List<Object> params = new ArrayList<>();
		// Only COMMISSION and DECOMMISSION need this list, while GET does not (hence the try/catch)
		try {
			params = ((ArrayList<Object>) evt.getNewValue());
		} catch (ClassCastException ignored) {
		}
		switch (Objects.requireNonNull(procedure)) {
		case COMMISSION:
		{
			switch ((ProcedureOptions) evt.getOldValue()) {
			case FULL:
			{
				commissioners.get((RatType) params.get(1)).commissionRadioUnit((String) params.get(0));
				break;
			}
			case SETUP:
			{
				commissioners.get((RatType) params.get(1)).setupRu((String) params.get(0));
				break;
			}
			case ACTIVATE:
			{
				commissioners.get((RatType) params.get(1)).activateRu((String) params.get(0));
				break;
			}
			case SCALING:
			{
				commissioners.get((RatType) params.get(1)).performSignalScaling((String) params.get(0));
				break;
			}
			case POST:
			{
				commissioners.get((RatType) params.get(1)).postActivation((String) params.get(0));
				break;
			}
			case DIAGNOSTIC:
			{
				commissioners.get((RatType) params.get(1)).performSelfDiagnotics((String) params.get(0));
				break;
			}
			}
			break;
		}
		case DECOMMISSION:
		{
			switch ((ProcedureOptions) evt.getOldValue()) {
			case FULL:
			{
				decommissioners.get((RatType) params.get(1)).decommissionRadioUnit((String) params.get(0));
				break;
			}
			case RELEASE:
			{
				decommissioners.get((RatType) params.get(1)).releaseRu((String) params.get(0));
				break;
			}
			case DEACTIVATE:
			{
				decommissioners.get((RatType) params.get(1)).deactivateRu((String) params.get(0));
				break;
			}
			case CARRIER:
			{
				decommissioners.get((RatType) params.get(1)).removeAllCarriersOnRu((String) params.get(0));
				break;
			}
			}
			break;
		}
		case GET:
		{
			currRatType = (RatType) evt.getNewValue();
			break;
		}
		}
	}
}
