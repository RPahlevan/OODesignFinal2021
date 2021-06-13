package carrierManagementSystem;
import java.util.List;

import common.FrequencyBand;
import common.RfPorts;

public interface CarrierManagementIf {
    /**
     * The below method is responsible for creating LTE carrier based on inputs.
     * This method will ask CarrierBuilder children class - LTECarrierBuilder- to
     * create LTE carrier based on inputs.
     *
     * @param rfPorts            LTE carrier needs 4 ports. These RF ports can be
     *                           selected from RFPorts enum class.
     * @param frequencyBand LTE carrier needs specific band to operate. List of
     *                           LTE carrier frequency are available in
     *                           CarrierFrequencies enum class.
     * @param transmittingPower  LTE carrier need transmitting power to be created.
     * @return LTECarrier Returns the LTECarrier that was built by the
     *         LTECarrierBuilder
     */
    public Carrier createLteCarrier(List<RfPorts> rfPorts, FrequencyBand frequencyBand,
            Double transmittingPower);

    /**
     * The below method is responsible for creating WCDMA carrier based on inputs.
     * This method will ask CarrierBuilder children class - WCDMACarrierBuilder- to
     * create WCDMA carrier based on inputs.
     *
     * @param rfPorts            WCDMA carrier needs 2 ports. These RF ports can be
     *                           selected from RFPorts enum class.
     * @param frequencyBand WCDMA carrier needs specific band to operate. List
     *                           of WCDMA carrier frequency are available in
     *                           CarrierFrequencies enum class.
     * @param transmittingPower  WCDMA carrier need transmitting power to be
     *                           created.
     * @return WCDMACarrier Returns the WCDMACarrier that was built by the
     *         WCDMACarrierBuilder
     */
    public Carrier createWcdmaCarrier(List<RfPorts> rfPorts, FrequencyBand frequencyBand,
            Double transmittingPower);
}
