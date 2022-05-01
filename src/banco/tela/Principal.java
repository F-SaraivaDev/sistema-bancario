package banco.tela;

import javax.swing.JOptionPane;

import banco.modelo.Agencia;
import banco.modelo.Cliente;
import banco.modelo.Conta;
import banco.modelo.ContaCorrente;
import banco.modelo.ContaPoupanca;
import banco.modelo.PessoaFisica;
import banco.modelo.PessoaJuridica;

public class Principal {

	public static void main(String[] args) {

		Agencia agencia = new Agencia(1, "Rio de Janeiro");

		String opcaoPrincipal = "";
 
		do {
			opcaoPrincipal = JOptionPane.showInputDialog(null,
					"CADASTRO DE CONTAS PARA A AG�NCIA: " + agencia.getNumero() + " - " + agencia.getNome() + "\n"
							+ "\nOP��ES:\n\n" + "1 - Incluir cliente e conta\n" + "2 - Listar contas cadastradas\n"
							+ "3 - Sair do Sistema");

			if (opcaoPrincipal.equals("1")) {

				Cliente cliente;

				String tipoCliente = JOptionPane.showInputDialog(null,
						"Escolha o tipo de cliente:\n" + "F - Pessoa F�sica\nJ - Pessoa Jur�dica");

				if (tipoCliente.equalsIgnoreCase("F")) {
					cliente = new PessoaFisica();

					((PessoaFisica) cliente).setNome(JOptionPane.showInputDialog(null, "Nome do Cliente:"));

					((PessoaFisica) cliente).setCpf(JOptionPane.showInputDialog(null, "CPF do Cliente:"));

				} else if (tipoCliente.equalsIgnoreCase("J")) {

					cliente = new PessoaJuridica();

					((PessoaJuridica) cliente).setRazaoSocial(JOptionPane.showInputDialog(null, "Raz�o Social:"));

					((PessoaJuridica) cliente).setCnpj(JOptionPane.showInputDialog(null, "CNPJ do Cliente:"));

				} else {

					JOptionPane.showMessageDialog(null, "OP��O INV�LIDA! Encerrando o programa...");
					return;
				}

				cliente.setCidade(JOptionPane.showInputDialog(null, "Cidade do Cliente:"));
				cliente.setEstado(JOptionPane.showInputDialog(null, "Estado do Cliente:"));

				JOptionPane.showMessageDialog(null, "DADOS DO CLIENTE\n\n" + cliente.listarDados());

				Conta conta = new Conta();

				String tipoConta = JOptionPane.showInputDialog(null,
						"Tipo de conta a ser criada\n" + "C - Conta Corrente\nP- Conta Poupan�a");

				if (tipoConta.equalsIgnoreCase("P")) {

					conta = new ContaPoupanca(cliente, 10, 5);

				} else if (tipoConta.equalsIgnoreCase("C")){

					conta = new ContaCorrente(cliente);

				}

				agencia.incluirConta(conta);

				JOptionPane.showMessageDialog(null, "DADOS DA CONTA\n\n" + conta.listarDados());

				int opcao = 0;

				String ret;

				do {
					String mensagem = "SALDO EM CONTA" + conta.getSaldoFormatado() + "\n\n" +

							"OP��ES: \n1 - Depositar valor\n2 - Sacar valor\n3 - Finalizar";

					try {

						opcao = Integer.parseInt(JOptionPane.showInputDialog(null, mensagem));

						switch (opcao) {

						case 1:
							ret = JOptionPane.showInputDialog(null, "Valor do dep�sito:");

							conta.depositar(Double.parseDouble(ret));

							JOptionPane.showMessageDialog(null, "Dep�sito realizado!");

							break;

						case 2:
							ret = JOptionPane.showInputDialog(null, "Valor do saque:");

							if (conta.sacar(Double.parseDouble(ret))) {

								JOptionPane.showMessageDialog(null, "Saque realizado!");

							} else {

								JOptionPane.showMessageDialog(null, "FALHA NO SAQUE!");
							}

						}

					} catch (NumberFormatException ex) {
						JOptionPane.showMessageDialog(null, "VALOR INV�LIDO");
					}

				} while ((opcao == 1) || (opcao == 2));

			} else if (opcaoPrincipal.equals("2")) {

				if (agencia.getContas().size() == 0) {

					JOptionPane.showMessageDialog(null, "N�O H� CONTAS CADASTRADAS NO MOMENTO.");

				} else {
					JOptionPane.showMessageDialog(null,
							"A Ag�ncia " + agencia.getNumero() + " - " + agencia.getNome() + " possui "
									+ agencia.getContas().size() + " conta(s).\n\nVeja quais s�o nas pr�ximas telas");

					for (Conta umaConta : agencia.getContas()) {
						JOptionPane.showMessageDialog(null, umaConta.listarDados());
					}

				}
			}
		} while ((opcaoPrincipal.equals("1")) || (opcaoPrincipal.equals("2")));

	}
}
