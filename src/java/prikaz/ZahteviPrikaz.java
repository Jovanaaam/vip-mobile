/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prikaz;

import domen.Servis;
import domen.StavkazahtevazaasPK;
import java.util.Date;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Korisnik
 */
@XmlRootElement
public class ZahteviPrikaz{
     @XmlElement
    private int idzahteva;
    @XmlElement
    private int rbr;
    @XmlElement
    private Servis servis;
    @XmlElement
    private Date rokisporuke;
     @XmlElement
   private StavkazahtevazaasPK pk;

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

    public Servis getServis() {
        return servis;
    }

    public void setServis(Servis servis) {
        this.servis = servis;
    }

    public Date getRokisporuke() {
        return rokisporuke;
    }

    public void setRokisporuke(Date rokisporuke) {
        this.rokisporuke = rokisporuke;
    }

    public StavkazahtevazaasPK getPk() {
        return pk;
    }

    public void setPk(StavkazahtevazaasPK pk) {
        this.pk = pk;
    }
     
     
            

 
    
}
