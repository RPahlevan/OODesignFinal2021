package common;

/**
 * This enum contains supported Carrier Frequencies
 */
@FunctionalInterface
public interface FrequencyBand {

    /**
     * Return the String representation of the frequency band.
     *
     * @return The band information, as a String.
     */
    String getBand();
}
