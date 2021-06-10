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
    
    void createRU(String name, Vendor vendor, RatType ratType);
    
    void printVendor(String name);
    
    void printRatType(String name);
    
    void printAlarmStatus(String name);
    
    void printRegistereredRaidoUnits();

    void displayCarrier(int id);
}
