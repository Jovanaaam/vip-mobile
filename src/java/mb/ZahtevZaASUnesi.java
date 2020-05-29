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
import domen.Valuta;
import domen.Vrstakredita;
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
import javax.xml.ws.WebServiceRef;
import kontroler.KontrolerZahtevZaAS;
import org.primefaces.event.SelectEvent;


/**
 *
 * @author Korisnik
 */
@ManagedBean
@RequestScoped
 
public class ZahtevZaASUnesi {

    
    //private KontrolerZahtevZaAS k;
    private HashMap<String, Integer> zaposleniMapa;
    private HashMap<String, Integer> ugovoriMapa;
    private HashMap<String, Integer> servisiMapa;
    private static int idzahteva;
    private static int rbr;
    private Date datum;
    private Date rokisporuke;
    private boolean odobrno;
    private static List<Stavkazahtevazaas> listaStavki = new ArrayList<>();
    private int idzaposlenog;
    private int idugovora;
    int idservisa;
    String datumString;
    private String datumDrugiString;
    private int lastID =0;
    Stavkazahtevazaas selektovanaStavka;
    private StavkazahtevazaasPK primarni;
    static int redniZapamcen = 0;

    public ZahtevZaASUnesi() {
       // k = new KontrolerZahtevZaAS();
        //listaStavki = new ArrayList<>();
         zaposleniMapa = new HashMap<>();
        ugovoriMapa = new HashMap<>();
        servisiMapa = new HashMap<>();
      
    }
   @PostConstruct
    public void popuni() {
        zaposleniMapa = KontrolerZahtevZaAS.vratiInstancu().ucitajZaposlene();
        ugovoriMapa = KontrolerZahtevZaAS.vratiInstancu().ucitajUgovore();
       servisiMapa = KontrolerZahtevZaAS.vratiInstancu().ucitajServise();
       lastID = KontrolerZahtevZaAS.vratiInstancu().lastIdZahtev();
       System.out.println(lastID + " LAST ID");
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

    public int getRbr() {
        return rbr;
    }

    public void setRbr(int rbr) {
        this.rbr = rbr;
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

    public List<Stavkazahtevazaas> getListaStavki() {
        return listaStavki;
    }

    public void setListaStavki(List<Stavkazahtevazaas> listaStavki) {
        this.listaStavki = listaStavki;
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
    
   
    
     private Zahtevzaas trenutniZahtevZaAS = null;

    public Zahtevzaas getTrenutniZahtevZaAS() {
        return trenutniZahtevZaAS;
    }

    public void setTrenutniZahtevZaAS(Zahtevzaas trenutniZahtevZaAS) {
        this.trenutniZahtevZaAS = trenutniZahtevZaAS;
    }

    
    
       public void dodajUZahtevZaAS() {
           
         try {
          
             if(datumString.trim().isEmpty()){
            FacesContext fc2 = FacesContext.getCurrentInstance();
            fc2.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Greška pri unosu","Morate uneti rok isporuke."));
            return;
           }
             
            
           if (!listaStavki.isEmpty()) {
            System.out.println("TU JE");
            for (Stavkazahtevazaas stavkazahteva : listaStavki) {
                if (stavkazahteva.getIdservisa().getIdservisa() == idservisa) {
                    System.out.println("DAAaaaaaaaaaaaaaaaaaaa" + idservisa);
                    FacesContext fc = FacesContext.getCurrentInstance();
                    fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"Greska", "Ne moze se dva puta uneti isti servis"));
                    return;
                }
            }
        }

      
            System.out.println("String: " + datumString +"String za servis:  " + idservisa);
             rokisporuke = new SimpleDateFormat("dd-MM-yyyy").parse(datumString);
             System.out.println("SALJEM OVO: " + lastID + " **** " + idservisa + " DATUM: " +rokisporuke);
          
            trenutniZahtevZaAS = KontrolerZahtevZaAS.vratiInstancu().ubaciStavkuUZahtev(lastID, idservisa,rokisporuke,null);
           
           
            idzahteva = trenutniZahtevZaAS.getIdzahteva();

           
       
             if (trenutniZahtevZaAS != null) {
            listaStavki = trenutniZahtevZaAS.getStavkeZahtevaLista();
               
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
           
          
           trenutniZahtevZaAS = KontrolerZahtevZaAS.vratiInstancu().izmeniStavku(lastID, rbr, new Servis(idservisa),rokisporuke, null);
           
           listaStavki = trenutniZahtevZaAS.getStavkeZahtevaLista();
                   
            
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
           
           listaStavki = trenutniZahtevZaAS.getStavkeZahtevaLista();
                   
            
      } catch (Exception ex) {
            Logger.getLogger(ZahtevZaASUnesi.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
       }
     
        public void sacuvajCeoZahtev() throws ParseException, DatatypeConfigurationException {
        if(datumDrugiString.trim().isEmpty()){
        
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
        boolean ret = KontrolerZahtevZaAS.vratiInstancu().sacuvajSve(lastID, u, z, datum, daLiJeOdobreno);
        if (ret) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Uspešan unos!"));
            lastID = 0;
            idugovora = 0;
            idzaposlenog = 0;
            daLiJeOdobreno = "";
            listaStavki.clear();
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Greška!"));

        }
      
    } 

    public int getLastID() {
        return lastID;
    }

    public void setLastID(int lastID) {
        this.lastID = lastID;
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
        listaStavki.clear();

    }
    
      public void onRowSelect(SelectEvent event) {
          if(listaStavki.isEmpty()){
              System.out.println("LISTA JE PRAZNA");
          }
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
           
           selektovanaStavka = new Stavkazahtevazaas(new StavkazahtevazaasPK(idzahteva, rbr), rokisporuke, new Servis(idservisa), new Zahtevzaas(idzahteva));
              
           
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

    public String getDatumDrugiString() {
        return datumDrugiString;
    }

    public void setDatumDrugiString(String datumDrugiString) {
        this.datumDrugiString = datumDrugiString;
    }

  
    
    
    
}
