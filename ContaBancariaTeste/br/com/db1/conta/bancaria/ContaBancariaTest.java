package br.com.db1.conta.bancaria;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ContaBancariaTest {

	private ContaBancaria conta;
	private ContaBancaria conta1;

	@Before
	public void init() {
		Cliente cliente = new Cliente("Maiko Cunha", "99999999999");
		conta = new ContaBancaria("1234", "0004", 1000.0, ContaBancariaTipo.CORRENTE, cliente);
		Cliente cliente2 = new Cliente("Lana Tormena", "11111111111");
		conta1 = new ContaBancaria("5678", "0005", 20000.0, ContaBancariaTipo.CORRENTE, cliente2);
	}

	@Test
	public void deveSacarDinheiroDaConta() {
		conta.sacar(500.0);
		Assert.assertEquals(500.0, conta.getSaldo(), 0);
		Assert.assertEquals(1, conta.getHistorico().size());
	}

	@Test(expected = RuntimeException.class)
	public void deveRetornarErroQuandoSaldoMenorQueSaque() {
		conta.sacar(2000.0);
	}

	@Test
	public void deveDepositarDinheiroNaConta() {
		conta.depositar(500.0);
		Assert.assertEquals(1500.0, conta.getSaldo(), 0);
		Assert.assertEquals(1, conta.getHistorico().size());
	}

	@Test(expected = RuntimeException.class)
	public void deveRetornarErroQuandoValorMenorQueZero() {
		conta.depositar(-2000.0);
	}

	@Test(expected = RuntimeException.class)
	public void deveRetornarErroValorIgualAZero() {
		conta.depositar(0.0);
	}

	@Test
	public void deveTransferir() {
		conta1.transferir(conta, 1500.0);
		Assert.assertEquals(2500.0, conta.getSaldo(), 0);
		Assert.assertEquals(1, conta.getHistorico().size());
		Assert.assertEquals(18500.0, conta1.getSaldo(), 0);
		Assert.assertEquals(1, conta1.getHistorico().size());

	}

}
