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
import modelo.Atendimento;
import service.AnimalService;
import service.AtendimentoService;

@ViewScoped
@ManagedBean
public class AtendimentoBean {
	
	private Long idAnimal = 0L; 
	private Atendimento atendimento = new Atendimento();
	private List<Atendimento> atendimentos = new ArrayList<Atendimento>();
	
	private List<Animal> animais = new ArrayList<Animal>();
	
	@EJB
	private AnimalService animalService;
	
	@EJB
	private AtendimentoService atendimentoService;
	
	
	
	@PostConstruct
	public void inicio(){
		listarAnimais();
		listarAtendimentos();
	}
	
	public void gravarAtendimento(){
		Animal animal = animalService.obtemPorId(idAnimal);
		getAtendimento().setAnimalAtendido(animal);
		
		/*System.out.println("Nome: "+atendimento.getDescricao());
		System.out.println("raça: "+atendimento.getValor());
		System.out.println("Animal Atendido: "+atendimento.getAnimalAtendido().getNome());*/
		
		atendimentoService.create(getAtendimento());
		
		FacesContext.getCurrentInstance().addMessage("Sucesso", 
				new FacesMessage("Atendimento do " +atendimento.getAnimalAtendido().getNome()
						+" do cliente "+atendimento.getAnimalAtendido().getDono().getNome()+" salvo com sucesso!"));
		
		atendimento = new Atendimento();
		idAnimal = 0L;
		listarAtendimentos();
	}
	
	public void listarAnimais(){
		animais = animalService.listAll();
	}
	
	public void listarAtendimentos(){
		atendimentos = atendimentoService.listAll();
	}
	
	public void excluirAtendimento(Atendimento atendimentoAtual){
		FacesContext.getCurrentInstance().addMessage("Sucesso", 
				new FacesMessage(atendimentoAtual.getAnimalAtendido().getNome()
						+" do cliente "+atendimentoAtual.getAnimalAtendido().getDono().getNome()+" excluido com sucesso!"));
		
		atendimentoService.remove(atendimentoAtual);
		listarAtendimentos();
		
	}
	
	public void selecionarAtendimento(Atendimento atendimentoAtual){
		FacesContext.getCurrentInstance().addMessage("Sucesso", 
				new FacesMessage("Descrição: "+atendimentoAtual.getDescricao()+
						" / Valor:"+ atendimentoAtual.getValor()+
						" / Dono: "+atendimentoAtual.getAnimalAtendido().getDono().getNome()+
						" / CPF: "+atendimentoAtual.getAnimalAtendido().getDono().getCpf()+
						" / Endereço para entrega: "+atendimentoAtual.getAnimalAtendido().getDono().getEndereco().getEnderecoResumido()
						));
	}

	
	/* G e S */
	public Atendimento getAtendimento() {
		return atendimento;
	}

	public void setAtendimento(Atendimento atendimento) {
		this.atendimento = atendimento;
	}

	public List<Atendimento> getAtendimentos() {
		return atendimentos;
	}

	public void setAtendimentos(List<Atendimento> atendimentos) {
		this.atendimentos = atendimentos;
	}

	public List<Animal> getAnimais() {
		return animais;
	}

	public void setAnimais(List<Animal> animais) {
		this.animais = animais;
	}

	public Long getIdAnimal() {
		return idAnimal;
	}

	public void setIdAnimal(Long idAnimal) {
		this.idAnimal = idAnimal;
	}
	
	
	
}
