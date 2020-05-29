/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soap;

import dbb.DBBroker;
import domen.Valuta;
import domen.Vrstakredita;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author Korisnik
 */
@WebService(serviceName = "WebServis")
public class WebServis {
    DBBroker dbb = new DBBroker();
    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "hello")
    public String hello(@WebParam(name = "name") String txt) {
        return "Hello " + txt + " !";
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "SacuvajVrstuKredita")
    public boolean SacuvajVrstuKredita(@WebParam(name = "nazivvrstekredita") String nazivvrstekredita, @WebParam(name = "idvalute") int idvalute) {
        boolean uspesno = false;

       try {
           dbb.otvoriKonekciju();
          dbb.pokreniTransakciju();
          uspesno = dbb.sacuvajVrstuKredita(new Vrstakredita(nazivvrstekredita, new Valuta(idvalute)));
            dbb.potvrdiTransakciju();
          dbb.zatvoriKonekciju();
       } catch (Exception e) {
          dbb.ponistiTransakciju();
           dbb.zatvoriKonekciju();
           e.printStackTrace();
        }

       return uspesno;
    }
}
