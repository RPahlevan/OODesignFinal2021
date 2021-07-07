package networkmanagementsystem;

public abstract class CommissionRadioUnit {
    abstract void setupRU(String ip);
    abstract void activateRU(String ip);
    abstract void postActivation(String ip);
    abstract void performSignalScaling(String ip);
    abstract void performSelfDiagnotics(String ip);
}
