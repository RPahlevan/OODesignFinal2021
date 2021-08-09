package radiounit;


import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.List;
import java.util.ArrayList;

import common.Carrier;
import common.FrequencyBand;
import common.LteFrequencyBand;

/**
 * Implements necessary Radio Unit commands for the Ericsson LTE Radio Unit
 * 
 * @author esiumat
 *
 */
public class EricssonLteRadioUnitReceiver implements RadioUnitReceiver {

	/*
	 * This is the critical resource of a radio.
	 * 
	 * The "volatile" keyword ensures that this resource is not cached, and is retrieved directly from
	 * 	memory every time it is accessed.
	 * 
	 * The three "-Internal" methods below are all synchronized and have exclusive access to this resource. 
	 */
	private volatile ConcurrentHashMap<Integer, Carrier> carriers;
	
	public EricssonLteRadioUnitReceiver()
	{
		this.carriers = new ConcurrentHashMap<>();
	}

	public void setupEricssonLte() {
		System.out.println("[EricssonLteRadioUnitReceiver] setupEricssonLte");
	}

	public void activateEricssonLte() {
		System.out.println("[EricssonLteRadioUnitReceiver] activateEricssonLte");
	}

	public void deactivateEricssonLte() {
		System.out.println("[EricssonLteRadioUnitReceiver] deactivateEricssonLte");
	}

	public void releaseEricssonLte() {
		System.out.println("[EricssonLteRadioUnitReceiver] releaseEricssonLte");
	}

	public void setupCarrierEricssonLte(Carrier carrier) {
		System.out.println("[EricssonLteRadioUnitReceiver] setupCarrierEricssonLte");
		
		// Validate carrier
		FrequencyBand band = carrier.getFrequencyBand();
		boolean isLte = false;
		
		for (LteFrequencyBand lteFreq : LteFrequencyBand.values())
		{
			if (band == lteFreq)
			{
				isLte = true;
				break;
			}
		}

		if (!isLte)
		{
			System.err.println("Cannot add WCDMA carrier to LTE radio!");
			return;
		}
		
		int carrierId = carrier.getCarrierId();
		
		if (this.carriers.containsKey(carrierId))
		{
			System.err.println("Carrier with id " + carrierId + " already exists!");
			return;
		}
		
		this.carriers.put(carrierId, carrier);
	}

	public void signalScalingEricssonLte() {
		System.out.println("[EricssonLteRadioUnitReceiver] signalScalingEricssonLte");
	}

	public void postActivationEricssonLte() {
		System.out.println("[EricssonLteRadioUnitReceiver] postActivationEricssonLte");
	}

	public void modifyCarrierEricssonLte(Integer carrierId, FrequencyBand frequencyBand) {
		System.out.println("[EricssonLteRadioUnitReceiver] modifyCarrierEricssonLte: " + carrierId + ", " + frequencyBand.getBand());

		if (!this.carriers.containsKey(carrierId))
		{
			System.err.println("Carrier with id " + carrierId + " does not exist!");
			return;
		}
		
		Carrier existingCarrier = this.carriers.get(carrierId);
		
		Objects.requireNonNull(existingCarrier).setFrequencyBand(frequencyBand);
		
		this.carriers.replace(carrierId, existingCarrier);
	}

	public void removeCarrierEricssonLte(Integer carrierId) {
		System.out.println("[EricssonLteRadioUnitReceiver] removeCarrierEricssonLte: " + carrierId);
		
		if (!this.carriers.containsKey(carrierId))
		{
			System.err.println("Carrier with id " + carrierId + " does not exist!");
			return;
		}
		
		this.carriers.remove(carrierId);
	}

	public void selfDiagnosticsEricssonLte() {
		System.out.println("[EricssonLteRadioUnitReceiver] selfDiagnosticsEricssonLte");
	}

	public void removeAllCarriersEricssonLte() {
		System.out.println("[EricssonLteRadioUnitReceiver] removeAllCarriersEricssonLte");
		
		this.carriers.clear();
	}

	public List<Carrier> getCarriers() {
		System.out.println("[EricssonLteRadioUnitReceiver] getCarriers");
		return new ArrayList<>(this.carriers.values());
	}

}
