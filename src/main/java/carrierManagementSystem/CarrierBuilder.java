package carrierManagementSystem;


/**
 * @author epahram
 * 
 * CarrierBuilder interface defines all possible ways to configure a LTE and
 * WCDMA product.
 * 
 * WCDMACarrierBuilder and LTECarrierBuilder class will implement these methods.
 */

public interface CarrierBuilder {

	void setCarrierId(Integer carrierID);

	void setRFPorts(RFPorts[] rfPorts);

	void setFrequencyBand(CarrierFrequencies carrierFrequencies);

	void setTransmittingPower(Double transmittingPower);
}
