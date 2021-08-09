package alarmmonitoringsystem;

import networkmanagementsystem.NetworkManagementSystemIf;

import java.rmi.RemoteException;

/**
 * @author edavleu
 */
public class AlarmMonitoringSystem implements AlarmMonitoringSystemIf {

    final NetworkManagementSystemIf networkMgmt;

    public AlarmMonitoringSystem(NetworkManagementSystemIf net) {
        this.networkMgmt = net;
    }

    public void getNetAlarms() {
        try {
            networkMgmt.getNetworkAlarms();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void acknowledgeAlarmOnRu(String ipAddress) {
        try {
            if (this.networkMgmt.acknowledgeAlarm(ipAddress)) {
                System.out.printf("Successfully acknowledged alarm on the Radio Unit with the IP address \"%s\"", ipAddress);
            } else {
                System.out.printf("Failed to acknowledge alarm on the Radio Unit with the IP address \"%s\"", ipAddress);
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}