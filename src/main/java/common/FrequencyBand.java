/**
 * 
 */
package common;

/**
 * This enum contains supported Carrier Frequencies
 */
public enum FrequencyBand {
	/**
     * List of WCDMA bands.
     */
    WCDMA_BAND_1("WCDMA_BAND_1 = 2100 MHz"), WCDMA_BAND_2("WCDMA_BAND_2 = 1900 MHz"),
    WCDMA_BAND_3("WCDMA_BAND_3 = 1800 MHz"), WCDMA_BAND_4("WCDMA_BAND_4 = 2100 MHz"),
    WCDMA_BAND_5("WCDMA_BAND_5 = 850 MHz-for U.S"), WCDMA_BAND_6("WCDMA_BAND_6 = 850 MHz-for Japan"),
    WCDMA_BAND_7("WCDMA_BAND_7 = 2500 MHz"), WCDMA_BAND_8("WCDMA_BAND_8 = 900 MHz"),

    /**
     * List of LTE bands.
     */
    LTE_BAND_1("LTE_BAND_1 = 1920 MHz"), LTE_BAND_2("LTE_BAND_2 = 1850 MHz"), LTE_BAND_3("LTE_BAND_3 = 1710 MHz"),
    LTE_BAND_4("LTE_BAND_4 = 1755 MHz"), LTE_BAND_5("LTE_BAND_5 = 824 MHz"), LTE_BAND_6("LTE_BAND_6 = 830 MHz"),
    LTE_BAND_7("LTE_BAND_7 = 2500 MHz"), LTE_BAND_8("LTE_BAND_8 = 880 MHz");
	
	private final String frequencyBand;
	
	FrequencyBand(String freqBand)
	{
		this.frequencyBand = freqBand;
	}
    /**
     * Return the String representation of the LTE or WCDMA band.
     *
     * @return The band information, as a String.
     */
    public String getBand() {
        return frequencyBand;
    }
}
