/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Korisnik
 */
@Entity
@Table(name = "SERVIS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Servis.findAll", query = "SELECT s FROM Servis s")
    , @NamedQuery(name = "Servis.findByIdservisa", query = "SELECT s FROM Servis s WHERE s.idservisa = :idservisa")
    , @NamedQuery(name = "Servis.findByNazivservisa", query = "SELECT s FROM Servis s WHERE s.nazivservisa = :nazivservisa")})
public class Servis implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDSERVISA")
    private Integer idservisa;
    @Size(max = 300)
    @Column(name = "NAZIVSERVISA")
    private String nazivservisa;
    @OneToMany(mappedBy = "idservisa")
    private Collection<Stavkazahtevazaas> stavkazahtevazaasCollection;

    public Servis(Integer idservisa, String nazivservisa) {
        this.idservisa = idservisa;
        this.nazivservisa = nazivservisa;
    }

    public Servis() {
    }

    public Servis(Integer idservisa) {
        this.idservisa = idservisa;
    }

    public Integer getIdservisa() {
        return idservisa;
    }

    public void setIdservisa(Integer idservisa) {
        this.idservisa = idservisa;
    }

    public String getNazivservisa() {
        return nazivservisa;
    }

    public void setNazivservisa(String nazivservisa) {
        this.nazivservisa = nazivservisa;
    }

    @XmlTransient
    public Collection<Stavkazahtevazaas> getStavkazahtevazaasCollection() {
        return stavkazahtevazaasCollection;
    }

    public void setStavkazahtevazaasCollection(Collection<Stavkazahtevazaas> stavkazahtevazaasCollection) {
        this.stavkazahtevazaasCollection = stavkazahtevazaasCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idservisa != null ? idservisa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Servis)) {
            return false;
        }
        Servis other = (Servis) object;
        if ((this.idservisa == null && other.idservisa != null) || (this.idservisa != null && !this.idservisa.equals(other.idservisa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nazivservisa;
    }
    
}
