package networkmanagementsystem;

/**
 * Concrete implementation of the CommissionRadioUnit class specifically
 * for WCDMA RAT type radio units.
 *
 * @author ebreojh
 */
public class CommissionWcdmaRadioUnit extends CommissionRadioUnit {

    /**
     * Signal if this commissioner is for LTE radio units.
     *
     * @return False as this class is not for LTE radio units
     */
    @Override
    boolean isLTE() {
        return false;
    }
}
