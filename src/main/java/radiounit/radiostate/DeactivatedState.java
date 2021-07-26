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
public class DeactivatedState implements RadioUnitState {
	
	private final AbstractRadioUnit radio;
	private final RadioUnitStateE name = RadioUnitStateE.DEACTIVATED;

	/**
	 * @param radio The radio that will be assigned to this DeactivatedState
	 */
	public DeactivatedState(AbstractRadioUnit radio) {
		this.radio = radio;
	}

	@Override
	public void setup() throws IllegalStateTransitionException {
		throw new IllegalStateTransitionException(
				String.format("Cannot setup Radio Unit while it is in the %s state.", this.name));
	}

	@Override
	public void activate() throws IllegalStateTransitionException {
		RadioUnitState nextState = new ActivatedState(this.radio);
		
		System.out.println("Activating Radio Unit");
		this.radio.getCommandExecutor().activate();
		
		this.radio.setState(nextState);
	}

	@Override
	public void deactivate() throws IllegalStateTransitionException {
		System.out.printf("Radio is already in the %s stated.%n", this.name);
	}

	@Override
	public void release() throws IllegalStateTransitionException {
		RadioUnitState nextState = new IdleState(this.radio);
		
		System.out.println("Releasing Radio Unit");
		this.radio.getCommandExecutor().release();
		
		this.radio.setState(nextState);
	}

	@Override
	public void setupCarrier(Carrier carrier) throws IllegalStateTransitionException {
		throw new IllegalStateTransitionException(
				String.format("Cannot setup carrier on Radio Unit while it is in the %s state.", this.name));
	}

	@Override
	public void signalScaling() throws IllegalStateTransitionException {
		throw new IllegalStateTransitionException(
				String.format("Cannot perform signal scaling on Radio Unit while it is in the %s state.", this.name));
	}

	@Override
	public void modifyCarrier(Integer carrierId, FrequencyBand band) throws IllegalStateTransitionException {
		throw new IllegalStateTransitionException(
				String.format("Cannot modify carrier on Radio Unit while it is in the %s state.", this.name));
	}

	@Override
	public void removeCarrier(Integer carrierId) throws IllegalStateTransitionException {
		System.out.printf("Removing carrier (id=%d)%n", carrierId);
		
		this.radio.getCommandExecutor().removeCarrier(carrierId);
	}

	@Override
	public void selfDiagnostics() throws IllegalStateTransitionException {
		throw new IllegalStateTransitionException(
				String.format("Cannot perform self diagnostics on Radio Unit while it is in the %s state.", this.name));
	}

	@Override
	public void removeAllCarriers(Integer carrierId) throws IllegalStateTransitionException {
		System.out.println("Removing all carriers");
		
		this.radio.getCommandExecutor().removeAllCarriers();
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
