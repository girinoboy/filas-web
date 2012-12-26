package br.com.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;

import br.com.factory.ConnectionFactory;
import br.com.factory.HibernateUtility;
import br.com.models.Menu;
import br.com.models.PermissaoMenu;
import br.com.models.Usuario;
import br.com.models.UsuarioPerfil;

public class ControleAcessoDAO extends GenericoDAO<PermissaoMenu, Serializable>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/*
	private Connection con;
	private final String COL_ID = "id";
	private final String COL_USUARIO = "usuarios_id";
	private final String COL_MENU =  "menus_id";*/
/*
	public List<PermissaoMenu> buscaPorUsuario(Usuario usuario) throws SQLException {
		List<PermissaoMenu> listaPermissao = new ArrayList<PermissaoMenu>();
		MenuDAO menuDAO = new MenuDAO();
		String query = "select * from permissoes where usuarios_id=?";
		con = ConnectionFactory.getConnection();
		PreparedStatement ps = con.prepareStatement(query);
		ps.setInt(1, usuario.getId());
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			PermissaoMenu permissao = new PermissaoMenu();
			permissao.setId(rs.getInt(COL_ID));
			permissao.setUsuario(usuario);
			permissao.setMenu(menuDAO.buscaPorId(rs.getInt(COL_MENU)));
			listaPermissao.add(permissao);
		}
		con.close();
		return listaPermissao;
	}*/
/*
	public PermissaoMenu buscaPorMenuUsuario(Menu menu, Usuario usuario) throws SQLException {
		PermissaoMenu permissao = new PermissaoMenu();
		String query = "select * from permissoes where usuarios_id=? and menus_id=?";
		con = ConnectionFactory.getConnection();
		PreparedStatement ps = con.prepareStatement(query);
		ps.setInt(1, usuario.getId());
		ps.setInt(2, menu.getId());
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			permissao.setId(rs.getInt(COL_ID));
			permissao.setMenu(menu);
			permissao.setUsuario(usuario);
		}
		con.close();
		return permissao;
	}*/
/*
	public void excluiPorUsuario(Usuario usuario) throws SQLException {
		String query = "delete from permissoes where usuarios_id=?";
		con = ConnectionFactory.getConnection();
		PreparedStatement ps = con.prepareStatement(query);
		ps.setInt(1, usuario.getId());
		ps.execute();
		con.close();
	}*/
/*
	public void salvar(PermissaoMenu permissao) throws SQLException {
		String query = "insert into permissoes(menus_id,usuarios_id) values(?,?)";
		con = ConnectionFactory.getConnection();
		PreparedStatement ps = con.prepareStatement(query);
		ps.setInt(1, permissao.getMenu().getId());
		ps.setInt(2, permissao.getUsuario().getId());
		ps.execute();
		con.close();
	}*/

	@SuppressWarnings("unchecked")
	public List<PermissaoMenu> listByIdUsuario(int id) throws Exception {
		List<PermissaoMenu> list;
		try {
			list = HibernateUtility.getSession()
					.createCriteria(PermissaoMenu.class)
					.add(Restrictions.eq("usuario.id", id))
					.list();
		} catch (HibernateException hibernateException) {
			cancel();
			throw hibernateException;
		}finally{
			HibernateUtility.closeSession();
		}


		return list;
	}

	public void deletePermissaoPorUsuario(int id) throws Exception {
		try {
			//Nome da classe e atributo
			String deleteQuery = "delete from Permissao where usuario.id = :usuariosId";  
			HibernateUtility.getSession().createQuery(deleteQuery)
			.setInteger("usuariosId", id)
			.executeUpdate();
			
			HibernateUtility.commitTransaction();

		} catch (HibernateException hibernateException) {
			cancel();
			throw hibernateException;
		}finally{
			HibernateUtility.closeSession();
		}
	}
	
	public void deletePermissaoPorPerfil(Long id) throws Exception {
		try {
			//Nome da classe e atributo
			String deleteQuery = "delete from PermissaoMenu where perfil.id = :idPerfil";  
			HibernateUtility.getSession().createQuery(deleteQuery)
			.setLong("idPerfil", id)
			.executeUpdate();
			
			HibernateUtility.commitTransaction();

		} catch (HibernateException hibernateException) {
			cancel();
			throw hibernateException;
		}finally{
			HibernateUtility.closeSession();
		}
	}

	public PermissaoMenu buscaPorMenuUsuario(int menu, int usuario) throws Exception {
		PermissaoMenu permissao;
		try {
			permissao = (PermissaoMenu) HibernateUtility.getSession().createCriteria(PermissaoMenu.class)
					.add(Restrictions.eq("usuarios_id", usuario))
					.add(Restrictions.eq("menus_id", menu))
					.uniqueResult();

		} catch (HibernateException hibernateException) {
			cancel();
			throw hibernateException;
		}finally{
			HibernateUtility.closeSession();
		}

		return permissao;

	}
	@SuppressWarnings("unchecked")
	public List<PermissaoMenu> listByIdPerfil(Long id) throws Exception {
		List<PermissaoMenu> list;
		try {
			list = HibernateUtility.getSession()
					.createCriteria(PermissaoMenu.class)
					.add(Restrictions.eq("perfil.id", id))
					.list();
		} catch (HibernateException hibernateException) {
			cancel();
			throw hibernateException;
		}finally{
			HibernateUtility.closeSession();
		}


		return list;
	}

	public PermissaoMenu buscaPorMenuPerfil(Long menu, Long perfil) throws Exception {
		PermissaoMenu permissao;
		try {
			permissao = (PermissaoMenu) HibernateUtility.getSession().createCriteria(PermissaoMenu.class)
					.add(Restrictions.eq("perfil.id", perfil))
					.add(Restrictions.eq("menu.id", menu))
					.uniqueResult();

		} catch (HibernateException hibernateException) {
			cancel();
			throw hibernateException;
		}finally{
			HibernateUtility.closeSession();
		}

		return permissao;

	}

	@SuppressWarnings("unchecked")
	public List<PermissaoMenu> listByIdsPerfil(List<UsuarioPerfil> usuarioPeril) throws Exception {
		List<PermissaoMenu> list;
		try {

			Criteria criteria = HibernateUtility.getSession().createCriteria(PermissaoMenu.class);
			for (UsuarioPerfil usuarioPerfil : usuarioPeril) {
				criteria.add(Restrictions.eq("perfil.id", usuarioPerfil.getPerfil().getId()));
			}
			list = criteria.list();
			
		} catch (HibernateException hibernateException) {
			cancel();
			throw hibernateException;
		}finally{
			HibernateUtility.closeSession();
		}


		return list;
	}

}

