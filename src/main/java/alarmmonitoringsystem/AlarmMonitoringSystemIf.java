package alarmmonitoringsystem;

/**
 * @author ebreojh
 */
interface AlarmMonitoringSystemIf {
    void getNetAlarms();

    void acknowledgeAlarmOnRu(String ipAddress);
}
