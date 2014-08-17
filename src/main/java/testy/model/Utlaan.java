package testy.model;

import java.util.Date;

/**
 *
 * @author heidisu
 */
public class Utlaan {
  private Laaner laaner;
  private BokEksemplar bokEksemplar;
  private Date utlaansdato;
  private int antallFornyelser;

  public Laaner getLaaner() {
    return laaner;
  }

  public void setLaaner(Laaner laaner) {
    this.laaner = laaner;
  }

  public BokEksemplar getBokEksemplar() {
    return bokEksemplar;
  }

  public void setBokEksemplar(BokEksemplar bokEksemplar) {
    this.bokEksemplar = bokEksemplar;
  }

  public Date getUtlaansdato() {
    return utlaansdato;
  }

  public void setUtlaansdato(Date utlaansdato) {
    this.utlaansdato = utlaansdato;
  }

  public int getAntallFornyelser() {
    return antallFornyelser;
  }

  public void setAntallFornyelser(int antallFornyelser) {
    this.antallFornyelser = antallFornyelser;
  }
  
  public Date getForfallsdato(){
    return new Date(utlaansdato.getTime() + 30 * 24 * 60 * 60 * 1000);
  }
}
