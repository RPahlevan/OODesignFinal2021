/**
 * 
 */
package networkmanagementsystem;

import common.*;
import mediator.ConcreteMediator;
import mediator.Mediator;
import radiounit.RadioUnitState;

/**
 * The ConcreteNetworkManagementSystem is responsible for managing various
 * network related functions. These mostly deal with radio units. However,
 * the implementation currently in place allows for this class to manage
 * radio units without explicitly knowing/referencing radio units.
 *
 * @author ebreojh

 */
public class ConcreteNetworkManagementSystem implements NetworkManagementSystem {
    private Mediator mediator;
    private CommissionRadioUnit commissionerLte;
    private CommissionRadioUnit commissionerWcdma;
    private DecommissionRadioUnit decommissionerLte;
    private DecommissionRadioUnit decommissionerWcdma;

    public ConcreteNetworkManagementSystem() {
        mediator = ConcreteMediator.getInstance();
    }
	
    @Override
    public void commissionRu(String ip) {
        if (mediator.getRadioUnit(ip).getRatType().equals(RatType.LTE)) {
            commissionerLte.commissionRadioUnit(ip);
        } else {
            commissionerWcdma.commissionRadioUnit(ip);
        }
    }

    @Override
    public void decommissionRu(String ip) {
        if (mediator.getRadioUnit(ip).getRatType().equals(RatType.LTE)) {
            decommissionerLte.decommissionRadioUnit(ip);
        } else {
            decommissionerWcdma.decommissionRadioUnit(ip);
        }
    }

    /**
     * Create an radio unit. This radio unit is not initially activated.
     *
     * @param name The name the radio unit can be identified by.
     * @param vendor The vendor of the radio unit.
     * @param ratType The RAT type of the radio unit.
     */
    @Override
    public void addRadioUnit(String name, Vendor vendor, RatType ratType) {
        mediator.createRu(name, vendor, ratType);

    }

	@Override
	public void removeRadioUnit(String ip) {
		// TODO Auto-generated method stub
	}
	
    @Override
    public void setupRU(String ip) {

    }

    @Override
    public void releaseRU(String ip) {

    }

    @Override
    public void activateRU(String ip) {

    }

    @Override
    public void deactivateRU(String ip) {

	}

	@Override
	public void setupCarrierOnRU(String ip, Carrier carrier) {
		// TODO Auto-generated method stub

	}


	@Override
	public void modifyCarrierOnRU(String ip, int id, FrequencyBand frequencyBand) {

	}
	
	@Override
	public void removeCarrierOnRU(String ip, int id) {

	}
	
    @Override
    public void signalScalingOnRU(String ip) {

	}


    @Override
    public void postActivation(String ip) {

	}

    @Override
    public void performSelfDiagnostics(String ip) {

	}	


	@Override
	public void removeAllCarrierOnRU(String ip) {

	}


    @Override
    public void listNetworkInventory() {
        mediator.printRegisteredRadioUnits();
    }

    @Override
    public void listRuByParam(Object obj) {
        mediator.listRuByParam(obj);
    }


	@Override
	public void listRadioUnitDetails(String ip) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setAlarmOnRU(String ip, AlarmStatusLevel alarm) {
		// TODO Auto-generated method stub

	}

	@Override
	public void getNetworkAlarms() {
		// TODO Auto-generated method stub

	}

    @Override
    public void acknowledgeAlarm(String ip) {

    }


}
