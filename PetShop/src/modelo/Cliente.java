package modelo;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Cliente {
	@Id @GeneratedValue
	private Long id;
	
	private String nome;
	private String cpf;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Endereco endereco = new Endereco();
	
	//@OneToMany
	//private List<Animal> animais = new ArrayList<Animal>();

	
	/*G e S*/
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	/*public List<Animal> getAnimal() {
		return animais;
	}

	public void setAnimal(List<Animal> animal) {
		this.animais = animais;
	}*/
	
	
}
