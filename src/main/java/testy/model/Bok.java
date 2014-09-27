package testy.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author heidisu
 */
public class Bok {

  String tittel;
  Forfatter forfatter;
  String omtale;
  List<Reservasjon> reservasjoner = new ArrayList<Reservasjon>();

  public String getTittel() {
    return tittel;
  }

  public void setTittel(String tittel) {
    this.tittel = tittel;
  }

  public Forfatter getForfatter() {
    return forfatter;
  }

  public void setForfatter(Forfatter forfatter) {
    this.forfatter = forfatter;
  }

  public String getOmtale() {
    return omtale;
  }

  public void setOmtale(String omtale) {
    this.omtale = omtale;
  }
  
  public List<Reservasjon> getReservasjoner(){
    return reservasjoner;
  }
  
  public void addReservasjon(Reservasjon reservasjon){
    reservasjoner.add(reservasjon);
  }
}
