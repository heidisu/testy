package testy.model;

import java.util.Date;

/**
 *
 * @author heidisu
 */
public class Reservasjon {
  private Laaner laaner;
  private Bok bok;
  private Date reservasjonsdato;

  public Laaner getLaaner() {
    return laaner;
  }

  public void setLaaner(Laaner laaner) {
    this.laaner = laaner;
  }

  public Bok getBok() {
    return bok;
  }

  public void setBok(Bok bok) {
    this.bok = bok;
  }

  public Date getReservasjonsdato() {
    return reservasjonsdato;
  }

  public void setReservasjonsdato(Date reservasjonsdato) {
    this.reservasjonsdato = reservasjonsdato;
  }
}
