package br.com.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;

import br.com.factory.HibernateUtility;
import br.com.models.Menu;

/**
 *
 * @author Marcleonio
 */
public class MenuDAO extends GenericoDAO<Menu, Serializable>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//Connection con;
	//String sql;
	/*
	private final String COL_ID = "id";
    private final String COL_DESCRICAO = "descricao";
    private final String COL_URL = "url";
    private final String COL_ICONE = "icone";
    private final String COL_INDICE = "indice";*/
/*
	public boolean verificaSubMenu(Menu menu) {
		sql = "select count(*) as total from tab_menus where tab_menus_id=?";
		con = ConnectionFactory.getConnection();
		int t = 0;
		try {
			PreparedStatement pstm;
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, menu.getId());
			ResultSet rs = pstm.executeQuery();
			if (rs.next()) {
				t = rs.getInt("total");
			}
		} catch (SQLException ex) {
			Logger.getLogger(MenuDAO.class.getName()).log(Level.SEVERE, null, ex);
		}
		return (t > 0);
	}*/
/*
	public List<Menu> buscaPorMenu(Menu menu) {
		List<Menu> listaMenu = new ArrayList<Menu>();
		sql = "select * from tab_menus where tab_menus_id=?";
		con = ConnectionFactory.getConnection();
		try {
			PreparedStatement pstm = con.prepareStatement(sql);
			pstm.setInt(1, menu.getId());
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				Menu m = new Menu();
				m.setId(rs.getInt("id"));
				m.setDescricao(rs.getString("descricao"));
				m.setIcone(rs.getString("icone"));
				m.setViewId(rs.getString("viewId"));
				Menu subMenu = new Menu();
				subMenu = buscaPorId(rs.getInt("tab_menus_id"));
				m.setMenu(subMenu);
				listaMenu.add(m);
			}
			con.close();
		} catch (SQLException ex) {
			Logger.getLogger(MenuDAO.class.getName()).log(Level.SEVERE, null, ex);
		}
		return listaMenu;
	}*/
/*
	public Menu buscaPorId(int id) {
		sql = "select * from tab_menus where id=?";
		con = ConnectionFactory.getConnection();
		Menu menu = new Menu();
		try {
			PreparedStatement pstm = con.prepareStatement(sql);
			pstm.setInt(1, id);
			ResultSet rs = pstm.executeQuery();
			if (rs.next()) {
				menu.setId(rs.getInt("id"));
				menu.setDescricao(rs.getString("descricao"));
				menu.setIcone(rs.getString("icone"));
				menu.setViewId(rs.getString("viewId"));
				Menu subMenu = new Menu();
				subMenu = buscaPorId(rs.getInt("tab_menus_id"));
				menu.setMenu(subMenu);
			}
			con.close();
		} catch (SQLException ex) {
			Logger.getLogger(MenuDAO.class.getName()).log(Level.SEVERE, null, ex);
		}
		return menu;
	}*/
/*
	public List<Menu> listaTodos() {
		List<Menu> listaMenu = new ArrayList<Menu>();
		sql = "select * from tab_menus";
		con = ConnectionFactory.getConnection();
		try {
			PreparedStatement pstm = con.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				Menu menu = new Menu();
				menu.setId(rs.getInt("id"));
				menu.setDescricao(rs.getString("descricao"));
				menu.setIcone(rs.getString("icone"));
				menu.setViewId(rs.getString("viewId"));
				Menu subMenu = new Menu();
				subMenu = buscaPorId(rs.getInt("tab_menus_id"));
				menu.setMenu(subMenu);
				listaMenu.add(menu);
			}
			con.close();
		} catch (SQLException ex) {
			Logger.getLogger(MenuDAO.class.getName()).log(Level.SEVERE, null, ex);
		}
		return listaMenu;
	}*/
	
	/*
	public List<Menu> listaTodosPermissao() throws SQLException {

    	List<Menu> listaMenu = new ArrayList<Menu>();
        String query = "select * from menus";
        con = ConnectionFactory.getConnection();
        PreparedStatement ps = con.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Menu menu = new Menu();
            menu.setId(rs.getInt(COL_ID));
            menu.setDescricao(rs.getString(COL_DESCRICAO));
            menu.setUrl(rs.getString(COL_URL));
            menu.setIcone(rs.getString(COL_ICONE));
            menu.setIndice(rs.getString(COL_INDICE));
            listaMenu.add(menu);
        }
        con.close();
        return listaMenu;
    }*//*
    public Menu buscaPorIdPermissao(int id) throws SQLException {
        Menu menu = new Menu();
        String query = "select * from menus where id=?";
        con = ConnectionFactory.getConnection();
        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            menu.setId(rs.getInt(COL_ID));
            menu.setDescricao(rs.getString(COL_DESCRICAO));
            menu.setUrl(rs.getString(COL_URL));
            menu.setIcone(rs.getString(COL_ICONE));
            menu.setIndice(rs.getString(COL_INDICE));
        }
        con.close();
        return menu;
    }*/

	@SuppressWarnings("unchecked")
	public List<Menu> listCabecalho() throws Exception {
		List<Menu> list;
		try {
			list = HibernateUtility.getSession()
					.createCriteria(Menu.class)
					.add(Restrictions.isNull("sub.id"))
					.list();
		} catch (HibernateException hibernateException) {
			cancel();
			throw hibernateException;
		}finally{
			HibernateUtility.closeSession();
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<Menu> listByIdSub(long id) throws Exception {
		// select * from tab_menus where tab_menus_id=?
		List<Menu> list;
		try {
			list = HibernateUtility.getSession()
					.createCriteria(Menu.class)
					.add(Restrictions.eq("sub.id",id))
					.list();
		} catch (HibernateException hibernateException) {
			cancel();
			throw hibernateException;
		}finally{
			HibernateUtility.closeSession();
		}
		return list;
	}
}