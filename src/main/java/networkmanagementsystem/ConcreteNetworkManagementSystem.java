package networkmanagementsystem;

import common.*;
import radiounit.RadioUnitState;

public class ConcreteNetworkManagementSystem implements NetworkManagementSystem {

    @Override
    public void addRadioUnit(String name, Vendor vendor, RatType ratType) {

    }

    @Override
    public void removeRadioUnit(String ip) {

    }

    @Override
    public void deactivateRU(String ip) {

    }

    @Override
    public void setupCarrierOnRU(String ip, Carrier carrier) {

    }

    @Override
    public void modifyCarrierOnRU(String ip, int id, FrequencyBand frequencyBand) {

    }

    @Override
    public void removeCarrierOnRU(String ip, int id) {

    }

    @Override
    public void signalScalingOnRU(String ip) {

    }

    @Override
    public void listNetworkInventory() {

    }

    @Override
    public void listRUsByStandard(RatType ratType) {

    }

    @Override
    public void listRUsByState(RadioUnitState ruState) {

    }

    @Override
    public void listRUsByBand(FrequencyBand frequencyBand) {

    }

    @Override
    public void listRadioUnitDetails(String ip) {

    }

    @Override
    public void setAlarmOnRU(String ip, AlarmStatusLevel alarm) {

    }

    @Override
    public void getNetworkAlarms() {

    }
}
