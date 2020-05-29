/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mb;

import domen.Servis;
import domen.Stavkazahtevazaas;
import domen.StavkazahtevazaasPK;
import domen.Ugovor;
import domen.Zahtevzaas;
import domen.Zaposleni;
import java.text.DateFormat;
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
import javax.xml.datatype.DatatypeConfigurationException;
import kontroler.KontrolerZahtevZaAS;
import static mb.ZahtevZaASIzmeni.idzahteva;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author Korisnik
 */
@ManagedBean
@RequestScoped
public class ZahtevZaASObrisi {
      static int idzahteva;   
     Date datum;
     boolean odobrno;
     int idzaposlenog;
    private int idugovora;
   static List<Zahtevzaas> zahtevi = new ArrayList<>();
   String datumZaPretragu;
Zahtevzaas selektovanZahtev;

    public ZahtevZaASObrisi() {
    }


    public Zahtevzaas getSelektovanZahtev() {
        return selektovanZahtev;
    }

    public void setSelektovanZahtev(Zahtevzaas selektovanZahtev) {
        this.selektovanZahtev = selektovanZahtev;
    }
   

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
       
         public void obrisiZahtev() throws ParseException, DatatypeConfigurationException {
       if(idzahteva == 0){
               FacesContext fc = FacesContext.getCurrentInstance();
            fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Greška pri brisanju","Morate odabrati zahtev za brisanje."));
            return;
           }
        boolean ret = KontrolerZahtevZaAS.vratiInstancu().obrisiZahtev(idzahteva);
        if (ret) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Uspešno ste obrisali zahtev!"));
            zahtevi.clear();
           
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Greška!"));

        }
        
}
          public void onRowSelect(SelectEvent event) {
        
          System.out.println("EVENT:  " + event.toString());
           Zahtevzaas z = (Zahtevzaas) event.getObject();
         
      
         
          idzahteva = z.getIdzahteva();
          
              
           
    }
}