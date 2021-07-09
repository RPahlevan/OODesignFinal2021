package networkmanagementsystem;


public abstract class DecommissionRadioUnit {
	NetworkManagementSystem networkManagementSys;
	public DecommissionRadioUnit(){
		networkManagementSys = new ConcreteNetworkManagementSystem();
	}
    void decommissionRadioUnit(String ip) {
    	networkManagementSys.deactivateRU(ip);
    	networkManagementSys.removeAllCarrierOnRU(ip);
    	networkManagementSys.removeRadioUnit(ip);
    }
}
