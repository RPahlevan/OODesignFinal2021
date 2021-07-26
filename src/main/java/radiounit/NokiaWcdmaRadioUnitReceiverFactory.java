package radiounit;

/**
 * Is responsible for creating NokiaWcdmaRadioUnitReceivers
 * 
 * @author esiumat
 *
 */
public class NokiaWcdmaRadioUnitReceiverFactory implements RadioUnitReceiverFactory {

	/**
	 * Singleton instance of NokiaWcdmaRadioUnitReceiverFactory
	 */
	private static volatile NokiaWcdmaRadioUnitReceiverFactory UNIQUE_INSTANCE;

	private NokiaWcdmaRadioUnitReceiverFactory() {
	}

	/**
	 * Static method for getting an instance of the singleton
	 * NokiaWcdmaRadioUnitReceiverFactory
	 * 
	 * Utilizes double-checked locking
	 * 
	 * @return singleton of NokiaWcdmaRadioUnitReceiverFactory
	 */
	public static synchronized NokiaWcdmaRadioUnitReceiverFactory getInstance() {
		if (UNIQUE_INSTANCE == null) {
			synchronized (NokiaWcdmaRadioUnitReceiverFactory.class) {
				if (UNIQUE_INSTANCE == null) {
					UNIQUE_INSTANCE = new NokiaWcdmaRadioUnitReceiverFactory();
				}
			}
		}
		return UNIQUE_INSTANCE;
	}

	@Override
	public RadioUnitReceiver createRadioUnitReceiver() {
		System.out.println("[NokiaWcdmaRadioUnitReceiverFactory] createRadioUnitReceiver");
		return new NokiaWcdmaRadioUnitReceiver();
	}

}
