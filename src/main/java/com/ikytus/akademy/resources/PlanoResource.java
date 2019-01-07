package com.ikytus.akademy.resources;

import java.net.URI;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

import com.ikytus.akademy.domain.Plano;
import com.ikytus.akademy.domain.models.Response;
import com.ikytus.akademy.security.UserService;
import com.ikytus.akademy.services.PlanoService;

@RestController
@RequestMapping(value="/planos")
@CrossOrigin(origins="*")
public class PlanoResource {
	
	@Autowired
	private PlanoService service;
	
	@Autowired
	private UserService userService;
	
	@PostMapping
	public ResponseEntity<Response<Plano>> insert(HttpServletRequest request, @RequestBody Plano obj, BindingResult result){	
		obj.setEmpresa(userService.userFromRequest(request).getEmpresa());
		Plano plano = service.createOrUpdate(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(plano.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Response<Plano>> update(@RequestBody Plano plano, @PathVariable String id){
		Response<Plano> response = new Response<Plano>();
		plano.setId(id);
		response.setData(service.createOrUpdate(plano));
		return ResponseEntity.ok(response);
	}
		
		
	@GetMapping("/{page}/{count}")
    public  ResponseEntity<Response<Page<Plano>>> findAllByEmpresa(@PathVariable int page, @PathVariable int count, HttpServletRequest request) {
		Response<Page<Plano>> response = new Response<Page<Plano>>();
		Page<Plano> planos = service.findByEmpresa(page, count, userService.userFromRequest(request).getEmpresa().getId());
		response.setData(planos);
		return ResponseEntity.ok(response);
    }
	
	@GetMapping
    public  ResponseEntity<Response<List<Plano>>> listAllByEmpresa(HttpServletRequest request) {
		Response<List<Plano>> response = new Response<List<Plano>>();
		List<Plano> planos = service.listByEmpresa(userService.userFromRequest(request).getEmpresa().getId());
		response.setData(planos);
		return ResponseEntity.ok(response);
    }
		
	@GetMapping(value="{id}")
	public ResponseEntity<Response<Plano>> findById(@PathVariable("id") String id) {
		Response<Plano> response = new Response<Plano>();
		Plano plano = service.findById(id);
		if (plano == null) {
			response.getErrors().add("Register not found id:" + id);
			return ResponseEntity.badRequest().body(response);
		}
		response.setData(plano);
		return ResponseEntity.ok(response);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable String id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}