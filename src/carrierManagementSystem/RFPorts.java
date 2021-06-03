package carrierManagementSystem;

public enum RFPorts {

	RF_0("RF_0 = A"), RF_1("RF_1 = B"), RF_2("RF_2 = C"), RF_3("RF_3 = D"), RF_4("RF_4 = E"), RF_5("RF_5 = F"),
	RF_6("RF_6 = G"), RF_7("RF_7 = H");

	private String RFPort;

	RFPorts(String RFPort) {
		try {
			this.RFPort = RFPort;
		} catch (Exception e) {
			System.out.println("From input: " + RFPort + "is not availble.");
		}
	}

	public String getRFPort() {
		return RFPort;
	}

}
