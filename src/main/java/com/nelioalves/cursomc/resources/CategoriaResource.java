package com.nelioalves.cursomc.resources;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.nelioalves.cursomc.domain.Categoria;
import com.nelioalves.cursomc.services.CategoriaService;

@RestController
@RequestMapping(value="/categorias")
public class CategoriaResource {
	
	@Autowired
	private CategoriaService service;
	
	@GetMapping(value="/{id}") 
	public ResponseEntity<?> find(@PathVariable("id") Integer id) { //Response entity encapsula/armazena varias informacoes de uma resposta http para um servico REST
		
		Categoria obj = service.buscar(id);
		
		Categoria cat1 = new Categoria(1, "Informatica");
		Categoria cat2 = new Categoria(2, "Escritorio");
		
		List<Categoria> lista = new ArrayList<>();
		
		lista.add(cat1);
		lista.add(cat2);
		
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@RequestBody Categoria obj) {
		
		obj = service.insert(obj);
		
		URI uri = buildURI(obj, "id", obj.getId());
		
		return ResponseEntity.created(uri).build();
	}
	
	private URI buildURI(Object obj, String pathVariable, int attr) {
		return ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path(String.format("/{%s}", pathVariable))
				.buildAndExpand(attr)
				.toUri();
	}
}