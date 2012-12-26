package br.com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;

import br.com.factory.ConnectionFactory;
import br.com.factory.HibernateUtility;
import br.com.models.Usuario;

public class UsuarioDAO extends GenericoDAO<Usuario, Long>{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Connection con;
    private final String COL_ID = "id";
    private final String COL_LOGIN = "login";
    private final String COL_SENHA = "senha";
    private final String COL_ULTIMOACESSO = "ultimo_acesso";

    public List<Usuario> listaTodos() throws SQLException {
        List<Usuario> listaUsuario = new ArrayList<Usuario>();
        String query = "select * from usuarios";
        con = ConnectionFactory.getConnection();
        PreparedStatement ps = con.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Usuario usuario = new Usuario();
            usuario.setId(rs.getInt(COL_ID));
            usuario.setLogin(rs.getString(COL_LOGIN));
            usuario.setSenha(rs.getString(COL_SENHA));
            usuario.setUltimoAcesso(rs.getTimestamp(COL_ULTIMOACESSO));
            listaUsuario.add(usuario);
        }
        con.close();
        return listaUsuario;
    }

    public Usuario buscaPorId(int id) throws SQLException {
        Usuario usuario = new Usuario();
        String query = "select * from usuarios where id=?";
        con = ConnectionFactory.getConnection();
        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            usuario.setId(rs.getInt(COL_ID));
            usuario.setLogin(rs.getString(COL_LOGIN));
            usuario.setSenha(rs.getString(COL_SENHA));
            usuario.setUltimoAcesso(rs.getTimestamp(COL_ULTIMOACESSO));
        }
        con.close();
        return usuario;
    }

	public Usuario verificaLoginSenha(Usuario usuario) throws HibernateException, Exception {
		usuario = (Usuario) HibernateUtility.getSession().createCriteria(Usuario.class)
				.add(Restrictions.eq("login", usuario.getLogin()))
				.add(Restrictions.eq("senha", usuario.getSenha()))
				.uniqueResult();
		
			return usuario;
		
	}

	public void saveTheme(String theme, Usuario usuario) throws HibernateException, Exception {
		//Nome da classe e atributo
		String updateQuery = "UPDATE Usuario obj SET tema = :valor WHERE obj.id = :idUsuario";  
		HibernateUtility.getSession().createQuery(updateQuery)
		.setString("valor", theme)
		.setLong("idUsuario",usuario.getId())
		.executeUpdate();
		
		HibernateUtility.commitTransaction();
		
	}
}

