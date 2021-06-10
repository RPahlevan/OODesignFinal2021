package carrierManagementSystem;

import java.util.List;

/**
 * This class builds LTE carriers. The LTECarrierBuilder class uses this class
 * to build an object. Each private data member inside this class has a getter
 * method which helps the builder class to build its object by using them.
 */
public class LteCarrier extends Carrier {

    public LteCarrier(Integer carrierId, List<RfPorts> rfPorts, CarrierFrequencies carrierFrequencies,
            Double transmittingPower) {
        super(carrierId, rfPorts, carrierFrequencies, transmittingPower);
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
            rfPorts.forEach(port -> info.append(port.getRfPort()).append("  "));
        } else {
            info.append("Not configured");
        }

        info.append("\nLTE Transmission Power: ");
        info.append(transmittingPower != null ? transmittingPower : "Not configured");

        return info.append("\n").toString();
    }
}
