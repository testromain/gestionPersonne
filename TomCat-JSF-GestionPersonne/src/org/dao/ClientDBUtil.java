package org.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.bean.metier.Client;



public class ClientDBUtil {
	
	private static ClientDBUtil instance;
	private DataSource datasource;
	// java:comp/env = dataSource (tj le meme) puis adresse dans context.xml
	private String jndiName ="java:comp/env/jdbc/gestion_client_h2";

	
	
	public static ClientDBUtil getInstance() throws Exception {
		if (instance == null){
			instance = new ClientDBUtil();
		}
		return instance; 
	}


	
	// ----- 1 -----------
	private DataSource getDataSource() throws NamingException{
		Context context = new InitialContext();

		DataSource theDataSource = (DataSource) context.lookup(jndiName);
		
		return theDataSource;
	}
	
	// ----- 2 -----------
	private ClientDBUtil()throws Exception{
		datasource = getDataSource();
	}
	
	public List<Client> getListClient() throws Exception{
		
		List<Client> listClient = new ArrayList<>(); 
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;

		try {
			myConn = datasource.getConnection();

			String sql = "select * from client;";

			myStmt = myConn.createStatement();

			myRs = myStmt.executeQuery(sql);

			while (myRs.next()) {
				// on recupere les informations
				int id = myRs.getInt("ID");
				String name = myRs.getString("name");
				String surname = myRs.getString("surname");
				String compte = myRs.getString("account");
				
				// on cree un nouveau client
				Client client = new Client (id, name, surname, compte);
				
				// on ajoute le client a la lsite
				listClient.add(client);
			}
		
		return listClient;
		}
	
	finally {
		close(myConn,myStmt, myRs);
	}
		
	}
	
public String getName(String name) throws Exception{
		
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		String nameRecup ="";

		
			myConn =  datasource.getConnection();
			String sql ="select c.name from Client c where c.name= '" + name + "';";
			
			myStmt = myConn.createStatement();
			myRs = myStmt.executeQuery(sql);
			
			while (myRs.next()) {
				nameRecup = myRs.getString(1);
				
			}
			
			close(myConn,myStmt, myRs);
		
		return nameRecup;
	}


	public void addClient (Client client) throws SQLException{
		Connection myConn = null;
		PreparedStatement stmt = null;
		
		try{
			myConn =  datasource.getConnection();
			
			String sql ="insert into Client (name, surname, account) values(?,?,?)";

			stmt = myConn.prepareStatement(sql);
			
			//saisie d'elements
			stmt.setString(1, client.getName());
			stmt.setString(2, client.getSurname());
			stmt.setString(3, client.getAccount());

			
			stmt.execute();
			
			
		}finally{
			close2(myConn,stmt);
		}
		
	}
	
	

	private void close2(Connection myConn, PreparedStatement stmt) throws SQLException {
		if(myConn!=null){
			myConn.close();
		}
		if(stmt!=null){
			stmt.close();
		}
		
	}



	private void close(Connection myConn, Statement myStmt, ResultSet myRs) throws SQLException {
		
		if(myConn!=null){
			myConn.close();
		}
		if(myStmt!=null){
			myStmt.close();
		}
		if(myRs!=null){
			myRs.close();
		}
		
	}	
	

	
	
}
