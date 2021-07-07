package networkmanagementsystem;

public abstract class DecommissionRadioUnit {
    abstract void deactivateRU(String ip);
    abstract void removeAllCarriersOnRU(String ip);
    abstract void releaseRU(String ip);
}
