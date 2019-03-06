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

import com.ikytus.akademy.domain.ItemFluxoCaixa;
import com.ikytus.akademy.domain.models.Response;
import com.ikytus.akademy.security.UserService;
import com.ikytus.akademy.services.ItemFluxoCaixaService;

@RestController
@RequestMapping(value="/itensFluxo")
@CrossOrigin(origins="*")
public class ItemFluxoCaixaResource {
	
	@Autowired
	private ItemFluxoCaixaService service;
			
	@Autowired
	private UserService userService;
	
	@PostMapping
	public ResponseEntity<Response<ItemFluxoCaixa>> insert(HttpServletRequest request, @RequestBody ItemFluxoCaixa itemFluxoCaixa, BindingResult result){	
		
		Response<ItemFluxoCaixa> response = new Response<ItemFluxoCaixa>();
		
		itemFluxoCaixa.setEmpresa(userService.userFromRequest(request).getEmpresa());
		ItemFluxoCaixa itemFluxoNew = service.createOrUpdate(itemFluxoCaixa);
		response.setData(itemFluxoNew);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(itemFluxoNew.getId()).toUri();
		return ResponseEntity.created(uri).build().ok(response);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Response<ItemFluxoCaixa>> update(@RequestBody ItemFluxoCaixa itemFluxoCaixa, @PathVariable String id){
		Response<ItemFluxoCaixa> response = new Response<ItemFluxoCaixa>();
		
		itemFluxoCaixa.setId(id);
		response.setData(service.createOrUpdate(itemFluxoCaixa));
		return ResponseEntity.ok(response);
	}
		
		
	@GetMapping("/{page}/{count}/{fluxoId}")
    public  ResponseEntity<Response<Page<ItemFluxoCaixa>>> findByEmpresa(
    		@PathVariable int page, @PathVariable int count,
    		@PathVariable String fluxoId, HttpServletRequest request) {
		
		Response<Page<ItemFluxoCaixa>> response = new Response<Page<ItemFluxoCaixa>>();
		
		Page<ItemFluxoCaixa> itemFluxosCaixa = service.findByFluxo(page, count, userService.userFromRequest(request).getEmpresa().getId(), fluxoId);
		response.setData(itemFluxosCaixa);
		return ResponseEntity.ok(response);
    }
						
	@GetMapping(value="{id}")
	public ResponseEntity<Response<ItemFluxoCaixa>> findById(@PathVariable("id") String id, HttpServletRequest request) {
		Response<ItemFluxoCaixa> response = new Response<ItemFluxoCaixa>();
		
		ItemFluxoCaixa itemFluxo= service.findById(id,userService.userFromRequest(request).getEmpresa().getId());
		if (itemFluxo == null) {
			response.getErrors().add("Register not found id:" + id);
			return ResponseEntity.badRequest().body(response);
		}
		response.setData(itemFluxo);
		return ResponseEntity.ok(response);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable String id, HttpServletRequest request){
		service.delete(id, userService.userFromRequest(request).getEmpresa().getId());
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/despesas")
	public ResponseEntity<List<ItemFluxoCaixa>> listDespesas(HttpServletRequest request){
		return ResponseEntity.ok(service.listDespesasFixas(userService.userFromRequest(request).getEmpresa().getId()));
	}
}