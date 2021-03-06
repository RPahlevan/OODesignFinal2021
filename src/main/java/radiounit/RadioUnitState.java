package radiounit;

import common.Carrier;
import common.FrequencyBand;
import common.RadioUnitStateE;
import radiounit.radiostate.IllegalStateTransitionException;

/**
 * @author edavleu
 *
 */
public interface RadioUnitState {
	
	void setup() throws IllegalStateTransitionException;
	
	void activate() throws IllegalStateTransitionException;
	
	void deactivate() throws IllegalStateTransitionException;
	
	void release() throws IllegalStateTransitionException;
	
	void setupCarrier(Carrier carrier) throws IllegalStateTransitionException;

	void signalScaling() throws IllegalStateTransitionException;
	
	void modifyCarrier(Integer carrierId, FrequencyBand band) throws IllegalStateTransitionException;
	
	void removeCarrier(Integer carrierId) throws IllegalStateTransitionException;
	
	void selfDiagnostics() throws IllegalStateTransitionException;
	
	void removeAllCarriers() throws IllegalStateTransitionException;

	void postActivation() throws IllegalStateTransitionException;
	
	RadioUnitStateE getRuStateE();
	
	@Override
	String toString();
	
}
