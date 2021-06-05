package carrierManagementSystem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LTECarrier {

    private final Integer carrierId;
    private List<RFPorts> rfPorts;
    private final CarrierFrequencies carrierFrequencies;
    private final Double transmittingPower;

    public LTECarrier(Integer carrierId, RFPorts[] rfPorts, CarrierFrequencies carrierFrequencies,
                      Double transmittingPower) {
        this.carrierId = carrierId;
        this.carrierFrequencies = carrierFrequencies;
        this.transmittingPower = transmittingPower;
        if (rfPorts != null) {
            this.rfPorts = new ArrayList<>(Arrays.asList(rfPorts));
        }
    }

    public Integer getCarrierId() {
        return carrierId;
    }

    public List<RFPorts> getRfPorts() {
        return rfPorts;
    }

    public CarrierFrequencies getCarrierFrequencies() {
        return carrierFrequencies;
    }

    public Double getTransmittingPower() {
        return transmittingPower;
    }

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
