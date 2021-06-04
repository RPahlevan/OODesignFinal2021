package carrierManagementSystem;

/**
 *
 * This class contain information related to rf ports.
 */
public enum RFPorts {

	RF_0("RF_0 = A"), RF_1("RF_1 = B"), RF_2("RF_2 = C"), RF_3("RF_3 = D"), RF_4("RF_4 = E"), RF_5("RF_5 = F"),
	RF_6("RF_6 = G"), RF_7("RF_7 = H");

	private String RFPORT;

	RFPorts(String RFPort) {
		try {
			this.RFPORT = RFPort;
		} catch (Exception e) {
			System.out.println("From input: " + RFPort + "is not availble.");
		}
	}

	/**
	 * This method will return related RF ports.
	 * 
	 * @return related RF port.
	 */
	public String getRFPort() {
		return RFPORT;
	}

}
