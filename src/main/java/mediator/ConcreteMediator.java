package mediator;

/**
 * The ConcreteMediator class is responsible for handling control/communication
 * logic between various system throughout the Wireless Network Management System.
 * <p>
 * In the current iteration, the mediator handles the references to various RUs
 * and manages communication between these RUs and the CarrierManagementSystem.
 * <p>
 * In future iterations, the mediator will also handle communication between
 * the Network Management System while maintaining a list of references to
 * built RUs.
 *
 * This class acts a Singleton to maintain data-consistency given the requirement
 * that the system should be able to handle multiple users at once. Having two
 * ConcreteMediators would result in two distinct lists of RUs, which would cause
 * a communication breakdown between the various systems as they try to determine
 * (and fail to do so) which mediator contains the RU they need to interface with.
 */
public class ConcreteMediator implements Mediator {
    private static volatile ConcreteMediator UNIQUE_INSTANCE = new ConcreteMediator();
    private List<RadioUnit> radioUnits;

    /**
     * Constructor for the ConcreteMediator class.
     * Required to be private to ensure the Singleton Pattern is followed.
     */
    private ConcreteMediator() {
        radioUnits = new List<RadioUnit>();
    }

    /**
     * Get the unique ConcreteMediator instance.
     *
     * @return The Singleton instance of the ConcreteMediator class.
     */
    public static ConcreteMediator getInstance() {
        return UNIQUE_INSTANCE;
    }

    /**
     * Registers an RU with the mediator. Only RUs that are registered can
     * be interacted with via other classes.
     *
     * @param radioUnit The RU that will be registered with the mediator.
     */
    @Override
    public synchronized void register(RadioUnit radioUnit) {
        if (!radioUnits.contains(radioUnit)) {
            radioUnits.add(radioUnit);
        }
    }

    /**
     * Displays the carrier associated with an RU given a specific
     * RU ID number.
     *
     * @param id The ID number for the RU that will have its carrier displayed.
     */
    @Override
    public void displayCarrier(int id) {
        radioUnits.forEach(ru -> {
            if (ru.getId() == id) {
                System.out.println(ru.displayCarrier());
                return;
            }
        });
        System.out.println(String.format("No RUs with the ID %d have been registered with the system.", id));
    }

}
