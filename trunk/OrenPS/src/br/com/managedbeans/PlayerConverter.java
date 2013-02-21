package br.com.managedbeans;

import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import br.com.models.Usuario;

@FacesConverter(value="player")
public class PlayerConverter implements Converter {

    public static List<Usuario> playerDB;

    static {
        playerDB = new ArrayList<Usuario>();

        playerDB.add(new Usuario("Messi", 10, "aristo", "messi@gmail.com"));
        playerDB.add(new Usuario("Bojan", 9, "aristo", "Bojan@gmail.com"));
        playerDB.add(new Usuario("Iniesta", 8, "aristo", "Iniesta@gmail.com"));
        playerDB.add(new Usuario("Villa", 7, "aristo", "Villa@gmail.com"));
        playerDB.add(new Usuario("Xavi", 6, "aristo", "Xavi@gmail.com"));
        playerDB.add(new Usuario("Puyol", 5, "aristo", "Puyol@gmail.com"));
        playerDB.add(new Usuario("Afellay", 20, "aristo", "Afellay@gmail.com"));
        playerDB.add(new Usuario("Abidal", 22, "aristo", "Abidal@gmail.com"));
        playerDB.add(new Usuario("Alves", 2, "aristo", "Alves@gmail.com"));
        playerDB.add(new Usuario("Pique", 3, "aristo", "Pique@gmail.com"));
        playerDB.add(new Usuario("Keita", 15, "aristo", "Keita@gmail.com"));
        playerDB.add(new Usuario("Adriano", 21, "aristo", "Adriano@gmail.com"));
        playerDB.add(new Usuario("Valdes", 1, "aristo", "Valdes@gmail.com"));
    }

    public Object getAsObject(FacesContext facesContext, UIComponent component, String submittedValue) {
        if (submittedValue.trim().equals("")) {
            return null;
        } else {
            try {
                int number = Integer.parseInt(submittedValue);

                for (Usuario p : playerDB) {
                    if (p.getId() == number) {
                        return p;
                    }
                }
               
            } catch(NumberFormatException exception) {
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid player"));
            }
        }

        return null;
    }

    public String getAsString(FacesContext facesContext, UIComponent component, Object value) {
        if (value == null || value.equals("")) {
            return "";
        } else {
            return String.valueOf(((Usuario) value).getId());
        }
    }
}

