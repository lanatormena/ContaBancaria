package br.com.db1.conta.bancaria;

public class Cliente {
	String nome;
	
	String cpf;
	
	public Cliente(String nome, String cpf) {
		if(nome == null) {
			throw new RuntimeException("Nome é obrigatorio");
		}
		if(nome.length() <5) {
			throw new RuntimeException("Nome deve ser maior ou igual");
		}
		
		this.nome = nome;
		this.cpf =cpf;
	}
	
	public String getNome() {
		return this.nome;
	}
	
	public String getCpf() {
		return this.cpf;
	}
}
