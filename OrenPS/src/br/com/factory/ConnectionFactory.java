package br.com.factory;


import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.JOptionPane;

public class ConnectionFactory {

	public static Connection getConnection() {
		
		Connection conn = null;
		try{
			//Class.forName("org.hsqldb.jdbcDriver");
			//conn = DriverManager.getConnection("jdbc:hsqldb:PermissoesTree;shutdown=true", "sa", "");
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/PermissoesTree", "root", "root");
		}catch(Exception e){
			System.out.println("Erro ao carregar o driver JDBC. ");
			JOptionPane.showMessageDialog(null, "Erro ao carregar o driver JDBC. ");
		}
		return conn;
	}

}
