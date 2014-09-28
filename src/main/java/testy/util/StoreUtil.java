package testy.util;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author heidisu
 */
public class StoreUtil {
  private static final List objects = new ArrayList();
  
  public static void save(Object object){
    // lagrer til databasen
    objects.remove(object);
    objects.add(object);
  }
  
  public static void remove(Object object){
    objects.remove(object);
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
