package radiounit;

/**
 * @author edavleu
 *
 */
public enum AlarmStatusLevel {

	CRITICAL("CRITICAL"),
	MAJOR("MAJOR"),
	MINOR("MINOR"),
	NO_ALARM("NO ALARM"),
	CRITICAL_ACKNOWLEDGED("CRITICAL ACKNOWLEDGED"),
	MAJOR_ACKNOWLEDGED("MAJOR ACKNOWLEDGED"),
	MINOR_ACKNOWLEDGED("MINOR ACKNOWLEDGED");
	
	private String alarmLabel;
	
	AlarmStatusLevel(String label)
	{
		alarmLabel = label;
	}
	
	public String getAlarmLabel()
	{
		return alarmLabel;
	}
}
