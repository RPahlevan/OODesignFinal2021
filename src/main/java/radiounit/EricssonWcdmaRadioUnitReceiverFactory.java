package radiounit;

/**
 * Is responsible for creating EricssonWcdmaRadioUnitReceivers
 * 
 * @author esiumat
 *
 */
public class EricssonWcdmaRadioUnitReceiverFactory implements RadioUnitReceiverFactory {

	/**
	 * Singleton instance of EricssonWcdmaRadioUnitReceiverFactory
	 */
	private static volatile EricssonWcdmaRadioUnitReceiverFactory UNIQUE_INSTANCE;

	private EricssonWcdmaRadioUnitReceiverFactory() {
	}

	/**
	 * Static method for getting an instance of the singleton
	 * EricssonWcdmaRadioUnitReceiverFactory
	 * 
	 * Utilizes double-checked locking
	 * 
	 * @return singleton of EricssonWcdmaRadioUnitReceiverFactory
	 */
	public static synchronized EricssonWcdmaRadioUnitReceiverFactory getInstance() {
		if (UNIQUE_INSTANCE == null) {
			synchronized (EricssonWcdmaRadioUnitReceiverFactory.class) {
				if (UNIQUE_INSTANCE == null) {
					UNIQUE_INSTANCE = new EricssonWcdmaRadioUnitReceiverFactory();
				}
			}
		}
		return UNIQUE_INSTANCE;
	}

	@Override
	public RadioUnitReceiver createRadioUnitReceiver() {
		System.out.println("[EricssonWcdmaRadioUnitReceiverFactory] createRadioUnitReceiver");
		return new EricssonWcdmaRadioUnitReceiver();
	}

}
