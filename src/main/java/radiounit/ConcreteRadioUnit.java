package radiounit;

import java.util.List;

import common.AlarmStatusLevel;
import common.Carrier;
import common.FrequencyBand;
import common.RatType;
import common.Vendor;
import radiounit.radiostate.*;

/**
 * 
 * @author esiumat
 *
 */
public class ConcreteRadioUnit extends AbstractRadioUnit {

	/**
	 * Constructor for class
	 */
	public ConcreteRadioUnit(String ipAddress, String name, Vendor vendor, RatType ratType) {
		this.ipAddress = ipAddress;
		this.name = name;
		this.vendor = vendor;
		this.ratType = ratType;
		this.alarmStatus = AlarmStatusLevel.NO_ALARM;
		this.state = new IdleState(this);
		// TODO: instantiate a RadioCommandExecutor
		// TODO: get RadioCommandExecutorFactory instance here.

	}

	/**
	 * Setup radio
	 */
	@Override
	public void setup() {
		cmdExecutor.setup();
	}

	/**
	 * Activate radio
	 */
	@Override
	public void activate() {
		cmdExecutor.activate();
	}

	/**
	 * Deactivate radio
	 */
	@Override
	public void deactivate() {
		cmdExecutor.deactivate();
	}

	/**
	 * Release radio
	 */
	@Override
	public void release() {
		cmdExecutor.release();
	}

	/**
	 * Scales signal for radio
	 */
	@Override
	public void signalScaling() {
		cmdExecutor.signalScaling();
	}

	/**
	 * Setup carrier in radio
	 */
	@Override
	public void setupCarrier(Carrier carrier) {
		cmdExecutor.setupCarrier(carrier);
	}

	/**
	 * Modify existing carrier in radio
	 */
	@Override
	public void modifyCarrier(int carrierId, FrequencyBand freq) {
		cmdExecutor.modifyCarrier(carrierId, freq);
	}

	/**
	 * Remove carrier from radio
	 */
	@Override
	public void removeCarrier(int carrierId) {
		cmdExecutor.removeCarrier(carrierId);
	}

	/**
	 * Performs self diagnostics
	 */
	@Override
	public void performSelfDiagnostics() {
		cmdExecutor.selfDiagnostics();
	}

	/**
	 * Removes all carriers from radio
	 */
	@Override
	public void removeAllCarriers() {
		cmdExecutor.removeAllCarriers();
	}

	/**
	 * Get list of all carriers in radio
	 */
	@Override
	public List<Carrier> getCarriers() {
		return cmdExecutor.getCarriers();
	}

	@Override
	public void postActivation() {
		// TODO Auto-generated method stub
		System.out.println("Doing post activation");
	}

	@Override
	public void acknowledgeAlarm() {
		// TODO Auto-generated method stub
		System.out.println("Acknowledge alarm");
	}

	@Override
	public void raiseAlarm(AlarmStatusLevel alarm) {
		// TODO Auto-generated method stub
		System.out.println("Raise alarm");
	}

	@Override
	public AlarmStatusLevel getAlarmStatus() {
		return alarmStatus;
	}

	@Override
	public void setAlarmStatus(AlarmStatusLevel status) {
		this.alarmStatus = status;

	}

	@Override
	public String getIpAddress() {
		return ipAddress;
	}

	@Override
	public String getRadioUnitName() {
		return name;
	}

	@Override
	public Vendor getVendor() {
		return vendor;
	}

	@Override
	public RatType getRatType() {
		return ratType;
	}

	@Override
	public void setRadioUnitName(String name) {
		this.name = name;
	}

	@Override
	public RadioUnitState getCurrentState() {
		return state;
	}

	@Override
	public void setState(RadioUnitState state) {
		this.state = state;
	}

	@Override
	public RadioCommandExecutor getCommandExecutor() {
		return cmdExecutor;
	}

}
