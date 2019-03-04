package com.ikytus.akademy.resources;

import java.net.URI;

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

import com.ikytus.akademy.domain.FluxoCaixa;
import com.ikytus.akademy.domain.models.Response;
import com.ikytus.akademy.security.UserService;
import com.ikytus.akademy.services.FluxoCaixaService;

@RestController
@RequestMapping(value="/fluxosCaixa")
@CrossOrigin(origins="*")
public class FluxoCaixaResource {
	
	@Autowired
	private FluxoCaixaService service;
			
	@Autowired
	private UserService userService;
	
	@PostMapping
	public ResponseEntity<Response<FluxoCaixa>> insert(HttpServletRequest request, @RequestBody FluxoCaixa fluxoCaixa, BindingResult result){	
		
		Response<FluxoCaixa> response = new Response<FluxoCaixa>();
		fluxoCaixa.setEmpresa(userService.userFromRequest(request).getEmpresa());
		FluxoCaixa fluxoNew = service.createOrUpdate(fluxoCaixa);
		response.setData(fluxoNew);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(fluxoNew.getId()).toUri();
		return ResponseEntity.created(uri).build().ok(response);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Response<FluxoCaixa>> update(@RequestBody FluxoCaixa fluxoCaixa, @PathVariable String id){
		Response<FluxoCaixa> response = new Response<FluxoCaixa>();
		fluxoCaixa.setId(id);
		response.setData(service.createOrUpdate(fluxoCaixa));
		return ResponseEntity.ok(response);
	}
		
		
	@GetMapping("/{page}/{count}/{ano}")
    public  ResponseEntity<Response<Page<FluxoCaixa>>> findByEmpresa(
    		@PathVariable int page, @PathVariable int count,
    		@PathVariable int ano, HttpServletRequest request) {
		
		Response<Page<FluxoCaixa>> response = new Response<Page<FluxoCaixa>>();
		Page<FluxoCaixa> fluxosCaixa = service.findByEmpresa(page, count, userService.userFromRequest(request).getEmpresa().getId(), ano);
		response.setData(fluxosCaixa);
		return ResponseEntity.ok(response);
    }
	
	/*
	@GetMapping
	 
    public  ResponseEntity<Response<List<FluxoCaixa>>> listByEmpresa(HttpServletRequest request) {
		Response<List<FluxoCaixa>> response = new Response<List<FluxoCaixa>>();
		List<FluxoCaixa> fluxosCaixa = service.listByEmpresa(userService.userFromRequest(request).getEmpresa().getId());
		response.setData(fluxosCaixa);
		return ResponseEntity.ok(response);
    }
	*/
				
	@GetMapping(value="{id}")
	public ResponseEntity<Response<FluxoCaixa>> findById(@PathVariable("id") String id, HttpServletRequest request) {
		Response<FluxoCaixa> response = new Response<FluxoCaixa>();
		FluxoCaixa fluxo= service.findById(id,userService.userFromRequest(request).getEmpresa().getId());
		if (fluxo == null) {
			response.getErrors().add("Register not found id:" + id);
			return ResponseEntity.badRequest().body(response);
		}
		response.setData(fluxo);
		return ResponseEntity.ok(response);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable String id, HttpServletRequest request){
		service.delete(id, userService.userFromRequest(request).getEmpresa().getId());
		return ResponseEntity.noContent().build();
	}
}