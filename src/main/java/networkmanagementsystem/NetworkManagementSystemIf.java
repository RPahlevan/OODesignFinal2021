package networkmanagementsystem;

import common.*;

import java.beans.PropertyChangeListener;
import java.util.HashMap;
import java.util.List;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * The NetworkManagementSystem interface serves as the forward-facing
 * API that users can access to interact with the entire system.
 * <p>
 * To view its implementation, please view {@link NetworkManagementSystem}
 *
 * @author ebreojh
 */
public interface NetworkManagementSystemIf extends Remote {

    void commissionRu(String ip) throws RemoteException;

    void decommissionRu(String ip) throws RemoteException;

    void addRadioUnit(String ip, String name, Vendor vendor, RatType ratType) throws RemoteException;

    void removeRadioUnit(String ip) throws RemoteException;

    void setupRu(String ip) throws RemoteException;

    void releaseRu(String ip) throws RemoteException;

    void activateRu(String ip) throws RemoteException;

    void deactivateRu(String ip) throws RemoteException;

    void setupCarrierOnRu(String ip, List<RfPort> rfPorts, FrequencyBand freq, Double transmittingPower) throws RemoteException;

    void modifyCarrierOnRu(String ip, int id, FrequencyBand frequencyBand) throws RemoteException;

    void removeCarrierOnRu(String ip, int id) throws RemoteException;

    void removeAllCarriersOnRu(String ip) throws RemoteException;

    void signalScalingOnRu(String ip) throws RemoteException;

    void postActivation(String ip) throws RemoteException;

    void performSelfDiagnostics(String ip) throws RemoteException;

    void listNetworkInventory() throws RemoteException;

    void listRuByParam(Object param) throws RemoteException;

    void listRadioUnitDetails(String ip) throws RemoteException;

    void setAlarmOnRu(String ip, AlarmStatusLevel alarm) throws RemoteException;

    void getNetworkAlarms() throws RemoteException;

    boolean acknowledgeAlarm(String ip) throws RemoteException;

    void addPropertyChangeListener(PropertyChangeListener pcl) throws RemoteException;

    HashMap<RatType, CommissionRadioUnit> getCommissioners() throws RemoteException;

    HashMap<RatType, DecommissionRadioUnit> getDecommissioners() throws RemoteException;

    RatType getRuRatType(String ip) throws RemoteException;

}
