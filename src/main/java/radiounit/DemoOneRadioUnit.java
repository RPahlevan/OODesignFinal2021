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

    public DemoOneRadioUnit(String name, Vendor vendor, RatType ruType) {
        this.carriers = new ArrayList<>();
        this.ipAddress = "1.1.1.1";
        this.name = name;
        this.vendor = vendor;
        this.ruType = ruType;
        this.alarm = AlarmStatusLevel.NO_ALARM;
    }

    @Override
    public void setup() {
        System.out.println("Setting up radio unit");

    }

    @Override
    public void activate() {
        System.out.println("Activating radio unit");

    }

    @Override
    public void deactivate() {
        System.out.println("Deactivating radio unit");

    }

    @Override
    public void release() {
        System.out.println("Releasing");

    }

    @Override
    public void signalScaling() {
        System.out.println("Signal scaling");

    }

    @Override
    public void postActivation() {
        System.out.println("Post activation");

    }

    @Override
    public void performSelfDiagnostics() {
        System.out.println("performSelfDiagnostics");

    }

    @Override
    public void acknowledgeAlarm() {
        System.out.println("Acknowledge alarm");

    }

    @Override
    public void setupCarrier(Carrier carrier) {
        System.out.println("Setting up carrier");

    }

    @Override
    public void modifyCarrier(int carrierId, FrequencyBand freq) {
        System.out.println("Modifying carrier");

    }

    @Override
    public void removeCarrier(int carrierId) {
        System.out.println("Remove carrier");

    }

    @Override
    public void removeAllCarriers() {
        System.out.println("Remove all carriers");

    }

    @Override
    public String getCurrentState() {
        System.out.println("Get current state");
        return null;
    }

    @Override
    public String getIpAddress() {
        System.out.println("Get IP address");
        return ipAddress;
    }

    @Override
    public String getRadioUnitName() {
        System.out.println("Get Radio Unit Name");
        return name;
    }

    @Override
    public Vendor getVendor() {
        System.out.println("Get Vendor");
        return vendor;
    }

    @Override
    public RatType getRatType() {
        System.out.println("Get Rat Type");
        return ruType;
    }

    @Override
    public List<Carrier> getCarriers() {
        System.out.println("Get Carriers");
        return carriers;
    }

    @Override
    public AlarmStatusLevel getAlarmStatus() {
        System.out.println("Get Alarm Status");
        return alarm;
    }

    @Override
    public void setRadioUnitName(String name) {
        System.out.println("Set Radio Unit Name");
        this.name = name;
    }

    @Override
    public void raiseAlarm(AlarmStatusLevel alarm) {
        System.out.println("Raise Alarm");
        this.alarm = alarm;
    }

}
