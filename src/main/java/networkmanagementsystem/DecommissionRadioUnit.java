package networkmanagementsystem;


import mediator.ConcreteMediator;
import mediator.Mediator;

public abstract class DecommissionRadioUnit {
    protected Mediator mediator;

	public DecommissionRadioUnit(){
        mediator = ConcreteMediator.getInstance();
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
