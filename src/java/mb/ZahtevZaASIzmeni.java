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
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.xml.datatype.DatatypeConfigurationException;
import kontroler.KontrolerZahtevZaAS;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author Korisnik
 */
@ManagedBean
@RequestScoped

public class ZahtevZaASIzmeni {
     HashMap<String, Integer> zaposleniMapa;
     HashMap<String, Integer> ugovoriMapa;
     HashMap<String, Integer> servisiMapa;
     static int idzahteva;   
     static int rbr;
     Date datum;
     Date rokisporuke;
    boolean odobrno;
    static List<Stavkazahtevazaas> stavke = new ArrayList<>();
    int idzaposlenog;
    private int idugovora;
    int idservisa;
    String datumString;
    String datumDrugiString;
     int lastID;
    Stavkazahtevazaas selektovanaStavka;
     StavkazahtevazaasPK primarni;
    static int redniZapamcen = 0;
    static List<Zahtevzaas> zahtevi = new ArrayList<>();
    Zahtevzaas selektovanZahtev;
   String datumZaPretragu =null;
    static List<Stavkazahtevazaas> stavkeZahteva;
    static String  nazivservisa;

    public String getNazivservisa() {
        return nazivservisa;
    }

    public void setNazivservisa(String nazivservisa) {
        this.nazivservisa = nazivservisa;
    }

    public ZahtevZaASIzmeni() {
        zaposleniMapa = new HashMap<>();
        ugovoriMapa = new HashMap<>();
        servisiMapa = new HashMap<>();
        //stavke = new ArrayList<>();
    }
    
     @PostConstruct
    public void popuni() {
       zaposleniMapa = KontrolerZahtevZaAS.vratiInstancu().ucitajZaposlene();
       ugovoriMapa = KontrolerZahtevZaAS.vratiInstancu().ucitajUgovore();
       servisiMapa = KontrolerZahtevZaAS.vratiInstancu().ucitajServise();
       lastID = KontrolerZahtevZaAS.vratiInstancu().lastIdZahtev();
       
    }

    public List<Stavkazahtevazaas> getStavkeZahteva() {
        return stavkeZahteva;
    }

    public void setStavkeZahteva(List<Stavkazahtevazaas> stavkeZahteva) {
        this.stavkeZahteva = stavkeZahteva;
    }

    
    public HashMap<String, Integer> getZaposleniMapa() {
        return zaposleniMapa;
    }

    public void setZaposleniMapa(HashMap<String, Integer> zaposleniMapa) {
        this.zaposleniMapa = zaposleniMapa;
    }

    public HashMap<String, Integer> getUgovoriMapa() {
        return ugovoriMapa;
    }

    public void setUgovoriMapa(HashMap<String, Integer> ugovoriMapa) {
        this.ugovoriMapa = ugovoriMapa;
    }

    public HashMap<String, Integer> getServisiMapa() {
        return servisiMapa;
    }

    public void setServisiMapa(HashMap<String, Integer> servisiMapa) {
        this.servisiMapa = servisiMapa;
    }

    public int getIdzahteva() {
        return idzahteva;
    }

    public void setIdzahteva(int idzahteva) {
        this.idzahteva = idzahteva;
    }

    public static int getRbr() {
        return rbr;
    }

    public static void setRbr(int rbr) {
        ZahtevZaASIzmeni.rbr = rbr;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public Date getRokisporuke() {
        return rokisporuke;
    }

    public void setRokisporuke(Date rokisporuke) {
        this.rokisporuke = rokisporuke;
    }

    public boolean isOdobrno() {
        return odobrno;
    }

    public void setOdobrno(boolean odobrno) {
        this.odobrno = odobrno;
    }

    public  List<Stavkazahtevazaas> getStavke() {
        return stavke;
    }

    public  void setStavke(List<Stavkazahtevazaas> stavke) {
        ZahtevZaASIzmeni.stavke = stavke;
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

    public int getIdservisa() {
        return idservisa;
    }

    public void setIdservisa(int idservisa) {
        this.idservisa = idservisa;
    }

    public String getDatumString() {
        return datumString;
    }

    public void setDatumString(String datumString) {
        this.datumString = datumString;
    }

    public String getDatumDrugiString() {
        return datumDrugiString;
    }

    public void setDatumDrugiString(String datumDrugiString) {
        this.datumDrugiString = datumDrugiString;
    }

    public int getLastID() {
        return lastID;
    }

    public void setLastID(int lastID) {
        this.lastID = lastID;
    }

    public Stavkazahtevazaas getSelektovanaStavka() {
        return selektovanaStavka;
    }

    public void setSelektovanaStavka(Stavkazahtevazaas selektovanaStavka) {
        this.selektovanaStavka = selektovanaStavka;
    }

    public StavkazahtevazaasPK getPrimarni() {
        return primarni;
    }

    public void setPrimarni(StavkazahtevazaasPK primarni) {
        this.primarni = primarni;
    }

    public static int getRedniZapamcen() {
        return redniZapamcen;
    }

    public static void setRedniZapamcen(int redniZapamcen) {
        ZahtevZaASIzmeni.redniZapamcen = redniZapamcen;
    }

    public List<Zahtevzaas> getZahtevi() {
        return zahtevi;
    }

    public void setZahtevi(List<Zahtevzaas> zahtevi) {
        this.zahtevi = zahtevi;
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
      public void onRowSelect(SelectEvent event) {
        
          System.out.println("EVENT:  " + event.toString());
           Stavkazahtevazaas s = (Stavkazahtevazaas) event.getObject();
          System.out.println("STAVKA: " + s);
            //selektovanaStavka = (Stavkazahtevazaas) event.getObject();
         
          idzahteva = s.getStavkazahtevazaasPK().getIdzahteva();
           rbr = s.getStavkazahtevazaasPK().getRbr();
           rokisporuke = s.getRokisporuke();
           
           DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy"); 
           datumString = dateFormat.format(rokisporuke);  
          
           idservisa = s.getIdservisa().getIdservisa();
           nazivservisa = s.getIdservisa().getNazivservisa();
           selektovanaStavka = new Stavkazahtevazaas(new StavkazahtevazaasPK(idzahteva, rbr), rokisporuke, new Servis(idservisa), new Zahtevzaas(idzahteva));
              
           
    }
        public void onRowSelectZahtev(SelectEvent event) {
            stavke = new ArrayList<>();
          if(zahtevi.isEmpty()){
              System.out.println("LISTA JE PRAZNA");
          }
          System.out.println("EVENT:  " + event.toString());
           Zahtevzaas z = (Zahtevzaas) event.getObject();
         
          idzahteva = z.getIdzahteva();
          datum =z.getDatum();
          idugovora = z.getIdugovora().getIdugovora();
          idzaposlenog = z.getIdzaposlenog().getIdzaposlenog();
          String provera = z.getOdobreno();
          if(provera.equals("D")){
              odobrno = true;
          } else{
              odobrno = false;
          }
           DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy"); 
           datumDrugiString = dateFormat.format(datum);  
           
            for (Stavkazahtevazaas stavkazahtevazaas : stavkeZahteva) {
                if(stavkazahtevazaas.getStavkazahtevazaasPK().getIdzahteva() == idzahteva){
                    stavke.add(stavkazahtevazaas);
                }
            }
          
           
           selektovanZahtev = new Zahtevzaas(idzahteva, datum, provera, stavke, new Ugovor(idugovora), new Zaposleni(idzaposlenog));
              
           
    }

   
        
    public void vratiZahteve() {
         try {
           if(datumZaPretragu.trim().isEmpty()){
    
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Prazno polje","Morate izabrati uneti kriterijum po kojem pretrazujete."));
            return;
       
    }   
             System.out.println("pretrazujem po ovom datumu: " +datumZaPretragu);
             Date datumPretraga = new SimpleDateFormat("dd-MM-yyyy").parse(datumZaPretragu);
             zahtevi = KontrolerZahtevZaAS.vratiInstancu().vratiZahteve(datumPretraga);
             stavkeZahteva = KontrolerZahtevZaAS.vratiInstancu().vratistavke();
         } catch (ParseException ex) {
             Logger.getLogger(ZahtevZaASIzmeni.class.getName()).log(Level.SEVERE, null, ex);
         }
        
    }
      private Zahtevzaas trenutniZahtevZaAS = null;

    public Zahtevzaas getTrenutniZahtevZaAS() {
        return trenutniZahtevZaAS;
    }

    public void setTrenutniZahtevZaAS(Zahtevzaas trenutniZahtevZaAS) {
        this.trenutniZahtevZaAS = trenutniZahtevZaAS;
    }

    
     public void dodajUZahtevZaAS() {
         try {
            if(idzahteva == 0){
                System.out.println("stigao do uslova");
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Greška ","Morate odabrati zahtev za izmenu."));
            return;
            }
             if(datumString.trim().isEmpty()){
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Greška pri unosu","Morate uneti rok isporuke."));
            return;
           }
            
           if (!stavke.isEmpty()) {
            System.out.println("TU JE");
            for (Stavkazahtevazaas stavkazahteva : stavke) {
                if (stavkazahteva.getIdservisa().getIdservisa() == idservisa) {
                    FacesContext fc = FacesContext.getCurrentInstance();
                    fc.addMessage(null, new FacesMessage("Greska", "Ne moze se dva puta uneti isti servis"));
                    return;
                }
            }
        }

      
            System.out.println("String: " + datumString +"String za servis:  " + idservisa);
             rokisporuke = new SimpleDateFormat("dd-MM-yyyy").parse(datumString);
             System.out.println("SALJEM OVO: " + lastID + " **** " + idservisa + " DATUM: " +rokisporuke);
            
             trenutniZahtevZaAS = KontrolerZahtevZaAS.vratiInstancu().ubaciStavkuUZahtev(idzahteva, idservisa,rokisporuke, stavke);
           
           
            idzahteva = trenutniZahtevZaAS.getIdzahteva();

          
       
             if (trenutniZahtevZaAS != null) {
            stavke = trenutniZahtevZaAS.getStavkeZahtevaLista();
               
           } 

        } catch (Exception ex) {
            Logger.getLogger(ZahtevZaASUnesi.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
      public void izmeniStavku(){
       try {
           if(idzahteva == 0 || rbr == 0 ){
               FacesContext fc = FacesContext.getCurrentInstance();
            fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Greška pri izmeni","Morate odabrati stavku za izmenu."));
            return;
           }
            if(datumString.trim().isEmpty()){
            FacesContext fc2 = FacesContext.getCurrentInstance();
            fc2.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Greška pri izmeni","Morate uneti rok isporuke."));
            return;
           }
           rokisporuke = new SimpleDateFormat("dd-MM-yyyy").parse(datumString);
           
          
           trenutniZahtevZaAS = KontrolerZahtevZaAS.vratiInstancu().izmeniStavku(idzahteva, rbr, new Servis(idservisa),rokisporuke, stavke);
           
           stavke = trenutniZahtevZaAS.getStavkeZahtevaLista();
                   
            
      } catch (Exception ex) {
            Logger.getLogger(ZahtevZaASUnesi.class.getName()).log(Level.SEVERE, null, ex);
        } 
       
        
     }
      
      public void obrisiStavku(){
        try {
             if(idzahteva == 0 || rbr == 0){
               FacesContext fc = FacesContext.getCurrentInstance();
            fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Greška pri brisanju","Morate odabrati stavku za brisanje."));
            return;
           }
           rokisporuke = new SimpleDateFormat("dd-MM-yyyy").parse(datumString);
           
          
           trenutniZahtevZaAS = KontrolerZahtevZaAS.vratiInstancu().obrisi(idzahteva, rbr, null);
           
           stavke = trenutniZahtevZaAS.getStavkeZahtevaLista();
                   
            
      } catch (Exception ex) {
            Logger.getLogger(ZahtevZaASUnesi.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
       }
      
        public void sacuvajCeoZahtev() throws ParseException, DatatypeConfigurationException {
            if(idzahteva == 0){
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Greška ","Morate odabrati zahtev za izmenu."));
            return;
            }
         if(datumString.trim().isEmpty()){
        
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Greška u unosu","Morate uneti datum."));
            return;
        }
        Zaposleni z = new Zaposleni(idzaposlenog);
        Ugovor u = new Ugovor(idugovora);
        String daLiJeOdobreno = "";
        datum = new SimpleDateFormat("dd-MM-yyyy").parse(datumDrugiString);
        if(odobrno){
            daLiJeOdobreno = "D";
        } else{
            daLiJeOdobreno = "N";
        }
        boolean ret = KontrolerZahtevZaAS.vratiInstancu().sacuvajSve(idzahteva, u, z, datum, daLiJeOdobreno);
        if (ret) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Uspešna izmena!"));
            /*lastID = 0;
            idugovora = 0;
            datum = null;
            rokisporuke = null;
            idzaposlenog = 0;
            odobrno = false;
            stavke.clear();*/
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Greška!"));

        }
        
      
    } 
      public void odbaciZahtev() {
        KontrolerZahtevZaAS.vratiInstancu().ocistiMapu();
        KontrolerZahtevZaAS.vratiInstancu().setNull();
        datum = null;
        datumString = "";
        idservisa = 0;
        idugovora = 0;
        idzahteva = 0;
        lastID = 0;
        odobrno = false;
        rbr = 0;
        rokisporuke = null;
      zahtevi.clear();
        stavke.clear();

    }
    
    
    
}
