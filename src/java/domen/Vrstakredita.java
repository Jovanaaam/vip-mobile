/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Korisnik
 */
@Entity
@Table(name = "VRSTAKREDITA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Vrstakredita.findAll", query = "SELECT v FROM Vrstakredita v")
    , @NamedQuery(name = "Vrstakredita.findByIdvrstekredita", query = "SELECT v FROM Vrstakredita v WHERE v.idvrstekredita = :idvrstekredita")
    , @NamedQuery(name = "Vrstakredita.findByNazivvrstekredita", query = "SELECT v FROM Vrstakredita v WHERE v.nazivvrstekredita = :nazivvrstekredita")
    , @NamedQuery(name = "Vrstakredita.findLastId", query = "SELECT max(v.idvrstekredita) FROM Vrstakredita v"),

})
public class Vrstakredita implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDVRSTEKREDITA")
    Integer idvrstekredita;
    @Size(max = 100)
    @Column(name = "NAZIVVRSTEKREDITA")
    String nazivvrstekredita;
    @JoinColumn(name = "IDVALUTE", referencedColumnName = "IDVALUTE")
    @ManyToOne
    private Valuta idvalute;

    public Vrstakredita(Integer idvrstekredita, String nazivvrstekredita, Valuta idvalute) {
        this.idvrstekredita = idvrstekredita;
        this.nazivvrstekredita = nazivvrstekredita;
        this.idvalute = idvalute;
    }
        public Vrstakredita(String nazivvrstekredita, Valuta idvalute) {
       
        this.nazivvrstekredita = nazivvrstekredita;
        this.idvalute = idvalute;
    }


    
    public Vrstakredita() {
    }

    /*public Vrstakredita(Integer idvrstekredita) {
        this.idvrstekredita = idvrstekredita;
    }*/

    public Integer getIdvrstekredita() {
        return idvrstekredita;
    }

    public void setIdvrstekredita(Integer idvrstekredita) {
        this.idvrstekredita = idvrstekredita;
    }

    public String getNazivvrstekredita() {
        return nazivvrstekredita;
    }

    public void setNazivvrstekredita(String nazivvrstekredita) {
        this.nazivvrstekredita = nazivvrstekredita;
    }

    public Valuta getIdvalute() {
        return idvalute;
    }

    public void setIdvalute(Valuta idvalute) {
        this.idvalute = idvalute;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idvrstekredita != null ? idvrstekredita.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Vrstakredita)) {
            return false;
        }
        Vrstakredita other = (Vrstakredita) object;
        if ((this.idvrstekredita == null && other.idvrstekredita != null) || (this.idvrstekredita != null && !this.idvrstekredita.equals(other.idvrstekredita))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nazivvrstekredita;
    }

  
    
}
