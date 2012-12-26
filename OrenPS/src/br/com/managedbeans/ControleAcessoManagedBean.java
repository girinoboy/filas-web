package br.com.managedbeans;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.lang.StringUtils;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

import br.com.dao.MenuDAO;
import br.com.dao.PerfilDAO;
import br.com.dao.ControleAcessoDAO;
import br.com.models.Menu;
import br.com.models.Perfil;
import br.com.models.PermissaoMenu;
import br.com.models.Usuario;

@ManagedBean
@ViewScoped
public class ControleAcessoManagedBean {

    private TreeNode root;
    private TreeNode[] nosSelecionados;
    private Usuario usuarioSelecionado = new Usuario();
    private List<Usuario> listaUsuario = new ArrayList<Usuario>();
    private List<PermissaoMenu> listaPermissao = new ArrayList<PermissaoMenu>();
    private List<Menu> listaMenu = new ArrayList<Menu>();
    private List<Menu> menusPermitidos = new ArrayList<Menu>();
    private List<Menu> menusUsuario = new ArrayList<Menu>();
    private ControleAcessoDAO permissaoDAO = new ControleAcessoDAO();
    private MenuDAO menuDAO = new MenuDAO();
    private List<Perfil> listaPerfil = new ArrayList<Perfil>();
    private PerfilDAO perfilDAO = new PerfilDAO();
    private Perfil perfil = new Perfil();
    
    public ControleAcessoManagedBean() {
        try {
            listaPerfil = perfilDAO.list();
        } catch (Exception ex) {
            Logger.getLogger(ControleAcessoManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

/* Getters e Setters */
    /*
    private boolean isFilho(String[] indicesPai, String[] indicesFilho) {
        boolean isFilho = false;
        int i = 0;
        if (indicesPai.length == indicesFilho.length - 1) {
            for (String string : indicesPai) {
                isFilho = string.equals(indicesFilho[i]);
                if (!isFilho) {
                    break;
                }
                i++;
            }
        }
        return isFilho;
    }*/
/*
    private void carregarFilhos(List<Menu> menus, Menu menu, TreeNode treeNode) {
        for (Menu m : menus) {
            String[] indicesPai = menu.getIndice().split("\\.");
            String[] indicesFilho = m.getIndice().split("\\.");
            TreeNode tr;
            if ((indicesFilho.length > indicesPai.length)) {
                if (isFilho(indicesPai, indicesFilho)) {
                    if (m.getUrl().trim().equals("")) {
                        tr = new DefaultTreeNode(m, treeNode);
                        carregarFilhos(menus, m, tr);
                    } else {
                        tr = new DefaultTreeNode(m, treeNode);
                        if (isPermitido(m)) {
                            tr.setSelected(true);
                        }
                    }
                }
            }
        }
    }
    */
    
    public void carregarFilhos(Menu menu, TreeNode treeNode) throws Exception{
		List<Menu> listaMenu = menuDAO.listByIdSub(menu.getId());
		TreeNode tr;
		for(Menu m:listaMenu){
			if(StringUtils.isBlank(m.getUrl()) && StringUtils.isBlank(m.getPagina()) ){
				tr = new DefaultTreeNode(m, treeNode);
				carregarFilhos(m, tr);
				
			}else{
				tr = new DefaultTreeNode(m, treeNode);
				//verifica se é permitidos apenas par ao ultimo node
                if (isPermitido(m)) {
                    tr.setSelected(true);
                }
			}
		}
	}

    private boolean isPermitido(Menu menu) {
        boolean retorno = false;
        for (Menu m : menusPermitidos) {
            if (m.getId().equals(menu.getId())) {
                retorno = true;
                break;
            }
        }
        return retorno;
    }

    public void carregaPermissoesUsuario() {
        permissaoDAO = new ControleAcessoDAO();
        menuDAO = new MenuDAO();
        root = new DefaultTreeNode("Qp", null);
        menusPermitidos = new ArrayList<Menu>();
        try {
            listaPermissao = permissaoDAO.listByIdPerfil(perfil.getId());
            listaMenu = menuDAO.listCabecalho();
            //pega todos os menus permitidos para esse usuario
            for (PermissaoMenu permissao : listaPermissao) {
                menusPermitidos.add(permissao.getMenu());
            }
            if (perfil.getId() != 0) {
                for (Menu menu : listaMenu) {
                        TreeNode treeNode = new DefaultTreeNode(menu, root);
                        if (isPermitido(menu)) {
                        	treeNode.setSelected(true);
                        }
                        carregarFilhos(menu, treeNode);
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(ControleAcessoManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void salvaPermissoes() {
        if (perfil.getId() == 0) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Nenhum usuário SELECIONADO", "Erro"));
        } else {
            try {
            	permissaoDAO.deletePermissaoPorPerfil(perfil.getId());
                for (TreeNode no : nosSelecionados) {
                    if (no.isLeaf()) {
                        Menu folha = (Menu) no.getData();
                        PermissaoMenu permissaoMenu = new PermissaoMenu();
                        permissaoMenu.setMenu(folha);
                        permissaoMenu.setPerfil(perfil);

                        permissaoDAO.save(permissaoMenu);
                        salvaPai(no);
                    }
                }
                carregaPermissoesUsuario();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Permissões Salvas", "Permissões Salvas"));
            } catch (Exception ex) {
                Logger.getLogger(ControleAcessoManagedBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void salvaPai(TreeNode no) throws Exception {
        TreeNode tr = no.getParent();
        if (!tr.equals(root)) {
            PermissaoMenu p;
            try {
                p = permissaoDAO.buscaPorMenuPerfil(((Menu) tr.getData()).getId(), perfil.getId());
                if (p == null) {
                    PermissaoMenu permissaoMenu = new PermissaoMenu();
                    permissaoMenu.setPerfil(perfil);
                    permissaoMenu.setMenu((Menu) tr.getData());
                    permissaoDAO.save(permissaoMenu);
                }
                salvaPai(tr);
            } catch (SQLException ex) {
                Logger.getLogger(ControleAcessoManagedBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

	public TreeNode getRoot() {
		return root;
	}

	public void setRoot(TreeNode root) {
		this.root = root;
	}

	public TreeNode[] getNosSelecionados() {
		return nosSelecionados;
	}

	public void setNosSelecionados(TreeNode[] nosSelecionados) {
		this.nosSelecionados = nosSelecionados;
	}

	public Usuario getUsuarioSelecionado() {
		return usuarioSelecionado;
	}

	public void setUsuarioSelecionado(Usuario usuarioSelecionado) {
		this.usuarioSelecionado = usuarioSelecionado;
	}

	public List<Usuario> getListaUsuario() {
		return listaUsuario;
	}

	public void setListaUsuario(List<Usuario> listaUsuario) {
		this.listaUsuario = listaUsuario;
	}

	public List<PermissaoMenu> getListaPermissao() {
		return listaPermissao;
	}

	public void setListaPermissao(List<PermissaoMenu> listaPermissao) {
		this.listaPermissao = listaPermissao;
	}

	public List<Menu> getListaMenu() {
		return listaMenu;
	}

	public void setListaMenu(List<Menu> listaMenu) {
		this.listaMenu = listaMenu;
	}

	public List<Menu> getMenusPermitidos() {
		return menusPermitidos;
	}

	public void setMenusPermitidos(List<Menu> menusPermitidos) {
		this.menusPermitidos = menusPermitidos;
	}

	public List<Menu> getMenusUsuario() {
		return menusUsuario;
	}

	public void setMenusUsuario(List<Menu> menusUsuario) {
		this.menusUsuario = menusUsuario;
	}

	public ControleAcessoDAO getPermissaoDAO() {
		return permissaoDAO;
	}

	public void setPermissaoDAO(ControleAcessoDAO permissaoDAO) {
		this.permissaoDAO = permissaoDAO;
	}

	public MenuDAO getMenuDAO() {
		return menuDAO;
	}

	public void setMenuDAO(MenuDAO menuDAO) {
		this.menuDAO = menuDAO;
	}

	public List<Perfil> getListaPerfil() {
		return listaPerfil;
	}

	public void setListaPerfil(List<Perfil> listaPerfil) {
		this.listaPerfil = listaPerfil;
	}

	public Perfil getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}
}


