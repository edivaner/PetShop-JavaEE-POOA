package controle;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import modelo.Cliente;
import service.ClienteService;

@ViewScoped
@ManagedBean
public class ClienteBean {
	private Cliente cliente = new Cliente();
	private List<Cliente> clientes = new ArrayList<Cliente>();
	
	@EJB
	private ClienteService clienteService;
	
	@PostConstruct
	public void iniciar(){
		listarClientes();
	}

	public void CadastrarCliente(){
		if(cliente.getId()==null){
			clienteService.create(cliente);
			FacesContext.getCurrentInstance().addMessage("Sucesso!",
					new FacesMessage("Cliente "+cliente.getNome()+" cadastrado com sucesso!"));
		}else{
			clienteService.merge(cliente);
			FacesContext.getCurrentInstance().addMessage("Sucesso!",
					new FacesMessage("Cliente "+cliente.getNome()+" alterado com sucesso!"));
		}
		
		//clienteService.create(cliente);
		
		cliente = new Cliente();
		listarClientes();
	}
	
	public void listarClientes(){
		clientes = clienteService.listAll();
	}
	
	public void carregarEdicao(Cliente clienteAtual){
		cliente = clienteAtual;
	}
	
	/*G e S*/
	
	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}
	
	
}
