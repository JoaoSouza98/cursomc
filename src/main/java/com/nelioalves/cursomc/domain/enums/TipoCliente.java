package com.nelioalves.cursomc.domain.enums;

import com.nelioalves.cursomc.services.validation.utils.Valid;
import com.nelioalves.cursomc.services.validation.utils.Cnpj;
import com.nelioalves.cursomc.services.validation.utils.Cpf;

public enum TipoCliente {
	
	PESSOAFISICA(1, "Pessoa Fisica") {
		@Override
		public Valid getDocumentValidator() {
			return new Cpf();
		}
	} ,
	PESSOAJURIDICA(2, "Pessoa Juridica") {
		@Override
		public Valid getDocumentValidator() {
			return new Cnpj();
		}
	};
	
	public final int cod;
	public final String descricao;
	
	private TipoCliente(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}
	
	public static TipoCliente toEnum(Integer cod) {
		
		if(cod == null) {
			return null;
		}
		
		for (TipoCliente x : TipoCliente.values()) {
			if (cod.equals(x.cod)) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("Id invalido: " + cod);
	}

	public abstract Valid getDocumentValidator();
}
