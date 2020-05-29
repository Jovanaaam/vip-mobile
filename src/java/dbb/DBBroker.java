/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbb;

import com.sun.javafx.scene.control.skin.VirtualFlow;
import domen.Servis;
import domen.Stavkazahtevazaas;
import domen.Ugovor;
import domen.Valuta;
import domen.Vrstakredita;
import domen.Zahtevzaas;
import domen.Zaposleni;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author Korisnik
 */
public class DBBroker {
    EntityManagerFactory emf;
    EntityManager em;
    //FPIS-projekatPU
     Map<Stavkazahtevazaas, String> mapaStatusa = new HashMap<>();

     public DBBroker() {
        emf = Persistence.createEntityManagerFactory("FPIS-projekatPU");
    }

    public void otvoriKonekciju() {
       
        if (em == null || !em.isOpen()) {
            em = emf.createEntityManager();
        }
    }

    public void zatvoriKonekciju() {
        if (em.isOpen()) {
            em.close();
             
        }
      
    }

    public void pokreniTransakciju() {
        
        if (em.isOpen()) {
            em.getTransaction().begin();
        }
    }

    public void potvrdiTransakciju() {
       
        if (em.isOpen()) {
            em.getTransaction().commit();
        }
    }

    public void ponistiTransakciju() {
        if (em.isOpen()) {
            em.getTransaction().rollback();
        }
    }

  

    public List<Valuta> ucitajValute() {
        List<Valuta> valute = new ArrayList<>();

        if (em.isOpen()) {
            valute = em.createNamedQuery("Valuta.findAll").getResultList();
        }
        

        return valute;
    }
    
    public boolean sacuvajVrstuKredita(Vrstakredita vrsta) {
        int id = 1;
        try {
         
            try{
          
          id = em.createNamedQuery("Vrstakredita.findLastId", Integer.class).getSingleResult();
         System.out.println("id nakon poziva metode: " + id);
           id++;
            }catch(Exception e){
               e.getMessage();
           }
            System.out.println("postavicuL " + id);
            vrsta.setIdvrstekredita(id);
            vrsta.setIdvalute(vrsta.getIdvalute());
            if (em.isOpen()) {
                em.persist(vrsta);
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public List<Vrstakredita> ucitajVrsteKredita() {
         List<Vrstakredita> vrste = new ArrayList<>();

        if (em.isOpen()) {
            vrste = em.createNamedQuery("Vrstakredita.findAll").getResultList();
        }

        return vrste;
    }

    public boolean izmeniVrstuKredita(Vrstakredita v) {
       try {
        Query query = em.createNativeQuery("UPDATE VRSTAKREDITA SET NAZIVVRSTEKREDITA=?, IDVALUTE = ? WHERE IDVRSTEKREDITA=?");
        query.setParameter(1, v.getNazivvrstekredita());
        query.setParameter(2, v.getIdvalute().getIdvalute());
        query.setParameter(3, v.getIdvrstekredita());
        query.executeUpdate();

            em.merge(v);
           
        } catch (Exception e) {
            return false;
        }
       return true;
    }

    public List<Vrstakredita> ucitajVrsteKreditaPoNazivu(String naziv) {
    
        List<Vrstakredita> vrste = new ArrayList<>();

        if (em.isOpen()) {
           vrste = em.createNamedQuery("Vrstakredita.findByNazivvrstekredita").setParameter("nazivvrstekredita", naziv).getResultList();
         

        }

        return vrste;
    }

    public List<Ugovor> ucitajUgovore() {
         List<Ugovor> ugovori = new ArrayList<>();

        if (em.isOpen()) {
            ugovori = em.createNamedQuery("Ugovor.findAll").getResultList();
        }

        return ugovori;
    }

    public List<Zaposleni> ucitajZaposlene() {
         List<Zaposleni> zaposleni = new ArrayList<>();

        if (em.isOpen()) {
            zaposleni = em.createNamedQuery("Zaposleni.findAll").getResultList();
        }

        return zaposleni;
    }

    public List<Servis> ucitajServise() {
          List<Servis> servisi = new ArrayList<>();

        if (em.isOpen()) {
            servisi = em.createNamedQuery("Servis.findAll").getResultList();
        }

        return servisi;
    }

    public void postaviStatus(Stavkazahtevazaas sz, String update) {
        
        mapaStatusa.put(sz, update);
      
    }

    public Servis vratiServisSaId(int idservisa) throws Exception {
       try {
           Servis s = new Servis();
           System.out.println("SALJEM MU ID: " + idservisa);
             if (em.isOpen()) {
            s = em.createNamedQuery("Servis.findByIdservisa", Servis.class).setParameter("idservisa", idservisa).getSingleResult();
            System.out.println("ISPIS: " +s);
             }
            return s;
        } catch (Exception e) {
            throw new Exception("Ne postoji servis sa unetom sifrom!");
        }
    }

    public Ugovor vratiUgovorSaId(int idugovora) throws Exception {
         try {
            Ugovor u = em.createNamedQuery("Ugovor.findByIdugovora", Ugovor.class).setParameter("idugovora", idugovora).getSingleResult();
            return u;
        } catch (Exception e) {
            throw new Exception("Ne postoji ugovor sa unetom sifrom!");
        }
    }

    public Zaposleni vratiZaposlenogSaId(int idzaposlenog) throws Exception {
        try {
            Zaposleni z = em.createNamedQuery("Zaposleni.findByIdzaposlenog", Zaposleni.class).setParameter("idzaposlenog", idzaposlenog).getSingleResult();
            return z;
        } catch (Exception e) {
            throw new Exception("Ne postoji zaposleni sa unetom sifrom!");
        }
    }

    public void ocistiMapu() {
      
        mapaStatusa.clear();
       
    }

    public Ugovor vratiUgovorSaIdObj(Ugovor u) throws Exception {
         try {
            Ugovor ugo = em.createNamedQuery("Ugovor.findByIdugovora", Ugovor.class).setParameter("idugovora", u.getIdugovora()).getSingleResult();
            return ugo;
        } catch (Exception e) {
            throw new Exception("Ne postoji ugovor sa unetim ID-em!");
        }
    }

    public Zaposleni vratiZaposlenogSaIdObj(Zaposleni z) throws Exception {
        try {
            Zaposleni zap = em.createNamedQuery("Zaposleni.findByIdzaposlenog", Zaposleni.class).setParameter("idzaposlenog", z.getIdzaposlenog()).getSingleResult();
            return zap;
        } catch (Exception e) {
            throw new Exception("Ne postoji zaposleni sa unetim ID-em!");
        }
    }

    public boolean zapamtiZahtev(Zahtevzaas zahtev) {
        
        Query query = em.createNativeQuery("INSERT INTO zahtevzaas (idzahteva, datum, odobreno, idzaposlenog, idugovora) VALUES(?, ?,?,?,?)");
        query.setParameter(1, zahtev.getIdzahteva());
       
        query.setParameter(2, zahtev.getDatum());
        
        query.setParameter(3, zahtev.getOdobreno());
        query.setParameter(4, zahtev.getIdzaposlenog().getIdzaposlenog());
        query.setParameter(5, zahtev.getIdugovora().getIdugovora());
        query.executeUpdate();
        if(em.isOpen()){
        em.merge(zahtev);
        }
        boolean ret = zapamtiNovu(zahtev);
        
        if (ret) {
            em.merge(zahtev);
            return true;
        } else {
            return false;
        }
     
    }

    public Zahtevzaas vratiZahtevSaId(int idzahteva) throws Exception {
     try {
            Zahtevzaas z = em.createNamedQuery("Zahtevzaas.findByIdzahteva", Zahtevzaas.class).setParameter("idzahteva", idzahteva).getSingleResult();
            return z;
        } catch (Exception e) {
            throw new Exception("Ne postoji zahtev sa unetom sifrom!");
        }
    }

    public boolean zapamtiSve(Zahtevzaas zahtev) {
        boolean ret = zapamtiZ(zahtev);
        System.out.println("ret zapamti sve " + ret);
        if (ret) {

                  if (em.isOpen()) {
            em.merge(zahtev);
                  }
            return true;
        } else {
            return false;
        }
    }
    
    
    public boolean zapamtiZ(Zahtevzaas z) {
        try {
            if (mapaStatusa.entrySet().isEmpty()) {

              
                return true;
            }

            for (Map.Entry<Stavkazahtevazaas, String> pair : mapaStatusa.entrySet()) {
                System.out.println("mapa" + mapaStatusa.entrySet());
                System.out.println("pair" + pair);
                Stavkazahtevazaas stavka = pair.getKey();
                String status = pair.getValue();
                System.out.println("stavka " + stavka + " status " + status);
                if (status.equals("UPDATE")) {
                    em.merge(stavka);
                    System.out.println("merge uradjen");
                } else if (status.equals("DELETE")) {
                          if (em.isOpen()) {
                    stavka = em.merge(stavka);
                    em.remove(stavka);
                          }
                    //z.getStavkeZahtevaLista().remove(stavka);
                    System.out.println("remove uradjen");
                } else {
                          if (em.isOpen()) {
                    em.persist(stavka);
                          }
                    System.out.println("persist uradjen");
                }
            }

            mapaStatusa.clear();
            System.out.println("mapa" + mapaStatusa.entrySet());
            return true;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    private boolean zapamtiNovu(Zahtevzaas zahtev) {
        try {

            for (Stavkazahtevazaas stavka : zahtev.getStavkeZahtevaLista()) {
                      if (em.isOpen()) {
                em.persist(stavka);
                      }
            }

            mapaStatusa.clear();
            System.out.println("Ocisti mapu " + mapaStatusa.entrySet());
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public int lastIDZahtev() {
       
         int id = 1;
       try{
       
   
          id = em.createNamedQuery("Zahtevzaas.findLastId", Integer.class).getSingleResult();
             System.out.println("id nakon poziva metode: " + id);
          id++;
       
       } catch(Exception e){
           e.getMessage();
       } 
      
         System.out.println("poslacu dalje kontroloru: " + id);
 return id;
       
    }

    public List<Zahtevzaas> vratiZahtevePoDatumu(java.util.Date datumPretraga) throws Exception {
        List<Zahtevzaas> zahtevi = new ArrayList<>();
        try {
            zahtevi = em.createNamedQuery("Zahtevzaas.findByDatum", Zahtevzaas.class).setParameter("datum", datumPretraga).getResultList();
            return zahtevi;
        } catch (Exception e) {
            throw new Exception("Ne postoje datumi nakon unetog datuma!");
        }
    }

 public List<Stavkazahtevazaas> vratistavke() throws Exception{
    List<Stavkazahtevazaas> stavke = new ArrayList<>();
        try {
            stavke = em.createNamedQuery("Stavkazahtevazaas.findAll", Stavkazahtevazaas.class).getResultList();
            return stavke;
        } catch (Exception e) {
            throw new Exception("Ne postoje datumi nakon unetog datuma!");
        }
       
   }

    public boolean obrisiVrstuKredita(int idvrstekredita) {
         try {
            Vrstakredita v = em.find(Vrstakredita.class, idvrstekredita);
          
            v = em.merge(v);

            em.remove(v);

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean obrisiZahtev(int idzahteva) {
      try {
            Zahtevzaas z = em.find(Zahtevzaas.class, idzahteva);
          
            z = em.merge(z);

            em.remove(z);

            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
