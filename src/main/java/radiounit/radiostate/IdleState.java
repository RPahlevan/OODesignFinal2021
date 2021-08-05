package radiounit.radiostate;

import common.Carrier;
import common.FrequencyBand;
import common.RadioUnitStateE;
import radiounit.AbstractRadioUnit;
import radiounit.RadioUnitState;

/**
 * @author edavleu
 *
 */
public class IdleState implements RadioUnitState {
	
	private final AbstractRadioUnit radio;
	private final RadioUnitStateE name = RadioUnitStateE.IDLE;
	
	public IdleState(AbstractRadioUnit radio)
	{
		this.radio = radio;
	}

	@Override
	public void setup() {
		RadioUnitState nextState = new DeactivatedState(this.radio);
		
		System.out.println("Performing setup on Radio Unit.");
		this.radio.getCommandExecutor().setup();
		
		this.radio.setState(nextState);
	}

	@Override
	public void activate() throws IllegalStateTransitionException {
		throw new IllegalStateTransitionException(
				String.format("Cannot activate Radio Unit while it is in the %s state.", this.name));
	}

	@Override
	public void deactivate() throws IllegalStateTransitionException {
		throw new IllegalStateTransitionException(
				String.format("Cannot deactivate Radio Unit while it is in the %s state.", this.name));
	}

	@Override
	public void release() throws IllegalStateTransitionException {
		System.out.printf("Radio Unit is already in the %s state.%n", this.name);
	}

	@Override
	public void setupCarrier(Carrier carrier) throws IllegalStateTransitionException {
		throw new IllegalStateTransitionException(
				String.format("Cannot setup carrier on Radio Unit while it is in the %s state.", this.name));	}

	@Override
	public void signalScaling() throws IllegalStateTransitionException {
		throw new IllegalStateTransitionException(
				String.format("Cannot perfrom signal scaling on Radio Unit while it is in the %s state.", this.name));
	}

	@Override
	public void modifyCarrier(Integer carrierId, FrequencyBand band) throws IllegalStateTransitionException {
		throw new IllegalStateTransitionException(
				String.format("Cannot modify carrier on Radio Unit while it is in the %s state.", this.name));
	}

	@Override
	public void removeCarrier(Integer carrierId) throws IllegalStateTransitionException {
		throw new IllegalStateTransitionException(
				String.format("Cannot remove carrier from Radio Unit while it is in the %s state.", this.name));
	}

	@Override
	public void selfDiagnostics() throws IllegalStateTransitionException {
		throw new IllegalStateTransitionException(
				String.format("Cannot perform self diagnostics on Radio Unit while it is in the %s state.", this.name));
	}

	@Override
	public void removeAllCarriers() throws IllegalStateTransitionException {
		throw new IllegalStateTransitionException(
				String.format("Cannot remove all carriers from Radio Unit while it is in the %s state.", this.name));
	}
	
	@Override
	public RadioUnitStateE getRuStateE() {
		return name;
	}
	
	@Override
	public String toString()
	{
		return String.format("%s state", this.name.getRuState());
	}

}
