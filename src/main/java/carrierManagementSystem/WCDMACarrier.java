package carrierManagementSystem;

/**
 *
 * This class build WCDMA carrier carrier. WCDMACarrierBuilder class use this
 * class to build an object. Each private data member inside this class has
 * getter method which helps builder class to build its object by using them.
 * 
 */
public class WCDMACarrier {
	private Integer CARRIER_ID;
	private RFPorts[] RFPORTS;
	private CarrierFrequencies CARRIER_FREQUENCY;
	private Double TRANSMITTING_POWER;

	public WCDMACarrier(Integer carrierId, RFPorts[] rfPorts, CarrierFrequencies carrierFrequencies,
			Double transmittingPower) {
		this.CARRIER_ID = carrierId;
		this.CARRIER_FREQUENCY = carrierFrequencies;
		this.TRANSMITTING_POWER = transmittingPower;
		this.RFPORTS = new RFPorts[2];
		for (int i = 0; i < rfPorts.length; i++) {
			this.RFPORTS[i] = rfPorts[i];
		}
		;
	}

	/**
	 * This method return carrier id to the builder class.
	 * 
	 * @return carrier id
	 */
	public Integer getCarrierId() {
		return CARRIER_ID;
	}

	/**
	 * This method return array of rf ports to the builder class.
	 * 
	 * @return all rf ports.
	 */
	public RFPorts[] getRfPorts() {
		return RFPORTS;
	}

	/**
	 * This method return carrier frequency band to the builder class.
	 * 
	 * @return carrier frequency
	 */
	public CarrierFrequencies getCarrierFrequencies() {
		return CARRIER_FREQUENCY;
	}

	/**
	 * This method return transmitting power to the builder class.
	 * 
	 * @return transmission power
	 */
	public Double getTransmittingPower() {
		return TRANSMITTING_POWER;
	}

	/**
	 * It will print all elements of the created object.
	 * 
	 * @return object information.
	 */
	public String print() {
		String info = "";

		info += "WCDMA Carrier ID:  ";
		if (CARRIER_ID != null) {
			info += CARRIER_ID + "\n";
		} else {
			info += "not configured \n";
		}

		info += "WCDMA Carrier Frequency: ";
		if (CARRIER_FREQUENCY != null) {
			info += CARRIER_FREQUENCY.getBand() + "\n";
		} else {
			info += "not configured \n";
		}

		info += "WCDMA RF Ports: ";
		if (RFPORTS != null) {
			for (int i = 0; i < RFPORTS.length; i++) {
				info += RFPORTS[i].getRFPort() + " ";
			}
			;
		} else {
			info += "not configured";
		}

		info += "\nWCDMA Transmission Power: ";
		if (TRANSMITTING_POWER != null) {
			info += TRANSMITTING_POWER + "\n";
		} else {
			info += "not configured\n";
		}

		return info;
	}
}
