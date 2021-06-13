package carriermanagementsystem;

import common.Carrier;
import common.FrequencyBand;
import common.RfPort;

import java.util.List;

/**
 * This class builds LTE carriers. The LTECarrierBuilder class uses this class
 * to build an object. Each private data member inside this class has a getter
 * method which helps the builder class to build its object by using them.
 */
public class LteCarrier extends Carrier {

    public LteCarrier(Integer carrierId, List<RfPort> rfPorts, FrequencyBand frequencyBand,
                      Double transmittingPower) {
        super(carrierId, rfPorts, frequencyBand, transmittingPower);
    }

    /**
     * Collects all elements of the carrier into a formatted String.
     *
     * @return The collected information, as a String.
     */
    public String toString() {
        StringBuffer info = new StringBuffer();

        info.append("LTE Carrier ID: ");
        info.append(carrierId != null ? carrierId : "Not configured");

        info.append("\nLTE Carrier Frequency: ");
        info.append(frequencyBand != null ? frequencyBand.getBand() : "Not configured");

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
