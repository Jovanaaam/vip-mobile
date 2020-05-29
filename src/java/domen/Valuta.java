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
@Table(name = "VALUTA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Valuta.findAll", query = "SELECT v FROM Valuta v")
    , @NamedQuery(name = "Valuta.findByIdvalute", query = "SELECT v FROM Valuta v WHERE v.idvalute = :idvalute")
    , @NamedQuery(name = "Valuta.findByNazivvalute", query = "SELECT v FROM Valuta v WHERE v.nazivvalute = :nazivvalute")})
public class Valuta implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDVALUTE")
    Integer idvalute;
    @Size(max = 100)
    @Column(name = "NAZIVVALUTE")
    String nazivvalute;
    @OneToMany(mappedBy = "idvalute")
    private Collection<Vrstakredita> vrstakreditaCollection;

    public Valuta() {
    } 

   public Valuta(Integer idvalute, String nazivvalute) {
        this.idvalute = idvalute;
        this.nazivvalute = nazivvalute;
        //this.vrstakreditaCollection = vrstakreditaCollection;
    }
  
    
    

    public Valuta(Integer idvalute) {
        this.idvalute = idvalute;
    }

    public Integer getIdvalute() {
        return idvalute;
    }

    public void setIdvalute(Integer idvalute) {
        this.idvalute = idvalute;
    }

    public String getNazivvalute() {
        return nazivvalute;
    }

    public void setNazivvalute(String nazivvalute) {
        this.nazivvalute = nazivvalute;
    }

    @XmlTransient
    public Collection<Vrstakredita> getVrstakreditaCollection() {
        return vrstakreditaCollection;
    }

    public void setVrstakreditaCollection(Collection<Vrstakredita> vrstakreditaCollection) {
        this.vrstakreditaCollection = vrstakreditaCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idvalute != null ? idvalute.hashCode() : 0);
        return hash;
        
            
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Valuta)) {
            return false;
        }
        Valuta other = (Valuta) object;
        if ((this.idvalute == null && other.idvalute != null) || (this.idvalute != null && !this.idvalute.equals(other.idvalute))) {
            return false;
        }
        return true;
    }
     @Override

     public String toString() {
        return idvalute + "";
    }
}
