package com.nelioalves.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.nelioalves.cursomc.domain.Cliente;
import com.nelioalves.cursomc.domain.enums.TipoCliente;
import com.nelioalves.cursomc.services.dto.ClienteDTO;
import com.nelioalves.cursomc.repositories.ClienteRepository;
import com.nelioalves.cursomc.services.exceptions.DataIntegrityException;
import com.nelioalves.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository repo;
	
	public Cliente find(Integer id) {
		Optional<Cliente> obj = repo.findById(id);
		
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto nao encontrado! Id: " + id
				+ ", Tipo: " + Cliente.class.getName()));
	}

	public Cliente insert(ClienteDTO obj) {
		//garantir que o metodo save realizara uma insercao e nao uma atualizacao
			
		return repo.save(ClienteDTO.to(obj));
	}
	
	public Cliente update(ClienteDTO newObj, Integer id) {
		Cliente obj = find(id);
		
		newObj = updateData(obj, newObj);
		
		return repo.save(ClienteDTO.to(newObj));
	}
	
	public void delete(Integer id) {
		find(id); //Caso o objeto nao seja encontrado o metodo find lanca uma excecao
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Nao eh possivel excluir uma categoria que possua produtos");
		}
	}
	
	public List<Cliente> findAll() {
		return repo.findAll();
	}
	
	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	
	private ClienteDTO updateData(Cliente obj, ClienteDTO newObj) {
		return new ClienteDTO (
			new Cliente(
				obj.id, newObj.nome, newObj.email, obj.cpfOuCnpj, TipoCliente.toEnum(obj.tipo)
			)
		);
	}
}
