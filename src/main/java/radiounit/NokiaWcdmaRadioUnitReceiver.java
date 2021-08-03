package radiounit;

import java.util.Objects;
import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

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
	private volatile ConcurrentHashMap<Integer, Carrier> carriers;

	public NokiaWcdmaRadioUnitReceiver() {
		this.carriers = new ConcurrentHashMap<>();
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
		
		int carrierId = carrier.getCarrierId();

		if (this.carriers.containsKey(carrierId)) {
			System.err.println("Carrier with ID [" + carrierId + "] already exists!");
			return;
		}

		this.carriers.put(carrierId, carrier);
	}

	public void signalScalingNokiaWcdma() {
		System.out.println("[NokiaWcdmaRadioUnitReceiver] signalScalingNokiaWcdma");
	}

	public void modifyCarrierNokiaWcdma(Integer carrierId, FrequencyBand frequencyBand) {
		System.out.println(
				"[NokiaWcdmaRadioUnitReceiver] modifyCarrierNokiaWcdma: " + carrierId + ", " + frequencyBand.getBand());

		if (!this.carriers.containsKey(carrierId)) {
			System.err.println("Carrier with ID [" + carrierId + "] does not exist!");
			return;
		}

		Carrier existingCarrier = this.carriers.get(carrierId);
		
		Objects.requireNonNull(existingCarrier).setFrequencyBand(frequencyBand);
		
		this.carriers.replace(carrierId, existingCarrier);
	}

	public void removeCarrierNokiaWcdma(Integer carrierId) {
		System.out.println("[NokiaWcdmaRadioUnitReceiver] removeCarrierNokiaWcdma: " + carrierId);
		
		if (!this.carriers.containsKey(carrierId)) {
			System.err.println("NokiaWcdmaRadioUnitReceiver[] Invalid carrierId - cannot remove carrier");
		}
		
		this.carriers.remove(carrierId);
	}

	public void selfDiagnosticsNokiaWcdma() {
		System.out.println("[NokiaWcdmaRadioUnitReceiver] selfDiagnosticsNokiaWcdma");
	}

	public void removeAllCarriersNokiaWcdma() {
		System.out.println("[NokiaWcdmaRadioUnitReceiver] removeAllCarriersNokiaWcdma");
		this.carriers.clear();
	}

	public List<Carrier> getCarriers() {
		System.out.println("[NokiaWcdmaRadioUnitReceiver] getCarriers");
		
		List<Carrier> allCarriers = new ArrayList<Carrier>(this.carriers.values());
		
		for (Carrier c : allCarriers)
		{
			System.out.println("Carrier: " + c);
		}
		
		return allCarriers;
	}
}
