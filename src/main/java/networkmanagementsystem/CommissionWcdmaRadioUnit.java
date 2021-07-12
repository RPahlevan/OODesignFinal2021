package networkmanagementsystem;


public class CommissionWcdmaRadioUnit extends CommissionRadioUnit {
    @Override
    public void setupRu(String ip) {

    }

    @Override
    public void activateRu(String ip) {

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
