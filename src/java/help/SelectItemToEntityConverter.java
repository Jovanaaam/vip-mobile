/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package help;

import konvertor.ABaseEntity;
import java.lang.annotation.Annotation;
import java.util.List;
import java.util.function.Predicate;
import javax.faces.component.UIComponent;
import javax.faces.component.UISelectItems;
import javax.faces.context.FacesContext;
import javax.faces.convert.FacesConverter;
import javax.persistence.Converter;

/**
 *
 * @author Korisnik
 */
@FacesConverter(value = "SelectItemToEntityConverter")
public class SelectItemToEntityConverter implements Converter {

    public Object getAsObject(FacesContext ctx, UIComponent comp, String value) {
        Object o = null;
        if (!(value == null || value.isEmpty())) {
            o = this.getSelectedItemAsEntity(comp, value);
        }
        return o;
    }

    public String getAsString(FacesContext ctx, UIComponent comp, Object value) {
        String s = "";
        if (value != null) {
            s = ((ABaseEntity) value).getIdentifier().toString();
        }
        return s;
    }

    private ABaseEntity getSelectedItemAsEntity(UIComponent comp, String value) {
        ABaseEntity item = null;

        List<ABaseEntity> selectItems = null;
        for (UIComponent uic : comp.getChildren()) {
            if (uic instanceof UISelectItems) {
                Long itemId = Long.valueOf(value);
                selectItems = (List<ABaseEntity>) ((UISelectItems) uic).getValue();

                if (itemId != null && selectItems != null && !selectItems.isEmpty()) {
                    Predicate<ABaseEntity> predicate = i -> i.getIdentifier().equals(itemId);
                    item = selectItems.stream().filter(predicate).findFirst().orElse(null);
                }
            }
        }

        return item;
    }

    @Override
    public boolean autoApply() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Class<? extends Annotation> annotationType() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

  
}