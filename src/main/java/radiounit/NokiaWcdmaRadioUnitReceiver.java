package radiounit;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import common.Carrier;
import common.FrequencyBand;
import common.WcdmaFrequencyBand;

/**
 * Implements necessary Radio Unit commands for the Nokia WCDMA Radio Unit
 * 
 * @author esiumat
 *
 */
public class NokiaWcdmaRadioUnitReceiver implements RadioUnitReceiver {

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

	public NokiaWcdmaRadioUnitReceiver() {
		this.carriers = new HashMap<Integer, Carrier>();
	}

	public void setupNokiaWcdma() {
		System.out.println("[NokiaWcdmaRadioUnitReceiver] setupNokiaWcdma");
	}

	public void activateNokiaWcdma() {
		System.out.println("[NokiaWcdmaRadioUnitReceiver] activateNokiaWcdma");
	}

	public void deactivateNokiaWcdma() {
		System.out.println("[NokiaWcdmaRadioUnitReceiver] deactivateNokiaWcdma");
	}

	public void releaseNokiaWcdma() {
		System.out.println("[NokiaWcdmaRadioUnitReceiver] releaseNokiaWcdma");
	}

	public void setupCarrierNokiaWcdma(Carrier carrier) {
		System.out.println("[NokiaWcdmaRadioUnitReceiver] setupCarrierNokiaWcdma: " + carrier);

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

	public void signalScalingNokiaWcdma() {
		System.out.println("[NokiaWcdmaRadioUnitReceiver] signalScalingNokiaWcdma");
	}

	public void modifyCarrierNokiaWcdma(Integer carrierId, FrequencyBand frequencyBand) {
		System.out.println(
				"[NokiaWcdmaRadioUnitReceiver] modifyCarrierNokiaWcdma: " + carrierId + ", " + frequencyBand.getBand());

		Carrier existingCarrier = carriers.get(carrierId);
		if (existingCarrier == null) {
			System.err.println("Carrier with ID [" + carrierId + "] does not exist!");
			return;
		}

		removeCarrierInternal(carrierId);
		existingCarrier.setFrequencyBand(frequencyBand);
		addCarrierInternal(existingCarrier);
	}

	public void removeCarrierNokiaWcdma(Integer carrierId) {
		System.out.println("[NokiaWcdmaRadioUnitReceiver] removeCarrierNokiaWcdma: " + carrierId);
		if (carriers.get(carrierId) == null) {
			System.err.println("EricssonWcdmaRadioUnitReceiver[] Invalid carrierId - cannot remove carrier");
		} else {
			carriers.remove(carrierId);
		}
	}

	public void selfDiagnosticsNokiaWcdma() {
		System.out.println("[NokiaWcdmaRadioUnitReceiver] selfDiagnosticsNokiaWcdma");
	}

	public void removeAllCarriersNokiaWcdma() {
		System.out.println("[NokiaWcdmaRadioUnitReceiver] removeAllCarriersNokiaWcdma");
		carriers.clear();
	}

	public List<Carrier> getCarriers() {

		List<Carrier> carrierList = new ArrayList<Carrier>(carriers.values());

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
	 * @param index Index of the carrier to be removed
	 */
	private synchronized void removeCarrierInternal(int carrierId) {
		if (carriers.get(carrierId) == null) {
			System.err.println("EricssonWcdmaRadioUnitReceiver[] Invalid carrierId - cannot remove carrier");
		} else {
			carriers.remove(carrierId);
		}
	}
}
