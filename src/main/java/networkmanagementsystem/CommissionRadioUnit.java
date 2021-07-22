package networkmanagementsystem;


import common.Procedure;
import common.ProcedureOptions;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public abstract class CommissionRadioUnit {
    protected final PropertyChangeSupport support;

    CommissionRadioUnit() {
        support = new PropertyChangeSupport(this);
    }

    void setupRu(String ip) {
        support.firePropertyChange(Procedure.COMMISSION.getDesc(), ProcedureOptions.SETUP, ip);
    }

    void activateRu(String ip) {
        support.firePropertyChange(Procedure.COMMISSION.getDesc(), ProcedureOptions.ACTIVATE, ip);
    }

    void postActivation(String ip) {
        support.firePropertyChange(Procedure.COMMISSION.getDesc(), ProcedureOptions.POST, ip);
    }

    void performSignalScaling(String ip) {
        support.firePropertyChange(Procedure.COMMISSION.getDesc(), ProcedureOptions.SCALING, ip);
    }

    void performSelfDiagnotics(String ip) {
        support.firePropertyChange(Procedure.COMMISSION.getDesc(), ProcedureOptions.DIAGNOSTIC, ip);
    }

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

    /**
     * Adds listeners to this class.
     *
     * @param pcl a property change listener
     */
    void addPropertyChangeListener(PropertyChangeListener pcl) {
        support.addPropertyChangeListener(pcl);
    }
}
