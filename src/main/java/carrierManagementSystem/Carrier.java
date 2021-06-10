package carrierManagementSystem;

import java.util.ArrayList;
import java.util.List;

/**
 * This abstract class defines a Carrier that will have concrete implementations
 * for different RAT types.
 */
public abstract class Carrier {
    protected Integer carrierId;
    protected List<RfPorts> rfPorts;
    protected CarrierFrequencies carrierFrequencies;
    protected Double transmittingPower;

    public Carrier(Integer carrierId, List<RfPorts> rfPorts, CarrierFrequencies carrierFrequencies,
            Double transmittingPower) {
        this.carrierId = carrierId;
        this.carrierFrequencies = carrierFrequencies;
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
    public CarrierFrequencies getCarrierFrequencies() {
        return carrierFrequencies;
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
    public abstract String print();
}
