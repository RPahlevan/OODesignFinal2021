package radiounit;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
	private volatile Map<Integer, Carrier> carriers;

	public NokiaLteRadioUnitReceiver() {
		this.carriers = new HashMap<>();
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

		if (carriers.get(carrier.getCarrierId()) != null) {
			System.err.println("Carrier with ID [" + carrier.getCarrierId() + "] already exists!");
			return;
		}

		addCarrierInternal(carrier);
	}

	public void signalScalingNokiaLte() {
		System.out.println("[NokiaLteRadioUnitReceiver] signalScalingNokiaLte");
	}

	public void modifyCarrierNokiaLte(Integer carrierId, FrequencyBand frequencyBand) {
		System.out.println(
				"[NokiaLteRadioUnitReceiver] modifyCarrierNokiaLte: " + carrierId + ", " + frequencyBand.getBand());

		Carrier existingCarrier = carriers.get(carrierId);
		if (existingCarrier == null) {
			System.err.println("Carrier with ID [" + carrierId + "] does not exist!");
			return;
		}

		removeCarrierInternal(carrierId);
		existingCarrier.setFrequencyBand(frequencyBand);
		addCarrierInternal(existingCarrier);
	}

	public void removeCarrierNokiaLte(Integer carrierId) {
		System.out.println("[NokiaLteRadioUnitReceiver] removeCarrierNokiaLte: " + carrierId);
		if (carriers.get(carrierId) == null) {
			System.err.println("NokiaLteRadioUnitReceiver[] Invalid carrierId - cannot remove carrier");
		} else {
			carriers.remove(carrierId);
		}
	}

	public void selfDiagnosticsNokiaLte() {
		System.out.println("[NokiaLteRadioUnitReceiver] selfDiagnosticsNokiaLte");
	}

	public void removeAllCarriersNokiaLte() {
		System.out.println("[NokiaLteRadioUnitReceiver] removeAllCarriersNokiaLte");
		carriers.clear();
	}

	public List<Carrier> getCarriers() {

		List<Carrier> carrierList = new ArrayList<>(carriers.values());

		for (Carrier c : carrierList) {
			System.out.println("Carrier: " + c);
		}

		return carrierList;
	}

	/**
	 * Private, internal method with exclusive "setter" access to the carriers list.
	 * 
	 * @param carrier Carrier to add to the list
	 */
	private synchronized void addCarrierInternal(Carrier carrier) {
		this.carriers.put(carrier.getCarrierId(), carrier);
	}

	/**
	 * Private, internal method with exclusive removal access to the carriers list.
	 * 
	 * @param carrierId Index of the carrier to be removed
	 */
	private synchronized void removeCarrierInternal(int carrierId) {
		if (carriers.get(carrierId) == null) {
			System.err.println("NokiaLteRadioUnitReceiver[] Invalid carrierId - cannot remove carrier");
		} else {
			carriers.remove(carrierId);
		}
	}
}
