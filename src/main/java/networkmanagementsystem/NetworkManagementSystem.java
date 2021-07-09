package networkmanagementsystem;

import common.*;
import radiounit.RadioUnitState;

public interface NetworkManagementSystem {

    void commissionRu(String ip);

    void decommissionRu(String ip);
	
    void addRadioUnit(String name, Vendor vendor, RatType ratType);

    void removeRadioUnit(String ip);
    
    void setupRU(String ip);
    
    void releaseRU(String ip);
    
    void activateRU(String ip);

    void deactivateRU(String ip);

    void setupCarrierOnRU(String ip, Carrier carrier);

    void modifyCarrierOnRU(String ip, int id, FrequencyBand frequencyBand);

    void removeCarrierOnRU(String ip, int id);
    
    void removeAllCarrierOnRU(String ip);

    void signalScalingOnRU(String ip);
    
    void postActivation(String ip);
    
    void performSelfDiagnotics(String ip);

    void listNetworkInventory();
    
    void listRuByParam(Object obj);

    void listRadioUnitDetails(String ip);

    void setAlarmOnRU(String ip, AlarmStatusLevel alarm);

    void getNetworkAlarms();

}
