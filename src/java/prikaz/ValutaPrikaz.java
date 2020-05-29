package prikaz;


import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Korisnik
 */
@XmlRootElement
public class ValutaPrikaz {
    @XmlElement
    private int idvalute;
    @XmlElement
    private String nazivvalute;

    public ValutaPrikaz() {
    }

    
    public int getIdvalute() {
        return idvalute;
    }

    public void setIdvalute(int idvalute) {
        this.idvalute = idvalute;
    }

    public String getNazivvalute() {
        return nazivvalute;
    }

    public void setNazivvalute(String nazivvalute) {
        this.nazivvalute = nazivvalute;
    }
    
    
}
