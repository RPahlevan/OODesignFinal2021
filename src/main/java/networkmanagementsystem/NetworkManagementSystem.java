package networkmanagementsystem;

import common.*;
import radiounit.RadioUnitState;

public interface NetworkManagementSystem {
    void addRadioUnit(String name, Vendor vendor, RatType ratType);

    void removeRadioUnit(String ip);

    void deactivateRU(String ip);

    void setupCarrierOnRU(String ip, Carrier carrier);

    void modifyCarrierOnRU(String ip, int id, FrequencyBand frequencyBand);

    void removeCarrierOnRU(String ip, int id);

    void signalScalingOnRU(String ip);

    void listNetworkInventory();

    void listRUsByStandard(RatType ratType);

    void listRUsByState(RadioUnitState ruState);

    void listRUsByBand(FrequencyBand frequencyBand);

    void listRadioUnitDetails(String ip);

    void setAlarmOnRU(String ip, AlarmStatusLevel alarm);

    void getNetworkAlarms();

}
