package testy.manager;

import java.util.List;
import java.util.Map;
import testy.model.Laaner;
import testy.model.Utlaan;

/**
 *
 * @author heidisu
 */
public interface IUtlaanManager {
  public Map<Laaner, List<Utlaan>> getForfallendeUtlaan();
}
