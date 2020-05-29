/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

import dbb.DBBroker;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Korisnik
 */
@Entity
@Table(name = "STAVKAZAHTEVAZAAS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Stavkazahtevazaas.findAll", query = "SELECT s FROM Stavkazahtevazaas s")
    , @NamedQuery(name = "Stavkazahtevazaas.findByIdzahteva", query = "SELECT s FROM Stavkazahtevazaas s WHERE s.stavkazahtevazaasPK.idzahteva = :idzahteva")
    , @NamedQuery(name = "Stavkazahtevazaas.findByRbr", query = "SELECT s FROM Stavkazahtevazaas s WHERE s.stavkazahtevazaasPK.rbr = :rbr")
    , @NamedQuery(name = "Stavkazahtevazaas.findByRokisporuke", query = "SELECT s FROM Stavkazahtevazaas s WHERE s.rokisporuke = :rokisporuke")})
public class Stavkazahtevazaas implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected StavkazahtevazaasPK stavkazahtevazaasPK;
    @Column(name = "ROKISPORUKE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date rokisporuke;
    @JoinColumn(name = "IDSERVISA", referencedColumnName = "IDSERVISA")
    @ManyToOne
    private Servis idservisa;
    @JoinColumn(name = "IDZAHTEVA", referencedColumnName = "IDZAHTEVA", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Zahtevzaas zahtevzaas;
    
    @Transient
    private int rbr;
    @Transient
    private String status;

    public Stavkazahtevazaas() {
    }

    public Stavkazahtevazaas(StavkazahtevazaasPK stavkazahtevazaasPK, Date rokisporuke, Servis idservisa, Zahtevzaas zahtevzaas) {
        this.stavkazahtevazaasPK = stavkazahtevazaasPK;
        this.rokisporuke = rokisporuke;
        this.idservisa = idservisa;
        this.zahtevzaas = zahtevzaas;
        
        
    }
    
    
    

    public Stavkazahtevazaas(StavkazahtevazaasPK stavkazahtevazaasPK) {
        this.stavkazahtevazaasPK = stavkazahtevazaasPK;
    }

    public Stavkazahtevazaas(Integer idzahteva, Integer rbr) {
        this.stavkazahtevazaasPK = new StavkazahtevazaasPK(idzahteva, rbr);
    }

    public StavkazahtevazaasPK getStavkazahtevazaasPK() {
        return stavkazahtevazaasPK;
    }

    public void setStavkazahtevazaasPK(StavkazahtevazaasPK stavkazahtevazaasPK) {
        this.stavkazahtevazaasPK = stavkazahtevazaasPK;
    }

    public Date getRokisporuke() {
        return rokisporuke;
    }

    public void setRokisporuke(Date rokisporuke) {
        this.rokisporuke = rokisporuke;
    }

    public Servis getIdservisa() {
        return idservisa;
    }

    public void setIdservisa(Servis idservisa) {
        this.idservisa = idservisa;
    }

    public Zahtevzaas getZahtevzaas() {
        return zahtevzaas;
    }

    public void setZahtevzaas(Zahtevzaas zahtevzaas) {
        this.zahtevzaas = zahtevzaas;
    }

    public int getRbr() {
        return rbr;
    }

    public void setRbr(int rbr) {
        this.rbr = rbr;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (stavkazahtevazaasPK != null ? stavkazahtevazaasPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Stavkazahtevazaas)) {
            return false;
        }
        Stavkazahtevazaas other = (Stavkazahtevazaas) object;
        if ((this.stavkazahtevazaasPK == null && other.stavkazahtevazaasPK != null) || (this.stavkazahtevazaasPK != null && !this.stavkazahtevazaasPK.equals(other.stavkazahtevazaasPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "domen.Stavkazahtevazaas[ stavkazahtevazaasPK=" + stavkazahtevazaasPK + " ]";
    }
    
      public Zahtevzaas izmeni(int idZahtevaZaAS, int redniBroj, Servis idservisa, DBBroker dbb, Date rokisporuke, Zahtevzaas z) {
        System.out.println("Izmeni metoda");
       Zahtevzaas zahtev = new Zahtevzaas(idZahtevaZaAS);
        Stavkazahtevazaas sz = new Stavkazahtevazaas();
        sz.setIdservisa(idservisa);
        sz.setRokisporuke(rokisporuke);
      
        sz.setStatus("UPDATE");
       
        StavkazahtevazaasPK pk = new StavkazahtevazaasPK(idZahtevaZaAS, redniBroj);
        sz.setStavkazahtevazaasPK(pk);
     
        dbb.postaviStatus(sz, "UPDATE");
        zahtev = z.izmeni(sz);
       return zahtev;
    }
}
