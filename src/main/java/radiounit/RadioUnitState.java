package radiounit;

import common.Carrier;
import common.FrequencyBand;

/**
 * @author edavleu
 *
 */
public interface RadioUnitState {
	
	public void setup();
	
	public void activate();
	
	public void deactivate();
	
	public void release();
	
	public void setupCarrier(Carrier carrier);

	public void signalScaling();
	
	public void modifyCarrier(Integer carrierId, FrequencyBand band);
	
	public void removeCarrier(Integer carrierId);
	
	public void selfDiagnostics();
	
	public void removeAllCarriers(Integer carrierId);
	
	@Override
	public String toString();
	
}
