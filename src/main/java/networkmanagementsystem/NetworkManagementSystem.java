package networkmanagementsystem;

import common.*;
import radiounit.RadioUnitState;

public interface NetworkManagementSystem {
    void commissionRu(String ip);

    void decommissionRu(String ip);

    void addRadioUnit(String name, Vendor vendor, RatType ratType);

    void removeRadioUnit(String ip);

    void setupRu(String ip);

    void releaseRu(String ip);

    void activateRu(String ip);

    void deactivateRU(String ip);

    void setupCarrierOnRU(String ip, Carrier carrier);

    void modifyCarrierOnRU(String ip, int id, FrequencyBand frequencyBand);

    void removeCarrierOnRU(String ip, int id);

    void signalScalingOnRU(String ip);

    void signalScalingOnRu(String ip);

    void postActivation(String ip);

    void performSelfDiagnostics(String ip);

    void listNetworkInventory();

    void listRuByParam(Object obj);

    void setAlarmOnRU(String ip, AlarmStatusLevel alarm);

    void getNetworkAlarms();

    void acknowledgeAlarm(String ip);

}
