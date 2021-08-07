package radiounit;

import common.Carrier;
import common.FrequencyBand;
import java.util.List;

/**
 * Interface for radio unit executor classes
 * 
 * @author esiumat
 *
 */
public interface RadioCommandExecutor {

	void setup();

	void activate();

	void deactivate();

	void release();

	void setupCarrier(Carrier carrier);

	void signalScaling();

	void modifyCarrier(Integer carrierId, FrequencyBand frequencyBand);

	void removeCarrier(Integer carrierId);

	void selfDiagnostics();

	void removeAllCarriers();

	void postActivation();

	List<Carrier> getCarriers();

}
