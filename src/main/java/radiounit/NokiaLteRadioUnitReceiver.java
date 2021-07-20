package radiounit;

import java.util.List;
import java.util.ArrayList;

import common.Carrier;
import common.FrequencyBand;

/**
 * Implements necessary Radio Unit commands for the Nokia LTE Radio Unit
 * 
 * @author esiumat
 *
 */
public class NokiaLteRadioUnitReceiver implements RadioUnitReceiver {

	private static final int CARRIER_NOT_FOUND_IDX = -1;

	/*
	 * The list of carriers is an important part of the radio
	 * 
	 * The "volatile" keyword ensures that this resource is not cached, and is
	 * retrieved directly from memory every time it is accessed.
	 * 
	 * The three "-Internal" methods below are all synchronized and have exclusive
	 * access to this resource.
	 */
	private volatile List<Carrier> carriers;

	public NokiaLteRadioUnitReceiver() {
		this.carriers = new ArrayList<Carrier>();
	}

	public void setupNokiaLte() {
		System.out.println("[NokiaLteRadioUnitReceiver] setupNokiaLte");
	}

	public void activateNokiaLte() {
		System.out.println("[NokiaLteRadioUnitReceiver] activateNokiaLte");
	}

	public void deactivateNokiaLte() {
		System.out.println("[NokiaLteRadioUnitReceiver] deactivateNokiaLte");
	}

	public void releaseNokiaLte() {
		System.out.println("[NokiaLteRadioUnitReceiver] releaseNokiaLte");
	}

	public void setupCarrierNokiaLte(Carrier carrier) {
		System.out.println("[NokiaLteRadioUnitReceiver] setupCarrierNokiaLte: " + carrier);
	}

	public void signalScalingNokiaLte() {
		System.out.println("[NokiaLteRadioUnitReceiver] signalScalingNokiaLte");
	}

	public void modifyCarrierNokiaLte(Integer carrierId, FrequencyBand frequencyBand) {
		System.out.println(
				"[NokiaLteRadioUnitReceiver] modifyCarrierNokiaLte: " + carrierId + ", " + frequencyBand.getBand());
	}

	public void removeCarrierNokiaLte(Integer carrierId) {
		System.out.println("[NokiaLteRadioUnitReceiver] removeCarrierNokiaLte: " + carrierId);
	}

	public void selfDiagnosticsNokiaLte() {
		System.out.println("[NokiaLteRadioUnitReceiver] selfDiagnosticsNokiaLte");
	}

	public void removeAllCarriersNokiaLte() {
		System.out.println("[NokiaLteRadioUnitReceiver] removeAllCarriersNokiaLte");
	}

	public List<Carrier> getCarriers() {
		return carriers;
	}

}
