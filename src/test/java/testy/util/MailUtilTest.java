package testy.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.testng.Assert.assertEquals;
import org.testng.annotations.Test;
import testy.model.Bok;
import testy.model.BokEksemplar;
import testy.model.Utlaan;

/**
 *
 * @author heidisu
 */
@Test
public class MailUtilTest {

  public void testGetForfallTekst(){
    Utlaan utlaan = new Utlaan();
    BokEksemplar bokeksemplar = new BokEksemplar();
    Bok bok = new Bok();
    bok.setTittel("Boktittel");
    bokeksemplar.setBok(bok);
    utlaan.setBokEksemplar(bokeksemplar);
    utlaan.setUtlaansdato(new Date(System.currentTimeMillis()));
    List<Utlaan> utlaanListe = new ArrayList<Utlaan>();
    utlaanListe.add(utlaan);
    String expected = "Følgende lån forfaller snart\n" 
        + bok.getTittel() 
        + ", forfallsdato " 
        + utlaan.getForfallsdato()
        + "\n";
    assertEquals(MailUtil.getForfallTekst(utlaanListe), expected );
  }
}
