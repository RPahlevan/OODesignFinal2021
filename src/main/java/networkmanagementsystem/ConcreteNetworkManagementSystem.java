/**
 * 
 */
package networkmanagementsystem;

import common.*;
import mediator.ConcreteMediator;
import mediator.Mediator;
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
        commissionerLte = new CommissionLteRadioUnit();
        commissionerWcdma = new CommissionWcdmaRadioUnit();
        decommissionerLte = new DecommissionLteRadioUnit();
        decommissionerWcdma = new DecommissionWcdmaRadioUnit();
        mediator = ConcreteMediator.getInstance();
    }
	
    @Override
    public void commissionRu(String ip) {
        switch(mediator.getRadioUnit(ip).getRatType()) {
            case LTE -> commissionerLte.commissionRadioUnit(ip);
            case WCDMA -> commissionerWcdma.commissionRadioUnit(ip);
            default -> System.out.println("[ERROR] Unexpected RAT type encountered.");
        }
    }

    @Override
    public void decommissionRu(String ip) {
        switch(mediator.getRadioUnit(ip).getRatType()) {
            case LTE -> decommissionerLte.decommissionRadioUnit(ip);
            case WCDMA -> decommissionerWcdma.decommissionRadioUnit(ip);
            default -> System.out.println("[ERROR] Unexpected RAT type encountered.");
        }
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
        mediator.createRu(name, vendor, ratType);
    }

	@Override
	public void removeRadioUnit(String ip) {
		mediator.removeRu(ip);
	}
	
    @Override
    public void setupRu(String ip) {
        commissionerLte.setupRu(ip);
    }

    @Override
    public void releaseRu(String ip) {
        decommissionerLte.releaseRu(ip);
    }

    @Override
    public void activateRu(String ip) {

    }

    @Override
    public void deactivateRu(String ip) {

	}

	@Override
	public void setupCarrierOnRu(String ip, Carrier carrier) {

	}


	@Override
	public void modifyCarrierOnRu(String ip, int id, FrequencyBand frequencyBand) {

	}
	
	@Override
	public void removeCarrierOnRu(String ip, int id) {

	}
	
    @Override
    public void signalScalingOnRu(String ip) {

	}


    @Override
    public void postActivation(String ip) {

	}

    @Override
    public void performSelfDiagnostics(String ip) {

	}	


	@Override
	public void removeAllCarrierOnRu(String ip) {

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

	}

	@Override
	public void setAlarmOnRu(String ip, AlarmStatusLevel alarm) {

	}

	@Override
	public void getNetworkAlarms() {

	}

    @Override
    public void acknowledgeAlarm(String ip) {

    }


}
