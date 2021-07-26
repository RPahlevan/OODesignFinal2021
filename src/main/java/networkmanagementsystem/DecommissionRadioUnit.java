package networkmanagementsystem;

import common.Procedure;
import common.ProcedureOptions;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * The DecommissionRadioUnit class is responsible for providing a template
 * for the decommission process radio units are supposed to follow. Normally,
 * the children on this abstract class would have different implementations
 * for certain steps involved in the template, however this was not the case
 * with this system.
 *
 * @author ebreojh
 */
public abstract class DecommissionRadioUnit {
    protected final PropertyChangeSupport support;

    /**
     * Constructor for the DecommissionRadioUnit class.
     */
    public DecommissionRadioUnit() {
        support = new PropertyChangeSupport(this);
    }

    /**
     * Notify any listeners that a radio unit is in need of deactivation.
     *
     * @param ip The IP address of the radio unit that will be deactivated.
     */
    final void deactivateRu(String ip) {
        support.firePropertyChange(Procedure.DECOMMISSION.getDesc(), ProcedureOptions.DEACTIVATE, ip);
    }

    /**
     * Notify any listeners that a radio unit needs to have all carriers removed.
     *
     * @param ip The IP address of the radio unit that will have all of its carriers removed.
     */
    final void removeAllCarriersOnRu(String ip) {
        support.firePropertyChange(Procedure.DECOMMISSION.getDesc(), ProcedureOptions.CARRIER, ip);
    }

    /**
     * Notify any listeners that a radio unit needs to be released.
     *
     * @param ip The IP address of the radio unit that will be released.
     */
    final void releaseRu(String ip) {
        support.firePropertyChange(Procedure.DECOMMISSION.getDesc(), ProcedureOptions.RELEASE, ip);
    }

    /**
     * Template for the decommission procedure that radio units need to follow.
     *
     * @param ip The IP address for the radio unit that is being decommissioned.
     */
    final void decommissionRadioUnit(String ip) {
        deactivateRu(ip);
        removeAllCarriersOnRu(ip);
        releaseRu(ip);
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
