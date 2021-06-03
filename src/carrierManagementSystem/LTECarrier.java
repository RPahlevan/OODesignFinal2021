package carrierManagementSystem;

public class LTECarrier {

	private Integer carrierId;
	private RFPorts[] rfPorts;
	private CarrierFrequencies carrierFrequencies;
	private Double transmittingPower;

	public LTECarrier(Integer carrierId, RFPorts[] rfPorts, CarrierFrequencies carrierFrequencies,
			Double transmittingPower) {
		this.carrierId = carrierId;
		this.carrierFrequencies = carrierFrequencies;
		this.transmittingPower = transmittingPower;
		if (rfPorts != null) {
			this.rfPorts = new RFPorts[4];
			for (int i = 0; i < rfPorts.length; i++) {
				this.rfPorts[i] = rfPorts[i];
			}
			;
		}
	}

	public Integer getCarrierId() {
		return carrierId;
	}

	public RFPorts[] getRfPorts() {
		return rfPorts;
	}

	public CarrierFrequencies getCarrierFrequencies() {
		return carrierFrequencies;
	}

	public Double getTransmittingPower() {
		return transmittingPower;
	}

	public String print() {
		String info = "";

		info += "LTE Carrier ID:  ";
		if (carrierId != null) {
			info += carrierId + "\n";
		} else {
			info += "not configured \n";
		}

		info += "LTE Carrier Frequency: ";
		if (carrierFrequencies != null) {
			info += carrierFrequencies.getBand() + "\n";
		} else {
			info += "not configured \n";
		}

		info += "LTE RF Ports: ";
		if (rfPorts != null) {
			for (int i = 0; i < rfPorts.length; i++) {
				info += rfPorts[i].getRFPort() + " ";
			}
			;
		} else {
			info += "not configured";
		}

		info += "\nLTE Transmission Power: ";
		if (transmittingPower != null) {
			info += transmittingPower + "\n";
		} else {
			info += "not configured\n";
		}

		return info;
	}
}
