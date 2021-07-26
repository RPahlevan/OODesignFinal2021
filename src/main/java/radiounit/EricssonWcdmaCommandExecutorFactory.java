package radiounit;

/**
 * Responsible for the creation of EricssonWcdmaCommandExecutor classes
 * 
 * @author esiumat
 *
 */
public class EricssonWcdmaCommandExecutorFactory implements RadioCommandExecutorFactory {

	/**
	 * Singleton instance of EricssonWcdmaCommandExecutorFactory class
	 */
	private static volatile EricssonWcdmaCommandExecutorFactory UNIQUE_INSTANCE;

	private EricssonWcdmaCommandExecutorFactory() {
	}

	/**
	 * Static method to return singleton EricssonWcdmaCommandExecutorFactory
	 * 
	 * Synchronized protects this method from multithreading issues
	 * 
	 * Utilizes double-checked locking
	 * 
	 * @return singleton of the EricssonWcdmaCommandExecutorFactory
	 */
	public static EricssonWcdmaCommandExecutorFactory getInstance() {
		if (UNIQUE_INSTANCE == null) {
			synchronized (EricssonWcdmaCommandExecutorFactory.class) {
				if (UNIQUE_INSTANCE == null) {
					UNIQUE_INSTANCE = new EricssonWcdmaCommandExecutorFactory();
				}
			}
		}
		return UNIQUE_INSTANCE;
	}

	@Override
	public RadioCommandExecutor createRadioCommandExecutor() {
		System.out.println("[EricssonWcdmaCommandExecutorFactory] createRadioCommandExecutor");
		return new EricssonWcdmaCommandExecutor();
	}

}
