package testy.manager;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import testy.model.Bibliotek;
import testy.model.Bok;
import testy.model.BokEksemplar;
import testy.model.Laaner;
import testy.model.Reservasjon;
import testy.model.Utlaan;
import testy.service.BokService;
import testy.service.FristService;
import testy.util.StoreUtil;

/**
 *
 * @author heidisu
 */
public class UtlaanManager implements IUtlaanManager {

  private static UtlaanManager instance;
  private static final BokService bokService = new BokService();

  //no instances
  private UtlaanManager() {

  }

  public static UtlaanManager getInstance() {
    if (instance == null) {
      instance = new UtlaanManager();
    }
    return instance;
  }

  public Map<Laaner, List<Utlaan>> getForfallendeUtlaan() {
    // Finner liste over lånere og deres forfallende utlån.
    // Om det hadde vært database her ville dette typisk vært en sql.
    Date omToDager = new Date(System.currentTimeMillis() + 24 * 60 * 60 * 1000);
    Map<Laaner, List<Utlaan>> forfallendeUtlaan = new HashMap<Laaner, List<Utlaan>>();
    List<Utlaan> utlaaner = StoreUtil.getObjects(Utlaan.class);
    for (Utlaan utlaan : utlaaner) {
      if (utlaan.getLevertDato() == null
          && omToDager.after(utlaan.getForfallsdato())) {
        List<Utlaan> laan = forfallendeUtlaan.get(utlaan.getLaaner());
        if (laan == null) {
          laan = new ArrayList<Utlaan>();
          laan.add(utlaan);
          forfallendeUtlaan.put(utlaan.getLaaner(), laan);
        } else {
          laan.add(utlaan);
        }
      }
    }
    return forfallendeUtlaan;
  }

  public List<Utlaan> getForfalteUtlaan(Bibliotek bibliotek) {
    // finner liste over lån som har forfalt for et gitt bibliotek. 
    // Om det hadde vært database her ville dette typisk vært en sql.
    Date idag = new Date(System.currentTimeMillis());
    List<Utlaan> utlaaner = StoreUtil.getObjects(Utlaan.class);
    List<Utlaan> forfalteUtlaan = new ArrayList<Utlaan>();
    for (Utlaan utlaan : utlaaner) {
      if (utlaan.getLaaner().getBibliotek().equals(bibliotek)
          && utlaan.getLevertDato() == null
          && idag.after(utlaan.getForfallsdato())) {
        forfalteUtlaan.add(utlaan);
      }
    }
    return forfalteUtlaan;
  }

  public String utlaanBok(Laaner laaner, BokEksemplar bokEksemplar) {
    FristService fristService = new FristService();
    if (bokEksemplar.getBok().getReservasjoner().isEmpty()) {
      Utlaan utlaan = new Utlaan();
      utlaan.setBokEksemplar(bokEksemplar);
      utlaan.setLaaner(laaner);
      laaner.addUtlaan(utlaan);
      utlaan.setUtlaansdato(new Date(System.currentTimeMillis()));
      StoreUtil.save(utlaan);
      return new SimpleDateFormat().format(utlaan.getForfallsdato());
    } else {
      return "Kan ikke lånes ut, det finnes reservasjoner";
    }
  }

  public void leverBok(Utlaan utlaan) {
    utlaan.setLevertDato(new Date(System.currentTimeMillis()));
    StoreUtil.save(utlaan);
    bokService.sjekkReservasjoner(utlaan.getBokEksemplar());
  }

  public Reservasjon lagReservasjon(Bok bok, Laaner laaner) {
    Reservasjon reservasjon = new Reservasjon();
    reservasjon.setBok(bok);
    reservasjon.setLaaner(laaner);
    StoreUtil.save(reservasjon);
    return reservasjon;
  }
}
