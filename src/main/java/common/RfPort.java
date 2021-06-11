/**
 * 
 */
package common;

/**
 * This class contain information related to RF ports.
 */
public enum RfPort {
	/**
     * List of RF ports.
     */
    RF_0("RF_0 = A"), RF_1("RF_1 = B"), RF_2("RF_2 = C"), RF_3("RF_3 = D"), RF_4("RF_4 = E"), RF_5("RF_5 = F"),
    RF_6("RF_6 = G"), RF_7("RF_7 = H");
	
	private final String rfPort;
	
	RfPort(String port)
	{
		this.rfPort = port;
	}
    /**
     * Returns the String representation of the RF port.
     *
     * @return The RF port information, as a String.
     */
    public String getRfPortLabel() {
        return rfPort;
    }
}
