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
			System.out.println(this.networkMgmt.getNetworkAlarms());
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	public void acknowledgeAlarmOnRu(String ipAddress)
	{
		try {
			if (this.networkMgmt.acknowledgeAlarm(ipAddress))
			{
				System.out.println("Successfully acknowledged alarm on " + ipAddress);
			}
			else
			{
				System.out.println("Failed to acknowledge alarm on " + ipAddress);
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
}
