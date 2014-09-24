package testy.manager;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import testy.model.BokEksemplar;
import testy.model.Laaner;
import testy.model.Utlaan;
import testy.service.FristService;
import testy.util.StoreUtil;

/**
 *
 * @author heidisu
 */
public class UtlaanManager implements IUtlaanManager{

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
  
  public String utlaanBok(Laaner laaner, BokEksemplar bokEksemplar){
    FristService fristService = new FristService();
    Utlaan utlaan = new Utlaan();
    utlaan.setBokEksemplar(bokEksemplar);
    utlaan.setLaaner(laaner);
    StoreUtil.save(utlaan);
    return new SimpleDateFormat().format(utlaan.getForfallsdato());
  }
}
