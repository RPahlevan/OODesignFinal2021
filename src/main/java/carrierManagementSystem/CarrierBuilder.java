package carrierManagementSystem;

import java.util.List;

/**
 * The CarrierBuilder interface defines all possible ways to configure a LTE and
 * WCDMA carrier.
 * <p>
 * The WCDMACarrierBuilder and LTECarrierBuilder classes will implement these methods.
 */

public interface CarrierBuilder {

    /**
     * Sets up an ID for carrier.
     *
     * @param carrierID The ID for the carrier.
     */
    void setCarrierId(Integer carrierID);

    /**
     * Sets up the RF ports.
     *
     * @param rfPorts The RF ports for the carrier.
     *                WCDMA needs 2 RF ports and LTE needs 4 RF ports.
     */
    void setRfPorts(List<RfPorts> rfPorts);

    /**
     * Sets up the carrier frequency.
     *
     * @param frequencyBand The carrier frequency for the LTE or WCDMA band.
     */
    void setFrequencyBand(FrequencyBand frequencyBand);

    /**
     * Sets up the transmission power.
     *
     * @param transmittingPower The power for the carrier.
     */
    void setTransmittingPower(Double transmittingPower);
    
    /**
     * Returns a built Carrier object.
     *
     * @return The built Carrier.
     */
    public Carrier getCarrier();
}
