package testy.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author heidisu
 */
public class Bibliotek {
   List<Laaner> laanere = new ArrayList<Laaner>();

  public List<Laaner> getLaanere() {
    return laanere;
  }

  public void setLaanere(List<Laaner> laanere) {
    this.laanere = laanere;
  }
  
  public void addLaaner(Laaner laaner){
    laanere.add(laaner);
  }
}
