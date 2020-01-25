package com.nelioalves.cursomc.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public final class Pedido implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public final Integer id;
	
	@JsonFormat(pattern="dd/MM/yyyy HH:mm")
	public final Date instante;
	
	@OneToOne(cascade=CascadeType.ALL, mappedBy="pedido")//para nao dar erro de entidade transient ao salvar a entidade ou a entidade que esta ligada a ela
	public final Pagamento pagamento;
	
	@ManyToOne
	@JoinColumn(name="cliente_id")
	public final Cliente cliente;
	
	@ManyToOne
	@JoinColumn(name="endereco_de_entrega_id")
	public final Endereco enderecoDeEntrega;
	
	@OneToMany(mappedBy="id.pedido")
	public final Set<ItemPedido> itens;

	public Pedido(Integer id, Date instante, Cliente cliente, Endereco enderecoDeEntrega, Pagamento pagamento) {
		super();
		this.id = id;
		this.instante = instante;
		this.cliente = cliente;
		this.enderecoDeEntrega = enderecoDeEntrega;
		this.pagamento = pagamento;
		itens = new HashSet<>();
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
		Pedido other = (Pedido) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
}
