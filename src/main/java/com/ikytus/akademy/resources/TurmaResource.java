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

import com.ikytus.akademy.domain.Turma;
import com.ikytus.akademy.domain.enums.DiaEnum;
import com.ikytus.akademy.domain.models.Response;
import com.ikytus.akademy.security.UserService;
import com.ikytus.akademy.services.TurmaService;

@RestController
@RequestMapping(value="/turmas")
@CrossOrigin(origins="*")
public class TurmaResource {
	
	@Autowired
	private TurmaService service;
	
	@Autowired
	private UserService userService;
	
	@PostMapping
	public ResponseEntity<Response<Turma>> insert(HttpServletRequest request, @RequestBody Turma turma, BindingResult result){	
		turma.setEmpresa(userService.userFromRequest(request).getEmpresa());
		Turma turmaNew = service.createOrUpdate(turma);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(turmaNew.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Response<Turma>> update(@RequestBody Turma turma, @PathVariable String id){
		Response<Turma> response = new Response<Turma>();
		turma.setId(id);
		response.setData(service.createOrUpdate(turma));
		return ResponseEntity.ok(response);
	}
		
		
	@GetMapping("/{page}/{count}")
    public  ResponseEntity<Response<Page<Turma>>> findAllByEmpresa(@PathVariable int page, @PathVariable int count, HttpServletRequest request) {
		Response<Page<Turma>> response = new Response<Page<Turma>>();
		Page<Turma> turmas = service.findByEmpresa(page, count, userService.userFromRequest(request).getEmpresa().getId());
		response.setData(turmas);
		return ResponseEntity.ok(response);
    }
	
	@GetMapping
    public  ResponseEntity<Response<List<Turma>>> listAllByEmpresa(HttpServletRequest request) {
		Response<List<Turma>> response = new Response<List<Turma>>();
		List<Turma> turmas = service.listByEmpresa(userService.userFromRequest(request).getEmpresa().getId());
		response.setData(turmas);
		return ResponseEntity.ok(response);
    }
	
	@GetMapping("/ltonly")
    public  ResponseEntity<Response<List<Turma>>> listTurmasByEmpresa(HttpServletRequest request) {
		Response<List<Turma>> response = new Response<List<Turma>>();
		List<Turma> turmas = service.listTurmasEmpresa(userService.userFromRequest(request).getEmpresa().getId());
		response.setData(turmas);
		return ResponseEntity.ok(response);
    }
	
	@GetMapping("/horario/{dia}")
    public  ResponseEntity<Response<List<Turma>>> listAllByEmpresaDia(HttpServletRequest request, @PathVariable("dia") DiaEnum dia) {
		Response<List<Turma>> response = new Response<List<Turma>>();
		System.out.println(dia);
		List<Turma> turmas = service.listByEmpresaDia(userService.userFromRequest(request).getEmpresa().getId(),dia);
		response.setData(turmas);
		return ResponseEntity.ok(response);
    }
				
	@GetMapping(value="{id}")
	public ResponseEntity<Response<Turma>> findById(@PathVariable("id") String id) {
		Response<Turma> response = new Response<Turma>();
		Turma turma = service.findById(id);
		if (turma == null) {
			response.getErrors().add("Register not found id:" + id);
			return ResponseEntity.badRequest().body(response);
		}
		response.setData(turma);
		return ResponseEntity.ok(response);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable String id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/{page}/{count}/{instrutorId}")
	public ResponseEntity<Response<Page<Turma>>> turmasInstrutor(@PathVariable int page, @PathVariable int count, @PathVariable String instrutorId){
		Response<Page<Turma>> response = new Response<Page<Turma>>();
		Page<Turma> turmas = service.findByInstrutor(page, count, instrutorId);
		response.setData(turmas);
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("/list/{instrutorId}")
	public ResponseEntity<Response<List<Turma>>> listTurmasInstrutor(@PathVariable String instrutorId){
		Response<List<Turma>> response = new Response<List<Turma>>();
		List<Turma> turmas = service.findListByInstrutor(instrutorId);
		response.setData(turmas);
		return ResponseEntity.ok(response);
	}
	@GetMapping("/dia/{dia}")
	public ResponseEntity<List<Turma>> findByDia(@PathVariable("dia") DiaEnum dia){
		
		return ResponseEntity.ok(service.findByDia(dia));
	}
}