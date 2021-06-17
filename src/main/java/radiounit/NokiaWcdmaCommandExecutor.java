package radiounit;

import java.util.List;

import common.Carrier;
import common.FrequencyBand;

/**
 * Is responsible for executing the necessary Radio Comamnds for the Nokia WCDMA
 * radios
 * 
 * @author esiumat
 *
 */
public class NokiaWcdmaCommandExecutor implements RadioCommandExecutor {

	private NokiaWcdmaRadioUnitReceiver receiver;
	private NokiaWcdmaRadioUnitReceiverFactory receiverFactory;

	@Override
	public void setup() {
		receiver.setupNokiaWcdma();
	}

	@Override
	public void activate() {
		receiver.activateNokiaWcdma();
	}

	@Override
	public void deactivate() {
		receiver.deactivateNokiaWcdma();
	}

	@Override
	public void release() {
		receiver.releaseNokiaWcdma();
	}

	@Override
	public void setupCarrier(Carrier carrrier) {
		receiver.setupCarrierNokiaWcdma(carrrier);
	}

	@Override
	public void signalScaling() {
		receiver.signalScalingNokiaWcdma();
	}

	@Override
	public void modifyCarrier(Integer carrierId, FrequencyBand frequencyBand) {
		receiver.modifyCarrierNokiaWcdma(carrierId, frequencyBand);
	}

	@Override
	public void removeCarrier(Integer carrierId) {
		receiver.removeCarrierNokiaWcdma(carrierId);
	}

	@Override
	public void selfDiagnostics() {
		receiver.selfDiagnosticsNokiaWcdma();
	}

	@Override
	public void removeAllCharacters() {
		receiver.removeAllCharactersNokiaWcdma();
	}

	@Override
	public List<Carrier> getCarriers() {
		return receiver.getCarriers();
	}

}
