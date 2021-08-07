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

	private final NokiaWcdmaRadioUnitReceiver receiver;

	public NokiaWcdmaCommandExecutor() {
		receiver = (NokiaWcdmaRadioUnitReceiver) NokiaWcdmaRadioUnitReceiverFactory.getInstance().createRadioUnitReceiver();
	}

	@Override
	public void setup() {
		System.out.println("[NokiaWcdmaCommandExecutor] setup");
		receiver.setupNokiaWcdma();
	}

	@Override
	public void activate() {
		System.out.println("[NokiaWcdmaCommandExecutor] activate");
		receiver.activateNokiaWcdma();
	}

	@Override
	public void deactivate() {
		System.out.println("[NokiaWcdmaCommandExecutor] deactivate");
		receiver.deactivateNokiaWcdma();
	}

	@Override
	public void release() {
		System.out.println("[NokiaWcdmaCommandExecutor] release");
		receiver.releaseNokiaWcdma();
	}

	@Override
	public void setupCarrier(Carrier carrier) {
		System.out.println("[NokiaWcdmaCommandExecutor] setupCarrier");
		receiver.setupCarrierNokiaWcdma(carrier);
	}

	@Override
	public void signalScaling() {
		System.out.println("[NokiaWcdmaCommandExecutor] signalScaling");
		receiver.signalScalingNokiaWcdma();
	}

	@Override
	public void modifyCarrier(Integer carrierId, FrequencyBand frequencyBand) {
		System.out.println("[NokiaWcdmaCommandExecutor] modifyCarrier: " + carrierId + ", " + frequencyBand.getBand());
		receiver.modifyCarrierNokiaWcdma(carrierId, frequencyBand);
	}

	@Override
	public void removeCarrier(Integer carrierId) {
		System.out.println("[NokiaWcdmaCommandExecutor] removeCarrier: " + carrierId);
		receiver.removeCarrierNokiaWcdma(carrierId);
	}

	@Override
	public void selfDiagnostics() {
		System.out.println("[NokiaWcdmaCommandExecutor] selfDiagnostics");
		receiver.selfDiagnosticsNokiaWcdma();
	}

	@Override
	public void removeAllCarriers() {
		System.out.println("[NokiaWcdmaCommandExecutor] removeAllCarriers");
		receiver.removeAllCarriersNokiaWcdma();
	}

	@Override
	public void postActivation() {
		System.out.println("[NokiaWcdmaCommandExecutor] postActivation");
		receiver.postActivationNokiaWcdma();
	}

	@Override
	public List<Carrier> getCarriers() {
		System.out.println("[NokiaWcdmaCommandExecutor] getCarriers");
		return receiver.getCarriers();
	}

}
