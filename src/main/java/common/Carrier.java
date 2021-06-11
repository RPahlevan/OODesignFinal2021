/**
 * 
 */
package common;

import java.util.ArrayList;
import java.util.List;

/**
 * This abstract class defines a Carrier that will have concrete implementations
 * for different RAT types.
 */
public abstract class Carrier {
	protected int carrierId;
	protected List<RfPort> rfPorts;
	protected CarrierFrequency carrierFreq;
	protected double transmittingPower;
	
	public Carrier(int carrierId, List<RfPort> rfPorts, CarrierFrequency freq)
	{
		this.carrierId = carrierId;
		this.carrierFreq = freq;
		
		if (rfPorts == null || rfPorts.isEmpty())
		{
			System.out.println("ERROR: attempting create carrier without RF ports");
		}
		
		this.rfPorts = new ArrayList<>(rfPorts);
	}
	
    /**
     * Returns the carrier id to the builder class.
     *
     * @return The carrier id, as an Integer.
     */
    public int getCarrierId() {
        return carrierId;
    }

    /**
     * Returns a list of RF ports to the builder class.
     *
     * @return The RF ports, as a List.
     */
    public List<RfPort> getRfPorts() {
        return rfPorts;
    }

    /**
     * Returns the carrier frequency band to the builder class.
     *
     * @return The carrier frequency, as a CarrierFrequencies object.
     */
    public CarrierFrequency getCarrierFrequencies() {
        return carrierFreq;
    }

    /**
     * Returns the transmitting power to the builder class.
     *
     * @return The transmission power, as a Double.
     */
    public Double getTransmittingPower() {
        return transmittingPower;
    }
}
