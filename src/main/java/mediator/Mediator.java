package mediator;

import common.*;
import radiounit.ManagedRadioUnit;

import java.util.List;

/**
 * The Mediator interface serves as the communication gateway
 * for all other components of the WirelessNetworkManagementSystem.
 * <p>
 * To view its implementation, please view {@link ConcreteMediator}
 *
 * @author ebreojh
 */
public interface Mediator {
    
    void createRu(String name, Vendor vendor, RatType ratType);
    
    void printVendor(String name);
    
    void printRatType(String name);
    
    void printAlarmStatus(String name);
    
    void printRegisteredRadioUnits();

    void printCreatedCarriers();

    void displayCarrierOnRu(String name);

    <E> void listRuByParam(E obj);

    void createCarrierAndRu(List<RfPort> rfPorts, FrequencyBand carrierFrequencies,
                            Double transmittingPower, String name, Vendor vendor, RatType ratType);

    void createCarrierOnRu(List<RfPort> rfPorts, FrequencyBand carrierFrequencies,
                       Double transmittingPower, String ruName);
    
    Carrier createCarrier(List<RfPort> rfPorts, FrequencyBand carrierFrequencies, Double transmittingPower, RatType ratType);

    ManagedRadioUnit getRadioUnit(String name);
}
