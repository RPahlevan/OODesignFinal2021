/**
 * 
 */
package radiounit;

import java.util.List;

import common.*;

/**
 * @author edavleu
 *
 */
public interface ManagedRadioUnit {
	
	void setup();
	
	void activate();
	
	void deactivate();

	void release();
	
	void signalScaling();
	
	void postActivation();
	
	void performSelfDiagnostics();

	void acknowledgeAlarm();
	
	void setupCarrier(Carrier carrier);
	
	void modifyCarrier(int carrierId, FrequencyBand freq);
	
	void removeCarrier(int carrierId);
	
	void removeAllCarriers();
	
	String getCurrentState();
	
	String getIpAddress();
	
	String getRadioUnitName();
	
	Vendor getVendor();
	
	RatType getRatType();
	
	List<Carrier> getCarriers();
	
	AlarmStatusLevel getAlarmStatus();
	
	void setRadioUnitName(String name);
	
	void raiseAlarm(AlarmStatusLevel alarm);
	
}
