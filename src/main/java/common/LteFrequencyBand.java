package common;

public enum LteFrequencyBand implements FrequencyBand {
    /*
     * List of LTE bands.
     */
    LTE_BAND_1("LTE_BAND_1 = 1920 MHz"), LTE_BAND_2("LTE_BAND_2 = 1850 MHz"), LTE_BAND_3("LTE_BAND_3 = 1710 MHz"),
    LTE_BAND_4("LTE_BAND_4 = 1755 MHz"), LTE_BAND_5("LTE_BAND_5 = 824 MHz"), LTE_BAND_6("LTE_BAND_6 = 830 MHz"),
    LTE_BAND_7("LTE_BAND_7 = 2500 MHz"), LTE_BAND_8("LTE_BAND_8 = 880 MHz");

    private final String frequencyBand;

    LteFrequencyBand(String freqBand) {
        this.frequencyBand = freqBand;
    }

    /**
     * Return the String representation of the LTE band.
     *
     * @return The band information, as a String.
     */
    @Override
    public String getBand() {
        return frequencyBand;
    }
}
