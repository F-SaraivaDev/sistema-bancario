package banco.modelo;

public class PessoaFisica extends Cliente {

	private String nome;
	private String cpf;

	public PessoaFisica() {
		super();
		setNome("INDEFINIDO");
		cpf = "";

	}

	public PessoaFisica(String cidade, String estado,String nome, String cpf) {
		super(cidade, estado);
		this.setNome(nome);
		this.cpf = cpf;
	}

//	public String getNome() {
//		return (nome.contains(" ") ? nome.substring(0, nome.indexOf(' ')) : nome);
//	}

//	public void setNome(String nome) {
//		if (nome.isEmpty()) {
//			this.nome = "Nome não fornecido";
//
//		} else {
//			this.nome = nome;
//		}
//	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String listarDados() {
		return "NOME: " + nome + "\n" + "CPF: " + cpf + "\n" + super.listarDados();
	}
}













