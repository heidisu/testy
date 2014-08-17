package testy.service;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import testy.model.Laaner;
import testy.model.Utlaan;
import testy.util.MailUtil;

/**
 *
 * @author heidisu
 */
public class UtlaanService {
    MailService mailService;
    
    UtlaanService(MailService mailService){
      this.mailService = mailService;
    }
    
    public void sendForfallspaaminnelse(){
        Map<Laaner, List<Utlaan>> snartForfallende = UtlaanManager.getInstance().getForfallendeUtlaan();
        for(Entry<Laaner, List<Utlaan>> entry : snartForfallende.entrySet()){
            String epost = entry.getKey().getEpost();
            String tittel = MailUtil.getForfallTittel();
            String tekst = MailUtil.getForfallTekst(entry.getValue());
            mailService.sendMail(tittel, tekst, epost);
        }
    }
}
