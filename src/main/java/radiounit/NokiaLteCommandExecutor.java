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

	public NokiaLteCommandExecutor() {
		receiver = (NokiaLteRadioUnitReceiver) NokiaLteRadioUnitReceiverFactory.getInstance().createRadioUnitReceiver();
	}

	@Override
	public void setup() {
		System.out.println("[NokiaLteCommandExecutor] setup");
		receiver.setupNokiaLte();
	}

	@Override
	public void activate() {
		System.out.println("[NokiaLteCommandExecutor] activate");
		receiver.activateNokiaLte();
	}

	@Override
	public void deactivate() {
		System.out.println("[NokiaLteCommandExecutor] deactivate");
		receiver.deactivateNokiaLte();
	}

	@Override
	public void release() {
		System.out.println("[NokiaLteCommandExecutor] release");
		receiver.releaseNokiaLte();
	}

	@Override
	public void setupCarrier(Carrier carrier) {
		System.out.println("[NokiaLteCommandExecutor] setupCarrier: " + carrier);
		receiver.setupCarrierNokiaLte(carrier);
	}

	@Override
	public void signalScaling() {
		System.out.println("[NokiaLteCommandExecutor] signalScaling");
		receiver.signalScalingNokiaLte();
	}

	@Override
	public void modifyCarrier(Integer carrierId, FrequencyBand frequencyBand) {
		System.out.println("[NokiaLteCommandExecutor] modifyCarrier: " + carrierId + ", " + frequencyBand.getBand());
		receiver.modifyCarrierNokiaLte(carrierId, frequencyBand);
	}

	@Override
	public void removeCarrier(Integer carrierId) {
		System.out.println("[NokiaLteCommandExecutor] removeCarrier: " + carrierId);
		receiver.removeCarrierNokiaLte(carrierId);
	}

	@Override
	public void selfDiagnostics() {
		System.out.println("[NokiaLteCommandExecutor] selfDiagnostics");
		receiver.selfDiagnosticsNokiaLte();
	}

	@Override
	public void removeAllCarriers() {
		System.out.println("[NokiaLteCommandExecutor] removeAllCarriers");
		receiver.removeAllCarriersNokiaLte();
	}

	@Override
	public List<Carrier> getCarriers() {
		System.out.println("[NokiaLteCommandExecutor] getCarriers");
		return receiver.getCarriers();
	}

}
