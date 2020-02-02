package com.nelioalves.cursomc.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.nelioalves.cursomc.domain.Categoria;
import com.nelioalves.cursomc.repositories.CategoriaRepository;
import com.nelioalves.cursomc.services.dto.CategoriaDTO;
import com.nelioalves.cursomc.services.exceptions.DataIntegrityException;
import com.nelioalves.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository repo;
	
	public CategoriaDTO find(Integer id) {
		return CategoriaDTO.from(repo.findById(id)
				.orElseThrow(() -> new ObjectNotFoundException("Objeto nao encontrado! Id: " + id
																+ ", Tipo: " + Categoria.class.getName())));
	}
	
	public CategoriaDTO insert(CategoriaDTO obj) {
		return CategoriaDTO.from(repo.save(CategoriaDTO.to(obj)));
	}
	
public Categoria update(CategoriaDTO newObj) {
		
		CategoriaDTO oldObj = find(newObj.id);
		
		return repo.save(CategoriaDTO.to(updateData(oldObj, newObj)));
	}
	
	public void delete(Integer id) {
		find(id); //Caso o objeto nao seja encontrado o metodo find lanca uma excecao
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Nao eh possivel excluir uma categoria que possua produtos");
		}
	}
	
	public List<CategoriaDTO> findAll() {
		return CategoriaDTO.fromList(repo.findAll());
	}
	
	public Page<CategoriaDTO> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return CategoriaDTO.fromPage(repo.findAll(pageRequest));
	}
	
	private CategoriaDTO updateData(CategoriaDTO oldObj, CategoriaDTO newObj) {
		return new CategoriaDTO(oldObj.id, newObj.nome);
	}
}