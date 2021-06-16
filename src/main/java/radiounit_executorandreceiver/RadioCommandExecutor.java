package radiounit_executorandreceiver;

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

	public void setup();

	public void activate();

	public void deactivate();

	public void release();

	public void setupCarrier(Carrier carrrier);

	public void signalScaling();

	public void modifyCarrier(Integer carrierId, FrequencyBand frequencyBand);

	public void removeCarrier(Integer carrierId);

	public void selfDiagnostics();

	public void removeAllCharacters();

	public List<Carrier> getCarriers();

}
