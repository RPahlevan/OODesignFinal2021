package radiounit;

import common.Carrier;
import java.util.List;

/**
 * Interface for RadioUnitReceiver classes
 * 
 * @author esiumat
 *
 */
@FunctionalInterface
public interface RadioUnitReceiver {

	List<Carrier> getCarriers();

}
