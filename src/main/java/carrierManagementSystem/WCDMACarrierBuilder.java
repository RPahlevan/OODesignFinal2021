package carrierManagementSystem;

/**
 * 
 * This class is a child class to CarrierBuilder. This class build WCDMACarrier.
 * 
 */
public class WCDMACarrierBuilder implements CarrierBuilder {

	private Integer CARRIER_ID;
	private RFPorts[] RFPORTS;
	private CarrierFrequencies CARRIER_FREQUENCY;
	private Double TRANSMITTING_POWER;

	/**
	 * This method helps with set up an ID for WCDMA carrier.
	 * 
	 * @param carrierID set up ID for carrier.
	 */
	@Override
	public void setCarrierId(Integer carrierID) {
		this.CARRIER_ID = carrierID;

	}

	/**
	 * This method helps us with set up rf ports for WCDMA.
	 * 
	 * @param WCDMA needs 2 rf ports.
	 */
	@Override
	public void setRFPorts(RFPorts[] rfPorts) {
		// catch exception for null rfports.
		if (rfPorts == null) {
			throw new NullPointerException("WCDMA RF Ports are not configured!");
		}

		// setup rf ports for lte carrier and if number of elements are less than 2
		// it will throw an exception.
		if (rfPorts.length == 2) {
			this.RFPORTS = new RFPorts[2];
			for (int i = 0; i < rfPorts.length; i++) {
				this.RFPORTS[i] = rfPorts[i];
			}
			;
		} else {
			throw new ArrayIndexOutOfBoundsException(
					"Invalid value for the number of WCDMA prots. Number of RF Ports for WCDMA carrier has to be 2.");
		}
	}

	/**
	 * This method helps us with set up carrier frequency for WCDMA.
	 * 
	 * @param carrierFrequencies would configure WCDMA band.
	 */
	@Override
	public void setFrequencyBand(CarrierFrequencies carrierFrequencies) {
		this.CARRIER_FREQUENCY = carrierFrequencies;

	}

	/**
	 * This method helps us with set up transmission power for WCDMA.
	 * 
	 * @param transmittingPower would set up power.
	 */
	@Override
	public void setTransmittingPower(Double transmittingPower) {
		this.TRANSMITTING_POWER = transmittingPower;

	}

	/**
	 * It will return an object of WCDMACarrier to CarrierManagementSystemDirector
	 * class.
	 * 
	 * @return WCDMACarrier object
	 */
	public WCDMACarrier getWCDMACarrier() {
		return new WCDMACarrier(CARRIER_ID, RFPORTS, CARRIER_FREQUENCY, TRANSMITTING_POWER);
	}
}
