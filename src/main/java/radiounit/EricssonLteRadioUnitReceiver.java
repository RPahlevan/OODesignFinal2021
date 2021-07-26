package radiounit;

import java.util.List;
import java.util.ArrayList;
import java.util.Objects;

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

	private static final int CARRIER_NOT_FOUND_IDX = -1;
	/*
	 * This is the critical resource of a radio.
	 * 
	 * The "volatile" keyword ensures that this resource is not cached, and is retrieved directly from
	 * 	memory every time it is accessed.
	 * 
	 * The three "-Internal" methods below are all synchronized and have exclusive access to this resource. 
	 */
	private volatile List<Carrier> carriers;
	
	public EricssonLteRadioUnitReceiver()
	{
		this.carriers = new ArrayList<>();
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
		System.out.println("[EricssonLteRadioUnitReceiver] setupCarrierEricssonLte: " + carrier);
		
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
		
		if (getCarrierById(carrier.getCarrierId()) != null)
		{
			System.err.println("Carrier with id " + carrier.getCarrierId() + " already exists!");
			return;
		}
		
		addCarrierInternal(carrier);
	}

	public void signalScalingEricssonLte() {
		System.out.println("[EricssonLteRadioUnitReceiver] signalScalingEricssonLte");
	}

	public void modifyCarrierEricssonLte(Integer carrierId, FrequencyBand frequencyBand) {
		System.out.println("[EricssonLteRadioUnitReceiver] modifyCarrierEricssonLte: " + carrierId + ", " + frequencyBand.getBand());
		
		int idx = getCarrierIdxById(carrierId);
		
		if (idx == CARRIER_NOT_FOUND_IDX)
		{
			System.err.println("Carrier with id " + carrierId + " does not exist!");
			return;
		}
		
		Carrier existingCarrier = getCarrierById(carrierId);
		
		removeCarrierInternal(idx);
		
		Objects.requireNonNull(existingCarrier).setFrequencyBand(frequencyBand);
		
		addCarrierInternal(existingCarrier);
	}

	public void removeCarrierEricssonLte(Integer carrierId) {
		System.out.println("[EricssonLteRadioUnitReceiver] removeCarrierEricssonLte: " + carrierId);
		
		int idx = getCarrierIdxById(carrierId);
		
		if (idx == CARRIER_NOT_FOUND_IDX)
		{
			System.err.println("Carrier with id " + carrierId + " does not exist!");
			return;
		}
		
		removeCarrierInternal(idx);
	}

	public void selfDiagnosticsEricssonLte() {
		System.out.println("[EricssonLteRadioUnitReceiver] selfDiagnosticsEricssonLte");
	}

	public void removeAllCarriersEricssonLte() {
		System.out.println("[EricssonLteRadioUnitReceiver] removeAllCarriersEricssonLte");
		
		for (int i = 0; i < getAllCarriersInternal().size(); ++i)
		{
			removeCarrierInternal(i);
		}
	}

	public List<Carrier> getCarriers() {
		System.out.println("[EricssonLteRadioUnitReceiver] getCarriers");
		
		List<Carrier> carrierList = getAllCarriersInternal();
		
		for (Carrier c : carrierList)
		{
			System.out.println("Carrier: " + c);
		}
		
		return carrierList;
	}
	
	private Carrier getCarrierById(int id)
	{
		int idx = getCarrierIdxById(id);
		
		if (idx == CARRIER_NOT_FOUND_IDX)
		{
			return null;
		}
		
		return getAllCarriersInternal().get(idx);
	}
	
	private int getCarrierIdxById(int id)
	{
		for (int i = 0; i < getAllCarriersInternal().size(); ++i)
		{
			Carrier c = getAllCarriersInternal().get(i);
			
			if (c.getCarrierId() == id)
			{
				return i;
			}
		}
		
		return CARRIER_NOT_FOUND_IDX;
	}
	

	/**
	 * Private, internal method with exclusive "getter" access to the carriers list.
	 *
	 * @return Carrier at the specified index
	 */
	private synchronized List<Carrier> getAllCarriersInternal()
	{
		return this.carriers;
	}
	
	/**
	 * Private, internal method with exclusive "setter" access to the carriers list.
	 * @param carrier Carrier to add to the list
	 */
	private synchronized void addCarrierInternal(Carrier carrier)
	{
		this.carriers.add(carrier);
	}
	
	/**
	 * Private, internal method with exclusive removal access to the carriers list.
	 * @param idx Index of the carrier to be removed
	 */
	private synchronized void removeCarrierInternal(int idx)
	{
		if (idx >= 0 && idx < this.carriers.size())
		{
			this.carriers.remove(idx);
		}
		else
		{
			System.err.println("EricssonLteRadioUnitReceiver[] Invalid index - cannot remove carrier");
		}
	}

}
