package mediator;

import common.*;
import radiounit.ManagedRadioUnit;

import java.beans.PropertyChangeListener;
import java.util.List;

/**
 * The Mediator interface serves as the communication gateway
 * for all other components of the WirelessNetworkManagementSystem.
 * <p>
 * To view its implementation, please view {@link Mediator}
 *
 * @author ebreojh
 */
public interface MediatorIf {
    void addPropertyChangeListener(PropertyChangeListener pcl);
}