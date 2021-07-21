/**
 * 
 */
package networkmanagementsystem;

import common.*;
import mediator.Mediator;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * The ConcreteNetworkManagementSystem is responsible for managing various
 * network related functions. These mostly deal with radio units. However,
 * the implementation currently in place allows for this class to manage
 * radio units without explicitly knowing/referencing radio units or the
 * mediator that controls the logic with the rest of the system.
 * <p>
 * Instead, the ConcreteNetworkManagementSystem engages in a one-way
 * communication agreement with the mediator. The ConcreteNetworkManagementSystem
 * does not know that the Mediator exists, and with some sneaky eager instantiation
 * we can register the ConcreteNetworkManagementSystem to the
 *
 * @author ebreojh
 */
public class ConcreteNetworkManagementSystem implements NetworkManagementSystem {
    private static volatile ConcreteNetworkManagementSystem UNIQUE_INSTANCE;
    private final PropertyChangeSupport support;

    private ConcreteNetworkManagementSystem() {
        support = new PropertyChangeSupport(this);
    }

    /**
     * Get the unique ConcreteNetworkManagementSystem instance.
     *
     * @return The Singleton instance of the Mediator class.
     */
    public static ConcreteNetworkManagementSystem getInstance() {
        if (UNIQUE_INSTANCE == null) {
            synchronized (Mediator.class) {
                if (UNIQUE_INSTANCE == null) {
                    UNIQUE_INSTANCE = new ConcreteNetworkManagementSystem();
                }
            }
        }
        return UNIQUE_INSTANCE;
    }

    @Override
    public void commissionRu(String ip) {
        support.firePropertyChange(Procedure.COMMISSION.getDesc(), ProcedureOptions.FULL, ip);
    }

    @Override
    public void decommissionRu(String ip) {
        support.firePropertyChange(Procedure.DECOMMISSION.getDesc(), ProcedureOptions.FULL, ip);
    }

    /**
     * Create a radio unit. This radio unit is not initially activated.
     *
     * @param name The name the radio unit can be identified by.
     * @param vendor The vendor of the radio unit.
     * @param ratType The RAT type of the radio unit.
     */
    @Override
    public void addRadioUnit(String name, Vendor vendor, RatType ratType) {
        List<Object> params = new ArrayList<>(Arrays.asList(name, vendor, ratType));
        support.firePropertyChange(Procedure.CREATE.getDesc(), ProcedureOptions.RU, params);
    }

	@Override
	public void removeRadioUnit(String ip) {
        support.firePropertyChange(Procedure.DELETE.getDesc(), ProcedureOptions.RU, ip);
	}
	
    @Override
    public void setupRu(String ip) {
        support.firePropertyChange(Procedure.COMMISSION.getDesc(), ProcedureOptions.SETUP, ip);
    }

    @Override
    public void releaseRu(String ip) {
        support.firePropertyChange(Procedure.DECOMMISSION.getDesc(), ProcedureOptions.RELEASE, ip);
    }

    @Override
    public void activateRu(String ip) {
        support.firePropertyChange(Procedure.COMMISSION.getDesc(), ProcedureOptions.ACTIVATE, ip);
    }

    @Override
    public void deactivateRu(String ip) {
        support.firePropertyChange(Procedure.DECOMMISSION.getDesc(), ProcedureOptions.DEACTIVATE, ip);
	}

	@Override
	public void setupCarrierOnRu(String ip, List<RfPort> rfPorts, FrequencyBand freq, Double transmittingPower, RatType ratType) {
        List<Object> params = new ArrayList<>(Arrays.asList(ip,  rfPorts, freq, transmittingPower, ratType));
        support.firePropertyChange(Procedure.CREATE.getDesc(), ProcedureOptions.CARRIER, params);
	}


	@Override
	public void modifyCarrierOnRu(String ip, int id, FrequencyBand frequencyBand) {
        List<Object> params = new ArrayList<>(Arrays.asList(ip,  id, frequencyBand));
        support.firePropertyChange(Procedure.MODIFY.getDesc(), ProcedureOptions.CARRIER, params);
	}
	
	@Override
	public void removeCarrierOnRu(String ip, int id) {
        List<Object> params = new ArrayList<>(Arrays.asList(ip,  id));
        support.firePropertyChange(Procedure.DELETE.getDesc(), ProcedureOptions.CARRIER, params);
	}
	
    @Override
    public void signalScalingOnRu(String ip) {
        support.firePropertyChange(Procedure.COMMISSION.getDesc(), ProcedureOptions.SCALING, ip);
	}

    @Override
    public void postActivation(String ip) {
        support.firePropertyChange(Procedure.COMMISSION.getDesc(), ProcedureOptions.POST, ip);
	}

    @Override
    public void performSelfDiagnostics(String ip) {
        support.firePropertyChange(Procedure.COMMISSION.getDesc(), ProcedureOptions.DIAGNOSTIC, ip);
	}	

	@Override
	public void removeAllCarrierOnRu(String ip) {
        support.firePropertyChange(Procedure.DECOMMISSION.getDesc(), ProcedureOptions.CARRIER, ip);
	}

    @Override
    public void listNetworkInventory() {
        support.firePropertyChange(Procedure.LIST.getDesc(), ProcedureOptions.FULL, "");
    }

    @Override
    public void listRuByParam(Object obj) {
        support.firePropertyChange(Procedure.LIST.getDesc(), ProcedureOptions.PARAM, obj);
    }

	@Override
	public void listRadioUnitDetails(String ip) {
        support.firePropertyChange(Procedure.LIST.getDesc(), ProcedureOptions.RU, ip);
	}

	@Override
	public void setAlarmOnRu(String ip, AlarmStatusLevel alarm) {
        List<Object> params = new ArrayList<>(Arrays.asList(ip, alarm));
        support.firePropertyChange(Procedure.CREATE.getDesc(), ProcedureOptions.ALARM, params);
	}

	@Override
	public void getNetworkAlarms() {
        support.firePropertyChange(Procedure.LIST.getDesc(), ProcedureOptions.ALARM, "");
	}

    @Override
    public void acknowledgeAlarm(String ip) {
        support.firePropertyChange(Procedure.ACKNOWLEDGE.getDesc(), ProcedureOptions.ALARM, ip);
    }

    /**
     * Adds listeners to this class.
     *
     * @param pcl a property change listener
     */
    public void addPropertyChangeListener(PropertyChangeListener pcl) {
        support.addPropertyChangeListener(pcl);
    }
}
