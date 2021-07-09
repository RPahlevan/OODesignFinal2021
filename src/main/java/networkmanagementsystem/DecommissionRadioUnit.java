package networkmanagementsystem;


public abstract class DecommissionRadioUnit {

	public DecommissionRadioUnit(){

	}

    abstract void deactivateRU(String ip);

    abstract void removeAllCarriersOnRU(String ip);

    abstract void releaseRU(String ip);

    void decommissionRadioUnit(String ip) {
        deactivateRU(ip);
        removeAllCarriersOnRU(ip);
        releaseRU(ip);
    }
}
