package testy.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import testy.model.Bibliotek;
import testy.model.Bok;
import testy.model.BokEksemplar;
import testy.model.Laaner;
import testy.model.Utlaan;
import testy.util.StoreUtil;

/**
 *
 * @author heidisu
 */
public class BibliotekService {
  private static final MailService mailService = new MailService();
 
  public void sendArrangementMail(Bibliotek bibliotek, 
      String maalgruppe, String tittel, String tekst){
    List<Laaner>laanere = bibliotek.getLaanere();
    for(Laaner laaner : laanere){
    switch(maalgruppe){
      case "alle": mailService.sendMail(tittel, tekst, laaner.getEpost());
      case "pensjonist": //if(laaner.getFoedselsdato()
      case "barn":
    }
    }
  }
  
  // oppgave legge til ny mailtype
   public void sendMailTilAlleLaanere(Bibliotek bibliotek, String mailtype, String text){
     List<Laaner>laanere = bibliotek.getLaanere();
     for(Laaner laaner : laanere){ // purring, arrangement, tilbud, nyebøker
       switch(mailtype){
         case "purring":
           // hvis pensjonist
           if(laaner.getType().equals("pensjonist")){
             List<Utlaan> utlaaner = laaner.getUtlaan();
             for(Utlaan utlaan : utlaaner){
           if(utlaan.getForfallsdato().equals(new Date(System.currentTimeMillis())) 
               && utlaan.getBokEksemplar().getEier().equals(bibliotek)){
             if(utlaan.getAntallFornyelser() == 0){
               new UtlaanService(new MailService()).fornyUtlaan(utlaan);
             }
             else{
               new MailService().sendMail("Purring", text, laaner.getEpost());
             }
           }
             }
           }
         default: 
       }
     }
   }
   
   public void varsleBibliotek(String varselType, BokEksemplar bokEksemplar, Laaner laaner){
     // ikke implementert enda..
   }
   
   public BokEksemplar getLedigBokeksemplar(Bibliotek bibliotek, Bok bok){
     List<BokEksemplar> bokEksemplarer = StoreUtil.getObjects(BokEksemplar.class);
     List< BokEksemplar> eksemplarer = new ArrayList<BokEksemplar>();
     for(BokEksemplar eksemplar : bokEksemplarer){
       if(eksemplar.getBok().equals(bok) && eksemplar.getEier().equals(bibliotek)){
         eksemplarer.add(eksemplar);
       }
     }
     if(eksemplarer.isEmpty()){
       return null;
     }
     List<Utlaan> utlaaner = StoreUtil.getObjects(Utlaan.class);
     for(Utlaan utlaan : utlaaner){
       if(eksemplarer.contains(utlaan.getBokEksemplar())){
         eksemplarer.remove(utlaan.getBokEksemplar());
       }
     }
     return eksemplarer.isEmpty() ? null : eksemplarer.iterator().next();
   }
}
