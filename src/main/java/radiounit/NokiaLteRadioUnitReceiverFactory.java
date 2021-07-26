package radiounit;

/**
 * Is responsible for creating NokiaLteRadioUnitReceivers
 * 
 * @author esiumat
 *
 */
public class NokiaLteRadioUnitReceiverFactory implements RadioUnitReceiverFactory {

	/**
	 * Singleton instance of NokiaLteRadioUnitReceiverFactory
	 */
	private static volatile NokiaLteRadioUnitReceiverFactory UNIQUE_INSTANCE;

	private NokiaLteRadioUnitReceiverFactory() {
	}

	/**
	 * Static method for getting an instance of the singleton
	 * NokiaLteRadioUnitReceiverFactory
	 * 
	 * Utilizes double-checked locking
	 * 
	 * @return singleton of NokiaLteRadioUnitReceiverFactory
	 */
	public static synchronized NokiaLteRadioUnitReceiverFactory getInstance() {
		if (UNIQUE_INSTANCE == null) {
			synchronized (NokiaLteRadioUnitReceiverFactory.class) {
				if (UNIQUE_INSTANCE == null) {
					UNIQUE_INSTANCE = new NokiaLteRadioUnitReceiverFactory();
				}
			}
		}
		return UNIQUE_INSTANCE;
	}

	@Override
	public RadioUnitReceiver createRadioUnitReceiver() {
		System.out.println("[NokiaLteRadioUnitReceiverFactory] createRadioUnitReceiver");
		return new NokiaLteRadioUnitReceiver();
	}

}
