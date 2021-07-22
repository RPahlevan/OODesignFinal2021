package networkmanagementsystem;

/**
 * Concrete implementation of the CommissionRadioUnit class specifically
 * for LTE RAT type radio units.
 *
 * @author ebreojh
 */
public class CommissionLteRadioUnit extends CommissionRadioUnit {

    /**
     * Signal if this commissioner is for LTE radio units.
     *
     * @return True as this class is for LTE radio units
     */
    @Override
    boolean isLTE() {
        return true;
    }
}
