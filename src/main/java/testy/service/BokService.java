package testy.service;

import java.util.List;
import testy.model.Bok;
import testy.model.BokEksemplar;
import testy.model.Laaner;
import testy.model.Reservasjon;
import testy.model.Utlaan;
import testy.util.MailUtil;
import testy.util.StoreUtil;

/**
 *
 * @author heidisu
 */
public class BokService {
  private static final BibliotekService bibliotekService = new BibliotekService();
  
  public void sjekkReservasjoner(BokEksemplar bokEksemplar){
    List<Reservasjon> reservasjoner = bokEksemplar.getBok().getReservasjoner();
    if(!reservasjoner.isEmpty()){
      Reservasjon eldsteReservasjon = null;
      for(Reservasjon reservasjon : reservasjoner){
        if(eldsteReservasjon == null || 
            reservasjon.getReservasjonsdato().before(eldsteReservasjon.getReservasjonsdato())){
          eldsteReservasjon = reservasjon;
        }
      }       
      if(bokEksemplar.getEier().equals(eldsteReservasjon.getLaaner().getBibliotek())){
        bibliotekService.varsleBibliotek("holdAv", bokEksemplar, eldsteReservasjon.getLaaner());   
        new MailService().sendMail(MailUtil.getReservasjonTittel(),
            MailUtil.getReservasjonTekst(eldsteReservasjon), eldsteReservasjon.getLaaner().getEpost());
      }
      else{
        bibliotekService.varsleBibliotek("sendVidere", bokEksemplar, eldsteReservasjon.getLaaner());
      }
      StoreUtil.remove(eldsteReservasjon);
    }
  }
  
  public void bestillBok(Bok bok, Laaner laaner){
    BokEksemplar bokEksemplar = bibliotekService.getLedigBokeksemplar(laaner.getBibliotek(), bok);
    if(bokEksemplar != null){
      bibliotekService.varsleBibliotek("holdAv", bokEksemplar, laaner);
    }
    else{
      bokEksemplar = finnLedigBokEksemplar(bok);
      if(bokEksemplar == null){
        Reservasjon reservasjon = new Reservasjon();
        reservasjon.setBok(bok);
        reservasjon.setLaaner(laaner);
        bok.addReservasjon(reservasjon);
        StoreUtil.save(bok);
        StoreUtil.save(reservasjon);
      }
      else{
        bibliotekService.varsleBibliotek("sendVidere", bokEksemplar, laaner);
      }
    }
  }
  
  public BokEksemplar finnLedigBokEksemplar(Bok bok) {
    List<BokEksemplar> bokEksemplarer = StoreUtil.getObjects(BokEksemplar.class);
    List<Utlaan> utlaaner = StoreUtil.getObjects(Utlaan.class);
    for (BokEksemplar bokEksemplar : bokEksemplarer) {
      boolean utlaant = false;
      for (Utlaan utlaan : utlaaner) {
        if (utlaan.getBokEksemplar().equals(bokEksemplar)) {
          utlaant = true;
        }
      }
      if (!utlaant && bokEksemplar.getBok().equals(bok)) {
        return bokEksemplar;
      }
    }
    return null;
  }
}
