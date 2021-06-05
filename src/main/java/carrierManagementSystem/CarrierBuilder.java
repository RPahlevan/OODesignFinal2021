package carrierManagementSystem;

/**
 * The CarrierBuilder interface defines all possible ways to configure a LTE and
 * WCDMA carrier.
 * <p>
 * The WCDMACarrierBuilder and LTECarrierBuilder classes will implement these methods.
 */

public interface CarrierBuilder {

	/**
	 * Sets up an ID for both LTE and WCDMA carrier.
	 * 
	 * @param carrierID The ID for the carrier.
	 */
	void setCarrierId(Integer carrierID);

	/**
	 * Sets up the RF ports for LTE and WCDMA.
	 * 
	 * @param rfPorts The RF ports for the carrier. 
	 *                WCDMA needs 2 RF ports and LTE needs 4 RF ports.
	 */
	void setRFPorts(RFPorts[] rfPorts);

	/**
	 * Sets up the carrier frequency for WCDMA and LTE.
	 * 
	 * @param carrierFrequencies The carrier frequency for the LTE or WCDMA band.
	 */
	void setFrequencyBand(CarrierFrequencies carrierFrequencies);

	/**
	 * Sets up the transmission power for WCDMA and LTE.
	 * 
	 * @param transmittingPower The power for the carrier.
	 */
	void setTransmittingPower(Double transmittingPower);
}
