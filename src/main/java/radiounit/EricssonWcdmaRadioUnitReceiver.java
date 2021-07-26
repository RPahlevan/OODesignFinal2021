package radiounit;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import common.Carrier;
import common.FrequencyBand;
import common.WcdmaFrequencyBand;

/**
 * Implements necessary Radio Unit commands for the Ericsson WCDMA Radio Unit
 * 
 * @author esiumat
 *
 */
public class EricssonWcdmaRadioUnitReceiver implements RadioUnitReceiver {

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

	public EricssonWcdmaRadioUnitReceiver() {
		this.carriers = new HashMap<>();
	}

	public void setupEricssonWcdma() {
		System.out.println("[EricssonWcdmaRadioUnitReceiver] setupEricssonWcdma");
	}

	public void activateEricssonWcdma() {
		System.out.println("[EricssonWcdmaRadioUnitReceiver] activateEricssonWcdma");
	}

	public void deactivateEricssonWcdma() {
		System.out.println("[EricssonWcdmaRadioUnitReceiver] deactivateEricssonWcdma");
	}

	public void releaseEricssonWcdma() {
		System.out.println("[EricssonWcdmaRadioUnitReceiver] releaseEricssonWcdma");
	}

	public void setupCarrierEricssonWcdma(Carrier carrier) {
		System.out.println("[EricssonWcdmaRadioUnitReceiver] setupCarrierEricssonWcdma: " + carrier);

		// check if carrier is a WCDMA carrier
		boolean isWcdma = false;
		FrequencyBand band = carrier.getFrequencyBand();
		for (WcdmaFrequencyBand wcdmaFreq : WcdmaFrequencyBand.values()) {
			if (band == wcdmaFreq) {
				isWcdma = true;
				break;
			}
		}

		if (!isWcdma) {
			System.err.println("Cannot add non-WCDMA carrier to WCDMA radio!");
			return;
		}

		if (carriers.get(carrier.getCarrierId()) != null) {
			System.err.println("Carrier with ID [" + carrier.getCarrierId() + "] already exists!");
			return;
		}

		addCarrierInternal(carrier);
	}

	public void signalScalingEricssonWcdma() {
		System.out.println("[EricssonWcdmaRadioUnitReceiver] signalScalingEricssonWcdma");
	}

	public void modifyCarrierEricssonWcdma(Integer carrierId, FrequencyBand frequencyBand) {
		System.out.println(
				"[EricssonWcdmaRadioUnitReceiver] modifyCarrierEricssonWcdma: " + carrierId + ", " + frequencyBand.getBand());

		Carrier existingCarrier = carriers.get(carrierId);
		if (existingCarrier == null) {
			System.err.println("Carrier with ID [" + carrierId + "] does not exist!");
			return;
		}

		removeCarrierInternal(carrierId);
		existingCarrier.setFrequencyBand(frequencyBand);
		addCarrierInternal(existingCarrier);
	}

	public void removeCarrierEricssonWcdma(Integer carrierId) {
		System.out.println("[EricssonWcdmaRadioUnitReceiver] removeCarrierEricssonWcdma: " + carrierId);
		if (carriers.get(carrierId) == null) {
			System.err.println("EricssonWcdmaRadioUnitReceiver[] Invalid carrierId - cannot remove carrier");
		} else {
			carriers.remove(carrierId);
		}
	}

	public void selfDiagnosticsEricssonWcdma() {
		System.out.println("[EricssonWcdmaRadioUnitReceiver] selfDiagnosticsEricssonWcdma");
	}

	public void removeAllCarriersEricssonWcdma() {
		System.out.println("[EricssonWcdmaRadioUnitReceiver] removeAllCarriersEricssonWcdma");
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
			System.err.println("EricssonWcdmaRadioUnitReceiver[] Invalid carrierId - cannot remove carrier");
		} else {
			carriers.remove(carrierId);
		}
	}
}
