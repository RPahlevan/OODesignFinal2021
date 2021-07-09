package radiounit;

/**
 * Responsible for the creation of EricssonLteCommandExecutor classes
 * 
 * @author esiumat
 *
 */
public class EricssonLteCommandExecutorFactory implements RadioCommandExecutorFactory {
	
	/**
	 *  Singleton instance of the EricssonLteCommandExecutorFactory class.
	 *  
	 *  "volatile" ensures that the variable "UNIQUE_INSTANCE" is never cached, and is retrieved directly from memory every time
	 */
	private static volatile EricssonLteCommandExecutorFactory UNIQUE_INSTANCE;
	
	private EricssonLteCommandExecutorFactory() { }
	
	/**
	 * Static method for retrieving the singleton object.
	 * The synchronized keyword ensures that the block it surrounds is only accessed by a single thread at a time 
	 * 
	 * Forcing every thread to wait it's turn before it can enter the block via synchronize can add additional
	 * 	overhead.
	 * 
	 * This method employs "double-checked locking" of the UNIQUE_INSTANCE variable, so that we only synchronize on the first time
	 * 	this method is called.
	 * 
	 * @return the singleton instance of the EricssonLteCommandExecutorFactory
	 */
	public static EricssonLteCommandExecutorFactory getInstance()
	{
		if (UNIQUE_INSTANCE == null)
		{
			synchronized (EricssonLteCommandExecutorFactory.class)
			{
				if (UNIQUE_INSTANCE == null)
				{
					System.out.println("[EricssonLteCommandExecutorFactory] creating new receiver factory");
					UNIQUE_INSTANCE = new EricssonLteCommandExecutorFactory();
				}
			}
		}
		
		return UNIQUE_INSTANCE;
	}

	@Override
	public RadioCommandExecutor createRadioCommandExecutor() {
		System.out.println("[EricssonLteCommandExecutorFactory] createRadioCommandExecutor");
		return new EricssonLteCommandExecutor();
	}

}
