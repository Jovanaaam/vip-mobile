/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mb;

import com.sun.javafx.scene.control.skin.VirtualFlow;
import domen.Valuta;
import domen.Vrstakredita;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.html.HtmlDataTable;
import javax.faces.context.FacesContext;
import javax.faces.convert.ConverterException;
import javax.persistence.Converter;
import javax.transaction.Transactional;
import kontroler.KontrolerVrstaKredita;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;
import org.primefaces.model.SelectableDataModel;


/**
 *
 * @author Korisnik
 */
@ManagedBean
@RequestScoped

public class IzmeniVrstuKredita{
    KontrolerVrstaKredita k;
    private HashMap<String, Integer> valuteMapa;
    static int idvrstekredita;
    String nazivvrstekredita;
    int idvalute;
    Vrstakredita slektovanaVrsta;
    int selektovanaVrstaID;
    String selektovanaVrstaNaziv;
    Valuta selektovanaValuta;
    Valuta proba;
    int idSelektovaneValute;
    List<Valuta> listaValuta = new VirtualFlow.ArrayLinkedList<>();
    int vrstaZaIzmenu;
    String nazivZaPretragu;
    int globalniIDValuta;

    private static List<Vrstakredita> vrsteKredita = new ArrayList<>();

    public IzmeniVrstuKredita() {
        k = new KontrolerVrstaKredita();
       vrsteKredita = new ArrayList<>();
       listaValuta = new ArrayList<>();
       globalniIDValuta = 0;
      valuteMapa = k.ucitajValute();
       //listaValuta = k.ucitajValute2();
     //proba = new Valuta(4, "HH");
      
    }
     @PostConstruct
    public void ucitajVrste() {
       
        // listaValuta.add(new Valuta(1, "RSD"));
         //listaValuta.add(new Valuta(2, "EUR"));
     //vrsteKredita.add(new Vrstakredita(1, "naziv", new Valuta(2, "dsds")));
        // System.out.println("ISPIS");
      //vrsteKredita = k.ucitajVrsteKreditaPoNazivu("yy");
       // vrsteKredita = k.ucitajVrsteKreditaPoNazivu(nazivZaPretragu);
      
   
  
     vrsteKredita = k.ucitajVrsteKredita();
      //vrsteKredita = k.ucitajVrsteKreditaPoNazivu(nazivZaPretragu);
      
       
   
    }
  

    public HashMap<String, Integer> getValuteMapa() {
        return valuteMapa;
    }

    public void setValuteMapa(HashMap<String, Integer> valuteMapa) {
        this.valuteMapa = valuteMapa;
    }

    public List<Vrstakredita> getVrsteKredita() {
        return vrsteKredita;
    }

    public void setVrsteKredita(List<Vrstakredita> vrsteKredita) {
        this.vrsteKredita = vrsteKredita;
    }

    public int getIdvrstekredita() {
        return idvrstekredita;
    }

    public void setIdvrstekredita(int idvrstekredita) {
        this.idvrstekredita = idvrstekredita;
    }

    public String getNazivvrstekredita() {
        return nazivvrstekredita;
    }

    public void setNazivvrstekredita(String nazivvrstekredita) {
        this.nazivvrstekredita = nazivvrstekredita;
    }

    public int getIdvalute() {
        return idvalute;
    }

    public void setIdvalute(int idvalute) {
        this.idvalute = idvalute;
    }

    public KontrolerVrstaKredita getK() {
        return k;
    }

    public void setK(KontrolerVrstaKredita k) {
        this.k = k;
    }

    public void setSlektovanaVrsta(Vrstakredita slektovanaVrsta) {
        this.slektovanaVrsta = slektovanaVrsta;
    }

    public int getSelektovanaVrstaID() {
        return selektovanaVrstaID;
    }

    public void setSelektovanaVrstaID(int selektovanaVrstaID) {
        this.selektovanaVrstaID = selektovanaVrstaID;
    }

    public String getSelektovanaVrstaNaziv() {
        return selektovanaVrstaNaziv;
    }

    public void setSelektovanaVrstaNaziv(String selektovanaVrstaNaziv) {
        this.selektovanaVrstaNaziv = selektovanaVrstaNaziv;
    }

    public Valuta getSelektovanaValuta() {
        return selektovanaValuta;
    }

    public void setSelektovanaValuta(Valuta selektovanaValuta) {
        this.selektovanaValuta = selektovanaValuta;
    }

    public Valuta getProba() {
        return proba;
    }

    public void setProba(Valuta proba) {
        this.proba = proba;
    }

    public List<Valuta> getListaValuta() {
        return listaValuta;
    }

    public void setListaValuta(List<Valuta> listaValuta) {
        this.listaValuta = listaValuta;
    }

   public void izmeniVrstuKredita(){
        if(idvrstekredita == 0){
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Greska","Morate izabrati vrstu kredita koju zelite da izmenite."));
            return;
       }
       if(nazivvrstekredita.trim().isEmpty()){
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Greška u unosu","Morate uneti naziv."));
            return;
       }
      
       
       System.out.println("MOJE");
       System.out.println("ID " + idvrstekredita + " NAziv " +nazivvrstekredita);
          
       boolean uspesno =
                k.izmeniVrstuKredita(idvrstekredita, nazivvrstekredita, new Valuta(idvalute));
     
       if (uspesno) {
            System.out.println("Vrsta kredita je uspesno izmenjena" +nazivvrstekredita);
           /* vrsteKredita = k.ucitajVrsteKredita();
            for (Vrstakredita v : vrsteKredita) {
                System.out.println("L: " + v);
           }*/
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.addMessage(null, new FacesMessage("Uspešna izmena", "Vrsta kredita "+nazivvrstekredita+" je uspešno izmenjena."));
          
            
           //resetujPolja();
         
       } else {
            System.out.println("Vrsta kredita nije uspesno izmenjena" +idvalute);
            
           FacesContext fc = FacesContext.getCurrentInstance();
            fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Greška pri izmeni","Vrsta kredita "+nazivvrstekredita+" nije uspešno izmenjena."));
        }
         vrsteKredita = k.ucitajVrsteKredita();
           for (Vrstakredita v : vrsteKredita) {
               System.out.println("PROVERA: " + v);
           }
   }
    
    


    public Vrstakredita getSlektovanaVrsta() {
        return slektovanaVrsta;
    }
  public void onRowSelect(SelectEvent event) {
          //rstakredita vrsta =(Vrstakredita)((DataTable)event.getComponent()).getRowData();
     
           Vrstakredita v = (Vrstakredita) event.getObject();
           System.out.println("PROVERI: " + v);
         /*  selektovanaVrstaID = v.getIdvrstekredita();
           selektovanaVrstaNaziv = v.getNazivvrstekredita();
           idSelektovaneValute = v.getIdvalute().getIdvalute();
           String  naziv = v.getIdvalute().getNazivvalute();
           selektovanaValuta = new Valuta(idSelektovaneValute, naziv);
           
           
           slektovanaVrsta = new Vrstakredita(selektovanaVrstaID, selektovanaVrstaNaziv, selektovanaValuta);*/
         
           idvrstekredita = v.getIdvrstekredita();
           nazivvrstekredita = v.getNazivvrstekredita();
           idvalute = v.getIdvalute().getIdvalute();
           String  naziv = v.getIdvalute().getNazivvalute();
           selektovanaValuta = new Valuta(idvalute, naziv);
           
           
           slektovanaVrsta = new Vrstakredita(selektovanaVrstaID, selektovanaVrstaNaziv, selektovanaValuta);
           
    }
 
    public void onRowUnselect(UnselectEvent event) {
       
    }

    public void setIdSelektovaneValute(int idSelektovaneValute) {
        this.idSelektovaneValute = idSelektovaneValute;
    }

    public int getIdSelektovaneValute() {
        return idSelektovaneValute;
    }
    
    public void sacuvajIDZaValutu(){
        globalniIDValuta = idvalute;
    }

    private void resetujPolja() {
        vrsteKredita = k.ucitajVrsteKredita();
    }

    public String getNazivZaPretragu() {
        return nazivZaPretragu;
    }

   

    public void setNazivZaPretragu(String nazivZaPretragu) {
        this.nazivZaPretragu = nazivZaPretragu;
    }

  
    public void vratiNazive(){
          if(nazivZaPretragu.trim().isEmpty()){
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Prazno polje","Morate uneti naziv po kojem ce se pretrazivati."));
            return;
       }
      //ucitajVrste();
         System.out.println("ISPIS: " + nazivZaPretragu);
       vrsteKredita = k.ucitajVrsteKreditaPoNazivu(nazivZaPretragu);
        for (Vrstakredita v : vrsteKredita) {
              System.out.println("JOVANA" + v);
        }
      
        //vrsteKredita.add(new Vrstakredita(1, "naziv", new Valuta(2, "dsds")));
    

    }
   
    
   public void odbaciVrstuKredita() {
        nazivvrstekredita = null;
        idvalute = 0;
        nazivZaPretragu = null;
    }
    
     
  
    
    
 
    
  

    
}
