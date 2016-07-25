package org.bean.controler;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import org.bean.metier.*;
import org.dao.ClientDBUtil;
import org.service.ServiceClient;

import com.sun.media.jfxmedia.logging.Logger;

@ManagedBean(name="listeClients")
@SessionScoped
public class ListeClient {
	
	private Client client;

	private List<Client> listeClient = new ArrayList<>();
	//private static Logger logger = Logger.getLogger(getClass().getName());
			

	
	public ListeClient() throws Exception {
	//listeClient = ClientDBUtil.getInstance().getListClient();
		ServiceClient serviceClient = new ServiceClient();
		listeClient = serviceClient.getListeClientService();
	}

	
	// chargement de la page index ------------
	public void loadSClients() {

		listeClient.clear();
		try {
			
			ServiceClient serviceClient = new ServiceClient();
			listeClient = serviceClient.getListeClientService();
			
		} catch (Exception exc) {
			System.out.println(exc);
		}
	}
	
	
	// ajouter des clients ------------
	public String addClient (Client client){
		
		//Object logger;
		//logger.info("Adding Client :" + client);
		
		try{
			//ClientDBUtil.getInstance().addClient(client);
			ServiceClient serviceClient = new ServiceClient();
			serviceClient.ajouterClientService(client);
		}catch (Exception exc){
			System.out.println(exc);
		//	logger.log(Level.SEVERE, "error adding Client", exc);
		 return null;
		}
		
		return "index?faces-redirect=true";
	}
	
	
	
	
	public List<Client> getListeClient() {
		return listeClient;
	}

	public void setListeClient(List<Client> listeClient) {
		this.listeClient = listeClient;
	}
	
	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	

}
