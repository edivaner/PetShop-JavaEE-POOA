package service;

import javax.ejb.Stateless;

import modelo.Cliente;

@Stateless
public class ClienteService extends GenericService<Cliente>{
	public ClienteService(){
		super(Cliente.class);
	}
}
