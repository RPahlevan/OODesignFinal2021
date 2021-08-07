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
public class ActivatedState implements RadioUnitState {
	
	private final AbstractRadioUnit radio;
	private final RadioUnitStateE name = RadioUnitStateE.ACTIVATED;

	/**
	 * @param radio The radio that will be assigned to this ActivatedState
	 */
	public ActivatedState(AbstractRadioUnit radio) {
		this.radio = radio;
	}

	@Override
	public void setup() throws IllegalStateTransitionException {
		throw new IllegalStateTransitionException(
				String.format("Cannot setup Radio Unit while it is in the %s state.", this.name));
	}

	@Override
	public void activate() throws IllegalStateTransitionException {
		System.out.printf("Radio Unit is already in the %s state.%n", this.name);
	}

	@Override
	public void deactivate() throws IllegalStateTransitionException {
		RadioUnitState nextState = new DeactivatedState(this.radio);
		
		System.out.println("Deactivating Radio Unit.");
		
		this.radio.getCommandExecutor().deactivate();
		
		this.radio.setState(nextState);
	}

	@Override
	public void release() throws IllegalStateTransitionException {
		throw new IllegalStateTransitionException(
				String.format("Cannot release Radio Unit while it is in the %s state.", this.name));
	}

	@Override
	public void setupCarrier(Carrier carrier) throws IllegalStateTransitionException {
		System.out.printf("Setting up carrier on Radio Unit.%n");
		this.radio.getCommandExecutor().setupCarrier(carrier);
	}

	@Override
	public void signalScaling() throws IllegalStateTransitionException {
		System.out.println("Performing signal scaling on Radio Unit.");
		
		this.radio.getCommandExecutor().signalScaling();
	}

	@Override
	public void modifyCarrier(Integer carrierId, FrequencyBand band) throws IllegalStateTransitionException {
		System.out.printf("Modifying carrier (id=%d, freq=%s) on Radio Unit.%n", carrierId, band);
		
		this.radio.getCommandExecutor().modifyCarrier(carrierId, band);
	}

	@Override
	public void removeCarrier(Integer carrierId) throws IllegalStateTransitionException {
		throw new IllegalStateTransitionException(
				String.format("Cannot remove carrier from Radio Unit while it is in the %s state.", this.name));
	}

	@Override
	public void selfDiagnostics() throws IllegalStateTransitionException {
		System.out.println("Performing self diagnostics on Radio Unit.");

		this.radio.getCommandExecutor().selfDiagnostics();
	}

	@Override
	public void removeAllCarriers() throws IllegalStateTransitionException {
		throw new IllegalStateTransitionException(
				String.format("Cannot remove all carriers from Radio Unit while it is in the %s state.", this.name));
	}

	@Override
	public void postActivation() throws IllegalStateTransitionException {
		System.out.println("Performing post activation on Radio Unit.");

		this.radio.getCommandExecutor().postActivation();
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
