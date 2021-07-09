package radiounit;

import common.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Matthew
 * <p>
 * This is currently just a temporary class for demo purposes
 */

public class DemoOneRadioUnit implements ManagedRadioUnit {

    List<Carrier> carriers;
    String ipAddress;
    String name;
    Vendor vendor;
    RatType ruType;
    AlarmStatusLevel alarm;

    public DemoOneRadioUnit(String ipAddress, String name, Vendor vendor, RatType ruType) {
        this.carriers = new ArrayList<>();
        this.ipAddress = ipAddress;
        this.name = name;
        this.vendor = vendor;
        this.ruType = ruType;
        this.alarm = AlarmStatusLevel.NO_ALARM;

        System.out.println(String.format("[DEBUG] IP address for this RU: %s", ipAddress));
    }

    @Override
    public void setup() {
        System.out.println("[DEBUG] Setting up radio unit");

    }

    @Override
    public void activate() {
        System.out.println("[DEBUG] Activating radio unit");

    }

    @Override
    public void deactivate() {
        System.out.println("[DEBUG] Deactivating radio unit");

    }

    @Override
    public void release() {
        System.out.println("[DEBUG] Releasing");

    }

    @Override
    public void signalScaling() {
        System.out.println("[DEBUG] Signal scaling");

    }

    @Override
    public void postActivation() {
        System.out.println("[DEBUG] Post activation");

    }

    @Override
    public void performSelfDiagnostics() {
        System.out.println("[DEBUG] performSelfDiagnostics");

    }

    @Override
    public void acknowledgeAlarm() {
        System.out.println("[DEBUG] Acknowledge alarm");

    }

    @Override
    public void setupCarrier(Carrier carrier) {
        System.out.println("[DEBUG] Setting up carrier");
        carriers.add(carrier);

    }

    @Override
    public void modifyCarrier(int carrierId, FrequencyBand freq) {
        System.out.println("[DEBUG] Modifying carrier");
        carriers.forEach(carrier -> {
            if (carrier.getCarrierId() == carrierId) {
                carrier.setFrequencyBand(freq);
                return;
            }
        });
    }

    @Override
    public void removeCarrier(int carrierId) {
        System.out.println("[DEBUG] Remove carrier");
        carriers.forEach(carrier -> {
            if (carrier.getCarrierId() == carrierId) {
                carriers.remove(carrier);
                return;
            }
        });

    }

    @Override
    public void removeAllCarriers() {
        System.out.println("[DEBUG] Remove all carriers");
        carriers.clear();
    }

    @Override
    public RadioUnitState getCurrentState() {
        System.out.println("[DEBUG] Get current state");
        return null;
    }

    @Override
    public String getIpAddress() {
        System.out.println("[DEBUG] Get IP address");
        return ipAddress;
    }

    @Override
    public String getRadioUnitName() {
        System.out.println("[DEBUG] Get Radio Unit Name");
        return name;
    }

    @Override
    public Vendor getVendor() {
        System.out.println("[DEBUG] Get Vendor");
        return vendor;
    }

    @Override
    public RatType getRatType() {
        System.out.println("[DEBUG] Get Rat Type");
        return ruType;
    }

    @Override
    public List<Carrier> getCarriers() {
        System.out.println("[DEBUG] Get Carriers");
        return carriers;
    }

    @Override
    public AlarmStatusLevel getAlarmStatus() {
        System.out.println("[DEBUG] Get Alarm Status");
        return alarm;
    }

    @Override
    public void setRadioUnitName(String name) {
        System.out.println("[DEBUG] Set Radio Unit Name");
        this.name = name;
    }

    @Override
    public void raiseAlarm(AlarmStatusLevel alarm) {
        System.out.println("[DEBUG] Raise Alarm");
        this.alarm = alarm;
    }

}
