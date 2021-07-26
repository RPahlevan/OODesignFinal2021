package common;

/**
 * @author edavleu
 *
 */
public enum RatType {
	
    LTE("Long-Term Evolution"),
	WCDMA("Wideband Code Division Multiple Access");
	
	private final String label;
	
	RatType(String label)
	{
		this.label = label;
	}
	
	public String getLabel()
	{
        return label;
    }

}
