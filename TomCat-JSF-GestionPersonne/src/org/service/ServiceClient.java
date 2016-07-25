package org.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.bean.metier.Client;
import org.dao.ClientDBUtil;

public class ServiceClient {
	
	private Client client;
	private List<Client> listeClient = new ArrayList<>();

	


	public ServiceClient() {
		super();
	}


	public ServiceClient(Client client, List<Client> listeClient) {
		super();
		this.client = client;
		this.listeClient = listeClient;
	}


	// recuperation de la liste de client
	public List<Client> getListeClientService() throws Exception {
	listeClient = ClientDBUtil.getInstance().getListClient();
	return listeClient;
	}
	
	// Ajout d'un client et verification de son existence dans la BDD
	public void ajouterClientService(Client client) throws SQLException, Exception{
		
		String name = client.getName();
		String nameBDD = ClientDBUtil.getInstance().getName(name);

			if (nameBDD.equals(name)){
				
			}else{
				ClientDBUtil.getInstance().addClient(client);
			}

	}

	
	public Client getClient() {
		return client;
	}


	public void setClient(Client client) {
		this.client = client;
	}


	public List<Client> getListeClient() {
		return listeClient;
	}


	public void setListeClient(List<Client> listeClient) {
		this.listeClient = listeClient;
	}

}
