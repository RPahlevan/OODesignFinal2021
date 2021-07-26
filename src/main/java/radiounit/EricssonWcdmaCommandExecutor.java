package radiounit;

import java.util.List;

import common.Carrier;
import common.FrequencyBand;

/**
 * Is responsible for executing the necessary Radio Comamnds for the Ericsson WCDMA
 * radios
 * 
 * @author esiumat
 *
 */
public class EricssonWcdmaCommandExecutor implements RadioCommandExecutor {

	private final EricssonWcdmaRadioUnitReceiver receiver;

	public EricssonWcdmaCommandExecutor() {
		receiver = (EricssonWcdmaRadioUnitReceiver) EricssonWcdmaRadioUnitReceiverFactory.getInstance().createRadioUnitReceiver();
	}

	@Override
	public void setup() {
		System.out.println("[EricssonWcdmaCommandExecutor] setup");
		receiver.setupEricssonWcdma();
	}

	@Override
	public void activate() {
		System.out.println("[EricssonWcdmaCommandExecutor] activate");
		receiver.activateEricssonWcdma();
	}

	@Override
	public void deactivate() {
		System.out.println("[EricssonWcdmaCommandExecutor] deactivate");
		receiver.deactivateEricssonWcdma();
	}

	@Override
	public void release() {
		System.out.println("[EricssonWcdmaCommandExecutor] release");
		receiver.releaseEricssonWcdma();
	}

	@Override
	public void setupCarrier(Carrier carrier) {
		System.out.println("[EricssonWcdmaCommandExecutor] setupCarrier: " + carrier);
		receiver.setupCarrierEricssonWcdma(carrier);
	}

	@Override
	public void signalScaling() {
		System.out.println("[EricssonWcdmaCommandExecutor] signalScaling");
		receiver.signalScalingEricssonWcdma();
	}

	@Override
	public void modifyCarrier(Integer carrierId, FrequencyBand frequencyBand) {
		System.out.println("[EricssonWcdmaCommandExecutor] modifyCarrier: " + carrierId + ", " + frequencyBand.getBand());
		receiver.modifyCarrierEricssonWcdma(carrierId, frequencyBand);
	}

	@Override
	public void removeCarrier(Integer carrierId) {
		System.out.println("[EricssonWcdmaCommandExecutor] removeCarrier: " + carrierId);
		receiver.removeCarrierEricssonWcdma(carrierId);
	}

	@Override
	public void selfDiagnostics() {
		System.out.println("[EricssonWcdmaCommandExecutor] selfDiagnostics");
		receiver.selfDiagnosticsEricssonWcdma();
	}

	@Override
	public void removeAllCarriers() {
		System.out.println("[EricssonWcdmaCommandExecutor] removeAllCarriers");
		receiver.removeAllCarriersEricssonWcdma();
	}

	@Override
	public List<Carrier> getCarriers() {
		System.out.println("[EricssonWcdmaCommandExecutor] getCarriers");
		return receiver.getCarriers();
	}

}
