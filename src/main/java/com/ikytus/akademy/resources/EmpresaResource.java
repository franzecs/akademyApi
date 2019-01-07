package com.ikytus.akademy.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ikytus.akademy.domain.Empresa;
import com.ikytus.akademy.domain.models.Response;
import com.ikytus.akademy.dto.EmpresaDTO;
import com.ikytus.akademy.services.EmpresaService;

@RestController
@RequestMapping(value="/empresas")
public class EmpresaResource {
	
	@Autowired
	private EmpresaService service;
	
	@GetMapping
	public ResponseEntity<Response<List<EmpresaDTO>>> findAll(){
		Response<List<EmpresaDTO>> response = new Response<List<EmpresaDTO>>(); 
		List<Empresa> list = service.findAll();
		List<EmpresaDTO> listDTO = list.stream().map(x -> new EmpresaDTO(x)).collect(Collectors.toList());
		response.setData(listDTO);
		return ResponseEntity.ok().body(response);
	}
	
	@GetMapping("/{page}/{count}")
	@PreAuthorize("hasAnyRole('ADMIN_SISTEMA')")
    public  ResponseEntity<Response<Page<EmpresaDTO>>> findAllPage(@PathVariable int page, @PathVariable int count) {
		Response<Page<EmpresaDTO>> response = new Response<Page<EmpresaDTO>>();
		Page<Empresa> empresas = service.findAllPage(page, count);
		Page<EmpresaDTO> listDTO = empresas.map(x -> new EmpresaDTO(x));
		response.setData(listDTO);
		return ResponseEntity.ok(response);
    }
	
	@GetMapping("/{id}")
	@PreAuthorize("hasAnyRole('ADMIN_SISTEMA') or hasAnyRole('ADMIN_EMPRESA')")
	public ResponseEntity<Response<Empresa>> findId(@PathVariable String id){
		Response<Empresa> response = new Response<Empresa>();
		Empresa empresa = service.findById(id);
		if (empresa == null) {
			response.getErrors().add("Register not found id:" + id);
			return ResponseEntity.badRequest().body(response);
		}
		response.setData(empresa);
		return ResponseEntity.ok(response);
	}
		
	@PostMapping
	public ResponseEntity<Response<Empresa>> create(HttpServletRequest request, @RequestBody Empresa empresa, BindingResult result){
		Response<Empresa> response = new Response<Empresa>();
		
		if(result.hasErrors()) {
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()) );
			return ResponseEntity.badRequest().body(response);
		}
		Empresa empresaPersistend = service.createOrUpdate(empresa);
		response.setData(empresaPersistend);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(empresaPersistend.getId()).toUri();
		return ResponseEntity.created(uri).body(response);
	}
		
	@PutMapping()
	public ResponseEntity<Response<Empresa>> update(HttpServletRequest request, @RequestBody Empresa empresa, BindingResult result){
		Response<Empresa> response = new Response<Empresa>();
		
		if(result.hasErrors()) {
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()) );
			return ResponseEntity.badRequest().body(response);
		}
		Empresa empresaPersistend = service.createOrUpdate(empresa);
		response.setData(empresaPersistend);
		return ResponseEntity.ok(response);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Response<String>> delete(@PathVariable("id") String id) {
		Response<String> response = new Response<String>();
		Empresa empresa = service.findById(id);
		if (empresa == null) {
			response.getErrors().add("Register not found id:" + id);
			return ResponseEntity.badRequest().body(response);
		}
		service.delete(id);
		return ResponseEntity.ok(new Response<String>());
	}
}