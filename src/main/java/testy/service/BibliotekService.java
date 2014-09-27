package testy.service;

import java.util.Date;
import java.util.List;
import testy.model.Bibliotek;
import testy.model.Bok;
import testy.model.BokEksemplar;
import testy.model.Laaner;
import testy.model.Reservasjon;
import testy.model.Utlaan;

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
     // ikke implementert enda..
     return null;
   }
}
