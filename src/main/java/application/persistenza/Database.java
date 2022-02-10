package application.persistenza;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import application.persistenza.dao.factory.DaoAbstractFactory;
import application.persistenza.dao.factory.DaoJDBCFactory;

public class Database {

	private Connection conn;
	private static Database instance = null;
	private DaoAbstractFactory factory;
	
	public static Database getInstance() {
		if(instance == null) {
			instance = new Database();
		}
		return instance;
	}
	
	
	private Database() {
		try {
			conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/VMF", "postgres", "postgres");
			factory = new DaoJDBCFactory();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Connection getConn() {
		return conn;
	}
	
	public DaoAbstractFactory getFactory() {
		return factory;
	}
}
