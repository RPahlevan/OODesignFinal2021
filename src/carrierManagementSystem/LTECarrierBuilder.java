package carrierManagementSystem;
/**
 * 
 * @author epahram
 *	
 */

public class LTECarrierBuilder implements CarrierBuilder {
	private Integer carrierId;
	private RFPorts[] rfPorts;
	private CarrierFrequencies carrierFrequencies;
	private Double transmittingPower;

	@Override
	public void setCarrierId(Integer carrierID) {
		this.carrierId = carrierID;

	}

	@Override
	public void setRFPorts(RFPorts[] rfPorts) {

		if (rfPorts == null) {
			throw new NullPointerException("LTE RF Ports are not configured!");
		}

		if (rfPorts.length == 4) {
			this.rfPorts = new RFPorts[4];
			for (int i = 0; i < rfPorts.length; i++) {
				this.rfPorts[i] = rfPorts[i];
			}
			;
		} else {
			throw new ArrayIndexOutOfBoundsException(
					"Invalid value for the number of LTE prots. Number of RF Ports for LTE carrier has to be 4.");
		}
	}

	@Override
	public void setFrequencyBand(CarrierFrequencies carrierFrequencies) {
		this.carrierFrequencies = carrierFrequencies;

	}

	@Override
	public void setTransmittingPower(Double transmittingPower) {
		this.transmittingPower = transmittingPower;

	}

	public LTECarrier getLTECarrier() {
		return new LTECarrier(carrierId, rfPorts, carrierFrequencies, transmittingPower);
	}

}
