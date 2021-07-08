package networkmanagementsystem;

import mediator.ConcreteMediator;
import mediator.Mediator;

public abstract class CommissionRadioUnit {

    CommissionRadioUnit() {
    }

    abstract void setupRU(String ip);

    abstract void activateRU(String ip);

    abstract void postActivation(String ip);

    abstract void performSignalScaling(String ip);

    abstract void performSelfDiagnotics(String ip);

    abstract boolean isLTE();

    final void commissionRadioUnit(String ip) {
        setupRU(ip);
        activateRU(ip);
        postActivation(ip);
        if (isLTE()) {
            performSignalScaling(ip);
        }
        performSelfDiagnotics(ip);
    }

}
