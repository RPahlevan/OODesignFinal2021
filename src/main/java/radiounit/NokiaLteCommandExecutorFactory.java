package radiounit;

/**
 * Responsible for the creation of NokiaLteCommandExecutor classes
 * 
 * @author esiumat
 *
 */
public class NokiaLteCommandExecutorFactory implements RadioCommandExecutorFactory {

	/**
	 * Singleton instance of NokiaLteCommandExecutorFactory class
	 */
	private static volatile NokiaLteCommandExecutorFactory UNIQUE_INSTANCE;

	private NokiaLteCommandExecutorFactory() {
	}

	/**
	 * Static method to return singleton NokiaLteCommandExecutorFactory
	 * 
	 * Synchronized protects this method from multithreading issues
	 * 
	 * Utilizes double-checked locking
	 * 
	 * @return singleton of the NokiaLteCommandExecutorFactory
	 */
	public static NokiaLteCommandExecutorFactory getInstance() {
		if (UNIQUE_INSTANCE == null) {
			synchronized (NokiaLteCommandExecutorFactory.class) {
				if (UNIQUE_INSTANCE == null) {
					UNIQUE_INSTANCE = new NokiaLteCommandExecutorFactory();
				}
			}
		}
		return UNIQUE_INSTANCE;
	}

	@Override
	public RadioCommandExecutor createRadioCommandExecutor() {
		System.out.println("[NokiaLteCommandExecutorFactory] createRadioCommandExecutor");
		return new NokiaLteCommandExecutor();
	}

}
