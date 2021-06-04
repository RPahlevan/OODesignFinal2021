package carrierManagementSystem;

/**
 * 
 * This class is a child class to CarrierBuilder. This class build LTECarrier.
 * 
 */

public class LTECarrierBuilder implements CarrierBuilder {
	private Integer CARRIER_ID;
	private RFPorts[] RFPORTS;
	private CarrierFrequencies CARRIER_FREQUENCY;
	private Double TRANSMITTING_POWER;

	/**
	 * This method helps with set up an ID for LTE carrier.
	 * 
	 * @param carrierID set up ID for carrier.
	 */
	@Override
	public void setCarrierId(Integer carrierID) {
		this.CARRIER_ID = carrierID;

	}

	/**
	 * This method helps us with set up rf ports for LTE.
	 * 
	 * @param lte needs 4 rf ports.
	 */
	@Override
	public void setRFPorts(RFPorts[] rfPorts) {

		// catch exception for null rfports.
		if (rfPorts == null) {
			throw new NullPointerException("LTE RF Ports are not configured!");
		}

		// setup rf ports for lte carrier and if number of elements are less than 4
		// it will throw an exception.
		if (rfPorts.length == 4) {
			this.RFPORTS = new RFPorts[4];
			for (int i = 0; i < rfPorts.length; i++) {
				this.RFPORTS[i] = rfPorts[i];
			}
			;
		} else {
			throw new ArrayIndexOutOfBoundsException(
					"Invalid value for the number of LTE prots. Number of RF Ports for LTE carrier has to be 4.");
		}
	}

	/**
	 * This method helps us with set up carrier frequency for LTE.
	 * 
	 * @param carrierFrequencies would configure lte band.
	 */
	@Override
	public void setFrequencyBand(CarrierFrequencies carrierFrequencies) {
		this.CARRIER_FREQUENCY = carrierFrequencies;

	}

	/**
	 * This method helps us with set up transmission power for LTE.
	 * 
	 * @param transmittingPower would set up power.
	 */
	@Override
	public void setTransmittingPower(Double transmittingPower) {
		this.TRANSMITTING_POWER = transmittingPower;

	}

	/**
	 * It will return an object of LTECarrier to CarrierManagementSystemDirector
	 * class.
	 * 
	 * @return LTECarrier object
	 */
	public LTECarrier getLTECarrier() {
		return new LTECarrier(CARRIER_ID, RFPORTS, CARRIER_FREQUENCY, TRANSMITTING_POWER);
	}

}
