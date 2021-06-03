package carrierManagementSystem;

public class WCDMACarrierBuilder implements CarrierBuilder {

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
			throw new NullPointerException("WCDMA RF Ports are not configured!");
		}

		if (rfPorts.length == 2) {
			this.rfPorts = new RFPorts[2];
			for (int i = 0; i < rfPorts.length; i++) {
				this.rfPorts[i] = rfPorts[i];
			}
			;
		} else {
			throw new ArrayIndexOutOfBoundsException(
					"Invalid value for the number of WCDMA prots. Number of RF Ports for WCDMA carrier has to be 2.");
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

	public WCDMACarrier getWCDMACarrier() {
		return new WCDMACarrier(carrierId, rfPorts, carrierFrequencies, transmittingPower);
	}
}
