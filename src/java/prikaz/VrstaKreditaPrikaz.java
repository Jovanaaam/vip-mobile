/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prikaz;

import java.util.Date;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Korisnik
 */
@XmlRootElement
public class VrstaKreditaPrikaz {
     @XmlElement
    private int idvrstekredita;
    @XmlElement
    private String nazivvrstekredita;
    @XmlElement
    private ValutaPrikaz valuta;

    public VrstaKreditaPrikaz() {
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

    public ValutaPrikaz getValuta() {
        return valuta;
    }

    public void setValuta(ValutaPrikaz valuta) {
        this.valuta = valuta;
    }
    
    
}
