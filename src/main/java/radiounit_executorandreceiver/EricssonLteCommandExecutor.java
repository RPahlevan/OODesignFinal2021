package radiounit_executorandreceiver;

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

	private EricssonLteRadioUnitReceiver receiver;
	private EricssonLteRadioUnitReceiverFactory receiverFactory;

	@Override
	public void setup() {
		receiver.setupEricssonLte();
	}

	@Override
	public void activate() {
		receiver.activateEricssonLte();
	}

	@Override
	public void deactivate() {
		receiver.deactivateEricssonLte();
	}

	@Override
	public void release() {
		receiver.releaseEricssonLte();
	}

	@Override
	public void setupCarrier(Carrier carrrier) {
		receiver.setupCarrierEricssonLte(carrrier);
	}

	@Override
	public void signalScaling() {
		receiver.signalScalingEricssonLte();
	}

	@Override
	public void modifyCarrier(Integer carrierId, FrequencyBand frequencyBand) {
		receiver.modifyCarrierEricssonLte(carrierId, frequencyBand);
	}

	@Override
	public void removeCarrier(Integer carrierId) {
		receiver.removeCarrierEricssonLte(carrierId);
	}

	@Override
	public void selfDiagnostics() {
		receiver.selfDiagnosticsEricssonLte();
	}

	@Override
	public void removeAllCharacters() {
		receiver.removeAllCharactersEricssonLte();
	}

	@Override
	public List<Carrier> getCarriers() {
		return receiver.getCarriers();
	}

}
