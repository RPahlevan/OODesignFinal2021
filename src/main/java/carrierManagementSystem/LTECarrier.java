package carrierManagementSystem;

import java.util.ArrayList;
import java.util.List;

/**
 * This class builds LTE carriers. The LTECarrierBuilder class uses this
 * class to build an object. Each private data member inside this class has a
 * getter method which helps the builder class to build its object by using them.
 */
public class LTECarrier {
    private final Integer carrierId;
    private List<RFPorts> rfPorts;
    private final CarrierFrequencies carrierFrequencies;
    private final Double transmittingPower;

    public LTECarrier(Integer carrierId, List<RFPorts> rfPorts, CarrierFrequencies carrierFrequencies,
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
    public List<RFPorts> getRfPorts() {
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
    public String print() {
        StringBuffer info = new StringBuffer();

        info.append("LTE Carrier ID: ");
        info.append(carrierId != null ? carrierId : "Not configured");

        info.append("\nLTE Carrier Frequency: ");
        info.append(carrierFrequencies != null ? carrierFrequencies.getBand() : "Not configured");

        info.append("\nLTE RF Ports: ");
        if (rfPorts != null) {
            rfPorts.forEach(port -> info.append(port.getRFPort()).append("  "));
        } else {
            info.append("Not configured");
        }

        info.append("\nLTE Transmission Power: ");
        info.append(transmittingPower != null ? transmittingPower : "Not configured");

        return info.append("\n").toString();
    }
}
