/**
 * 
 */
package networkmanagementsystem;

import common.AlarmStatusLevel;
import common.Carrier;
import common.FrequencyBand;
import common.RatType;
import common.Vendor;
import radiounit.RadioUnitState;

/**
 * @author enuyhza
 *
 */
public class ConcreteNetworkManagementSystem implements NetworkManagementSystem {

	/**
	 * 
	 */
	public ConcreteNetworkManagementSystem() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void commissionRu(String ip) {
    	
    }
	
	@Override
    public void decommissionRu(String ip) {
    	
    }

	@Override
	public void addRadioUnit(String name, Vendor vendor, RatType ratType) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeRadioUnit(String ip) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setupRU(String ip) {
		// TODO Auto-generated method stub

	}
	
	@Override
	public void releaseRU(String ip) {
		// TODO Auto-generated method stub
	}

	@Override
	public void activateRU(String ip) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deactivateRU(String ip) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setupCarrierOnRU(String ip, Carrier carrier) {
		// TODO Auto-generated method stub

	}

	@Override
	public void modifyCarrierOnRU(String ip, int id, FrequencyBand frequencyBand) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeCarrierOnRU(String ip, int id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeAllCarrierOnRU(String ip) {
		// TODO Auto-generated method stub

	}

	@Override
	public void signalScalingOnRU(String ip) {
		// TODO Auto-generated method stub

	}

	@Override
	public void postActivation(String ip) {
		// TODO Auto-generated method stub

	}

	@Override
	public void performSelfDiagnotics(String ip) {
		// TODO Auto-generated method stub

	}

	@Override
	public void listNetworkInventory() {
		// TODO Auto-generated method stub

	}
	
	@Override
	public void listRuByParam(Object obj) {
		
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

}
