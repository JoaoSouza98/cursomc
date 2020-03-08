package com.nelioalves.cursomc.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nelioalves.cursomc.domain.enums.TipoCliente;

@Entity
public final class Cliente implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public final Integer id;
	public final String nome;
	public final String email;
	public final String cpfOuCnpj;
	public final Integer tipo;
		
	@OneToMany(mappedBy="cliente", cascade = CascadeType.ALL) //permite que operacoes em cliente que afetariam seus enderecos associados sejam efetuadas (ex. delete)
	public final List<Endereco> enderecos;
	
	@ElementCollection
	@CollectionTable(name="TELEFONE")
	public final Set<String> telefones;
	
	@JsonIgnore
	@OneToMany(mappedBy="cliente")
	public final List<Pedido> pedidos;

	@SuppressWarnings("unused")
	private Cliente() {
		id = null;
		nome = null;
		email = null;
		cpfOuCnpj = null;
		tipo = null;
		enderecos = null;
		telefones = null;
		pedidos = null;
	}

	public Cliente(Integer id, String nome, String email, String cpfOuCnpj, TipoCliente tipo, List<Endereco> enderecos) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.cpfOuCnpj = cpfOuCnpj;
		this.tipo = (tipo == null) ? null : tipo.cod;
		this.enderecos = new ArrayList<>();
		this.telefones = new HashSet<>();
		this.pedidos = new ArrayList<>();
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}