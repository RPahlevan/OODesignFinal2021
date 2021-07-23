package radiounit;

import common.AlarmStatusLevel;
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

	public abstract void setState(RadioUnitState state);

	public abstract void setAlarmStatus(AlarmStatusLevel status);

	public abstract RadioCommandExecutor getCommandExecutor();
}
