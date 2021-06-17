package radiounit;

import java.util.List;

import common.Carrier;
import common.FrequencyBand;

/**
 * Is responsible for executing the necessary Radio Comamnds for the Nokia LTE
 * radios
 * 
 * @author esiumat
 *
 */
public class NokiaLteCommandExecutor implements RadioCommandExecutor {

	private NokiaLteRadioUnitReceiver receiver;
	private NokiaLteRadioUnitReceiverFactory receiverFactory;

	@Override
	public void setup() {
		receiver.setupNokiaLte();
	}

	@Override
	public void activate() {
		receiver.activateNokiaLte();
	}

	@Override
	public void deactivate() {
		receiver.deactivateNokiaLte();
	}

	@Override
	public void release() {
		receiver.releaseNokiaLte();
	}

	@Override
	public void setupCarrier(Carrier carrrier) {
		receiver.setupCarrierNokiaLte(carrrier);
	}

	@Override
	public void signalScaling() {
		receiver.signalScalingNokiaLte();
	}

	@Override
	public void modifyCarrier(Integer carrierId, FrequencyBand frequencyBand) {
		receiver.modifyCarrierNokiaLte(carrierId, frequencyBand);
	}

	@Override
	public void removeCarrier(Integer carrierId) {
		receiver.removeCarrierNokiaLte(carrierId);
	}

	@Override
	public void selfDiagnostics() {
		receiver.selfDiagnosticsNokiaLte();
	}

	@Override
	public void removeAllCharacters() {
		receiver.removeAllCharactersNokiaLte();
	}

	@Override
	public List<Carrier> getCarriers() {
		return receiver.getCarriers();
	}

}
