/**
 * 
 */
package networkmanagementsystem;

import common.*;
import mediator.Mediator;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.*;

import static common.ProcedureOptions.*;

/**
 * The NetworkManagementSystem is responsible for managing various
 * network related functions. These mostly deal with radio units. However,
 * the implementation currently in place allows for this class to manage
 * radio units without explicitly knowing/referencing radio units or the
 * mediator that controls the logic with the rest of the system.
 * <p>
 * Instead, the NetworkManagementSystem engages in a one-way
 * communication agreement with the mediator. The NetworkManagementSystem
 * does not know that the Mediator exists, and with some sneaky eager instantiation
 * we can register the NetworkManagementSystem to the
 *
 * @author ebreojh
 */
public class NetworkManagementSystem implements NetworkManagementSystemIf, PropertyChangeListener {
    private static volatile NetworkManagementSystem UNIQUE_INSTANCE;
    private final PropertyChangeSupport support;
    private final HashMap<RatType, CommissionRadioUnit> commissioners;
    private final HashMap<RatType, DecommissionRadioUnit> decommissioners;

    private NetworkManagementSystem() {
        commissioners = new HashMap<>();
        commissioners.put(RatType.LTE, new CommissionLteRadioUnit());
        commissioners.put(RatType.WCDMA, new CommissionWcdmaRadioUnit());
        decommissioners = new HashMap<>();
        decommissioners.put(RatType.LTE, new DecommissionLteRadioUnit());
        decommissioners.put(RatType.WCDMA, new DecommissionWcdmaRadioUnit());
        support = new PropertyChangeSupport(this);
    }

    /**
     * Get the unique NetworkManagementSystem instance.
     *
     * @return The Singleton instance of the Mediator class.
     */
    public static NetworkManagementSystem getInstance() {
        if (UNIQUE_INSTANCE == null) {
            synchronized (Mediator.class) {
                if (UNIQUE_INSTANCE == null) {
                    UNIQUE_INSTANCE = new NetworkManagementSystem();
                }
            }
        }
        return UNIQUE_INSTANCE;
    }

    @Override
    public void commissionRu(String ip) {
        List<Object> params = new ArrayList<>(Arrays.asList(ip, ProcedureOptions.FULL));
        support.firePropertyChange(Procedure.COMMISSION.getDesc(), ProcedureOptions.RAT_TYPE, params);
    }

    @Override
    public void decommissionRu(String ip) {
        List<Object> params = new ArrayList<>(Arrays.asList(ip, ProcedureOptions.FULL));
        support.firePropertyChange(Procedure.DECOMMISSION.getDesc(), ProcedureOptions.RAT_TYPE, params);
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
        List<Object> params = new ArrayList<>(Arrays.asList(ip, SETUP));
        support.firePropertyChange(Procedure.COMMISSION.getDesc(), ProcedureOptions.RAT_TYPE, params);
    }

    @Override
    public void releaseRu(String ip) {
        List<Object> params = new ArrayList<>(Arrays.asList(ip, ProcedureOptions.RELEASE));
        support.firePropertyChange(Procedure.DECOMMISSION.getDesc(), ProcedureOptions.RAT_TYPE, params);
    }

    @Override
    public void activateRu(String ip) {
        List<Object> params = new ArrayList<>(Arrays.asList(ip, ACTIVATE));
        support.firePropertyChange(Procedure.COMMISSION.getDesc(), ProcedureOptions.RAT_TYPE, params);
    }

    @Override
    public void deactivateRu(String ip) {
        List<Object> params = new ArrayList<>(Arrays.asList(ip, ProcedureOptions.DEACTIVATE));
        support.firePropertyChange(Procedure.DECOMMISSION.getDesc(), ProcedureOptions.RAT_TYPE, params);
	}

	@Override
	public void setupCarrierOnRu(String ip, List<RfPort> rfPorts, FrequencyBand freq, Double transmittingPower) {
        List<Object> params = new ArrayList<>(Arrays.asList(ip, rfPorts, freq, transmittingPower));
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
        List<Object> params = new ArrayList<>(Arrays.asList(ip, SCALING));
        support.firePropertyChange(Procedure.COMMISSION.getDesc(), ProcedureOptions.RAT_TYPE, params);
	}

    @Override
    public void postActivation(String ip) {
        List<Object> params = new ArrayList<>(Arrays.asList(ip, ProcedureOptions.POST));
        support.firePropertyChange(Procedure.COMMISSION.getDesc(), ProcedureOptions.RAT_TYPE, params);
	}

    @Override
    public void performSelfDiagnostics(String ip) {
        List<Object> params = new ArrayList<>(Arrays.asList(ip, ProcedureOptions.DIAGNOSTIC));
        support.firePropertyChange(Procedure.COMMISSION.getDesc(), ProcedureOptions.RAT_TYPE, params);
	}	

	@Override
	public void removeAllCarrierOnRu(String ip) {
        List<Object> params = new ArrayList<>(Arrays.asList(ip, ProcedureOptions.CARRIER));
        support.firePropertyChange(Procedure.DECOMMISSION.getDesc(), ProcedureOptions.RAT_TYPE, params);
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
     *
     * @return
     */
    public HashMap<RatType, CommissionRadioUnit> getCommissioners() {
        return commissioners;
    }

    /**
     *
     * @return
     */
    public HashMap<RatType, DecommissionRadioUnit> getDecommissioners() {
        return decommissioners;
    }

    /**
     * Adds listeners to this class.
     *
     * @param pcl A property change listener
     */
    public void addPropertyChangeListener(PropertyChangeListener pcl) {
        support.addPropertyChangeListener(pcl);
    }

    /**
     * This method gets called when a bound property is changed.
     *
     * @param evt A PropertyChangeEvent object describing the event source
     *            and the property that has changed.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        Procedure procedure = null;
        for (Procedure currProcedure : Procedure.values()) {
            if (currProcedure.getDesc().equals(evt.getPropertyName())) {
                procedure = currProcedure;
            }
        }
        List<Object> params = ((ArrayList<Object>) evt.getOldValue());
        switch (Objects.requireNonNull(procedure)) {
            case COMMISSION -> {
                switch ((ProcedureOptions) evt.getOldValue()) {
                    case FULL -> commissioners.get(params.get(1)).commissionRadioUnit((String) params.get(0));
                    case SETUP -> commissioners.get(params.get(1)).setupRu((String) params.get(0));
                    case ACTIVATE -> commissioners.get(params.get(1)).activateRu((String) params.get(0));
                    case SCALING -> commissioners.get(params.get(1)).performSignalScaling((String) params.get(0));
                    case POST -> commissioners.get(params.get(1)).postActivation((String) params.get(0));
                    case DIAGNOSTIC -> commissioners.get(params.get(1)).performSelfDiagnotics((String) params.get(0));
                }
            }
            case DECOMMISSION -> {
                switch ((ProcedureOptions) evt.getOldValue()) {
                    case FULL -> decommissioners.get(params.get(1)).decommissionRadioUnit((String) params.get(0));
                    case RELEASE -> decommissioners.get(params.get(1)).releaseRu((String) params.get(0));
                    case DEACTIVATE -> decommissioners.get(params.get(1)).deactivateRu((String) params.get(0));
                    case CARRIER -> decommissioners.get(params.get(1)).removeAllCarriersOnRu((String) params.get(0));
                }
            }
        }
    }
}
