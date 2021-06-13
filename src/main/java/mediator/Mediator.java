package mediator;

import common.FrequencyBand;
import common.RatType;
import common.RfPort;
import common.Vendor;
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
	List<ManagedRadioUnit> getRegisteredRadioUnits();
    
    void createRu(String name, Vendor vendor, RatType ratType);
    
    void printVendor(String name);
    
    void printRatType(String name);
    
    void printAlarmStatus(String name);
    
    void printRegisteredRaidoUnits();

    void displayCarrierOnRu(String name);

    void createCarrierAndRu(List<RfPort> rfPorts, FrequencyBand carrierFrequencies,
                            Double transmittingPower, String name, Vendor vendor, RatType ratType);

    void createCarrier(List<RfPort> rfPorts, FrequencyBand carrierFrequencies,
                       Double transmittingPower, String ruName);
}
