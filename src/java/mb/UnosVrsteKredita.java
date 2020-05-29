/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mb;

import java.io.Serializable;
import java.util.HashMap;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import kontroler.KontrolerVrstaKredita;


/**
 *
 * @author Korisnik
 */
@ManagedBean
@RequestScoped

public class UnosVrsteKredita{
    KontrolerVrstaKredita k;
    HashMap<String, Integer> valuteMapa;
    
    int idvrstekredita;
    String nazivvrstekredita;
    int idvalute;

    public UnosVrsteKredita() {
        k = new KontrolerVrstaKredita();
    }
     @PostConstruct
    public void popuniValute() {
        valuteMapa = k.ucitajValute();
    }
    
    public void unesiVrstuKredita() {
       if(nazivvrstekredita.trim().isEmpty()){
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Greška u unosu","Morate uneti naziv."));
            return;
       }
        
        boolean uspesno = k.sacuvajVrstuKredita(nazivvrstekredita, idvalute);
        
        if (uspesno) {
            System.out.println("Vrsta kredita je uspesno uneta");
            
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.addMessage(null, new FacesMessage("Uspešan unos", "Vrsta kredita "+nazivvrstekredita+" je uspešno uneta."));
            
            resetujPolja();
        } else {
            System.out.println("Vrsta kredita nije uspesno uneta");
            
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Greška u unosu","Vrsta kredita "+nazivvrstekredita+" nije uspešno uneta."));
        }
     
    }

    public KontrolerVrstaKredita getK() {
        return k;
    }

    public void setK(KontrolerVrstaKredita k) {
        this.k = k;
    }

    public HashMap<String, Integer> getValuteMapa() {
        return valuteMapa;
    }

    public void setValuteMapa(HashMap<String, Integer> valuteMapa) {
        this.valuteMapa = valuteMapa;
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
      private void resetujPolja() {
        nazivvrstekredita = null;
        idvalute = 0;
    }
       public void odbaciVrstuKredita() {
        nazivvrstekredita = null;
        idvalute = 0;
    }
    
    
    
}
