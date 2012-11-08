package br.com.models;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "permissoes_menus")
public class PermissaoMenu {

	@Id 
	@GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    @ManyToOne
	@JoinColumn(name="menus_id", referencedColumnName = "id")
    private Menu menu = new Menu();
    @ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="perfis_id", referencedColumnName = "id")
    private Perfil perfil = new Perfil();

   /* Getters e Setters */

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final PermissaoMenu other = (PermissaoMenu) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 11 * hash + this.id;
        return hash;
    }

    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	public Perfil getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}

	
}

