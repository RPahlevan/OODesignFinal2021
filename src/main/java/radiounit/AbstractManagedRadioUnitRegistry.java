package radiounit;

import java.util.List;
import common.AlarmStatusLevel;
import common.Carrier;
import common.FrequencyBand;
import common.RatType;
import common.Vendor;

/**
 * Abstract class for ManagedRadioUnitRegistry
 * 
 * @author esiumat
 *
 */
public abstract class AbstractManagedRadioUnitRegistry {

	List<ManagedRadioUnit> radioUnits;

	public abstract void addRadioUnit(String ipAddress, String name, Vendor vendor);

	public abstract List<ManagedRadioUnit> getByIpAddress(String ipAddress);

	public abstract List<ManagedRadioUnit> getByName(String name);

	public abstract List<ManagedRadioUnit> getByVendor(Vendor vendor);

	public abstract List<ManagedRadioUnit> getByRatType(RatType ratType);

	public abstract List<ManagedRadioUnit> getByAlarmStatus(AlarmStatusLevel alarmStatus);

	public abstract List<ManagedRadioUnit> getByState(RadioUnitState state);

}
