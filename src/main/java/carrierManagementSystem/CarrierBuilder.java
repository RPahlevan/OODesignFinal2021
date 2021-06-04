package carrierManagementSystem;

/**
 * 
 * CarrierBuilder interface defines all possible ways to configure a LTE and
 * WCDMA product.
 * 
 * WCDMACarrierBuilder and LTECarrierBuilder class will implement these methods.
 */

public interface CarrierBuilder {

	/**
	 * This method helps with set up an ID for both LTE and WCDMA carrier.
	 * 
	 * @param carrierID set up ID for carrier.
	 */
	void setCarrierId(Integer carrierID);

	/**
	 * This method helps us with set up rf ports for LTE and WCDMA.
	 * 
	 * @param rfPorts wcdma needs 2 rf ports and lte needs 4 rf ports.
	 */
	void setRFPorts(RFPorts[] rfPorts);

	/**
	 * This method helps us with set up carrier frequency for WCDMA and LTE.
	 * 
	 * @param carrierFrequencies would configure lte or wcdma band.
	 */
	void setFrequencyBand(CarrierFrequencies carrierFrequencies);

	/**
	 * This method helps us with set up transmission power for WCDMA and LTE.
	 * 
	 * @param transmittingPower would set up power.
	 */
	void setTransmittingPower(Double transmittingPower);
}
