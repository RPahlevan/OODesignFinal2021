package radiounit;

import java.util.List;
import java.util.ArrayList;

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

	private static final int CARRIER_NOT_FOUND_INDEX = -1;

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
		
		// check if carrier is a LTE carrier
		boolean isLte = false;
		FrequencyBand band = carrier.getCarrierFrequencies();
		
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
		
		if (getCarrierById(carrier.getCarrierId()) != null) {
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
		
		int index = getCarrierIndexById(carrierId);
		
		// check that carrier exists
		if (index == CARRIER_NOT_FOUND_INDEX) {
			System.err.println("Carrier with ID [" + "] does not exist!");
			return;
		}
		
		Carrier existingCarrier = getCarrierById(carrierId);
		
		removeCarrierInternal(index);
		existingCarrier.setFrequencyBand(frequencyBand);
		addCarrierInternal(existingCarrier);
	}

	public void removeCarrierNokiaLte(Integer carrierId) {
		System.out.println("[NokiaLteRadioUnitReceiver] removeCarrierNokiaLte: " + carrierId);
		
		int index = getCarrierIndexById(carrierId);
		
		// check that carrier exists
		if (index == CARRIER_NOT_FOUND_INDEX) {
			System.err.println("Carrier with ID [" + "] does not exist!");
			return;
		}
		
		removeCarrierInternal(index);
	}

	public void selfDiagnosticsNokiaLte() {
		System.out.println("[NokiaLteRadioUnitReceiver] selfDiagnosticsNokiaLte");
	}

	public void removeAllCarriersNokiaLte() {
		System.out.println("[NokiaLteRadioUnitReceiver] removeAllCarriersNokiaLte");
		
		for (int i = 0; i < getAllCarriersInternal().size(); ++i) {
			removeCarrierInternal(i);
		}
	}

	public List<Carrier> getCarriers() {
		
		List<Carrier> carrierList = getAllCarriersInternal();
		
		for (Carrier c : carrierList) {
			System.out.println("Carrier: " + c);
		}
		
		return carrierList;
	}
	
	private Carrier getCarrierById(int id)
	{
		int index = getCarrierIndexById(id);

		if (index == CARRIER_NOT_FOUND_INDEX)
		{
			return null;
		}

		return getAllCarriersInternal().get(index);
	}

	private int getCarrierIndexById(int id)
	{
		for (int i = 0; i < getAllCarriersInternal().size(); ++i)
		{
			Carrier c = getAllCarriersInternal().get(i);

			if (c.getCarrierId() == id)
			{
				return i;
			}
		}

		return CARRIER_NOT_FOUND_INDEX;
	}

	/**
	 * Private, internal method with exclusive "getter" access to the carriers list.
	 * @param idx Index of the carrier to be retrieved
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
	 * @param index Index of the carrier to be removed
	 */
	private synchronized void removeCarrierInternal(int index)
	{
		if (index >= 0 && index < this.carriers.size())
		{
			this.carriers.remove(index);
		}
		else
		{
			System.err.println("EricssonLteRadioUnitReceiver[] Invalid index - cannot remove carrier");
		}
	}
}
