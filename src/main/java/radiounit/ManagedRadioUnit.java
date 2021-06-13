/**
 * 
 */
package radiounit;

import java.util.List;
import common.AlarmStatusLevel;
import common.Carrier;
import common.CarrierFrequency;
import common.RatType;

/**
 * @author edavleu
 *
 */
public interface ManagedRadioUnit {
	
	public void setup();
	
	public void activate();
	
	public void deactivate();
	
	public void release();
	
	public void signalScaling();
	
	public void postActivation();
	
	public void performSelfDiagnostics();
	
	public void acknowledgeAlarm();
	
	public void setupCarrier(Carrier carrier);
	
	public void modifyCarrier(int carrierId, CarrierFrequency freq);
	
	public void removeCarrier(int carrierId);
	
	public void removeAllCarriers();
	
	public String getCurrentState();
	
	public String getIpAddress();
	
	public String getRadioUnitName();
	
	public String getVendor();
	
	public RatType getRatType();
	
	public List<Carrier> getCarriers();
	
	public AlarmStatusLevel getAlarmStatus();
	
	public void setRadioUnitName(String name);
	
	public void raiseAlarm(AlarmStatusLevel alarm);
	
}
