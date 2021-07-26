package radiounit;

/**
 * Responsible for the creation of NokiaWcdmaCommandExecutor classes
 * 
 * @author esiumat
 *
 */
public class NokiaWcdmaCommandExecutorFactory implements RadioCommandExecutorFactory {

	/**
	 * Singleton instance of NokiaWcdmaCommandExecutorFactory class
	 */
	private static volatile NokiaWcdmaCommandExecutorFactory UNIQUE_INSTANCE;

	private NokiaWcdmaCommandExecutorFactory() {
	}

	/**
	 * Static method to return singleton NokiaWcdmaCommandExecutorFactory
	 * 
	 * Synchronized protects this method from multithreading issues
	 * 
	 * Utilizes double-checked locking
	 * 
	 * @return singleton of the NokiaWcdmaCommandExecutorFactory
	 */
	public static NokiaWcdmaCommandExecutorFactory getInstance() {
		if (UNIQUE_INSTANCE == null) {
			synchronized (NokiaWcdmaCommandExecutorFactory.class) {
				if (UNIQUE_INSTANCE == null) {
					UNIQUE_INSTANCE = new NokiaWcdmaCommandExecutorFactory();
				}
			}
		}
		return UNIQUE_INSTANCE;
	}

	@Override
	public RadioCommandExecutor createRadioCommandExecutor() {
		System.out.println("[NokiaWcdmaCommandExecutorFactory] createRadioCommandExecutor");
		return new NokiaWcdmaCommandExecutor();
	}

}
