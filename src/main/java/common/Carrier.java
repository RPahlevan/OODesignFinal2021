/**
 *
 */
package common;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * This abstract class defines a Carrier that will have concrete implementations
 * for different RAT types.
 */
public abstract class Carrier {
    protected Integer carrierId;
    protected List<RfPort> rfPorts;
    protected FrequencyBand frequencyBand;
    protected Double transmittingPower;

    public Carrier(Integer carrierId, List<RfPort> rfPorts, FrequencyBand freq, Double transmittingPower) {
        this.carrierId = carrierId;
        this.frequencyBand = freq;

        if (rfPorts == null || rfPorts.isEmpty()) {
            System.out.println("[ERROR] Attempted to create a carrier without RF ports.");
        }

        this.rfPorts = new ArrayList<>(Objects.requireNonNull(rfPorts));
        this.transmittingPower = transmittingPower;
    }

    /**
     * Returns the carrier id to the builder class.
     *
     * @return The carrier id, as an Integer.
     */
    public int getCarrierId() {
        return carrierId;
    }

    /**
     * Returns a list of RF ports to the builder class.
     *
     * @return The RF ports, as a List.
     */
    public List<RfPort> getRfPorts() {
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
}
