package carrierManagementSystem;

import java.util.Stack;
import java.util.ArrayList;
import java.util.Collections;
import java.util.EmptyStackException;
import java.util.List;

/**
 * This class is responsible to create LTE carrier and WCDMA carrier. We can
 * call this class director as it will direct the creation command from
 * client(CarrierManagementSystemClient) to Carrier builder class. This class
 * will also assign a random unique carrier ID (in range 1 - 1000) to each
 * carrier it creates.
 */
public class CarrierManagementSystemDirector implements CarrierManagementIf {

    private Stack<Integer> carrierIdGenerator = new Stack<Integer>();
    private ArrayList<Integer> randomizeCarrierId = new ArrayList<Integer>();

    public CarrierManagementSystemDirector() {
        for (int i = 1; i < 1001; i++) {
            randomizeCarrierId.add(i);
        }
        Collections.shuffle(randomizeCarrierId);
        carrierIdGenerator.addAll(randomizeCarrierId);
    }

    @Override
    public Carrier createLteCarrier(List<RfPorts> rfPorts, CarrierFrequencies carrierFrequencies,
            Double transmittingPower) {
        LteCarrierBuilder lteCarrier = new LteCarrierBuilder();
        try {
        	lteCarrier.setCarrierId(carrierIdGenerator.pop());
        	}catch (EmptyStackException e) {
        		System.out.println("carrierIdGenerator stack for LTE carrier is empty!");
        		}
        lteCarrier.setFrequencyBand(carrierFrequencies);
        lteCarrier.setTransmittingPower(transmittingPower);
        lteCarrier.setRfPorts(rfPorts);
        Carrier LTE = lteCarrier.getLteCarrier();
        System.out.println("Final result: \n" + LTE.print());
        return LTE;
    }

    @Override
    public Carrier createWcdmaCarrier(List<RfPorts> rfPorts, CarrierFrequencies carrierFrequencies,
            Double transmittingPower) {
        WcdmaCarrierBuilder wcdmaCarrier = new WcdmaCarrierBuilder();
        try {
        	wcdmaCarrier.setCarrierId(carrierIdGenerator.pop());
        	}catch (EmptyStackException e) {
        		System.out.println("carrierIdGenerator stack for WCDMA carrier is empty!");
        		}
        wcdmaCarrier.setFrequencyBand(carrierFrequencies);
        wcdmaCarrier.setTransmittingPower(transmittingPower);
        wcdmaCarrier.setRfPorts(rfPorts);
        Carrier WCDMA = wcdmaCarrier.getWcdmaCarrier();
        System.out.println("Final result: \n" + WCDMA.print());
        return WCDMA;
    }
}
