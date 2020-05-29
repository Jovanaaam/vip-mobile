/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

import com.sun.javafx.scene.control.skin.VirtualFlow;
import dbb.DBBroker;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import kontroler.KontrolerZahtevZaAS;

/**
 *
 * @author Korisnik
 */
@Entity
@Table(name = "ZAHTEVZAAS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Zahtevzaas.findAll", query = "SELECT z FROM Zahtevzaas z")
    , @NamedQuery(name = "Zahtevzaas.findByIdzahteva", query = "SELECT z FROM Zahtevzaas z WHERE z.idzahteva = :idzahteva")
    , @NamedQuery(name = "Zahtevzaas.findByDatum", query = "SELECT z FROM Zahtevzaas z WHERE z.datum = :datum")
    , @NamedQuery(name = "Zahtevzaas.findByOdobreno", query = "SELECT z FROM Zahtevzaas z WHERE z.odobreno = :odobreno")
     , @NamedQuery(name = "Zahtevzaas.findLastId", query = "SELECT max(z.idzahteva) FROM Zahtevzaas z")
})
public class Zahtevzaas implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDZAHTEVA")
    private Integer idzahteva;
    @Column(name = "DATUM")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datum;
    @Size(max = 1)
    @Column(name = "ODOBRENO")
    private String odobreno;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "zahtevzaas")
    private static List<Stavkazahtevazaas> stavkeZahtevaLista;
    @JoinColumn(name = "IDUGOVORA", referencedColumnName = "IDUGOVORA")
    @ManyToOne
    private Ugovor idugovora;
    @JoinColumn(name = "IDZAPOSLENOG", referencedColumnName = "IDZAPOSLENOG")
    @ManyToOne
    private Zaposleni idzaposlenog;

    public Zahtevzaas(Integer idzahteva, Date datum, String odobreno, List<Stavkazahtevazaas> stavkeZahtevaLista, Ugovor idugovora, Zaposleni idzaposlenog) {
        this.idzahteva = idzahteva;
        this.datum = datum;
        this.odobreno = odobreno;
        this.stavkeZahtevaLista = stavkeZahtevaLista;
        this.idugovora = idugovora;
        this.idzaposlenog = idzaposlenog;
    }

   
   
    

    public Zahtevzaas() {
        
        stavkeZahtevaLista = new ArrayList<>();
    }

    public Zahtevzaas(Integer idzahteva) {
        this.idzahteva = idzahteva;
    }

    public Integer getIdzahteva() {
        return idzahteva;
    }

    public void setIdzahteva(Integer idzahteva) {
        this.idzahteva = idzahteva;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public String getOdobreno() {
        return odobreno;
    }

    public void setOdobreno(String odobreno) {
        this.odobreno = odobreno;
    }

    @XmlTransient
    public List<Stavkazahtevazaas> getStavkeZahtevaLista() {
        return stavkeZahtevaLista;
    }

    public void setStavkeZahtevaLista(List<Stavkazahtevazaas> stavkeZahtevaLista) {
        this.stavkeZahtevaLista = stavkeZahtevaLista;
    }

    public Ugovor getIdugovora() {
        return idugovora;
    }

   

    public void setIdugovora(Ugovor idugovora) {
        this.idugovora = idugovora;
    }

    public Zaposleni getIdzaposlenog() {
        return idzaposlenog;
    }

    public void setIdzaposlenog(Zaposleni idzaposlenog) {
        this.idzaposlenog = idzaposlenog;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idzahteva != null ? idzahteva.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Zahtevzaas)) {
            return false;
        }
        Zahtevzaas other = (Zahtevzaas) object;
        if ((this.idzahteva == null && other.idzahteva != null) || (this.idzahteva != null && !this.idzahteva.equals(other.idzahteva))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "domen.Zahtevzaas[ idzahteva=" + idzahteva + " ]";
    }
    public void ubaciStavku(Servis servis, DBBroker dbb, Date rokisporuke, int lastID) {
        int rb = dajRB();
        System.out.println("rb " + rb);
        Stavkazahtevazaas sz = new Stavkazahtevazaas();
        StavkazahtevazaasPK pk = new StavkazahtevazaasPK();
        pk.setRbr(rb);
        pk.setIdzahteva(lastID);
        sz.setStatus("INSERT");
        sz.setStavkazahtevazaasPK(pk);
        sz.setRbr(rb);
        sz.setRokisporuke(rokisporuke);
        sz.setIdservisa(servis);
     
        this.getStavkeZahtevaLista().add(sz);
      System.out.println("br stavki: " + getStavkeZahtevaLista().size());
        dbb.postaviStatus(sz, "INSERT");
        
    }
    private int dajRB() {
     
        if (stavkeZahtevaLista.isEmpty()) {
            System.out.println("null je");
            return stavkeZahtevaLista.size() + 1;
        } else {

            int max = Integer.MIN_VALUE;
            System.out.println("max " + max);
            for (Stavkazahtevazaas stavka : stavkeZahtevaLista) {
                if (stavka.getStavkazahtevazaasPK().getRbr() > max) {
                    max = stavka.getStavkazahtevazaasPK().getRbr();
                }
            }
            System.out.println("max" + max);
            return max + 1;
        }
    }
     public Zahtevzaas izmeni(Stavkazahtevazaas sz) {
        for (Stavkazahtevazaas stavka : stavkeZahtevaLista) {
          
            if (stavka.getStavkazahtevazaasPK().getRbr() == sz.getStavkazahtevazaasPK().getRbr()) {
                System.out.println("usao je u izmenu stavke");
                stavka.setStatus("UPDATE");
                System.out.println("");
                stavka.setIdservisa(sz.getIdservisa());
                stavka.setRokisporuke(sz.getRokisporuke());
            }
        }
        return this;
    }
    
      public Zahtevzaas obrisi(int stavkaZaBrisanjeId, DBBroker dbb) {
          Stavkazahtevazaas stavkaZaBrisanje = new Stavkazahtevazaas();
        for (Stavkazahtevazaas sz : stavkeZahtevaLista) {
            if (sz.getStavkazahtevazaasPK().getRbr()== stavkaZaBrisanjeId) {
                System.out.println("dosao je da obrise stavku");
                sz.setStatus("DELETE");
                dbb.postaviStatus(sz, "DELETE");
                stavkaZaBrisanje = sz;
               
            }
        }
         stavkeZahtevaLista.remove(stavkaZaBrisanje);
        return this;
    }

   

    
      


    
}
