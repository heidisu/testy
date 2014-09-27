package testy.util;

import java.util.ArrayList;
import java.util.List;
import testy.model.Laaner;
import testy.model.Utlaan;

/**
 *
 * @author heidisu
 */
public class StoreUtil {
  private static List objects = new ArrayList();
  public static void save(Object object){
    // lagrer til databasen
    objects.remove(object);
    objects.add(object);
  }

    public static List getObjects(Class clazz) {
      List liste = new ArrayList();
      for(Object object : objects){
        if(object.getClass().equals(clazz)){
          liste.add(object);
        }
      }
        return liste;
    }
}
