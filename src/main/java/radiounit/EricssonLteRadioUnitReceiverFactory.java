/**
 * 
 */
package radiounit;

/**
 * Is responsible for creating EricssonLteRadioUnitReceivers
 * 
 * @author esiumat
 *
 */
public class EricssonLteRadioUnitReceiverFactory implements RadioUnitReceiverFactory {

	/**
	 *  Singleton instance of the EricssonLteRadioUnitReceiverFactory class.
	 *  
	 *  "volatile" ensures that the variable "UNIQUE_INSTANCE" is never cached, and is retrieved directly from memory every time
	 */
	private static volatile EricssonLteRadioUnitReceiverFactory UNIQUE_INSTANCE;
	
	private EricssonLteRadioUnitReceiverFactory() { }

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
	 * @return the singleton instance of the EricssonLteRadioUnitReceiverFactory
	 */
	public static synchronized EricssonLteRadioUnitReceiverFactory getInstance()
	{
		if (UNIQUE_INSTANCE == null)
		{
			synchronized (EricssonLteRadioUnitReceiverFactory.class)
			{
				if (UNIQUE_INSTANCE == null)
				{
					System.out.println("[EricssonLteRadioUnitReceiverFactory] creating new receiver factory");
					UNIQUE_INSTANCE = new EricssonLteRadioUnitReceiverFactory();
				}
			}
		}
		
		return UNIQUE_INSTANCE;
	}

	@Override
	public RadioUnitReceiver createRadioUnitReceiver() {
		System.out.println("[EricssonLteRadioUnitReceiverFactory] createRadioUnitReceiver");
		return new EricssonLteRadioUnitReceiver();
	}

}
