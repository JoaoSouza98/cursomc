package com.nelioalves.cursomc.resources.categoria;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nelioalves.cursomc.resources.categoria.requests.CategoriaRequest;
import com.nelioalves.cursomc.resources.util.URIUtil;
import com.nelioalves.cursomc.services.CategoriaService;
import com.nelioalves.cursomc.services.dto.CategoriaDTO;

@RestController
@RequestMapping(value="/categorias")
public class CategoriaResource {
	
	@Autowired
	private CategoriaService service;
	
	@GetMapping(value="/{id}") 
	public ResponseEntity<CategoriaDTO> find(@PathVariable("id") Integer id) { //Response entity encapsula/armazena varias informacoes de uma resposta http para um servico REST
		
		return ResponseEntity.ok().body(service.find(id));
	}
	
	@PostMapping
	public ResponseEntity<Void> insert(@Valid @RequestBody CategoriaRequest request) {
		
		CategoriaDTO response = service.insert(CategoriaRequest.to(request));
		
		URI uri = URIUtil.buildURI("id", response.id);
		
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping(value="/{id}") 
	public ResponseEntity<Void> update(@Valid @RequestBody CategoriaRequest request, @PathVariable Integer id) {
		service.update(CategoriaRequest.to(request, id));
		
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping(value="/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);
		
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping
	public ResponseEntity<List<CategoriaDTO>> findAll() {
	
		return ResponseEntity.ok().body(service.findAll());
	}
	
	@GetMapping(value="/page")
	public ResponseEntity<Page<CategoriaDTO>> findPage(
			@RequestParam(value="page", defaultValue="0") Integer page, 
			@RequestParam(value="linesPerPage", defaultValue="24")Integer linesPerPage, 
			@RequestParam(value="orderBy", defaultValue="nome")String orderBy, 
			@RequestParam(value="direction", defaultValue="ASC")String direction) {
		
		return ResponseEntity.ok().body(
				service.findPage(page, linesPerPage, orderBy, direction)
		);
	}
}