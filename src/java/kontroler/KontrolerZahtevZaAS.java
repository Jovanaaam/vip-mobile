/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kontroler;

import dbb.DBBroker;
import domen.Servis;
import domen.Stavkazahtevazaas;
import domen.Ugovor;
import domen.Valuta;
import domen.Vrstakredita;
import domen.Zahtevzaas;
import domen.Zaposleni;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 *
 * @author Korisnik
 */
public class KontrolerZahtevZaAS {
        private static KontrolerZahtevZaAS instanca;
       DBBroker dbb;
        Zahtevzaas zahtev = null;
    
     public KontrolerZahtevZaAS() {
        dbb = new DBBroker();
     }
public static KontrolerZahtevZaAS vratiInstancu() {
        if (instanca == null) {
            instanca = new KontrolerZahtevZaAS();
        }
        return instanca;
    }
    public Zahtevzaas getZahtev() {
        return zahtev;
    }

    public void setZahtev(Zahtevzaas zahtev) {
        this.zahtev = zahtev;
    }
     
     

    public HashMap<String, Integer> ucitajUgovore() {
        HashMap<String, Integer> ugovoriMapa = new HashMap<>();
        List<Ugovor> ugovoriLista = new ArrayList<>();
        try {
            dbb.otvoriKonekciju();
            ugovoriLista = dbb.ucitajUgovore();
            dbb.zatvoriKonekciju();
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (Ugovor u : ugovoriLista) {
            ugovoriMapa.put(u.getNazivugovora(), u.getIdugovora());
        }

        return ugovoriMapa;
    }

    public HashMap<String, Integer> ucitajZaposlene() {
       HashMap<String, Integer> zaposleniMapa = new HashMap<>();
        List<Zaposleni> zaposleniLista = new ArrayList<>();
        try {
            dbb.otvoriKonekciju();
            zaposleniLista = dbb.ucitajZaposlene();
            dbb.zatvoriKonekciju();
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (Zaposleni z : zaposleniLista) {
            zaposleniMapa.put(z.getImeprezimezaposlenog(), z.getIdzaposlenog());
        }

        return zaposleniMapa;
    }

    public HashMap<String, Integer> ucitajServise() {
         HashMap<String, Integer> servisiMapa = new HashMap<>();
        List<Servis> servisiLista = new ArrayList<>();
        try {
            dbb.otvoriKonekciju();
            servisiLista = dbb.ucitajServise();
            dbb.zatvoriKonekciju();
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (Servis s : servisiLista) {
            servisiMapa.put(s.getNazivservisa(), s.getIdservisa());
        }

        return servisiMapa;
    }

    public Zahtevzaas ubaciStavkuUZahtev(int lastID, int idservisa, Date rokisporuke, List<Stavkazahtevazaas> stavke) {
            try {
               dbb.otvoriKonekciju();
               dbb.pokreniTransakciju();
            Servis servis = dbb.vratiServisSaId(idservisa);
            
            if (zahtev == null) {
                zahtev = new Zahtevzaas();
                zahtev.setIdzahteva(lastID);
                if(stavke != null){
                zahtev.setStavkeZahtevaLista(stavke);
                }
                zahtev.ubaciStavku(servis, dbb, rokisporuke, lastID);
                dbb.potvrdiTransakciju();
                dbb.zatvoriKonekciju(); //
                
            } else {
                 zahtev.ubaciStavku(servis, dbb, rokisporuke, lastID);
                 dbb.zatvoriKonekciju();//
            }

        } catch (Exception ex) {
            Logger.getLogger(KontrolerZahtevZaAS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return zahtev;
    }
      public void ocistiMapu() {
          dbb.otvoriKonekciju();//
        dbb.ocistiMapu();
        dbb.zatvoriKonekciju();//
    }

    public boolean sacuvajZahtev(int idZahteva, Ugovor u, Zaposleni z, String odobreno, Date datum) {
        if (zahtev == null) {
            zahtev = new Zahtevzaas(idZahteva);
        
        }
        dbb.otvoriKonekciju();
        dbb.pokreniTransakciju();
        Ugovor ugovor = new Ugovor();
        Zaposleni zaposleni = new Zaposleni();
        try {
            ugovor = dbb.vratiUgovorSaIdObj(u);
            zaposleni = dbb.vratiZaposlenogSaIdObj(z);       
        } catch (Exception ex) {
            Logger.getLogger(KontrolerZahtevZaAS.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (ugovor != null && zaposleni != null) {
            zahtev.setIdzaposlenog(zaposleni);
            zahtev.setIdugovora(ugovor);
            zahtev.setDatum(datum);
            zahtev.setOdobreno(odobreno);
            boolean ret = dbb.zapamtiZahtev(zahtev);

            if (ret) {

                dbb.potvrdiTransakciju();
                dbb.zatvoriKonekciju(); // 
                setZahtev(null);
                return true;

            } else {
                dbb.ponistiTransakciju();
                   dbb.zatvoriKonekciju(); // 
                setZahtev(null);
                return false;
            }
        } else {
            dbb.ponistiTransakciju();
               dbb.zatvoriKonekciju(); // 
            setZahtev(null);
            return false;
        }
    }

    public Zahtevzaas vratiZahtev(int idzahteva) throws Exception {
        dbb.otvoriKonekciju();
        dbb.pokreniTransakciju();
        zahtev= dbb.vratiZahtevSaId(idzahteva);
         dbb.zatvoriKonekciju(); // 
        return zahtev;

    }

  

    
   
      public Zahtevzaas izmeniStavku(int idzahteva, int redniBroj,  Servis servis, Date rokisporuke, List<Stavkazahtevazaas> stavke) throws Exception {
       
      Zahtevzaas z = new Zahtevzaas(idzahteva);
    
      if(zahtev == null)
      zahtev = new Zahtevzaas();
          zahtev.setIdzahteva(idzahteva);
            if(stavke!=null){
          zahtev.setStavkeZahtevaLista(stavke);
            }
       /* Stavkazahtevazaas sz = new Stavkazahtevazaas(idzahteva, redniBroj);
     

        if (servis != null) {
           
            sz.izmeni(idzahteva, redniBroj, servis, dbb, rokisporuke, zahtev);
        }
        return sz;*/
       dbb.otvoriKonekciju();//
       Servis s = dbb.vratiServisSaId(servis.getIdservisa());
       Stavkazahtevazaas sz = new Stavkazahtevazaas(idzahteva, redniBroj);
       z = sz.izmeni(idzahteva, redniBroj, s, dbb, rokisporuke, zahtev);
         dbb.zatvoriKonekciju();//
        return z;
    }

    public Zahtevzaas obrisi(int idzahteva, int stavkaZaBrisanjeId, List<Stavkazahtevazaas> stavke) {
        dbb.otvoriKonekciju();//
        
            if(zahtev == null) zahtev = new Zahtevzaas(idzahteva);
            if(stavke != null)
            zahtev.setStavkeZahtevaLista(stavke);
        
        zahtev = zahtev.obrisi(stavkaZaBrisanjeId, dbb);
        dbb.zatvoriKonekciju();//
        return zahtev;
    }

    public boolean sacuvajSve(int idzahteva, Ugovor u, Zaposleni z, Date datum, String odobreno) throws DatatypeConfigurationException {
      
        if (zahtev == null) {
            zahtev = new Zahtevzaas(idzahteva);
           
        }
        dbb.otvoriKonekciju();
        dbb.pokreniTransakciju();

       if (u != null && z!=null) {
            zahtev.setIdugovora(u);
            zahtev.setIdzaposlenog(z);
            zahtev.setDatum(datum);
            zahtev.setOdobreno(odobreno);
            

            boolean ret = dbb.zapamtiSve(zahtev);
       
       
            setZahtev(null);

            if (ret) {
                dbb.potvrdiTransakciju();
                return true;
            } else {
                dbb.ponistiTransakciju();
                return false;
            }
        } else {
            dbb.ponistiTransakciju();
            return false;
        }
 
    }
    
    

    public int lastIdZahtev() {
        int lastID = 0;

       try {
           dbb.otvoriKonekciju();
           dbb.pokreniTransakciju();
          lastID = dbb.lastIDZahtev(); 
           System.out.println("KONTROLER UZEO: " + lastID);
          dbb.zatvoriKonekciju();
       } catch (Exception e) {
          dbb.ponistiTransakciju();
           dbb.zatvoriKonekciju();
           e.printStackTrace();
        }
        return lastID;
    }

    public void setNull() {
        instanca = null;
    }

    public List<Zahtevzaas> vratiZahteve(Date datumPretraga)  {
            List<Zahtevzaas> zahtevi = new ArrayList<>();
            try {
            
                dbb.otvoriKonekciju();
                dbb.pokreniTransakciju();
                zahtevi= dbb.vratiZahtevePoDatumu(datumPretraga);
                   dbb.zatvoriKonekciju(); // 
            
            } catch (Exception ex) {
                Logger.getLogger(KontrolerZahtevZaAS.class.getName()).log(Level.SEVERE, null, ex);
            }
                return zahtevi;
    }

    public List<Stavkazahtevazaas> vratistavke() {
         List<Stavkazahtevazaas> stavke = new ArrayList<>();
            try {
            
                dbb.otvoriKonekciju();
                
                stavke= dbb.vratistavke();
                   dbb.zatvoriKonekciju(); // 
            
            } catch (Exception ex) {
                Logger.getLogger(KontrolerZahtevZaAS.class.getName()).log(Level.SEVERE, null, ex);
            }
                return stavke;
    }

    public boolean obrisiZahtev(int idzahteva) {
        dbb.otvoriKonekciju();
        dbb.pokreniTransakciju();

        boolean ret = dbb.obrisiZahtev(idzahteva);

        if (ret) {
            dbb.potvrdiTransakciju();
            dbb.zatvoriKonekciju();
            return true;
        } else {
            dbb.ponistiTransakciju();
            dbb.zatvoriKonekciju();
            return false;
        }
    }

   

   
    

 
       
}
