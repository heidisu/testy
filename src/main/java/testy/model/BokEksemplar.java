package testy.model;

/**
 *
 * @author heidisu
 */
public class BokEksemplar {
    private Bok bok;
    private Utgiver utgiver;
    private String hylleplassering;
    private Bibliotek eier;

  public String getHylleplassering() {
    return hylleplassering;
  }

  public void setHylleplassering(String hylleplassering) {
    this.hylleplassering = hylleplassering;
  }

  public Bibliotek getEier() {
    return eier;
  }

  public void setEier(Bibliotek eier) {
    this.eier = eier;
  }

  public Bok getBok() {
    return bok;
  }

  public void setBok(Bok bok) {
    this.bok = bok;
  }

  public Utgiver getUtgiver() {
    return utgiver;
  }

  public void setUtgiver(Utgiver utgiver) {
    this.utgiver = utgiver;
  }
}
