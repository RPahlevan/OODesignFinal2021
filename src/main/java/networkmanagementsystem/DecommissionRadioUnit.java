package networkmanagementsystem;


import mediator.Mediator;

public abstract class DecommissionRadioUnit {
    protected Mediator mediator;

	public DecommissionRadioUnit(){
        mediator = Mediator.getInstance();
	}

    abstract void deactivateRu(String ip);

    abstract void removeAllCarriersOnRu(String ip);

    void releaseRu(String ip) {
        mediator.getRadioUnit(ip).release();
    }

    void decommissionRadioUnit(String ip) {
        deactivateRu(ip);
        removeAllCarriersOnRu(ip);
        releaseRu(ip);
    }
}
