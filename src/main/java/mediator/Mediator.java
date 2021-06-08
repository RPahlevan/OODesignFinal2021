package mediator;

/**
 * The Mediator interface serves as the communication gateway
 * for all other components of the WirelessNetworkManagementSystem.
 * <p>
 * To view its implementation, please view {@link ConcreteMediator}
 */
public interface Mediator {
    void register(RadioUnit radioUnit);

    void displayCarrier(int id);
}
