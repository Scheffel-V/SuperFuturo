package com.ufrgs.superfuturo.model;

public class OutputObject {
	
	private String id;
	private String nome;
	private String quantidade;
	private String preco;
	
	public OutputObject() {
		
	}
	
	public OutputObject(final String id, final String nome, final String quantidade, final String preco) {
		this.setId(id);
		this.setNome(nome);
		this.setQuantidade(quantidade);
		this.setPreco(preco);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(String quantidade) {
		this.quantidade = quantidade;
	}

	public String getPreco() {
		return preco;
	}

	public void setPreco(String preco) {
		this.preco = preco;
	}
}
