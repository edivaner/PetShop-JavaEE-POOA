package controle;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import modelo.Animal;
import modelo.Cliente;
import service.AnimalService;
import service.ClienteService;

@ViewScoped
@ManagedBean
public class AnimalBean {
	private Long idDono = 0L;
	private Animal animal = new Animal();
	private List<Animal> animais = new ArrayList<Animal>();
	
	private List<Cliente> donos = new ArrayList<Cliente>();
	
	@EJB
	private AnimalService animalService;
	
	@EJB
	private ClienteService clienteService;
	
	@PostConstruct
	public void inicio(){
		donos = clienteService.listAll();
		listarAnimais();
	}
	
	public void gravarAnimal(){
		if(idDono == 0){
			//mensagens - Caso o usuario não selecione o dono
			FacesContext.getCurrentInstance().addMessage("Atenção", 
					new FacesMessage("Selecione o dono do Pet..."));
		}else{
			if(getAnimal().getId()!=null){
				// Em editar, evitar que o usuario troque o dono 
				if(idDono!=getAnimal().getDono().getId()){
					FacesContext.getCurrentInstance().addMessage("Atenção", 
							new FacesMessage("Não é possivel modificar o dono!"));
				}else{
					//Em editar, edita os campos deanimais
					animalService.merge(getAnimal());
					FacesContext.getCurrentInstance().addMessage("Sucesso", 
							new FacesMessage("Animal "+animal.getNome()+" atualizado para o dono "
									+animal.getDono().getNome()+" com sucesso!"));
					setAnimal(new Animal());
					idDono = 0L;
					listarAnimais();
				}
			}else{
				// Cadastro
				Cliente dono = clienteService.obtemPorId(idDono);
				getAnimal().setDono(dono);
				
				/*System.out.println("Nome: "+animal.getNome());
				System.out.println("raça: "+animal.getRaca());
				System.out.println("Especie: "+animal.getEspecie());
				System.out.println("Dono: "+animal.getDono().getNome());
				System.out.println("Dono: "+getAnimal().getDono().getNome());*/
				
				animalService.create(getAnimal());
				
				FacesContext.getCurrentInstance().addMessage("Sucesso", 
						new FacesMessage("Animal "+animal.getNome()+" registrado para o dono "+animal.getDono().getNome()+" com sucesso!"));
				
				setAnimal(new Animal());
				idDono = 0L;
				listarAnimais();
			}
		}
		
	}
	
	public void listarAnimais(){
		animais = animalService.listAll();
	}
	
	public void editarAnimais(Animal animalAtual){
		setAnimal(animalAtual);
		idDono = animalAtual.getDono().getId();
	}
	
	
	/*G e S*/
	public Animal getAnimal() {
		return animal;
	}

	public void setAnimal(Animal animal) {
		this.animal = animal;
	}

	public List<Animal> getAnimais() {
		return animais;
	}

	public void setAnimais(List<Animal> animais) {
		this.animais = animais;
	}

	public Long getIdDono() {
		return idDono;
	}

	public void setIdDono(Long idDono) {
		this.idDono = idDono;
	}

	public List<Cliente> getDonos() {
		return donos;
	}

	public void setDonos(List<Cliente> donos) {
		this.donos = donos;
	}

}
