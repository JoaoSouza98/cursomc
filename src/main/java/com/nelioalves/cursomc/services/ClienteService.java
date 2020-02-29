package com.nelioalves.cursomc.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.nelioalves.cursomc.domain.Cliente;
import com.nelioalves.cursomc.repositories.ClienteRepository;
import com.nelioalves.cursomc.services.dto.cliente.ClienteDetailDTO;
import com.nelioalves.cursomc.services.dto.cliente.ClienteSimpleDTO;
import com.nelioalves.cursomc.services.exceptions.DataIntegrityException;
import com.nelioalves.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository repo;
	
	@Autowired
	private CidadeService cidadeService;

	public ClienteDetailDTO find(Integer id) {
		
		return ClienteDetailDTO.from(repo.findById(id).orElseThrow(() -> 
			new ObjectNotFoundException("Objeto nao encontrado! Id: " + id
				+ ", Tipo: " + Cliente.class.getName())));
	}

	@Transactional
	public Cliente insert(ClienteDetailDTO obj, Integer cidadeId) {

		Cliente entity = ClienteDetailDTO.to(obj);

		entity.enderecos.forEach(e -> {
			cidadeService.insert(e.cidade);
			
		});

		return repo.save(entity);
	}
	
	public Cliente update(ClienteSimpleDTO newObj) {
		
		ClienteDetailDTO oldObj = find(newObj.id);
		
		return repo.save(ClienteSimpleDTO.to(updateData(oldObj, newObj)));
	}
	
	public void delete(Integer id) {
		
		find(id); //Caso o objeto nao seja encontrado o metodo find lanca uma excecao
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Nao eh possivel excluir porque há pedidos relacionados");
		}
	}
	
	public List<ClienteSimpleDTO> findAll() {
		return ClienteSimpleDTO.fromList(repo.findAll());
	}
	
	public Page<ClienteSimpleDTO> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return ClienteSimpleDTO.fromPage(repo.findAll(pageRequest));
	}
	
	private ClienteSimpleDTO updateData(ClienteDetailDTO oldObj, ClienteSimpleDTO newObj) {
		return new ClienteSimpleDTO(oldObj.id, newObj.nome, newObj.email, oldObj.cpfOuCnpj, oldObj.tipo);
	}
}
