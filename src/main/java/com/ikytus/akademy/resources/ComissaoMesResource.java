package com.ikytus.akademy.resources;

import java.net.URI;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ikytus.akademy.domain.ComissaoMes;
import com.ikytus.akademy.domain.models.Response;
import com.ikytus.akademy.services.ComissaoMesService;

@RestController
@RequestMapping(value = "/comissaomes")
@CrossOrigin(origins = "*")
public class ComissaoMesResource {

	@Autowired
	private ComissaoMesService service;
	
	@GetMapping("/{ano}/{mes}")
	public ResponseEntity<Response<List<ComissaoMes>>> listAllByEmpresa(HttpServletRequest request,
			@PathVariable int ano, @PathVariable int mes) {

		Response<List<ComissaoMes>> response = new Response<List<ComissaoMes>>();
		Sort sort = Sort.by("ano").ascending().and(Sort.by("mes").ascending());

		List<ComissaoMes> comissaoMes = service.findAll(ano, mes, sort);
		response.setData(comissaoMes);
		return ResponseEntity.ok(response);
	}
	
	@PostMapping
	public ResponseEntity<Response<ComissaoMes>> insert(HttpServletRequest request, @RequestBody ComissaoMes comissaoMes,
			BindingResult result) {
		
		ComissaoMes comissaoMesNew = service.createOrUpdate(comissaoMes);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(comissaoMesNew.getId())
				.toUri();
		return ResponseEntity.created(uri).build();
	}

	@PutMapping("/{id}")
	public ResponseEntity<Response<ComissaoMes>> update(@RequestBody ComissaoMes comissaoMes, @PathVariable String id) {
		Response<ComissaoMes> response = new Response<ComissaoMes>();
		
		comissaoMes.setId(id);
		response.setData(service.createOrUpdate(comissaoMes));
		return ResponseEntity.ok(response);
	}

	@GetMapping(value = "{id}")
	public ResponseEntity<Response<ComissaoMes>> findById(@PathVariable("id") String id) {
		Response<ComissaoMes> response = new Response<ComissaoMes>();
		ComissaoMes comissaoMes = service.findById(id);
		if (comissaoMes == null) {
			response.getErrors().add("Register not found id:" + id);
			return ResponseEntity.badRequest().body(response);
		}
		response.setData(comissaoMes);
		return ResponseEntity.ok(response);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable String id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}