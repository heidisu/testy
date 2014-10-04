package testy.service;

import org.testng.annotations.Test;
import testy.model.Bibliotek;

/**
 *
 * @author heidisu
 */
public class BibliotekServiceTest {

  @Test
  public void testSendForfallspaaminnelse() {
    BibliotekService bibliotekService = new BibliotekService();
    Bibliotek lysaker = new Bibliotek();
    bibliotekService.sendMailTilAlleLaanere(lysaker, "purring", "lever tilbake!!", null);

    //TODO: test at alle forfallendeUtlaan resulterer i en epost sent til riktig person.
  }
}
