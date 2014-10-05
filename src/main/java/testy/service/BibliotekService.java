package testy.service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import testy.manager.UtlaanManager;
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

  public void sendMailTilAlleLaanere(Bibliotek bibliotek,
      String mailtype, String text, String maalgruppe) {
    List<Laaner> laanere = bibliotek.getLaanere();
    for (Laaner laaner : laanere) {
      switch (mailtype) { // purring, arrangement, boktips
        case "purring":
          List<Utlaan> forfalteUtlaan = UtlaanManager.getInstance().getForfalteUtlaan(bibliotek);
          for (Utlaan utlaan : forfalteUtlaan) {
            mailService.sendMail(text,
                "Følgende utlån må leveres snart, ellers..."
                + utlaan.getBokEksemplar().getBok().getTittel(),
                utlaan.getLaaner().getEpost());
          }
        case "arrangement": // arrangementstyper alle, pensjonist, barn
          boolean sendMail = false;
          Calendar calendar = new GregorianCalendar();
          calendar.setTime(laaner.getFoedselsdato());
          int foedselAar = calendar.get(Calendar.YEAR);
          Calendar idag = new GregorianCalendar();
          idag.setTimeInMillis(System.currentTimeMillis());
          int aar = idag.get(Calendar.YEAR);
          if (maalgruppe.equals("alle")) {
            sendMail = true;
          } else if (maalgruppe.equals("pensjonist")) {
            if (aar - foedselAar > 67) {
              sendMail = true;
            }
          } else if (maalgruppe.equals("barn")) {
            if (aar - foedselAar < 12) {
              sendMail = true;
            }
          }
          if (sendMail) {
            mailService.sendMail("Bli med på arrangement", text, laaner.getEpost());
          }
        default:
      }
    }
  }

  public void varsleBibliotek(String varselType, BokEksemplar bokEksemplar, Laaner laaner) {
    String server = "localhost";
    int port = 21;
    String user = "bibliotekadmin";
    String pass = "bibliotek123";

    FTPClient ftpClient = new FTPClient();
    try {

      ftpClient.connect(server, port);
      ftpClient.login(user, pass);
      ftpClient.enterLocalPassiveMode();

      ftpClient.setFileType(FTP.ASCII_FILE_TYPE);

      String firstRemoteFile = "varsel_" + System.currentTimeMillis() + ".txt";
      String content = varselType
          + ";"
          + bokEksemplar.getHylleplassering()
          + ";"
          + laaner.getId();
      byte[] bytes = content.getBytes(StandardCharsets.UTF_8);
      InputStream inputStream = new ByteArrayInputStream(bytes);

      ftpClient.storeFile(firstRemoteFile, inputStream);
      inputStream.close();

    } catch (IOException ex) {
      Logger.getLogger(BibliotekService.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  public BokEksemplar getLedigBokeksemplar(Bibliotek bibliotek, Bok bok) {
    List<BokEksemplar> bokEksemplarer = StoreUtil.getObjects(BokEksemplar.class);
    List< BokEksemplar> eksemplarer = new ArrayList<BokEksemplar>();
    for (BokEksemplar eksemplar : bokEksemplarer) {
      if (eksemplar.getBok().equals(bok) && eksemplar.getEier().equals(bibliotek)) {
        eksemplarer.add(eksemplar);
      }
    }
    if (eksemplarer.isEmpty()) {
      return null;
    }
    List<Utlaan> utlaaner = StoreUtil.getObjects(Utlaan.class);
    for (Utlaan utlaan : utlaaner) {
      if (eksemplarer.contains(utlaan.getBokEksemplar())) {
        eksemplarer.remove(utlaan.getBokEksemplar());
      }
    }
    return eksemplarer.isEmpty() ? null : eksemplarer.iterator().next();
  }
}
