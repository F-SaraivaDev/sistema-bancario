package banco.modelo;

import java.text.DecimalFormat;

public class Conta {

	protected int numero;
	protected Cliente cliente;
	protected double saldo;

	private static int contador;

	public Conta() {
	}

	public Conta(Cliente cliente) {
		super();
		this.cliente = cliente;

	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public double getSaldo() {
		return saldo;
	}

	public boolean sacar(double valor) {

		if (saldo >= valor) {
			saldo -= valor;
			return true;

		} else {
			return false;

		}

	}

	public void depositar(double valor) {
		this.saldo += valor;
	}

	public String getSaldoFormatado() {

		return DecimalFormat.getCurrencyInstance().format(saldo);
	}

	public static int getContador() {
		return contador;
	}

	public static void setContador(int contador) {
		Conta.contador = contador;
	}

	public String listarDados() {
//		String nomeConta;

//		if(cliente instanceof PessoaFisica) {
//			nomeConta = ((PessoaFisica)cliente).getNome();
//		
//		}else {
//			nomeConta = ((PessoaJuridica)cliente).getRazaoSocial();
//		}
//		
		return "NÚMERO: " + numero + "\n"
		// + "CORRENTISTA: " + nomeConta + "\n"
				+ "SALDO: " + DecimalFormat.getCurrencyInstance().format(saldo);
	}
}
