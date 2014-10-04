package testy.util;

import java.util.ArrayList;
import java.util.List;
import testy.model.Bibliotek;
import testy.model.Bok;
import testy.model.Laaner;
import testy.model.Reservasjon;
import testy.model.Utlaan;

/**
 * Klasse som simulerer en lagring av objekter ved å holde på en liste av alle
 * objektene i applikasjonen
 *
 * @author heidisu
 */
public class StoreUtil {

  private static final List objects = new ArrayList();

  public static void save(Object object) {
    objects.remove(object);
    objects.add(object);
  }

  public static void remove(Object object) {
    objects.remove(object);
    updateSet(object);
  }

  public static List getObjects(Class clazz) {
    List liste = new ArrayList();
    for (Object object : objects) {
      if (object.getClass().equals(clazz)) {
        liste.add(object);
      }
    }
    return liste;
  }

  public static void emptyStore() {
    objects.clear();
  }

  private static void updateSet(Object object) {
    if (object instanceof Reservasjon) {
      Reservasjon reservasjon = (Reservasjon) object;
      List<Bok> boeker = getObjects(Bok.class);
      for (Bok bok : boeker) {
        bok.getReservasjoner().remove(reservasjon);
      }
    }
    if (object instanceof Utlaan) {
      Utlaan utlaan = (Utlaan) object;
      List<Laaner> laanere = getObjects(Laaner.class);
      for (Laaner laaner : laanere) {
        laaner.getUtlaan().remove(utlaan);
      }
    }
    if (object instanceof Laaner) {
      Laaner laaner = (Laaner) object;
      List<Bibliotek> biblioteker = getObjects(Bibliotek.class);
      for (Bibliotek bibliotek : biblioteker) {
        bibliotek.getLaanere().remove(laaner);
      }
    }
  }
}
