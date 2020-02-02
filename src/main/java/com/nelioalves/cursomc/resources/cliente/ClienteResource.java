package com.nelioalves.cursomc.resources.cliente;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nelioalves.cursomc.resources.cliente.requests.ClienteUpdateRequest;
import com.nelioalves.cursomc.services.ClienteService;
import com.nelioalves.cursomc.services.dto.cliente.ClienteDetailDTO;
import com.nelioalves.cursomc.services.dto.cliente.ClienteSimpleDTO;

@RestController
@RequestMapping(value="/clientes")
public class ClienteResource {
	
	@Autowired
	private ClienteService service;
	
	@GetMapping(value="/{id}")
	public ResponseEntity<ClienteDetailDTO> find(@PathVariable Integer id) { //Response entity encapsula/armazena varias informacoes de uma resposta http para um servico REST
		
		return ResponseEntity.ok().body(service.find(id));
	}
	
	@PutMapping(value="/{id}")
	public ResponseEntity<Void> update(@RequestBody ClienteUpdateRequest obj, @PathVariable Integer id) {

		service.update(ClienteUpdateRequest.to(obj, id));

		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping(value="/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping
	public ResponseEntity<List<ClienteSimpleDTO>> findAll() {

		return ResponseEntity.ok().body(service.findAll());
	}
	
	@GetMapping(value="/page")
	public ResponseEntity<Page<ClienteSimpleDTO>> findPage(
			@RequestParam(value="page", defaultValue="0") Integer page, 
			@RequestParam(value="linesPerPage", defaultValue="24")Integer linesPerPage, 
			@RequestParam(value="orderBy", defaultValue="nome")String orderBy,
			@RequestParam(value="direction", defaultValue="ASC")String direction) {

		return ResponseEntity.ok().body(service.findPage(page, linesPerPage, orderBy, direction));
	}
}