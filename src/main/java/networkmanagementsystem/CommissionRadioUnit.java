package networkmanagementsystem;


import common.Procedure;
import common.ProcedureOptions;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * The CommissionRadioUnit class is responsible for providing a template
 * for the commission process radio units are supposed to follow. Implementation
 * is forced for abstract methods present.
 *
 * @author ebreojh
 */
public abstract class CommissionRadioUnit {
    protected final PropertyChangeSupport support;

    /**
     * Constructor for the CommissionRadioUnit class.
     */
    CommissionRadioUnit() {
        support = new PropertyChangeSupport(this);
    }

    /**
     * Notify any listeners that a radio unit needs to setup.
     *
     * @param ip The IP address of the radio unit that will be setup.
     */
    final void setupRu(String ip) {
        support.firePropertyChange(Procedure.COMMISSION.getDesc(), ProcedureOptions.SETUP, ip);
    }

    /**
     * Notify any listeners that a radio unit needs to activate.
     *
     * @param ip The IP address of the radio unit that will be activated.
     */
    final void activateRu(String ip) {
        support.firePropertyChange(Procedure.COMMISSION.getDesc(), ProcedureOptions.ACTIVATE, ip);
    }

    /**
     * Notify any listeners that a radio unit needs to perform post activation.
     *
     * @param ip The IP address of the radio unit that will be performing post activation.
     */
    final void postActivation(String ip) {
        support.firePropertyChange(Procedure.COMMISSION.getDesc(), ProcedureOptions.POST, ip);
    }

    /**
     * Notify any listeners that a radio unit needs to perform signal scaling.
     *
     * @param ip The IP address of the radio unit that will be performing signal scaling.
     */
    final void performSignalScaling(String ip) {
        support.firePropertyChange(Procedure.COMMISSION.getDesc(), ProcedureOptions.SCALING, ip);
    }

    /**
     * Notify any listeners that a radio unit needs to perform self diagnotics.
     *
     * @param ip The IP address of the radio unit that will be performing self diagnostics.
     */
    final void performSelfDiagnotics(String ip) {
        support.firePropertyChange(Procedure.COMMISSION.getDesc(), ProcedureOptions.DIAGNOSTIC, ip);
    }

    abstract boolean isLte();

    /**
     * Template for the commission procedure that radio units need to follow.
     *
     * @param ip The IP address for the radio unit that is being commissioned.
     */
    final void commissionRadioUnit(String ip) {
        setupRu(ip);
        activateRu(ip);
        postActivation(ip);
        if (isLte()) {
            performSignalScaling(ip);
        }
        performSelfDiagnotics(ip);
    }

    /**
     * Adds listeners to this class.
     *
     * @param pcl a property change listener
     */
    final void addPropertyChangeListener(PropertyChangeListener pcl) {
        support.addPropertyChangeListener(pcl);
    }
}
