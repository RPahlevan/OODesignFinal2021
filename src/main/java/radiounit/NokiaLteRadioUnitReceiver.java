package radiounit;

import java.util.Objects;
import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

import common.Carrier;
import common.FrequencyBand;
import common.LteFrequencyBand;

/**
 * Implements necessary Radio Unit commands for the Nokia LTE Radio Unit
 * 
 * @author esiumat
 *
 */
public class NokiaLteRadioUnitReceiver implements RadioUnitReceiver {

	/*
	 * The list of carriers is an important part of the radio
	 * 
	 * The "volatile" keyword ensures that this resource is not cached, and is
	 * retrieved directly from memory every time it is accessed.
	 * 
	 * The three "-Internal" methods below are all synchronized and have exclusive
	 * access to this resource.
	 */
	private volatile ConcurrentHashMap<Integer, Carrier> carriers;

	public NokiaLteRadioUnitReceiver() {
		this.carriers = new ConcurrentHashMap<>();
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
		System.out.println("[NokiaLteRadioUnitReceiver] setupCarrierNokiaLte");

		// check if carrier is a LTE carrier
		boolean isLte = false;
		FrequencyBand band = carrier.getFrequencyBand();
		for (LteFrequencyBand lteFreq : LteFrequencyBand.values()) {
			if (band == lteFreq) {
				isLte = true;
				break;
			}
		}

		if (!isLte) {
			System.err.println("Cannot add non-LTE carrier to LTE radio!");
			return;
		}
		
		int carrierId = carrier.getCarrierId();

		if (this.carriers.containsKey(carrierId)) {
			System.err.println("Carrier with ID [" + carrierId + "] already exists!");
			return;
		}

		this.carriers.put(carrierId, carrier);
	}

	public void signalScalingNokiaLte() {
		System.out.println("[NokiaLteRadioUnitReceiver] signalScalingNokiaLte");
	}

	public void modifyCarrierNokiaLte(Integer carrierId, FrequencyBand frequencyBand) {
		System.out.println(
				"[NokiaLteRadioUnitReceiver] modifyCarrierNokiaLte: " + carrierId + ", " + frequencyBand.getBand());

		if (!this.carriers.containsKey(carrierId)) {
			System.err.println("Carrier with ID [" + carrierId + "] does not exist!");
			return;
		}

		Carrier existingCarrier = this.carriers.get(carrierId);
		
		Objects.requireNonNull(existingCarrier).setFrequencyBand(frequencyBand);
		
		this.carriers.replace(carrierId, existingCarrier);
	}

	public void removeCarrierNokiaLte(Integer carrierId) {
		System.out.println("[NokiaLteRadioUnitReceiver] removeCarrierNokiaLte: " + carrierId);
		if (!this.carriers.containsKey(carrierId)) {
			System.err.println("NokiaLteRadioUnitReceiver[] Invalid carrierId - cannot remove carrier");
		}
		
		
		this.carriers.remove(carrierId);
	}

	public void selfDiagnosticsNokiaLte() {
		System.out.println("[NokiaLteRadioUnitReceiver] selfDiagnosticsNokiaLte");
	}

	public void postActivationNokiaLte() {
		System.out.println("[NokiaLteRadioUnitReceiver] postActivationNokiaLte");
	}

	public void removeAllCarriersNokiaLte() {
		System.out.println("[NokiaLteRadioUnitReceiver] removeAllCarriersNokiaLte");

		this.carriers.clear();
	}

	public List<Carrier> getCarriers() {
		System.out.println("[NokiaLteRadioUnitReceiver] getCarriers");
		return new ArrayList<>(this.carriers.values());
	}
}
