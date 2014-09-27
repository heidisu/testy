package testy.service;

import java.util.List;
import testy.model.Bok;
import testy.model.BokEksemplar;
import testy.model.Laaner;
import testy.model.Reservasjon;
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
      eldsteReservasjon.setBokEksemplar(bokEksemplar);
      StoreUtil.save(bokEksemplar);
       
      if(bokEksemplar.getEier().equals(eldsteReservasjon.getLaaner().getBibliotek())){
        bibliotekService.varsleBibliotek("holdAv", eldsteReservasjon);   
        new MailService().sendMail(MailUtil.getReservasjonTittel(),
            MailUtil.getReservasjonTekst(eldsteReservasjon), eldsteReservasjon.getLaaner().getEpost());
      }
      else{
        bibliotekService.varsleBibliotek("sendVidere", eldsteReservasjon);
      }
    }
  }
  
  public void bestillBok(Bok bok, Laaner laaner){
    
  }
}
