package testy.model;

/**
 *
 * @author heidisu
 */
public class BokEksemplar {
    private Bok bok;
    private Utgiver utgiver;

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
