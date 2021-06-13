/**
 * 
 */
package common;

/**
 * @author edavleu
 *
 */
public enum RatType {
	LTE("LTE"),
	WCDMA("WCDMA");
	
	private String label;
	
	RatType(String label)
	{
		this.label = label;
	}
	
	public String getLabel()
	{
		return label;
	}

}
