package networkmanagementsystem;

import mediator.ConcreteMediator;
import mediator.Mediator;

public abstract class DecommissionRadioUnit {
	Mediator mediator;
	public DecommissionRadioUnit() {
		 mediator = ConcreteMediator.getInstance();
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
