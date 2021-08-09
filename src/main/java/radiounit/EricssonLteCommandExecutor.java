package radiounit;

import java.util.List;

import common.Carrier;
import common.FrequencyBand;

/**
 * Is responsible for executing the necessary Radio Comamnds for the Ericsson
 * LTE radios
 * 
 * @author esiumat
 *
 */
public class EricssonLteCommandExecutor implements RadioCommandExecutor {

	/* 
	 * Each ConcreteRadioUnit could have it's own RadioCommandExecutor, thus this can't be a singleton
	 * 
	 * Since the receiver provides it's own protection mechanisms for critical resources, we don't need to provide any here.
	 */
	private final EricssonLteRadioUnitReceiver receiver;
	
	public EricssonLteCommandExecutor()
	{
		receiver = (EricssonLteRadioUnitReceiver) EricssonLteRadioUnitReceiverFactory
				.getInstance()
				.createRadioUnitReceiver();
	}

	@Override
	public void setup() {
		System.out.println("[EricssonLteCommandExecutor] setup");
		receiver.setupEricssonLte();
	}

	@Override
	public void activate() {
		System.out.println("[EricssonLteCommandExecutor] activate");
		receiver.activateEricssonLte();
	}

	@Override
	public void deactivate() {
		System.out.println("[EricssonLteCommandExecutor] deactivate");
		receiver.deactivateEricssonLte();
	}

	@Override
	public void release() {
		System.out.println("[EricssonLteCommandExecutor] release");
		receiver.releaseEricssonLte();
	}

	@Override
	public void setupCarrier(Carrier carrier) {
		System.out.println("[EricssonLteCommandExecutor] setupCarrier");
		receiver.setupCarrierEricssonLte(carrier);
	}

	@Override
	public void postActivation() {
		System.out.println("[EricssonLteCommandExecutor] postActivation");
		receiver.postActivationEricssonLte();
	}

	@Override
	public void signalScaling() {
		System.out.println("[EricssonLteCommandExecutor] signalScaling");
		receiver.signalScalingEricssonLte();
	}

	@Override
	public void modifyCarrier(Integer carrierId, FrequencyBand frequencyBand) {
		System.out.println("[EricssonLteCommandExecutor] modifyCarrier: " + carrierId + ", " + frequencyBand.getBand());
		receiver.modifyCarrierEricssonLte(carrierId, frequencyBand);
	}

	@Override
	public void removeCarrier(Integer carrierId) {
		System.out.println("[EricssonLteCommandExecutor] removeCarrier: " + carrierId);
		receiver.removeCarrierEricssonLte(carrierId);
	}

	@Override
	public void selfDiagnostics() {
		System.out.println("[EricssonLteCommandExecutor] selfDiagnostics");
		receiver.selfDiagnosticsEricssonLte();
	}

	@Override
	public void removeAllCarriers() {
		System.out.println("[EricssonLteCommandExecutor] removeAllCarriers");
		receiver.removeAllCarriersEricssonLte();
	}

	@Override
	public List<Carrier> getCarriers() {
		System.out.println("[EricssonLteCommandExecutor] getCarriers");
		return receiver.getCarriers();
	}

}
