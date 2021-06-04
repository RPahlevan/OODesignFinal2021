package carrierManagementSystem;

/**
 * 
 * This class is responsible to create LTE carrier and WCDMA carrier. We can
 * call this class director as it will direct the creation command from
 * client(CarrirerManagementSystemClient) to Carrier builder class.
 */
public class CarrierManagementSystemDirector {

	/**
	 * The below method is responsible for creating LTE carrier based on inputs.
	 * This method will ask CarrierBuilder children class - LTECarrierBuilder- to
	 * create LTE carrier based on inputs.
	 * 
	 * @param carrierId          Carrier ID will be assigned to LTE carrier.
	 * @param rfPorts            LTE carrier needs 4 ports. These RF ports can be
	 *                           selected from RFPorts enum class.
	 * @param carrierFrequencies LTE carrier needs specific band to operate. List of
	 *                           LTE carrier frequency are available in
	 *                           CarrierFrequencies enum class.
	 * @param transmittingPower  LTE carrier need transmitting power to be created.
	 */
	public void CreateLTECarrier(Integer carrierId, RFPorts[] rfPorts, CarrierFrequencies carrierFrequencies,
			Double transmittingPower) {
		LTECarrierBuilder lteCarrier = new LTECarrierBuilder();
		lteCarrier.setCarrierId(carrierId);
		lteCarrier.setFrequencyBand(carrierFrequencies);
		lteCarrier.setTransmittingPower(transmittingPower);
		lteCarrier.setRFPorts(rfPorts);
		LTECarrier LTE = lteCarrier.getLTECarrier();
		System.out.println("Final result: \n" + LTE.print());
	}

	/**
	 * The below method is responsible for creating WCDMA carrier based on inputs.
	 * This method will ask CarrierBuilder children class - WCDMACarrierBuilder- to
	 * create LTE carrier based on inputs.
	 * 
	 * @param carrierId          Carrier ID will be assigned to WCDMA carrier.
	 * @param rfPorts            WCDMA carrier needs 2 ports. These RF ports can be
	 *                           selected from RFPorts enum class.
	 * @param carrierFrequencies WCDMA carrier needs specific band to operate. List
	 *                           of WCDMA carrier frequency are available in
	 *                           CarrierFrequencies enum class.
	 * @param transmittingPower  WCDMA carrier need transmitting power to be
	 *                           created.
	 */
	public void CreateWCDMACarrier(Integer carrierId, RFPorts[] rfPorts, CarrierFrequencies carrierFrequencies,
			Double transmittingPower) {

		WCDMACarrierBuilder wcdmaCarrier = new WCDMACarrierBuilder();
		wcdmaCarrier.setCarrierId(carrierId);
		wcdmaCarrier.setFrequencyBand(carrierFrequencies);
		wcdmaCarrier.setTransmittingPower(transmittingPower);
		wcdmaCarrier.setRFPorts(rfPorts);
		WCDMACarrier WCDMA = wcdmaCarrier.getWCDMACarrier();
		System.out.println("Final result: \n" + WCDMA.print());
	}
}
