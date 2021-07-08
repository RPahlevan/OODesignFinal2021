package radiounit;

import java.util.List;

import common.AlarmStatusLevel;
import common.Carrier;
import common.FrequencyBand;
import common.RatType;
import common.Vendor;

/**
 * 
 * @author esiumat
 *
 */
public class ConcreteRadioUnit extends AbstractRadioUnit {

	/**
	 * Constructor for class
	 */
	public ConcreteRadioUnit() {
		// TODO: instantiate RadioCommandExecutorFactory

		// TODO: instantiate a RadioCommandExecutor

	}

	@Override
	public void setup() {
		cmdExecutor.setup();
	}

	@Override
	public void activate() {
		cmdExecutor.activate();
	}

	@Override
	public void deactivate() {
		cmdExecutor.deactivate();
	}

	@Override
	public void release() {
		cmdExecutor.release();
	}

	@Override
	public void signalScaling() {
		cmdExecutor.signalScaling();
	}

	@Override
	public void setupCarrier(Carrier carrier) {
		cmdExecutor.setupCarrier(carrier);
	}

	@Override
	public void modifyCarrier(int carrierId, FrequencyBand freq) {
		cmdExecutor.modifyCarrier(carrierId, freq);
	}

	@Override
	public void removeCarrier(int carrierId) {
		cmdExecutor.removeCarrier(carrierId);
	}

	@Override
	public void performSelfDiagnostics() {
		cmdExecutor.selfDiagnostics();
	}

	@Override
	public void removeAllCarriers() {
		cmdExecutor.removeAllCharacters();
	}

	@Override
	public List<Carrier> getCarriers() {
		return cmdExecutor.getCarriers();
	}

	
	
	
	
	
	
	
	
	
	
	@Override
	public void postActivation() {
		// TODO Auto-generated method stub

	}

	@Override
	public void acknowledgeAlarm() {
		// TODO Auto-generated method stub

	}

	@Override
	public RadioUnitState getCurrentState() {
		return state;
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
	public AlarmStatusLevel getAlarmStatus() {
		return alarmStatus;
	}

	@Override
	public void setRadioUnitName(String name) {
		this.name = name;
	}

	@Override
	public void raiseAlarm(AlarmStatusLevel alarm) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setState(RadioUnitState state) {
		this.state = state;
	}

	@Override
	public void setAlarmStatus(AlarmStatusLevel status) {
		this.alarmStatus = status;

	}

	@Override
	public RadioCommandExecutor getCommandExecutor() {
		return cmdExecutor;
	}

}
