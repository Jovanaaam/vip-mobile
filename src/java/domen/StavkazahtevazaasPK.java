/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Korisnik
 */
@Embeddable
public class StavkazahtevazaasPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "IDZAHTEVA")
    private Integer idzahteva;
    @Basic(optional = false)
    @NotNull
    @Column(name = "RBR")
    private Integer rbr;

    public StavkazahtevazaasPK() {
    }

    public StavkazahtevazaasPK(Integer idzahteva, Integer rbr) {
        this.idzahteva = idzahteva;
        this.rbr = rbr;
    }

    public Integer getIdzahteva() {
        return idzahteva;
    }

    public void setIdzahteva(Integer idzahteva) {
        this.idzahteva = idzahteva;
    }

    public Integer getRbr() {
        return rbr;
    }

    public void setRbr(Integer rbr) {
        this.rbr = rbr;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idzahteva;
         hash += (int) rbr;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof StavkazahtevazaasPK)) {
            return false;
        }
        StavkazahtevazaasPK other = (StavkazahtevazaasPK) object;
        if ((this.idzahteva == null && other.idzahteva != null) || (this.idzahteva != null && !this.idzahteva.equals(other.idzahteva))) {
            return false;
        }
        if ((this.rbr == null && other.rbr != null) || (this.rbr != null && !this.rbr.equals(other.rbr))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "domen.StavkazahtevazaasPK[ idzahteva=" + idzahteva + ", rbr=" + rbr + " ]";
    }
    
}
