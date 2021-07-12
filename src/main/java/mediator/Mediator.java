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

    void removeRu(String ip);
    
    void printVendor(String ip);
    
    void printRatType(String ip);
    
    void printAlarmStatus(String ip);
    
    void printRegisteredRadioUnits();

    void printCreatedCarriers();

    void displayCarrierOnRu(String ip);

    <E> void listRuByParam(E obj);

    void createCarrierAndRu(List<RfPort> rfPorts, FrequencyBand carrierFrequencies,
                            Double transmittingPower, String name, Vendor vendor, RatType ratType);

    void createCarrierOnRu(List<RfPort> rfPorts, FrequencyBand carrierFrequencies,
                       Double transmittingPower, String ruName);
    
    Carrier createCarrier(List<RfPort> rfPorts, FrequencyBand carrierFrequencies, Double transmittingPower, RatType ratType);

    ManagedRadioUnit getRadioUnit(String ip);
}
