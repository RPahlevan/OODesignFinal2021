package mediator;

import java.beans.PropertyChangeListener;

/**
 * The Mediator interface serves as the communication gateway
 * for all other components of the WirelessNetworkManagementSystem.
 * <p>
 * To view its implementation, please view {@link Mediator}
 *
 * @author ebreojh
 */
@FunctionalInterface
public interface MediatorIf {
    void addPropertyChangeListener(PropertyChangeListener pcl);
}
