package carrierManagementSystem;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Client class. This class would perform the unit tests for the carrier
 * management system.
 */
public class CarrierManagementSystemClient {

    public static void main(String[] args) {
        CarrierManagementSystemDirector director = new CarrierManagementSystemDirector();

        /**
         * The below code would show how to create LTE carrier with a carrier id, RF
         * ports, LTE band and transmission power.
         *
         * Please note that a LTE carrier needs 4 RF ports.
         */
        List<RfPorts> LteRfPorts = Stream.of(RfPorts.RF_3, RfPorts.RF_4, RfPorts.RF_5, RfPorts.RF_6)
                .collect(Collectors.toList());
        CarrierFrequencies LteBand_3 = CarrierFrequencies.LTE_BAND_3;
        double transmittingPower = 12.2;
        director.createLteCarrier(LteRfPorts, LteBand_3, transmittingPower);

        /**
         * Below lines of code would show how to create WCDMA carrier with carrier Id,
         * Rf ports, WCDMA band and transmission power.
         * 
         * Please note that WCDMA carrier needs 2 rf ports.
         */
        List<RfPorts> WcdmaRfPorts = Stream.of(RfPorts.RF_1, RfPorts.RF_2).collect(Collectors.toList());
        CarrierFrequencies WcdmaBand_5 = CarrierFrequencies.WCDMA_BAND_5;
        transmittingPower = 11.7;
        director.createWcdmaCarrier(WcdmaRfPorts, WcdmaBand_5, transmittingPower);

    }

}
