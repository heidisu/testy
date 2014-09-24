package testy.service;

import java.util.Date;
import testy.model.Utlaan;

/**
 *
 * @author heidisu
 */
public class FristService {
   public Date beregnForfallsdato(Utlaan utlaan){
     int antallFornyelser = utlaan.getAntallFornyelser();
     if(antallFornyelser == 0){
       return new Date(utlaan.getUtlaansdato().getTime() + 30 * 24 * 60 * 60 * 1000);
     }
     else if(antallFornyelser >=1 && antallFornyelser < 3){
       return new Date(utlaan.getUtlaansdato().getTime() + 30 * 24 * 60 * 60 * 1000 +
           antallFornyelser * 20 * 24 * 60 * 60 *1000);
     }
     else if(antallFornyelser > 3){
       return new Date(utlaan.getUtlaansdato().getTime() + 30 * 24 * 60 * 60 * 1000 +
           3 * 20 * 24 * 60 * 60 *1000);
     }
     return null;
   }
}
