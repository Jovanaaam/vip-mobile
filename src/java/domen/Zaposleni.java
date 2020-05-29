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
@Table(name = "ZAPOSLENI")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Zaposleni.findAll", query = "SELECT z FROM Zaposleni z")
    , @NamedQuery(name = "Zaposleni.findByIdzaposlenog", query = "SELECT z FROM Zaposleni z WHERE z.idzaposlenog = :idzaposlenog")
    , @NamedQuery(name = "Zaposleni.findByImeprezimezaposlenog", query = "SELECT z FROM Zaposleni z WHERE z.imeprezimezaposlenog = :imeprezimezaposlenog")})
public class Zaposleni implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDZAPOSLENOG")
    private Integer idzaposlenog;
    @Size(max = 300)
    @Column(name = "IMEPREZIMEZAPOSLENOG")
    private String imeprezimezaposlenog;
    @OneToMany(mappedBy = "idzaposlenog")
    private Collection<Zahtevzaas> zahtevzaasCollection;

    public Zaposleni() {
    }

    public Zaposleni(Integer idzaposlenog) {
        this.idzaposlenog = idzaposlenog;
    }

    public Integer getIdzaposlenog() {
        return idzaposlenog;
    }

    public void setIdzaposlenog(Integer idzaposlenog) {
        this.idzaposlenog = idzaposlenog;
    }

    public String getImeprezimezaposlenog() {
        return imeprezimezaposlenog;
    }

    public void setImeprezimezaposlenog(String imeprezimezaposlenog) {
        this.imeprezimezaposlenog = imeprezimezaposlenog;
    }

    @XmlTransient
    public Collection<Zahtevzaas> getZahtevzaasCollection() {
        return zahtevzaasCollection;
    }

    public void setZahtevzaasCollection(Collection<Zahtevzaas> zahtevzaasCollection) {
        this.zahtevzaasCollection = zahtevzaasCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idzaposlenog != null ? idzaposlenog.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Zaposleni)) {
            return false;
        }
        Zaposleni other = (Zaposleni) object;
        if ((this.idzaposlenog == null && other.idzaposlenog != null) || (this.idzaposlenog != null && !this.idzaposlenog.equals(other.idzaposlenog))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return imeprezimezaposlenog;
    }
    
}
