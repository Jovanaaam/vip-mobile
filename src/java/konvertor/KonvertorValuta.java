/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package konvertor;


import domen.Valuta;
import java.math.BigInteger;
import java.util.List;
import java.util.function.Predicate;
import javax.faces.component.UIComponent;
import javax.faces.component.UISelectItems;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

//@FacesConverter(forClass = domen.Valuta.class, value="Help")
public class KonvertorValuta implements Converter{

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        Object o = null;
           List<Valuta> selectItems = null;
             Valuta valuta = null;
        if (!(value == null || value.isEmpty())) {
       
           
             for (UIComponent uic : component.getChildren()) {
            if (uic instanceof UISelectItems) {
                Long itemId = Long.valueOf(value);
                selectItems = (List<Valuta>) ((UISelectItems) uic).getValue();

                if (itemId != null && selectItems != null && !selectItems.isEmpty()) {
                    Predicate<Valuta> predicate = i -> i.getIdvalute().equals(itemId);
                    valuta = selectItems.stream().filter(predicate).findFirst().orElse(null);
                    o = valuta;
                }
            }
        }
            System.out.println("Magacin? " + valuta);
        }
        return o;
       
       
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
     String s = "";
        if (value != null) {
            s = ((Valuta) value).getIdvalute().toString();
        }
        return s;
       
    }
 

   
   
    
}