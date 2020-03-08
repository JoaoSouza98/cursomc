package com.nelioalves.cursomc.resources.cliente.requests;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.nelioalves.cursomc.domain.enums.TipoCliente;
import com.nelioalves.cursomc.services.dto.EnderecoDTO;
import com.nelioalves.cursomc.services.dto.cliente.ClienteDetailDTO;
import com.nelioalves.cursomc.services.validation.CPFCNPJ;


@CPFCNPJ
public class ClienteNewRequest implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@NotBlank(message = "Preenchimento obrigatório")
	@Length(min = 5, max = 120, message = "O tamanho deve ser entre 5 e 120 caracteres")
	public final String nome;

	@NotBlank(message = "Preenchimento obrigatório")
	@Email(message="Email inválido")
	public final String email;

	@NotNull(message = "Preenchimento obrigatório")
	public final TipoCliente tipo;
	
	@NotBlank(message = "Preenchimento obrigatório")
	public final String cpfOuCnpj;

	@NotBlank(message = "Preenchimento obrigatório")
	public final String logradouro;

	@NotBlank(message = "Preenchimento obrigatório")
	public final String numero;

	public final String complemento;
	public final String bairro;

	@NotBlank(message = "Preenchimento obrigatório")
	public final String cep;
	
	public final Integer cidadeId;
	
	@NotEmpty(message = "Preenchimento obrigatório")
	public final Set<String> telefones;

	public ClienteNewRequest(String nome, String email, String cpfOuCnpj, TipoCliente tipo, String logradouro,
			String numero, String complemento, String bairro, String cep, Set<String> telefones, Integer cidadeId) {
		super();
		this.nome = nome;
		this.email = email;
		this.cpfOuCnpj = cpfOuCnpj;
		this.tipo = tipo;
		this.logradouro = logradouro;
		this.numero = numero;
		this.complemento = complemento;
		this.bairro = bairro;
		this.cep = cep;
		this.telefones = telefones;
		this.cidadeId = cidadeId;
	}
	
	public static ClienteDetailDTO to (ClienteNewRequest obj) {
		return new ClienteDetailDTO(null, obj.nome, obj.email, obj.cpfOuCnpj, obj.tipo.toString(),
				Arrays.asList(new EnderecoDTO(obj.logradouro, obj.numero, obj.complemento, obj.bairro, obj.cep, null, null)), obj.telefones);
	}
}