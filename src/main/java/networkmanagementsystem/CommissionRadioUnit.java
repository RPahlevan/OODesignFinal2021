package networkmanagementsystem;


public abstract class CommissionRadioUnit {
	NetworkManagementSystem networkManagementSys;
	public CommissionRadioUnit() {
		networkManagementSys = new ConcreteNetworkManagementSystem();
	}
    abstract boolean isLTE();
    void commissionRadioUnit(String ip) {
    	networkManagementSys.setupRU(ip);
    	networkManagementSys.activateRU(ip);
    	networkManagementSys.postActivation(ip);
    	if (isLTE()) {
    		networkManagementSys.signalScalingOnRU(ip);
    	}    	
    	networkManagementSys.performSelfDiagnotics(ip);
    }
    
}
