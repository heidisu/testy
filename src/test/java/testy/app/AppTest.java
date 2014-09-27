package testy.app;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import testy.manager.UtlaanManager;
import testy.model.*;
import testy.service.BibliotekService;
import testy.service.FristService;
import testy.service.LaanerService;
import testy.util.StoreUtil;

/**
 * @author Michael Gfeller
 */
@Test
public class AppTest {

  public void testRun() {
    Bibliotek lysaker = new Bibliotek();
    Bibliotek oslo = new Bibliotek();
    StoreUtil.save(lysaker);
    StoreUtil.save(oslo);

    Laaner michael = new Laaner();
    michael.setBibliotek(lysaker);
    michael.setFornavn("Michael");
    michael.setType("normal");
    StoreUtil.save(michael);

    Laaner heidi = new Laaner();
    heidi.setFornavn("Heidi");
    StoreUtil.save(heidi);

    oslo.setLaanere(new ArrayList<Laaner>());
    oslo.getLaanere().add(heidi);

    Utgiver aschehoug = new Utgiver();
    StoreUtil.save(aschehoug);

    Forfatter nesboe = new Forfatter();
    StoreUtil.save(nesboe);

    Bok politi = new Bok();
    politi.setForfatter(nesboe);
    politi.setTittel("Politi");
    StoreUtil.save(politi);

    BokEksemplar politiExemplar = new BokEksemplar();
    politiExemplar.setBok(politi);
    politiExemplar.setEier(lysaker);
    politiExemplar.setUtgiver(aschehoug);
    StoreUtil.save(politiExemplar);

    UtlaanManager.getInstance().utlaanBok(michael, politiExemplar);

    List<Utlaan> utlaanList = UtlaanManager.getInstance().getUtlaan(michael);

    for (Utlaan utlaan : utlaanList) {
      System.out.println(utlaan);
    }

    Assert.assertEquals(utlaanList.size(), 1);

  }

  @Test(dependsOnMethods = { "testRun" })
  public void testForfallsDato() {
    List<Laaner> laanere = new LaanerService().getLaanere();
    for (Laaner laaner : laanere) {
      List<Utlaan> utlaanList = UtlaanManager.getInstance().getUtlaan(laaner);
      for (Utlaan utlaan : utlaanList) {
        Assert.assertEquals(utlaan.getForfallsdato(), new FristService().beregnForfallsdato(utlaan));
      }
    }
  }

  @Test(dependsOnMethods = { "testRun" })
  public void testSendForfallspaaminnelse() {
      Map<Laaner, List<Utlaan>> forfallendeUtlaan = UtlaanManager.getInstance().getForfallendeUtlaan();
      BibliotekService bibliotekService = new BibliotekService();

      Bibliotek lysaker = new Bibliotek();
      bibliotekService.sendMailTilAlleLaanere(lysaker, "purring", "lever tilbake!!");

      //TODO: test at alle forfallendeUtlaan resulterer i en epost sent til riktig person.
      Assert.assertTrue(false);
  }
}
