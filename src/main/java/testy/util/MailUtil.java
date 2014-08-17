package testy.util;

import java.util.List;
import testy.model.Utlaan;

/**
 *
 * @author heidisu
 */
public class MailUtil {
  public static String getForfallTekst(List<Utlaan> utlaan){
    String text = "Følgende lån forfaller snart\n";
    for(Utlaan item : utlaan){
      text += item.getBokEksemplar().getBok().getTittel() 
          + ", forfallsdato " 
          + item.getForfallsdato()
          + "\n";
    }
    return text;
  }
  
  public static String getForfallTittel(){
    return "Påminnelse om forfallende lån";
  }
}
