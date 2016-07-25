package org.bean.metier;

import javax.faces.bean.ManagedBean;

@ManagedBean(name="client")
public class Client {
	
	private String name;
	private String surname;
	private String account;
	private int id;
	

	public Client() {
		super();
	}
	
		
	public Client(int id, String name, String surname, String account) {
		super();
		this.name = name;
		this.surname = surname;
		this.account = account;
		this.id = id;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
	

}
