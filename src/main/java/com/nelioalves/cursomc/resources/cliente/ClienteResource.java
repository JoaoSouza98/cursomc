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

import com.nelioalves.cursomc.domain.Cliente;
import com.nelioalves.cursomc.services.dto.ClienteDTO;
import com.nelioalves.cursomc.services.ClienteService;

@RestController
@RequestMapping(value="/clientes")
public class ClienteResource {
	
	@Autowired
	private ClienteService service;
	
	@GetMapping(value="/{id}")
	public ResponseEntity<Cliente> find(@PathVariable Integer id) { //Response entity encapsula/armazena varias informacoes de uma resposta http para um servico REST
		
		Cliente obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@PutMapping(value="/{id}")
	public ResponseEntity<Void> update(@RequestBody ClienteDTO obj, @PathVariable Integer id) {

		service.update(obj, id);

		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping(value="/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping
	public ResponseEntity<List<ClienteDTO>> findAll() {
		List<ClienteDTO> list = ClienteDTO.fromList(service.findAll());

		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value="/page")
	public ResponseEntity<Page<ClienteDTO>> findPage(
			@RequestParam(value="page", defaultValue="0") Integer page, 
			@RequestParam(value="linesPerPage", defaultValue="24")Integer linesPerPage, 
			@RequestParam(value="orderBy", defaultValue="nome")String orderBy,
			@RequestParam(value="direction", defaultValue="ASC")String direction) {
		Page<ClienteDTO> list = ClienteDTO.fromPage(service.findPage(page, linesPerPage, orderBy, direction));

		return ResponseEntity.ok().body(list);
	}
}