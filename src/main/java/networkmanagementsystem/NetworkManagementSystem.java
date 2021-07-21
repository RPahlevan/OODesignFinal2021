package networkmanagementsystem;

import common.*;
import radiounit.RadioUnitState;

import java.beans.PropertyChangeListener;
import java.util.List;

public interface NetworkManagementSystem {


    void commissionRu(String ip);

    void decommissionRu(String ip);

    void addRadioUnit(String name, Vendor vendor, RatType ratType);

    void removeRadioUnit(String ip);
    
    void setupRu(String ip);
    
    void releaseRu(String ip);
    
    void activateRu(String ip);

    void deactivateRu(String ip);

    void setupCarrierOnRu(String ip, List<RfPort> rfPorts, FrequencyBand freq, Double transmittingPower, RatType ratType);

    void modifyCarrierOnRu(String ip, int id, FrequencyBand frequencyBand);

    void removeCarrierOnRu(String ip, int id);
    
    void removeAllCarrierOnRu(String ip);

    void signalScalingOnRu(String ip);
    
    void postActivation(String ip);
    
    void performSelfDiagnostics(String ip);

    void listNetworkInventory();

    void listRuByParam(Object obj);
    
    void listRadioUnitDetails(String ip);

    void setAlarmOnRu(String ip, AlarmStatusLevel alarm);

    void getNetworkAlarms();

    void acknowledgeAlarm(String ip);

    void addPropertyChangeListener(PropertyChangeListener pcl);

}
