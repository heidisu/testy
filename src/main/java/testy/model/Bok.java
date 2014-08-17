package testy.model;

/**
 *
 * @author heidisu
 */
public class Bok {

  String tittel;
  Forfatter forfatter;
  String omtale;

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
}
