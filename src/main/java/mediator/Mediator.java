package mediator;

import java.util.List;

/**
 * The Mediator interface serves as the communication gateway
 * for all other components of the WirelessNetworkManagementSystem.
 * <p>
 * To view its implementation, please view {@link ConcreteMediator}
 */
public interface Mediator {
	List<RadioUnits> getRegisteredRadioUnits();
    
    void createRu(String name, Vendor vendor, RatType ratType);
    
    void printVendor(String name);
    
    void printRatType(String name);
    
    void printAlarmStatus(String name);
    
    void printRegisteredRaidoUnits();

    void displayCarrierOnRu(String name);

    void createCarrierAndRu(List<RfPorts> rfPorts, CarrierFrequencies carrierFrequencies,
                       Double transmittingPower, String name, Vendor vendor, RatType ratType);

    void createCarrier(List<RfPorts> rfPorts, CarrierFrequencies carrierFrequencies,
                       Double transmittingPower, String ruName);
}
