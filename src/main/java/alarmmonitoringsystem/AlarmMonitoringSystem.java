package alarmmonitoringsystem;

import java.rmi.RemoteException;

import networkmanagementsystem.NetworkManagementSystemIf;

/**
 * @author edavleu
 *
 */
public class AlarmMonitoringSystem {

	final NetworkManagementSystemIf networkMgmt;
	
	public AlarmMonitoringSystem(NetworkManagementSystemIf net)
	{
		this.networkMgmt = net;
	}
	
	public void getNetAlarms()
	{
		try {
			this.networkMgmt.getNetworkAlarms();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	public void acknowledgeAlarmOnRu(String ipAddress)
	{
		try {
			this.networkMgmt.acknowledgeAlarm(ipAddress);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
}
