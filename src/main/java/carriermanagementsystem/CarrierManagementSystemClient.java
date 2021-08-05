package carriermanagementsystem;

import common.FrequencyBand;
import common.LteFrequencyBand;
import common.RfPort;
import common.WcdmaFrequencyBand;
import common.Carrier;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Client class. This class would perform the unit tests for the carrier
 * management system.
 */
public class CarrierManagementSystemClient {

    public static void main(String[] args) {
        CarrierManagementSystemDirector director = CarrierManagementSystemDirector.getInstance();

        /*
         * The below code would show how to create LTE carrier with a carrier id, RF
         * ports, LTE band and transmission power.
         *
         * Please note that a LTE carrier needs 4 RF ports.
         */
        List<RfPort> LteRfPorts = Stream.of(RfPort.RF_3, RfPort.RF_4, RfPort.RF_5, RfPort.RF_6)
                .collect(Collectors.toList());
        FrequencyBand LteBand_3 = LteFrequencyBand.LTE_BAND_3;
        double transmittingPower = 12.2;
        Carrier lte = director.createLteCarrier(LteRfPorts, LteBand_3, transmittingPower);
        System.out.println(lte.getRatType());

        /*
         * Below lines of code would show how to create WCDMA carrier with carrier Id,
         * Rf ports, WCDMA band and transmission power.
         * 
         * Please note that WCDMA carrier needs 2 rf ports.
         */
        List<RfPort> WcdmaRfPorts = Stream.of(RfPort.RF_1, RfPort.RF_2).collect(Collectors.toList());
        FrequencyBand WcdmaBand_5 = WcdmaFrequencyBand.WCDMA_BAND_5;
        transmittingPower = 11.7;
        Carrier wcdma = director.createWcdmaCarrier(WcdmaRfPorts, WcdmaBand_5, transmittingPower);
        System.out.println(wcdma.getRatType());

    }

}
