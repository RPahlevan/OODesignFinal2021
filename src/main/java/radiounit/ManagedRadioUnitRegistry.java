package radiounit;

import java.util.List;

import common.AlarmStatusLevel;
import common.RatType;
import common.Vendor;

/**
 * Radio Unit Registry to keep track of all Radio Units
 * 
 * 
 * @author esiumat
 *
 */
public class ManagedRadioUnitRegistry extends AbstractManagedRadioUnitRegistry {

	public ManagedRadioUnitRegistry() {

	}

	@Override
	public void addRadioUnit(String ipAddress, String name, Vendor vendor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<ManagedRadioUnit> getByIpAddress(String ipAddress) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ManagedRadioUnit> getByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ManagedRadioUnit> getByVendor(Vendor vendor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ManagedRadioUnit> getByRatType(RatType ratType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ManagedRadioUnit> getByAlarmStatus(AlarmStatusLevel alarmStatus) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ManagedRadioUnit> getByState(RadioUnitState state) {
		// TODO Auto-generated method stub
		return null;
	}

}
