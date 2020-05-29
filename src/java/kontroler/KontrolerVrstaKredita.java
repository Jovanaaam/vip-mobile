/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kontroler;

import dbb.DBBroker;
import domen.Valuta;
import domen.Vrstakredita;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import prikaz.VrstaKreditaPrikaz;

/**
 *
 * @author Korisnik
 */
public class KontrolerVrstaKredita {
    
    DBBroker dbb;
    
     public KontrolerVrstaKredita() {
        dbb = new DBBroker();
       
    }
     public HashMap<String, Integer> ucitajValute() {
        HashMap<String, Integer> valuteMapa = new HashMap<>();
        List<Valuta> valuteLista = new ArrayList<>();
        try {
            dbb.otvoriKonekciju();
            valuteLista = dbb.ucitajValute();
            dbb.zatvoriKonekciju();
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (Valuta v : valuteLista) {
            valuteMapa.put(v.getNazivvalute(), v.getIdvalute());
        }

        return valuteMapa;
    }
  

    public boolean sacuvajVrstuKredita(String nazivvrstekredita, int idvalute) {
      
      return sacuvajVrstuKredita_1(nazivvrstekredita, idvalute);
    }
       private static boolean sacuvajVrstuKredita_1(java.lang.String nazivvrstekredita, int idvalute) {
        service.WebServis_Service service = new service.WebServis_Service();
        service.WebServis port = service.getWebServisPort();
        return port.sacuvajVrstuKredita(nazivvrstekredita, idvalute);
    }

    
     /*  private Vrstakredita kreirajVrstuKredita(String nazivvrstekredita, int idvalute) {
        Vrstakredita v = new Vrstakredita();
       v.setIdvalute(new Valuta(idvalute));
       v.setNazivvrstekredita(nazivvrstekredita);
        return v;
    }*/

    public List<Vrstakredita> ucitajVrsteKredita() {
    
        List<Vrstakredita> vrste = new ArrayList<>();
        try {
            dbb.otvoriKonekciju();
             dbb.pokreniTransakciju();
            vrste = dbb.ucitajVrsteKredita();
             dbb.potvrdiTransakciju();
            dbb.zatvoriKonekciju();
        } catch (Exception e) {
            dbb.ponistiTransakciju();
           dbb.zatvoriKonekciju();
            e.printStackTrace();
        }

      

        return vrste;
    
    }
    
     public List<Valuta> ucitajValute2() {
        List<Valuta> valute = new ArrayList<>();
        try {
            dbb.otvoriKonekciju();
            valute = dbb.ucitajValute();
            dbb.zatvoriKonekciju();
        } catch (Exception e) {
            e.printStackTrace();
        }

      

        return valute;
    }

    public boolean izmeniVrstuKredita(int selektovanaVrstaID, String selektovanaVrstaNaziv, Valuta selektovanaValuta) {
        boolean uspesno = false;

       try {
           dbb.otvoriKonekciju();
          dbb.pokreniTransakciju();
          
          uspesno = dbb.izmeniVrstuKredita(new Vrstakredita(selektovanaVrstaID, selektovanaVrstaNaziv, selektovanaValuta));
            dbb.potvrdiTransakciju();
          dbb.zatvoriKonekciju();
       } catch (Exception e) {
          dbb.ponistiTransakciju();
           dbb.zatvoriKonekciju();
           e.printStackTrace();
        }
return uspesno;
      
    }
    
      public List<Vrstakredita> ucitajVrsteKreditaPoNazivu(String naziv) {
    
        List<Vrstakredita> vrste = new ArrayList<>();
        try {
            dbb.otvoriKonekciju();
          
            vrste = dbb.ucitajVrsteKreditaPoNazivu(naziv);
        
            dbb.zatvoriKonekciju();
        } catch (Exception e) {
            e.printStackTrace();
           
          
        }

      

        return vrste;
    
    }

    public boolean obrisiVrstuKredita(int idvrstekredita) {
        dbb.otvoriKonekciju();
        dbb.pokreniTransakciju();

        boolean ret = dbb.obrisiVrstuKredita(idvrstekredita);

        if (ret) {
            dbb.potvrdiTransakciju();
            dbb.zatvoriKonekciju();
            return true;
        } else {
            dbb.ponistiTransakciju();
            dbb.zatvoriKonekciju();
            return false;
        }
    }

 
  
    

}
