package networkmanagementsystem;


public class CommissionWcdmaRadioUnit extends CommissionRadioUnit {
    @Override
    public void setupRU(String ip) {

    }

    @Override
    public void activateRU(String ip) {

    }

    @Override
    void postActivation(String ip) {

    }

    @Override
    void performSignalScaling(String ip) {

    }

    @Override
    void performSelfDiagnotics(String ip) {

    }

    @Override
    boolean isLTE() {
        return false;
    }
}
