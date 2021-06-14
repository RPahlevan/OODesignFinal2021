package carriermanagementsystem;

import common.FrequencyBand;
import common.RfPort;

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
        FrequencyBand LteBand_3 = FrequencyBand.LTE_BAND_3;
        double transmittingPower = 12.2;
        director.createLteCarrier(LteRfPorts, LteBand_3, transmittingPower);

        /*
         * Below lines of code would show how to create WCDMA carrier with carrier Id,
         * Rf ports, WCDMA band and transmission power.
         * 
         * Please note that WCDMA carrier needs 2 rf ports.
         */
        List<RfPort> WcdmaRfPorts = Stream.of(RfPort.RF_1, RfPort.RF_2).collect(Collectors.toList());
        FrequencyBand WcdmaBand_5 = FrequencyBand.WCDMA_BAND_5;
        transmittingPower = 11.7;
        director.createWcdmaCarrier(WcdmaRfPorts, WcdmaBand_5, transmittingPower);
        
//        /**
//         * error & not configured cases for demo purpose
//         */
//        
//        /**
//         * case #1: pass null for the transmittingPower
//         */
//        List<RfPorts> LteRfPorts_2 = Stream.of(RfPorts.RF_3, RfPorts.RF_4, RfPorts.RF_5, RfPorts.RF_6)
//                .collect(Collectors.toList());
//        FrequencyBand LteBand_4 = FrequencyBand.LTE_BAND_4;
//        director.createLteCarrier(LteRfPorts_2, LteBand_4, null);
//        
//        /**
//         * case #1: result:
//         * 
//         * Final result: 
//         * LTE Carrier ID: 138
//         * LTE Carrier Frequency: LTE_BAND_4 = 1755 MHz
//         * LTE RF Ports: RF_3 = D  RF_4 = E  RF_5 = F  RF_6 = G  
//         * LTE Transmission Power: Not configured
//         */
//        
//        
//        /**
//         * case #2: pass null for the RfPorts 
//         */
//        
//        FrequencyBand WcdmaBand_6 = FrequencyBand.WCDMA_BAND_6;
//        transmittingPower = 11.7;
//        director.createWcdmaCarrier(null, WcdmaBand_6, transmittingPower);
//        
//        /**
//         * case #2:  Result:
//         * 
//         * Exception in thread "main" java.lang.NullPointerException: WCDMA RF Ports are not configured!
//         */
//        
//        /**
//         * case #3: pass invalid number of RfPorts 
//         */
//        List<RfPorts> LteRfPorts_3 = Stream.of(RfPorts.RF_3, RfPorts.RF_4, RfPorts.RF_6)
//                .collect(Collectors.toList());
//        FrequencyBand LteBand_6 = FrequencyBand.LTE_BAND_6;
//        director.createLteCarrier(LteRfPorts_3, LteBand_6, null);
//        
//        /**
//         * case #3: Result:
//         * Exception in thread "main" java.lang.ArrayIndexOutOfBoundsException: Invalid value for the number of LTE ports. The number of RF Ports for LTE carrier has to be 4.
//         */
    }

}
