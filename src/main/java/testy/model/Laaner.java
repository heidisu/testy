package testy.model;

import java.util.List;

/**
 *
 * @author heidisu
 */
public class Laaner {
  String fornavn;
  String etternavn;
  String epost;
  String mobilnr;
  List<Utlaan> utlaan;
  Bibliotek bibliotek;
  String type;

  public String getFornavn() {
    return fornavn;
  }

  public void setFornavn(String fornavn) {
    this.fornavn = fornavn;
  }

  public String getEtternavn() {
    return etternavn;
  }

  public void setEtternavn(String etternavn) {
    this.etternavn = etternavn;
  }

  public String getEpost() {
    return epost;
  }

  public void setEpost(String epost) {
    this.epost = epost;
  }

  public String getMobilnr() {
    return mobilnr;
  }

  public void setMobilnr(String mobilnr) {
    this.mobilnr = mobilnr;
  }

  public List<Utlaan> getUtlaan() {
    return utlaan;
  }

  public void setUtlaan(List<Utlaan> utlaan) {
    this.utlaan = utlaan;
  }
  
  public Bibliotek getBibliotek() {
    return bibliotek;
  }

  public void setBibliotek(Bibliotek bibliotek) {
    this.bibliotek = bibliotek;
  }
  
  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }
}
