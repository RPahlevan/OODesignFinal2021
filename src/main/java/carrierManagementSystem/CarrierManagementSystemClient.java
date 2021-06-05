package carrierManagementSystem;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Client class. This class would perform the unit tests
 * for the carrier management system.
 */
public class CarrierManagementSystemClient {

    public static void main(String[] args) {
        CarrierManagementSystemDirector director = new CarrierManagementSystemDirector();

        /*
         * The below code would show how to create LTE carrier with a carrier id, RF
         * ports, LTE band and transmission power.
         *
         * Please note that a LTE carrier needs 4 RF ports.
         */
        int carrierId = 2;
        List<RFPorts> LTErfPorts = Stream.of(RFPorts.RF_3, RFPorts.RF_4, RFPorts.RF_5, RFPorts.RF_6).collect(Collectors.toList());
        CarrierFrequencies LTEBand_3 = CarrierFrequencies.LTE_BAND_3;
        double transmittingPower = 12.2;
        director.CreateLTECarrier(carrierId, LTErfPorts, LTEBand_3, transmittingPower);

        /*
         * The below code would show how to create a WCDMA carrier with a carrier id,
         * RF ports, WCDMA band and transmission power.
         *
         * Please note that a WCDMA carrier needs 2 RF ports.
         */
        carrierId = 3;
        List<RFPorts> WCDMArfPorts = Stream.of(RFPorts.RF_1, RFPorts.RF_2).collect(Collectors.toList());
        CarrierFrequencies WCDMABand_5 = CarrierFrequencies.WCDMA_BAND_5;
        transmittingPower = 11.7;
        director.CreateWCDMACarrier(carrierId, WCDMArfPorts, WCDMABand_5, transmittingPower);
    }
}
