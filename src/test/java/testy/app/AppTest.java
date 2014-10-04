package testy.app;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import testy.manager.UtlaanManager;
import testy.model.*;
import testy.service.BibliotekService;
import testy.service.BokService;
import testy.service.FristService;
import testy.service.LaanerService;
import testy.service.MailService;
import testy.service.UtlaanService;
import testy.service.UtlaanServiceTest;
import testy.util.StoreUtil;

/**
 * @author Michael Gfeller
 */
@Test
public class AppTest {

  private Laaner heidi;
  private Laaner michael;
  private Bok politi;
  private Bibliotek oslo;

  @BeforeMethod(alwaysRun = true)
  public void setUp() {
    Bibliotek lysaker = new Bibliotek();
    oslo = new Bibliotek();
    StoreUtil.save(lysaker);
    StoreUtil.save(oslo);

    michael = new Laaner();
    michael.setBibliotek(lysaker);
    lysaker.addLaaner(michael);
    michael.setFornavn("Michael");
    michael.setType("normal");
    michael.setId("123");
    michael.setEpost("");
    StoreUtil.save(michael);

    heidi = new Laaner();
    heidi.setFornavn("Heidi");
    heidi.setBibliotek(oslo);
    heidi.setId("124");
    heidi.setEpost("");
    Calendar kalender = new GregorianCalendar();
    kalender.set(Calendar.YEAR, 1980);
    kalender.set(Calendar.MONTH, 10);
    kalender.set(Calendar.DAY_OF_MONTH, 23);
    heidi.setFoedselsdato(new Date(kalender.getTimeInMillis()));
    oslo.addLaaner(heidi);
    StoreUtil.save(heidi);

    Utgiver aschehoug = new Utgiver();
    StoreUtil.save(aschehoug);

    Forfatter nesboe = new Forfatter();
    StoreUtil.save(nesboe);

    politi = new Bok();
    politi.setForfatter(nesboe);
    politi.setTittel("Politi");
    StoreUtil.save(politi);

    BokEksemplar politiExemplar = new BokEksemplar();
    politiExemplar.setBok(politi);
    politiExemplar.setEier(lysaker);
    politiExemplar.setUtgiver(aschehoug);
    politiExemplar.setHylleplassering("a14");
    StoreUtil.save(politiExemplar);

    UtlaanManager.getInstance().utlaanBok(michael, politiExemplar);

    List<Utlaan> utlaanList = michael.getUtlaan();

    Assert.assertEquals(utlaanList.size(), 1);
  }

  @AfterMethod(alwaysRun = true)
  public void tearDown() {
    StoreUtil.emptyStore();
  }

  public void testForfallsDato() {
    List<Laaner> laanere = new LaanerService().getLaanere();
    for (Laaner laaner : laanere) {
      List<Utlaan> utlaanList = laaner.getUtlaan();
      for (Utlaan utlaan : utlaanList) {
        Assert.assertEquals(utlaan.getForfallsdato(), new FristService().beregnForfallsdato(utlaan));
      }
    }
  }

  public void testBestillBok() {
    BokService bokService = new BokService();
    bokService.bestillBok(politi, heidi);
    Assert.assertEquals(politi.getReservasjoner().size(), 1);
    Assert.assertEquals(StoreUtil.getObjects(Reservasjon.class).size(), 1);
  }

  public void testLeverBok() {
    Date idag = new Date(System.currentTimeMillis());
    Utlaan utlaan = michael.getUtlaan().iterator().next();
    UtlaanManager.getInstance().leverBok(utlaan);
    Assert.assertTrue(idag.equals(utlaan.getLevertDato()));
  }

  public void testLeverBokMedReservasjon() {
    Date idag = new Date(System.currentTimeMillis());
    BokService bokService = new BokService();
    bokService.bestillBok(politi, heidi);
    Utlaan utlaan = michael.getUtlaan().iterator().next();
    Assert.assertEquals(politi.getReservasjoner().size(), 1);
    UtlaanManager.getInstance().leverBok(utlaan);
    Assert.assertTrue(idag.equals(utlaan.getLevertDato()));
    Assert.assertTrue(politi.getReservasjoner().isEmpty());
  }
}
