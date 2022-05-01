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
					"CADASTRO DE CONTAS PARA A AGÊNCIA: " + agencia.getNumero() + " - " + agencia.getNome() + "\n"
							+ "\nOPÇÕES:\n\n" + "1 - Incluir cliente e conta\n" + "2 - Listar contas cadastradas\n"
							+ "3 - Sair do Sistema");

			if (opcaoPrincipal.equals("1")) {

				Cliente cliente;

				String tipoCliente = JOptionPane.showInputDialog(null,
						"Escolha o tipo de cliente:\n" + "F - Pessoa Física\nJ - Pessoa Jurídica");

				if (tipoCliente.equalsIgnoreCase("F")) {
					cliente = new PessoaFisica();

					((PessoaFisica) cliente).setNome(JOptionPane.showInputDialog(null, "Nome do Cliente:"));

					((PessoaFisica) cliente).setCpf(JOptionPane.showInputDialog(null, "CPF do Cliente:"));

				} else if (tipoCliente.equalsIgnoreCase("J")) {

					cliente = new PessoaJuridica();

					((PessoaJuridica) cliente).setRazaoSocial(JOptionPane.showInputDialog(null, "Razão Social:"));

					((PessoaJuridica) cliente).setCnpj(JOptionPane.showInputDialog(null, "CNPJ do Cliente:"));

				} else {

					JOptionPane.showMessageDialog(null, "OPÇÃO INVÁLIDA! Encerrando o programa...");
					return;
				}

				cliente.setCidade(JOptionPane.showInputDialog(null, "Cidade do Cliente:"));
				cliente.setEstado(JOptionPane.showInputDialog(null, "Estado do Cliente:"));

				JOptionPane.showMessageDialog(null, "DADOS DO CLIENTE\n\n" + cliente.listarDados());

				Conta conta = new Conta();

				String tipoConta = JOptionPane.showInputDialog(null,
						"Tipo de conta a ser criada\n" + "C - Conta Corrente\nP- Conta Poupança");

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

							"OPÇÕES: \n1 - Depositar valor\n2 - Sacar valor\n3 - Finalizar";

					try {

						opcao = Integer.parseInt(JOptionPane.showInputDialog(null, mensagem));

						switch (opcao) {

						case 1:
							ret = JOptionPane.showInputDialog(null, "Valor do depósito:");

							conta.depositar(Double.parseDouble(ret));

							JOptionPane.showMessageDialog(null, "Depósito realizado!");

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
						JOptionPane.showMessageDialog(null, "VALOR INVÁLIDO");
					}

				} while ((opcao == 1) || (opcao == 2));

			} else if (opcaoPrincipal.equals("2")) {

				if (agencia.getContas().size() == 0) {

					JOptionPane.showMessageDialog(null, "NÃO HÁ CONTAS CADASTRADAS NO MOMENTO.");

				} else {
					JOptionPane.showMessageDialog(null,
							"A Agência " + agencia.getNumero() + " - " + agencia.getNome() + " possui "
									+ agencia.getContas().size() + " conta(s).\n\nVeja quais são nas próximas telas");

					for (Conta umaConta : agencia.getContas()) {
						JOptionPane.showMessageDialog(null, umaConta.listarDados());
					}

				}
			}
		} while ((opcaoPrincipal.equals("1")) || (opcaoPrincipal.equals("2")));

	}
}
