package carrierManagementSystem;

/**
 * This class is a child class to CarrierBuilder. It builds LTECarrier objects.
 */
public class LTECarrierBuilder implements CarrierBuilder {
    private Integer carrierId;
    private RFPorts[] rfPorts;
    private CarrierFrequencies carrierFrequencies;
    private Double transmittingPower;

	/**
	 * Sets up an ID for the LTE carrier.
	 * 
	 * @param carrierID The ID for carrier.
	 */
    @Override
    public void setCarrierId(Integer carrierID) {
        this.carrierId = carrierID;
    }

	/**
	 * Sets up RF ports for LTE.
	 * 
	 * @param rfPorts The RF ports for LTE. LTE needs 4 RF ports.
	 */
    @Override
    public void setRFPorts(RFPorts[] rfPorts) {
		// Catch exception for null rfPorts.
        if (rfPorts == null) {
            throw new NullPointerException("LTE RF Ports are not configured!");
        }

		// Setup the RF ports for the LTE carrier and if the number of elements 
		// is less than 4 it will throw an exception.
        if (rfPorts.length == 4) {
            this.rfPorts = new RFPorts[4];
            System.arraycopy(rfPorts, 0, this.rfPorts, 0, rfPorts.length);
        } else {
            throw new ArrayIndexOutOfBoundsException(
                    "Invalid value for the number of LTE ports. The number of RF Ports for LTE carrier has to be 4.");
        }
    }

	/**
	 * Sets up carrier frequency for LTE.
	 * 
	 * @param carrierFrequencies Configures the LTE band.
	 */
    @Override
    public void setFrequencyBand(CarrierFrequencies carrierFrequencies) {
        this.carrierFrequencies = carrierFrequencies;
    }

	/**
	 * Sets up transmission power for LTE.
	 * 
	 * @param transmittingPower Sets up the power.
	 */
    @Override
    public void setTransmittingPower(Double transmittingPower) {
        this.transmittingPower = transmittingPower;
    }

	/**
	 * Returns a built LTECarrier object.
	 * 
	 * @return The built LTECarrier.
	 */
    public LTECarrier getLTECarrier() {
        return new LTECarrier(carrierId, rfPorts, carrierFrequencies, transmittingPower);
    }
}
