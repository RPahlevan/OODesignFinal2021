package common;

public enum WcdmaFrequencyBand implements FrequencyBand {
    /*
     * List of WCDMA bands.
     */
    WCDMA_BAND_1("2100 MHz"), WCDMA_BAND_2("1900 MHz"),
    WCDMA_BAND_3("1800 MHz"), WCDMA_BAND_4("2100 MHz"),
    WCDMA_BAND_5("850 MHz-for U.S"), WCDMA_BAND_6("850 MHz-for Japan"),
    WCDMA_BAND_7("2500 MHz"), WCDMA_BAND_8("900 MHz");

    private final String frequencyBand;

    WcdmaFrequencyBand(String freqBand) {
        this.frequencyBand = freqBand;
    }

    /**
     * Return the String representation of the WCDMA band.
     *
     * @return The band information, as a String.
     */
    @Override
    public String getBand() {
        return frequencyBand;
    }
}
