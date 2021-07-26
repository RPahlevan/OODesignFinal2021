package radiounit;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import common.*;
import carriermanagementsystem.*;

/**
 * @author edavleu
 *
 */
public class ManagedRadioUnitClient {

	/**
	 * @param args The command-line arguments passed during initialization
	 */
	public static void main(String[] args) {

		System.out.println("\n **********************");
		System.out.println("[ManagedRadioUnitClient] Creating test subject");
		System.out.println(" **********************\n");
		
//		RadioCommandExecutor executor = EricssonLteCommandExecutorFactory
//				.getInstance()
//				.createRadioCommandExecutor();
		
//		RadioCommandExecutor executor = EricssonWcdmaCommandExecutorFactory
//				.getInstance()
//				.createRadioCommandExecutor();
		
		RadioCommandExecutor executor = NokiaLteCommandExecutorFactory
				.getInstance()
				.createRadioCommandExecutor();
		
//		RadioCommandExecutor executor = NokiaWcdmaCommandExecutorFactory
//				.getInstance()
//				.createRadioCommandExecutor();
		
		List<RfPort> rfPorts = Stream.of(RfPort.RF_0, RfPort.RF_1, RfPort.RF_2, RfPort.RF_3)
				.collect(Collectors.toList());
		
		List<Carrier> carriers = Stream.of(
				new LteCarrier(0, rfPorts, LteFrequencyBand.LTE_BAND_1, 10.0),
				new LteCarrier(1, rfPorts, LteFrequencyBand.LTE_BAND_2, 10.0),
				new LteCarrier(2, rfPorts, LteFrequencyBand.LTE_BAND_3, 10.0),
				new LteCarrier(3, rfPorts, LteFrequencyBand.LTE_BAND_4, 10.0)
		).collect(Collectors.toList());
		
		List<Carrier> badCarriers = Stream.of(
				new WcdmaCarrier(4, rfPorts, WcdmaFrequencyBand.WCDMA_BAND_1, 10.0),
				new WcdmaCarrier(5, rfPorts, WcdmaFrequencyBand.WCDMA_BAND_2, 10.0),
				new WcdmaCarrier(6, rfPorts, WcdmaFrequencyBand.WCDMA_BAND_3, 10.0),
				new WcdmaCarrier(7, rfPorts, WcdmaFrequencyBand.WCDMA_BAND_4, 10.0)
		).collect(Collectors.toList());
		
//		List<Carrier> carriers = Stream.of(
//				new WcdmaCarrier(0, rfPorts, WcdmaFrequencyBand.WCDMA_BAND_1, 10.0),
//				new WcdmaCarrier(1, rfPorts, WcdmaFrequencyBand.WCDMA_BAND_2, 10.0),
//				new WcdmaCarrier(2, rfPorts, WcdmaFrequencyBand.WCDMA_BAND_3, 10.0),
//				new WcdmaCarrier(3, rfPorts, WcdmaFrequencyBand.WCDMA_BAND_4, 10.0)
//		).collect(Collectors.toList());
//		
//		List<Carrier> badCarriers = Stream.of(
//				new LteCarrier(4, rfPorts, LteFrequencyBand.LTE_BAND_1, 10.0),
//				new LteCarrier(5, rfPorts, LteFrequencyBand.LTE_BAND_2, 10.0),
//				new LteCarrier(6, rfPorts, LteFrequencyBand.LTE_BAND_3, 10.0),
//				new LteCarrier(7, rfPorts, LteFrequencyBand.LTE_BAND_4, 10.0)
//		).collect(Collectors.toList());

		System.out.println("\n **********************");
		System.out.println("[ManagedRadioUnitClient] Test Activate");
		System.out.println(" **********************\n");
		
		executor.activate();

		System.out.println("\n **********************");
		System.out.println("[ManagedRadioUnitClient] Test Signal Scaling");
		System.out.println(" **********************\n");
		
		executor.signalScaling();

		System.out.println("\n **********************");
		System.out.println("[ManagedRadioUnitClient] Test Self Diagnostics");
		System.out.println(" **********************\n");
		
		executor.selfDiagnostics();

		System.out.println("\n **********************");
		System.out.println("[ManagedRadioUnitClient] Test adding carriers");
		System.out.println(" **********************\n");
		
		for (Carrier c : carriers)
		{
			executor.setupCarrier(c);
		}

		System.out.println("\n **********************");
		System.out.println("[ManagedRadioUnitClient] Test attempting to add WCDMA carriers to LTE radio");
		System.out.println(" **********************\n");
		
		for (Carrier c : badCarriers)
		{
			executor.setupCarrier(c);
		}

		System.out.println("\n **********************");
		System.out.println("[ManagedRadioUnitClient] Test add a carrier with ID that already exists");
		System.out.println(" **********************\n");
		
		executor.setupCarrier(carriers.get(0));

		System.out.println("\n **********************");
		System.out.println("[ManagedRadioUnitClient] Test retrieving all carriers after adding");
		System.out.println(" **********************\n");
		
		executor.getCarriers();

		System.out.println("\n **********************");
		System.out.println("[ManagedRadioUnitClient] Test modifying existing carrier");
		System.out.println(" **********************\n");

		executor.modifyCarrier(3, LteFrequencyBand.LTE_BAND_6);
//		executor.modifyCarrier(3, WcdmaFrequencyBand.WCDMA_BAND_6);

		System.out.println("\n **********************");
		System.out.println("[ManagedRadioUnitClient] Test attempting to modify carrier that doesn't exist");
		System.out.println(" **********************\n");
		
		executor.modifyCarrier(4, LteFrequencyBand.LTE_BAND_6);
//		executor.modifyCarrier(4, WcdmaFrequencyBand.WCDMA_BAND_6);

		System.out.println("\n **********************");
		System.out.println("[ManagedRadioUnitClient] Test retrieving all carriers after modifying");
		System.out.println(" **********************\n");
		
		executor.getCarriers();

		System.out.println("\n **********************");
		System.out.println("[ManagedRadioUnitClient] Test removing a single carrier");
		System.out.println(" **********************\n");
		
		executor.removeCarrier(2);

		System.out.println("\n **********************");
		System.out.println("[ManagedRadioUnitClient] Test attempting to remove carrier that doesn't exist");
		System.out.println(" **********************\n");
		
		executor.removeCarrier(5);

		System.out.println("\n **********************");
		System.out.println("[ManagedRadioUnitClient] Test retrieving all carriers after modifying");
		System.out.println(" **********************\n");
		
		executor.getCarriers();

		System.out.println("\n **********************");
		System.out.println("[ManagedRadioUnitClient] Test removing all carriers");
		System.out.println(" **********************\n");
		
		executor.removeAllCarriers();

		System.out.println("\n **********************");
		System.out.println("[ManagedRadioUnitClient] Test retrieving all carriers after removing all");
		System.out.println(" **********************\n");
		
		executor.getCarriers();

		System.out.println("\n **********************");
		System.out.println("[ManagedRadioUnitClient] Test deactivating radio");
		System.out.println(" **********************\n");
		
		executor.deactivate();

		System.out.println("\n **********************");
		System.out.println("[ManagedRadioUnitClient] Test releasing radio");
		System.out.println(" **********************\n");
		
		executor.release();
	}

}
