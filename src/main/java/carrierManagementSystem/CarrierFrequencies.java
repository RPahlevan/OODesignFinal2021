package carrierManagementSystem;

public enum CarrierFrequencies {

	WCDMA_BAND_1("WCDMA_BAND_1  = 2100 MHz"), WCDMA_BAND_2("WCDMA_BAND_2  = 1900 MHz"),
	WCDMA_BAND_3("WCDMA_BAND_3  = 1800 MHz"), WCDMA_BAND_4("WCDMA_BAND_4  = 2100 MHz"),
	WCDMA_BAND_5("WCDMA_BAND_5  = 850 MHz-for U.S"), WCDMA_BAND_6("WCDMA_BAND_6  = 850 MHz-for Japan"),
	WCDMA_BAND_7("WCDMA_BAND_7  = 2500 MHz"), WCDMA_BAND_8("WCDMA_BAND_8  = 900 MHz"),

	LTE_BAND_1("LTE_BAND_1  = 1920 MHz"), LTE_BAND_2("LTE_BAND_2  = 1850 MHz"), LTE_BAND_3("LTE_BAND_3  = 1710 MHz"),
	LTE_BAND_4("LTE_BAND_4  = 1755 MHz"), LTE_BAND_5("LTE_BAND_5  = 824 MHz"), LTE_BAND_6("LTE_BAND_6  = 830 MHz"),
	LTE_BAND_7("LTE_BAND_7  = 2500 MHz"), LTE_BAND_8("LTE_BAND_8  = 880 MHz");

	private String FrequencyBand;

	CarrierFrequencies(String FrequencyBand) {
		try {
			this.FrequencyBand = FrequencyBand;
		} catch (Exception e) {
			System.out.println("From input: " + FrequencyBand + "is not availble.");
		}
	}

	public String getBand() {
		return FrequencyBand;
	}

}
