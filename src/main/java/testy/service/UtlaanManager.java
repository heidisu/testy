package testy.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import testy.model.Laaner;
import testy.model.Utlaan;

/**
 *
 * @author heidisu
 */
public class UtlaanManager {

  private static UtlaanManager instance;
  
  //no instances
  private UtlaanManager(){
    
  }
 
  public static UtlaanManager getInstance(){
    if(instance == null){
      instance = new UtlaanManager();
    }
    return instance;
  }
  
  public Map<Laaner, List<Utlaan>> getForfallendeUtlaan(){
    // 
    return new HashMap<Laaner, List<Utlaan>>();
  }
}
