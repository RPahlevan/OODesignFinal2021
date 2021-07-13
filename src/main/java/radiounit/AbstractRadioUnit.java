package radiounit;

import java.util.List;

import common.AlarmStatusLevel;
import common.Carrier;
import common.FrequencyBand;
import common.RatType;
import common.Vendor;

/**
 * @author edavleu
 *
 */
public abstract class AbstractRadioUnit implements ManagedRadioUnit {

	protected String ipAddress;
	
	protected String name;
	
	protected Vendor vendor;
	
	protected RatType ratType;
	
	protected AlarmStatusLevel alarmStatus;

	protected RadioUnitState state;

	protected RadioCommandExecutor cmdExecutor;

	protected RadioCommandExecutorFactory executorFactory;
	
	protected boolean postActivationComplete;
	
	protected boolean signalScalingComplete;

	public abstract void setState(RadioUnitState state);

	public abstract void setAlarmStatus(AlarmStatusLevel status);

	public abstract RadioCommandExecutor getCommandExecutor();
}
