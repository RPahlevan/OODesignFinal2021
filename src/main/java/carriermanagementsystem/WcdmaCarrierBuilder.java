package carriermanagementsystem;

import common.Carrier;
import common.FrequencyBand;
import common.RfPort;

import java.util.ArrayList;
import java.util.List;
/**
 * This class is a child class to CarrierBuilder. It builds WCDMACarrier objects.
 */
public class WcdmaCarrierBuilder implements CarrierBuilder {
    private Integer carrierId;
    private List<RfPort> rfPorts;
    private FrequencyBand frequencyBand;
    private Double transmittingPower;

    /**
     * Sets up an ID for the WCDMA carrier.
     *
     * @param carrierID The ID for carrier.
     */
    @Override
    public void setCarrierId(Integer carrierID) {
        this.carrierId = carrierID;

    }

    /**
     * Sets up RF ports for WCDMA.
     *
     * @param rfPorts The RF ports for WCDMA. WCDMA needs 2 RF ports.
     */
    @Override
    public void setRfPorts(List<RfPort> rfPorts) {
        // Catch exception for null rfPorts.
        if (rfPorts == null) {
            throw new NullPointerException("WCDMA RF Ports are not configured!");
        }

        // Setup the RF ports for the WCDMA carrier and if the number of elements
        // is less than 2 it will throw an exception.
        if (rfPorts.size() == 2) {
            this.rfPorts = new ArrayList<>(rfPorts);
        } else {
            throw new ArrayIndexOutOfBoundsException(
                    "Invalid value for the number of WCDMA ports. Number of RF Ports for WCDMA carrier has to be 2.");
        }
    }

    /**
     * Sets up carrier frequency for WCDMA.
     *
     * @param frequencyBand Configures the WCDMA band.
     */
    @Override
    public void setFrequencyBand(FrequencyBand frequencyBand) {
        this.frequencyBand = frequencyBand;

    }

    /**
     * Sets up transmission power for WCDMA.
     *
     * @param transmittingPower Sets up the power.
     */
    @Override
    public void setTransmittingPower(Double transmittingPower) {
        this.transmittingPower = transmittingPower;

    }

    /**
     * Returns a built WCDMACarrier object.
     *
     * @return The built WCDMACarrier.
     */
    public Carrier getCarrier() {
        return new WcdmaCarrier(carrierId, rfPorts, frequencyBand, transmittingPower);
    }
}