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
@Table(name = "UGOVOR")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ugovor.findAll", query = "SELECT u FROM Ugovor u")
    , @NamedQuery(name = "Ugovor.findByIdugovora", query = "SELECT u FROM Ugovor u WHERE u.idugovora = :idugovora")
    , @NamedQuery(name = "Ugovor.findByNazivugovora", query = "SELECT u FROM Ugovor u WHERE u.nazivugovora = :nazivugovora")})
public class Ugovor implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDUGOVORA")
    private Integer idugovora;
    @Size(max = 300)
    @Column(name = "NAZIVUGOVORA")
    private String nazivugovora;
    @OneToMany(mappedBy = "idugovora")
    private Collection<Zahtevzaas> zahtevzaasCollection;

    public Ugovor() {
    }

    public Ugovor(Integer idugovora) {
        this.idugovora = idugovora;
    }

    public Integer getIdugovora() {
        return idugovora;
    }

    public void setIdugovora(Integer idugovora) {
        this.idugovora = idugovora;
    }

    public String getNazivugovora() {
        return nazivugovora;
    }

    public void setNazivugovora(String nazivugovora) {
        this.nazivugovora = nazivugovora;
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
        hash += (idugovora != null ? idugovora.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ugovor)) {
            return false;
        }
        Ugovor other = (Ugovor) object;
        if ((this.idugovora == null && other.idugovora != null) || (this.idugovora != null && !this.idugovora.equals(other.idugovora))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nazivugovora;
    }
    
}
