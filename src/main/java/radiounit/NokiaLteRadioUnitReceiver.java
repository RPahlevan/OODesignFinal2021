package radiounit;

import java.util.List;

import common.Carrier;
import common.FrequencyBand;

/**
 * Implements necessary Radio Unit commands for the Nokia LTE Radio Unit
 * 
 * @author esiumat
 *
 */
public class NokiaLteRadioUnitReceiver implements RadioUnitReceiver {

	private List<Carrier> carriers;

	public void setupNokiaLte() {
		// TODO Auto-generated method stub

	}

	public void activateNokiaLte() {
		// TODO Auto-generated method stub

	}

	public void deactivateNokiaLte() {
		// TODO Auto-generated method stub
		
	}

	public void releaseNokiaLte() {
		// TODO Auto-generated method stub

	}

	public void setupCarrierNokiaLte(Carrier carrier) {
		// TODO Auto-generated method stub

	}

	public void signalScalingNokiaLte() {
		// TODO Auto-generated method stub

	}

	public void modifyCarrierNokiaLte(Integer carrierId, FrequencyBand frequencyBand) {
		// TODO Auto-generated method stub

	}

	public void removeCarrierNokiaLte(Integer carrierId) {
		// TODO Auto-generated method stub

	}

	public void selfDiagnosticsNokiaLte() {
		// TODO Auto-generated method stub

	}

	public void removeAllCarriersNokiaLte() {
		// TODO Auto-generated method stub

	}

	public List<Carrier> getCarriers() {
		return carriers;
	}

}
