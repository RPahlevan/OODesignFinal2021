package networkmanagementsystem;


import mediator.ConcreteMediator;
import mediator.Mediator;

public abstract class CommissionRadioUnit {
    protected Mediator mediator;

    CommissionRadioUnit() {
        mediator = ConcreteMediator.getInstance();
    }

    void setupRu(String ip) {
        mediator.getRadioUnit(ip).setup();
    }

    abstract void activateRu(String ip);

    abstract void postActivation(String ip);

    abstract void performSignalScaling(String ip);

    abstract void performSelfDiagnotics(String ip);

    abstract boolean isLTE();

    final void commissionRadioUnit(String ip) {
        setupRu(ip);
        activateRu(ip);
        postActivation(ip);
        if (isLTE()) {
            performSignalScaling(ip);
        }
        performSelfDiagnotics(ip);

    }

}
