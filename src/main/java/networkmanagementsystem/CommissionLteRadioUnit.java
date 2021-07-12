package networkmanagementsystem;



public class CommissionLteRadioUnit extends CommissionRadioUnit {


    @Override
    public void setupRu(String ip) {
    }

    @Override
    public void activateRu(String ip) {

    }

    @Override
    public void postActivation(String ip) {

    }

    @Override
    public void performSignalScaling(String ip) {

    }

    @Override
    public void performSelfDiagnotics(String ip) {

    }

    @Override
    boolean isLTE() {
        return true;
    }
}
