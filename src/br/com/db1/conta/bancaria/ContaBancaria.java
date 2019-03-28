package br.com.db1.conta.bancaria;

import java.util.ArrayList;
import java.util.List;

public class ContaBancaria {

	private String numero;
	private String agencia;
	private Double saldo;
	private ContaBancariaTipo tipo = ContaBancariaTipo.CORRENTE;
	private List<ContaBancariaHistorico> historicos = new ArrayList<>();
	private Cliente cliente;

	// construtor
	public ContaBancaria(String numero, String agencia, Double saldo, ContaBancariaTipo tipo, Cliente cliente) {

		this.numero = numero;
		this.agencia = agencia;
		this.saldo = saldo;
		this.tipo = tipo;
		this.cliente = cliente;
	}

	// metodos

	// verifica se tem saldo para saque
	public void sacar(Double valor) {

		if (valor > saldo) {
			throw new RuntimeException("Você não possui saldo sufuciente" + saldo);
		}

		this.saldo = this.saldo - valor;

		ContaBancariaHistorico historico = new ContaBancariaHistorico(ContaBancariaTipoOperacao.SAIDA, valor);

		this.historicos.add(historico);

	}

	// Depositar
	public void depositar(Double valor) {

		if (valor <= 0) {
			throw new RuntimeException("Informe um Valor Maior que 0 ");
		}

		this.saldo = this.saldo += valor;

		this.novoHistorico(ContaBancariaTipoOperacao.ENTRADA, valor);

	}

	private void novoHistorico(ContaBancariaTipoOperacao tipo, Double valor) {

		ContaBancariaHistorico historico = new ContaBancariaHistorico(ContaBancariaTipoOperacao.ENTRADA, valor);

		this.historicos.add(historico);

	}

	public Double getSaldo() {

		return this.saldo;
	}

	public List<ContaBancariaHistorico> getHistorico() {
		return this.historicos;
	}

// TRANSFERENCIA
	public void transferir(ContaBancaria destino, double valor) {
		if (valor > this.saldo) {
			throw new RuntimeException("Você não possui saldo sufuciente" + saldo);
		}
		if (valor <= 0) {
			throw new RuntimeException("Informe um Valor Maior que 0 ");
		} else {
			this.saldo = this.saldo - valor;
			destino.saldo = destino.saldo + valor;
			ContaBancariaHistorico historicoOrigem = new ContaBancariaHistorico(ContaBancariaTipoOperacao.SAIDA, valor);
			this.novoHistorico(ContaBancariaTipoOperacao.SAIDA, valor);
			ContaBancariaHistorico historicoDestino = new ContaBancariaHistorico(ContaBancariaTipoOperacao.ENTRADA,
					valor);
			destino.novoHistorico(ContaBancariaTipoOperacao.ENTRADA, valor);
		}
	}
}
