package carrierManagementSystem;

import java.util.ArrayList;
import java.util.List;

import common.FrequencyBand;
import common.RfPorts;

/**
 * This abstract class defines a Carrier that will have concrete implementations
 * for different RAT types.
 */
public abstract class Carrier {
    protected Integer carrierId;
    protected List<RfPorts> rfPorts;
    protected FrequencyBand frequencyBand;
    protected Double transmittingPower;

    public Carrier(Integer carrierId, List<RfPorts> rfPorts, FrequencyBand frequencyBand,
            Double transmittingPower) {
        this.carrierId = carrierId;
        this.frequencyBand = frequencyBand;
        this.transmittingPower = transmittingPower;
        if (rfPorts != null) {
            this.rfPorts = new ArrayList<>(rfPorts);
        }
    }

    /**
     * Returns the carrier id to the builder class.
     *
     * @return The carrier id, as an Integer.
     */
    public Integer getCarrierId() {
        return carrierId;
    }

    /**
     * Returns a list of RF ports to the builder class.
     *
     * @return The RF ports, as a List.
     */
    public List<RfPorts> getRfPorts() {
        return rfPorts;
    }

    /**
     * Returns the carrier frequency band to the builder class.
     *
     * @return The carrier frequency, as a CarrierFrequencies object.
     */
    public FrequencyBand getCarrierFrequencies() {
        return frequencyBand;
    }

    /**
     * Returns the transmitting power to the builder class.
     *
     * @return The transmission power, as a Double.
     */
    public Double getTransmittingPower() {
        return transmittingPower;
    }

    /**
     * Collects all elements of the carrier into a formatted String.
     *
     * @return The collected information, as a String.
     */
    public abstract String toString();
}