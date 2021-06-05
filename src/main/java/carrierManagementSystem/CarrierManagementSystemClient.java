package carrierManagementSystem;

/**
 * Client class. This class would perform the unit tests 
 * for the carrier management system.
 */
public class CarrierManagementSystemClient {

    public static void main(String[] args) {

        CarrierManagementSystemDirector director = new CarrierManagementSystemDirector();

		/*
		 * Below lines of code would show how to create LTE carrier with carrier id, RF
		 * ports, LTE band and transmission power.
		 * 
		 * Please note that LTE carrier needs 4 RF ports.
		 */
		Integer carrierId = 2;
		RFPorts[] LTErfPorts = { RFPorts.RF_3, RFPorts.RF_4, RFPorts.RF_5, RFPorts.RF_6 };
		CarrierFrequencies LTEBand_3 = CarrierFrequencies.LTE_BAND_3;
		Double transmittingPower = 12.2;
		director.CreateLTECarrier(carrierId, LTErfPorts, LTEBand_3, transmittingPower);

		/**
		 * Below lines of code would show how to create WCDMA carrier with carrier id,
		 * RF ports, WCDMA band and transmission power.
		 * 
		 * Please note that WCDMA carrier needs 2 RF ports.
		 */
		carrierId = 3;
		RFPorts[] WCDMArfPorts = { RFPorts.RF_1, RFPorts.RF_2 };
		CarrierFrequencies WCDMABand_5 = CarrierFrequencies.WCDMA_BAND_5;
		transmittingPower = 11.7;
		director.CreateWCDMACarrier(carrierId, WCDMArfPorts, WCDMABand_5, transmittingPower);
	}

}
