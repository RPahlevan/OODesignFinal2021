package radiounit;

import common.Carrier;
import common.FrequencyBand;
import radiounit.radiostate.IllegalStateTransitionException;

/**
 * @author edavleu
 *
 */
public interface RadioUnitState {
	
	public void setup() throws IllegalStateTransitionException;
	
	public void activate() throws IllegalStateTransitionException;
	
	public void deactivate() throws IllegalStateTransitionException;
	
	public void release() throws IllegalStateTransitionException;
	
	public void setupCarrier(Carrier carrier) throws IllegalStateTransitionException;

	public void signalScaling() throws IllegalStateTransitionException;
	
	public void modifyCarrier(Integer carrierId, FrequencyBand band) throws IllegalStateTransitionException;
	
	public void removeCarrier(Integer carrierId) throws IllegalStateTransitionException;
	
	public void selfDiagnostics() throws IllegalStateTransitionException;
	
	public void removeAllCarriers(Integer carrierId) throws IllegalStateTransitionException;
	
	@Override
	public String toString();
	
}
