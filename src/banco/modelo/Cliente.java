package banco.modelo;

public class Cliente {

    protected int codigo;
	protected String cidade;
	protected String estado;

	private static int quantidade;

	public Cliente() {
		
		quantidade++;
		codigo = quantidade;
		cidade = "Rio de Janeiro";
		estado = "RJ";

	}

	public Cliente(String cidade, String estado) {
		quantidade++;
		codigo = quantidade;
		this.cidade = cidade;
		this.estado = estado;
	}

	

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String listarDados() {

		return "Código: " + codigo + "\n" + "Cidade: " + this.getCidade() + "\n" + "Estado: " + this.getEstado();
	}

	public static int qdtClientes() {
		return quantidade;
	}
}
