/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mb;

import domen.Zahtevzaas;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import kontroler.KontrolerZahtevZaAS;
import static mb.ZahtevZaASIzmeni.zahtevi;

/**
 *
 * @author Korisnik
 */
@ManagedBean
@RequestScoped
public class ZahtevZaASPretrazi {
    static int idzahteva;   
     Date datum;
     boolean odobrno;
     int idzaposlenog;
    private int idugovora;
   static List<Zahtevzaas> zahtevi = new ArrayList<>();
   String datumZaPretragu;

    public String getDatumZaPretragu() {
        return datumZaPretragu;
    }

    public void setDatumZaPretragu(String datumZaPretragu) {
        this.datumZaPretragu = datumZaPretragu;
    }
    public int getIdzahteva() {
        return idzahteva;
    }

    public void setIdzahteva(int idzahteva) {
        this.idzahteva = idzahteva;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public boolean isOdobrno() {
        return odobrno;
    }

    public void setOdobrno(boolean odobrno) {
        this.odobrno = odobrno;
    }

    public int getIdzaposlenog() {
        return idzaposlenog;
    }

    public void setIdzaposlenog(int idzaposlenog) {
        this.idzaposlenog = idzaposlenog;
    }

    public int getIdugovora() {
        return idugovora;
    }

    public void setIdugovora(int idugovora) {
        this.idugovora = idugovora;
    }

    public List<Zahtevzaas> getZahtevi() {
        return zahtevi;
    }

    public void setZahtevi(List<Zahtevzaas> zahtevi) {
        this.zahtevi = zahtevi;
    }
       public void vratiZahteve() {
         try {
             if(datumZaPretragu.trim().isEmpty()){
               FacesContext fc = FacesContext.getCurrentInstance();
            fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Greška pri pretrazi","Morate uneti kriterijum za pretragu."));
            return;
           }
          
             System.out.println("pretrazujem po ovom datumu: " +datumZaPretragu);
             Date datumPretraga = new SimpleDateFormat("dd-MM-yyyy").parse(datumZaPretragu);
             zahtevi = KontrolerZahtevZaAS.vratiInstancu().vratiZahteve(datumPretraga);
             
         } catch (ParseException ex) {
             Logger.getLogger(ZahtevZaASIzmeni.class.getName()).log(Level.SEVERE, null, ex);
         }
        
    }
   
}
