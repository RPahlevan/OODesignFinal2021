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
		this.postActivationComplete = false;
		this.signalScalingComplete = false;

		switch (this.vendor) {
		case ERICSSON:
			switch (this.ratType) {
				case LTE:
                    cmdExecutor = EricssonLteCommandExecutorFactory.getInstance().createRadioCommandExecutor();
                    break;
				case WCDMA:
                    cmdExecutor = EricssonWcdmaCommandExecutorFactory.getInstance().createRadioCommandExecutor();
                    break;
			}
			break;
		case NOKIA:
			switch (this.ratType) {
				case LTE:
                    cmdExecutor = NokiaLteCommandExecutorFactory.getInstance().createRadioCommandExecutor();
                    break;
				case WCDMA:
                    cmdExecutor = NokiaWcdmaCommandExecutorFactory.getInstance().createRadioCommandExecutor();
                    break;
			}
			break;
		}
	}

	/**
	 * Setup radio
	 */
	@Override
	public void setup() {
		try {
			state.setup();
		} catch (IllegalStateTransitionException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Activate radio
	 */
	@Override
	public void activate() {
		try {
			state.activate();
		} catch (IllegalStateTransitionException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Deactivate radio
	 */
	@Override
	public void deactivate() {
		try {
			state.deactivate();
		} catch (IllegalStateTransitionException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Release radio
	 */
	@Override
	public void release() {
		try {
			state.release();
		} catch (IllegalStateTransitionException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Scales signal for radio
	 */
	@Override
	public void signalScaling() {
		try {
			state.signalScaling();
			signalScalingComplete = true; // TODO DAVID
		} catch (IllegalStateTransitionException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Setup carrier in radio
	 */
	@Override
	public void setupCarrier(Carrier carrier) {
		try {
			state.setupCarrier(carrier);
		} catch (IllegalStateTransitionException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Modify existing carrier in radio
	 */
	@Override
	public void modifyCarrier(int carrierId, FrequencyBand freq) {
		try {
			state.modifyCarrier(carrierId, freq);
		} catch (IllegalStateTransitionException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Remove carrier from radio
	 */
	@Override
	public void removeCarrier(int carrierId) {
		try {
			state.removeCarrier(carrierId);
		} catch (IllegalStateTransitionException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Performs self diagnostics
	 */
	@Override
	public void performSelfDiagnostics() {
		try {
			state.selfDiagnostics();
		} catch (IllegalStateTransitionException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Removes all carriers from radio
	 */
	@Override
	public void removeAllCarriers() {
		try {
			state.removeAllCarriers();
		} catch (IllegalStateTransitionException e) {
			System.out.println(e.getMessage());
		}
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
		try {
			state.postActivation();
			postActivationComplete = true;
		} catch (IllegalStateTransitionException e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public void acknowledgeAlarm() {
		alarmStatus = AlarmStatusLevel.NO_ALARM;
	}

	@Override
	public void raiseAlarm(AlarmStatusLevel alarm) {
		alarmStatus = alarm;
	}

	@Override
	public AlarmStatusLevel getAlarmStatus() {
		return alarmStatus;
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

	@Override
	public String toString() {
		StringBuffer details = new StringBuffer().append("Radio Unit Name: ").append(name).append("\nIP Address: ").append(ipAddress).append("\nVendor and RAT type: ").append(vendor.getLabel()).append(" ").append(ratType.getLabel()).append("\nState: ").append(state).append("\nAlarm: ").append(alarmStatus);
		List<Carrier> carriers = getCarriers();
		if (carriers.size() != 0) {
			if (carriers.size() == 1) {
				details.append("\nCarrier: ");
			} else {
				details.append("\nCarriers:\n");
			}
			carriers.forEach(carrier -> details.append(carrier.toString()));
		}
		return details.toString();
	}
}