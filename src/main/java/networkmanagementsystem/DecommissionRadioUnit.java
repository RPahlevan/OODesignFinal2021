package networkmanagementsystem;

import common.Procedure;
import common.ProcedureOptions;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public abstract class DecommissionRadioUnit {
    protected final PropertyChangeSupport support;

    public DecommissionRadioUnit() {
        support = new PropertyChangeSupport(this);
    }

    void deactivateRu(String ip) {
        support.firePropertyChange(Procedure.DECOMMISSION.getDesc(), ProcedureOptions.DEACTIVATE, ip);
    }

    void removeAllCarriersOnRu(String ip) {
        support.firePropertyChange(Procedure.DECOMMISSION.getDesc(), ProcedureOptions.CARRIER, ip);
    }

    void releaseRu(String ip) {
        support.firePropertyChange(Procedure.DECOMMISSION.getDesc(), ProcedureOptions.RELEASE, ip);
    }

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
    void addPropertyChangeListener(PropertyChangeListener pcl) {
        support.addPropertyChangeListener(pcl);
    }
}
