package carriermanagementsystem;

import common.Carrier;
import common.FrequencyBand;
import common.RfPort;

import java.util.Stack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EmptyStackException;
import java.util.List;

/**
 * This singleton class is responsible to create LTE carrier and WCDMA carrier.
 * We can call this class director as it will direct the creation command from
 * client(CarrierManagementSystemClient) to Carrier builder class. This class
 * will also assign a random unique carrier ID (in range 1 - 1000) to each
 * carrier it creates.
 */
public class CarrierManagementSystemDirector implements CarrierManagementIf {
    private static final CarrierManagementSystemDirector UNIQUE_INSTANCE = new CarrierManagementSystemDirector();
    private final int LTE_MAXIMUM_CARRIER_ID = 500;
    private final int WCDMA_MAXIMUM_CARRIER_ID = 500;
    private Stack<Integer> lteCarrierIdGenerator = new Stack<>();
    private Stack<Integer> wcdmaCarrierIdGenerator = new Stack<>();

    // For the future usage.
    // This list will save all carrier object and carrierID so the object can be
    // modified from radio unit.
    // private ConcurrentHashMap<Integer,Carrier> carrierList;

    private CarrierManagementSystemDirector() {
        ArrayList<Integer> lteRandomizeCarrierId = new ArrayList<>();
        ArrayList<Integer> wcdmaRandomizeCarrierId = new ArrayList<>();

        // Generate random number for lte carrier ID.
        for (int i = 1; i < LTE_MAXIMUM_CARRIER_ID; i++) {
            lteRandomizeCarrierId.add(i);
        }
        Collections.shuffle(lteRandomizeCarrierId);
        lteCarrierIdGenerator.addAll(lteRandomizeCarrierId);

        // Generate random number for wcdma carrier ID.
        for (int i = 1; i < WCDMA_MAXIMUM_CARRIER_ID; i++) {
            wcdmaRandomizeCarrierId.add(i);
        }
        Collections.shuffle(wcdmaRandomizeCarrierId);
        wcdmaCarrierIdGenerator.addAll(wcdmaRandomizeCarrierId);
    }

    /**
     * Get the unique CarrierManagementSystemDirector instance
     * 
     * @return CarrierManagementSystemDirector singleton instance
     */
    public static synchronized CarrierManagementSystemDirector getInstance() {
        return UNIQUE_INSTANCE;
    }

    @Override
    public Carrier createLteCarrier(List<RfPort> rfPorts, FrequencyBand frequencyBand, Double transmittingPower) {
        LteCarrierBuilder lteCarrier = new LteCarrierBuilder();
        try {
            lteCarrier.setCarrierId(lteCarrierIdGenerator.pop());
        } catch (EmptyStackException e) {
            System.out.println("Maximum LTE carriers reached. Please remove unused carriers.");
        }
        lteCarrier.setFrequencyBand(frequencyBand);
        lteCarrier.setTransmittingPower(transmittingPower);
        lteCarrier.setRfPorts(rfPorts);
        Carrier lte = lteCarrier.getCarrier();
        System.out.println("Final result: \n" + lte.toString());
        return lte;
    }

    @Override
    public Carrier createWcdmaCarrier(List<RfPort> rfPorts, FrequencyBand frequencyBand, Double transmittingPower) {
        WcdmaCarrierBuilder wcdmaCarrier = new WcdmaCarrierBuilder();
        try {
            wcdmaCarrier.setCarrierId(wcdmaCarrierIdGenerator.pop());
        } catch (EmptyStackException e) {
            System.out.println("Maximum WCDMA carriers reached. Please remove unused carriers.");
        }
        wcdmaCarrier.setFrequencyBand(frequencyBand);
        wcdmaCarrier.setTransmittingPower(transmittingPower);
        wcdmaCarrier.setRfPorts(rfPorts);
        Carrier wcdma = wcdmaCarrier.getCarrier();
        System.out.println("Final result: \n" + wcdma.toString());
        return wcdma;
    }
}
