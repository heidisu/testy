package testy.manager;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import testy.model.BokEksemplar;
import testy.model.Laaner;
import testy.model.Utlaan;
import testy.service.BokService;
import testy.service.FristService;
import testy.util.StoreUtil;

/**
 *
 * @author heidisu
 */
public class UtlaanManager implements IUtlaanManager{

  private static UtlaanManager instance;
  private static final BokService bokService = new BokService();
  
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
    // finner liste over lånere og deres forfallende utlån i databasen
    return new HashMap<Laaner, List<Utlaan>>();
  }
  
  public String utlaanBok(Laaner laaner, BokEksemplar bokEksemplar){
    FristService fristService = new FristService();
    if(bokEksemplar.getBok().getReservasjoner().isEmpty()){
    Utlaan utlaan = new Utlaan();
    utlaan.setBokEksemplar(bokEksemplar);
    utlaan.setLaaner(laaner);
    laaner.addUtlaan(utlaan);
    utlaan.setUtlaansdato(new Date(System.currentTimeMillis()));
    StoreUtil.save(utlaan);
    return new SimpleDateFormat().format(utlaan.getForfallsdato());
    }
    else{
      return "Kan ikke lånes ut, det finnes reservasjoner";
    }
  }
  
  public void leverBok(Utlaan utlaan){
    utlaan.setLevertDato(new Date(System.currentTimeMillis()));
    StoreUtil.save(utlaan);
    bokService.sjekkReservasjoner(utlaan.getBokEksemplar());
  }
}
