/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mb;

import com.sun.javafx.scene.control.skin.VirtualFlow;
import domen.Valuta;
import domen.Vrstakredita;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import kontroler.KontrolerVrstaKredita;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;


/**
 *
 * @author Korisnik
 */
@ManagedBean
@RequestScoped

public class ObrisiVrstuKredita {
    KontrolerVrstaKredita k;
    static int idvrstekredita;
    String nazivvrstekredita;
    int idvalute;
    Vrstakredita slektovanaVrsta;
    int selektovanaVrstaID;
 int idvrstezabrisanje;
    Valuta selektovanaValuta;
 
   
    List<Valuta> listaValuta = new VirtualFlow.ArrayLinkedList<>();
    int vrstaZaIzmenu;
    String nazivZaPretragu;
 

    private static List<Vrstakredita> vrsteKredita = new ArrayList<>();

    public ObrisiVrstuKredita() {
        k = new KontrolerVrstaKredita();
       vrsteKredita = new ArrayList<>();
    
   
      
    }
     @PostConstruct
    public void ucitajVrste() {
   
     vrsteKredita = k.ucitajVrsteKredita();
    
       
   
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

   

    public Valuta getSelektovanaValuta() {
        return selektovanaValuta;
    }

    public void setSelektovanaValuta(Valuta selektovanaValuta) {
        this.selektovanaValuta = selektovanaValuta;
    }

  

    public List<Valuta> getListaValuta() {
        return listaValuta;
    }

    public void setListaValuta(List<Valuta> listaValuta) {
        this.listaValuta = listaValuta;
    }

   public void obrisiVrstuKredita(){
       
    if(idvrstekredita == 0){
    
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Greška u unosu","Morate izabrati vrstu kreditza za brisanje."));
            return;
       
    }   
          
       boolean uspesno =
                k.obrisiVrstuKredita(idvrstekredita);
     
       if (uspesno) {
          
          
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.addMessage(null, new FacesMessage("Uspešno brisanje", "Vrsta kredita je uspešno obrisana."));
          
            
           //resetujPolja();
         
       } else {
            System.out.println("Vrsta kredita nije uspesno izmenjena" +idvalute);
            
           FacesContext fc = FacesContext.getCurrentInstance();
            fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Greška pri brisanju","Vrsta kredita nije uspešno obrisana."));
        }
        
   }
    
    


    public Vrstakredita getSlektovanaVrsta() {
        return slektovanaVrsta;
    }
  public void onRowSelect(SelectEvent event) {
       
     
           Vrstakredita v = (Vrstakredita) event.getObject();
          slektovanaVrsta = v;
         
           idvrstekredita = v.getIdvrstekredita();
             
           
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

    public int getIdvrstezabrisanje() {
        return idvrstezabrisanje;
    }

    public void setIdvrstezabrisanje(int idvrstezabrisanje) {
        this.idvrstezabrisanje = idvrstezabrisanje;
    }
    

  
    public void vratiNazive(){
      if(nazivZaPretragu.trim().isEmpty()){
    
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Prazno polje","Morate izabrati uneti kriterijum po kojem pretrazujete."));
            return;
       
    }   
       vrsteKredita = k.ucitajVrsteKreditaPoNazivu(nazivZaPretragu);
       
     
      
    }
    
  
   
}
