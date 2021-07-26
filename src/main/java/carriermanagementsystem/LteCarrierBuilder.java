package carriermanagementsystem;

import common.Carrier;
import common.FrequencyBand;
import common.RfPort;
import common.LteFrequencyBand;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is a child class to CarrierBuilder. It builds LTECarrier objects.
 */
public class LteCarrierBuilder implements CarrierBuilder {
    private static final int LTE_RF_PORTS_NUMBER = 4;
    private Integer carrierId;
    private List<RfPort> rfPorts;
    private FrequencyBand frequencyBand;
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
    public void setRfPorts(List<RfPort> rfPorts) {
        if (rfPorts == null) {
            System.out.println("[ERROR] LTE RF Ports are not configured!");
            return;
        }

        if (rfPorts.size() == LTE_RF_PORTS_NUMBER) {
            this.rfPorts = new ArrayList<>(rfPorts);
        } else {
            System.out.println(
                    "[ERROR] Invalid value for the number of LTE ports. The number of RF Ports for LTE carrier has to be 4. RF ports will not be configured.");
        }
    }

    /**
     * Sets up carrier frequency for LTE.
     *
     * @param frequencyBand Configures the LTE band.
     */
    @Override
    public void setFrequencyBand(FrequencyBand frequencyBand) {
        if (frequencyBand instanceof LteFrequencyBand) {
            this.frequencyBand = frequencyBand;
        } else {
            System.out.println("[ERROR] Invalid value for the LTE frequency band.");
        }
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
    public Carrier getCarrier() {
        return new LteCarrier(carrierId, rfPorts, frequencyBand, transmittingPower);
    }
}
