package radiounit;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import common.AlarmStatusLevel;
import common.FrequencyBand;
import common.RatType;
import common.Vendor;
import common.RadioUnitStateE;

/**
 * Radio Unit Registry to keep track of all Radio Units
 * 
 * 
 * @author esiumat
 *
 */
public class ManagedRadioUnitRegistry extends AbstractManagedRadioUnitRegistry {

	public ManagedRadioUnitRegistry() {
		radioUnits = new ArrayList<>();
	}

	@Override
	public void addRadioUnit(String ipAddress, String name, Vendor vendor, RatType ratType) {
		// check that radio hasn't already been added
		if (getByIpAddress(ipAddress) != null) {
			System.err.println("A radio with this IP address has already been added");
			return;
		}

		radioUnits.add(new ConcreteRadioUnit(ipAddress, name, vendor, ratType));
	}

	@Override
	public ManagedRadioUnit getByIpAddress(String ipAddress) {
		List<ManagedRadioUnit> radioUnitsFilteredByIpAddress = radioUnits.stream()
				.filter(ru -> ru.getIpAddress().equals(ipAddress)).collect(Collectors.toList());
		try {
			return radioUnitsFilteredByIpAddress.get(0);
		} catch (IndexOutOfBoundsException ignored) {
			return null;
		}
	}

	@Override
	public List<ManagedRadioUnit> getByName(String name) {
		return radioUnits.stream()
				.filter(ru -> ru.getRadioUnitName().equals(name)).collect(Collectors.toList());
	}

	@Override
	public List<ManagedRadioUnit> getByVendor(Vendor vendor) {
		return radioUnits.stream()
				.filter(ru -> ru.getVendor().equals(vendor)).collect(Collectors.toList());
	}

	@Override
	public List<ManagedRadioUnit> getByRatType(RatType ratType) {
		return radioUnits.stream()
				.filter(ru -> ru.getRatType().equals(ratType)).collect(Collectors.toList());
	}

	@Override
	public List<ManagedRadioUnit> getByAlarmStatus(AlarmStatusLevel alarmStatus) {
		return radioUnits.stream()
				.filter(ru -> ru.getAlarmStatus().equals(alarmStatus)).collect(Collectors.toList());
	}

	@Override
	public List<ManagedRadioUnit> getByState(RadioUnitStateE state) {
		return radioUnits.stream()
				.filter(ru -> ru.getCurrentState().getRuStateE().equals(state)).collect(Collectors.toList());
	}

	@Override
	public List<ManagedRadioUnit> getByBand(FrequencyBand band) {
		List<ManagedRadioUnit> radioUnitsFilteredByBand = new ArrayList<>();
		radioUnits.forEach(ru -> ru.getCarriers().forEach(carrier -> {
			if (carrier.getFrequencyBand() == band) {
				radioUnitsFilteredByBand.add(ru);
			}
		}));
		return radioUnitsFilteredByBand;
	}

	@Override
	public List<ManagedRadioUnit> getSpecificRadio(String ipAddress, String name, Vendor vendor, RatType ratType) {
		return radioUnits.stream()
				.filter(ru -> ru.getIpAddress().equals(ipAddress)).filter(ru -> ru.getRadioUnitName().equals(name))
				.filter(ru -> ru.getVendor().equals(vendor)).filter(ru -> ru.getRatType().equals(ratType))
				.collect(Collectors.toList());
	}

	@Override
	public List<ManagedRadioUnit> getAllRadios() {
		return radioUnits;
	}

	@Override
	public void removeRadioUnit(String ip) {
		radioUnits.removeIf(ru -> (ru.getIpAddress().equals(ip)));
	}

}
