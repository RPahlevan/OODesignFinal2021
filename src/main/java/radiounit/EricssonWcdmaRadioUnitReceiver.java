package radiounit;

import java.util.Objects;
import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

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
	private volatile ConcurrentHashMap<Integer, Carrier> carriers;

	public EricssonWcdmaRadioUnitReceiver() {
		this.carriers = new ConcurrentHashMap<>();
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
		System.out.println("[EricssonWcdmaRadioUnitReceiver] setupCarrierEricssonWcdma");

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
		
		int carrierId = carrier.getCarrierId();

		if (this.carriers.containsKey(carrierId)) {
			System.err.println("Carrier with ID [" + carrierId + "] already exists!");
			return;
		}

		this.carriers.put(carrierId, carrier);
	}

	public void signalScalingEricssonWcdma() {
		System.out.println("[EricssonWcdmaRadioUnitReceiver] signalScalingEricssonWcdma");
	}

	public void postActivationEricssonWcdma() {
		System.out.println("[EricssonWcdmaRadioUnitReceiver] postActivationEricssonWcdma");
	}

	public void modifyCarrierEricssonWcdma(Integer carrierId, FrequencyBand frequencyBand) {
		System.out.println(
				"[EricssonWcdmaRadioUnitReceiver] modifyCarrierEricssonWcdma: " + carrierId + ", " + frequencyBand.getBand());

		if (!this.carriers.containsKey(carrierId)) {
			System.err.println("Carrier with ID [" + carrierId + "] does not exist!");
			return;
		}

		Carrier existingCarrier = this.carriers.get(carrierId);
		
		Objects.requireNonNull(existingCarrier).setFrequencyBand(frequencyBand);
		
		this.carriers.replace(carrierId, existingCarrier);
	}

	public void removeCarrierEricssonWcdma(Integer carrierId) {
		System.out.println("[EricssonWcdmaRadioUnitReceiver] removeCarrierEricssonWcdma: " + carrierId);

		if (!this.carriers.containsKey(carrierId)) {
			System.err.println("EricssonWcdmaRadioUnitReceiver[] Invalid carrierId - cannot remove carrier");
			return;
		}
		
		this.carriers.remove(carrierId);
	}

	public void selfDiagnosticsEricssonWcdma() {
		System.out.println("[EricssonWcdmaRadioUnitReceiver] selfDiagnosticsEricssonWcdma");
	}

	public void removeAllCarriersEricssonWcdma() {
		System.out.println("[EricssonWcdmaRadioUnitReceiver] removeAllCarriersEricssonWcdma");

		this.carriers.clear();
	}

	public List<Carrier> getCarriers() {
		System.out.println("[EricssonWcdmaRadioUnitReceiver] getCarriers");
		return new ArrayList<>(this.carriers.values());
	}
}
