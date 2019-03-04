package com.ikytus.akademy.resources;

import java.net.URI;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
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

import com.ikytus.akademy.domain.Empresa;
import com.ikytus.akademy.domain.User;
import com.ikytus.akademy.domain.enums.Perfil;
import com.ikytus.akademy.domain.models.Response;
import com.ikytus.akademy.dto.EmpresaDTO;
import com.ikytus.akademy.dto.UserDTO;
import com.ikytus.akademy.security.UserService;
import com.ikytus.akademy.services.EmpresaService;

@RestController
@RequestMapping(value="/users")
@CrossOrigin(origins = "*")
public class UserResource {
	
	@Autowired
	private UserService service;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private EmpresaService empresaService;
	
	@GetMapping("/{page}/{count}")
	//@PreAuthorize("hasAnyRole('ADMIN_SISTEMA')")
    public  ResponseEntity<Response<Page<User>>> findAll(@PathVariable int page, @PathVariable int count) {
		Response<Page<User>> response = new Response<Page<User>>();
		Page<User> users = service.findAll(page, count);
		response.setData(users);
		return ResponseEntity.ok(response);
    }
	
	@GetMapping("/{id}")
	//@PreAuthorize("hasAnyRole('ADMIN_EMPRESA')")
	public ResponseEntity<Response<User>> findId(@PathVariable String id){
		Response<User> response = new Response<User>();
		User user = service.findById(id);
		if (user == null) {
			response.getErrors().add("Register not found id:" + id);
			return ResponseEntity.badRequest().body(response);
		}
		response.setData(user);
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("/currentuser/{email}")
	public ResponseEntity<Response<User>> findEmail(@PathVariable String email){
		Response<User> response = new Response<User>();
		User user = service.findByEmail(email);
		if (user == null) {
			response.getErrors().add("Register not found id:" + email);
			response.setData(new User());
			return ResponseEntity.ok(response);
		}
		response.setData(user);
		return ResponseEntity.ok(response);
	}
	
	@PostMapping
	public ResponseEntity<Response<User>> create(HttpServletRequest request, @RequestBody User user, 
			                                      BindingResult result) throws ParseException{
		
		Response<User> response = new Response<User>();
		
		if(result.hasErrors()) {
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()) );
			return ResponseEntity.badRequest().body(response);
		}
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("America/Sao_Paulo"));

		user.setDataNascimento(sdf.parse(user.getDtr()));
				
		user.setSenha(passwordEncoder.encode(user.getSenha()));
		User userPersistend = service.createOrUpdate(user);
		response.setData(userPersistend);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(userPersistend.getId()).toUri();
		return ResponseEntity.created(uri).body(response);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Response<User>> update(HttpServletRequest request, @RequestBody User user, 
			                                     @PathVariable("id") String id, BindingResult result) throws ParseException{
		Response<User> response = new Response<User>();
					
		if(result.hasErrors()) {
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()) );
			return ResponseEntity.badRequest().body(response);
		}
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("America/Sao_Paulo"));

		user.setDataNascimento(sdf.parse(user.getDtr()));
		
		User userdb = service.findById(id);
		
		if(!userdb.getSenha().equals(user.getSenha())) {
			user.setSenha(passwordEncoder.encode(user.getSenha()));
		}
		
		user.setId(id);
		User userPersistend = service.createOrUpdate(user);
		
		response.setData(userPersistend);
		return ResponseEntity.ok(response);
	}
	
	@PutMapping("/{status}/{id}")
	public ResponseEntity<Response<String>> updateStatus(
			@PathVariable("status") String status, 
			@PathVariable("id") String id){
		
		service.updateStatus(status, id);
		return ResponseEntity.ok(new Response<String>());
	}
	
	@PutMapping("/perfil/{id}")
	public ResponseEntity<Response<String>> updateUrlPerfil(
			@PathVariable("id") String id,
			@RequestBody String url){
		
		service.updateURLPerfil(url, id);
		return ResponseEntity.ok(new Response<String>());
	}
			
	@DeleteMapping("/{id}")
	public ResponseEntity<Response<String>> delete(@PathVariable("id") String id) {
		Response<String> response = new Response<String>();
		User user = service.findById(id);
		if (user == null) {
			response.getErrors().add("Register not found id:" + id);
			return ResponseEntity.badRequest().body(response);
		}
		service.delete(id);
		return ResponseEntity.ok(new Response<String>());
	}
	
	@GetMapping("/{id}/empresas")
	public ResponseEntity<List<EmpresaDTO>> findEmpresas(@PathVariable String id){
		User user = service.findById(id);
		List<Empresa> list= new ArrayList<>();
		
		if(user.getPerfis().contains(Perfil.ADMIN_SISTEMA)) {
			list = empresaService.findAll();
		}else {
			String idEmpresa = service.findById(id).getEmpresa().getId();
			list = service.empresasFindByUser(idEmpresa);
		}
		List<EmpresaDTO> listDTO = list.stream().map(x -> new EmpresaDTO(x)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}
	
	@GetMapping("/search/{page}/{count}/{tipo}")
	public ResponseEntity<Response<Page<User>>> findByTipoUserAndEmpresaId(HttpServletRequest request,
			@PathVariable("page") int page, @PathVariable("count") int count,
			@PathVariable("tipo") String tipo){
		
		Response<Page<User>> response = new Response<Page<User>>();
		Page<User> users = service.findByTipoAndEmpresa(page, count, tipo, service.userFromRequest(request).getEmpresa().getId());
		response.setData(users);
		
		return ResponseEntity.ok().body(response);
	}
	
	@GetMapping("/search/alunos/{page}/{count}/{tipo}/{ativo}/{nome}")
	public ResponseEntity<Response<Page<User>>> findByTipoUserAndEmpresaIdAtivo(HttpServletRequest request,
			@PathVariable("page") int page, @PathVariable("count") int count,
			@PathVariable("tipo") String tipo, @PathVariable("ativo") boolean ativo,
			@PathVariable("nome") String nome){
		
		Response<Page<User>> response = new Response<Page<User>>();
		Page<User> users = service.findByTipoAndEmpresaAtivoNome(page, count, tipo, service.userFromRequest(request).getEmpresa().getId(), ativo, nome);
		response.setData(users);
		
		return ResponseEntity.ok().body(response);
	}
	
	@GetMapping("/search/{page}/{count}/{tipo}/{nome}")
	public ResponseEntity<Response<Page<User>>> findByTipoUserAndEmpresaIdNome(HttpServletRequest request,
			@PathVariable("page") int page, @PathVariable("count") int count,
			@PathVariable("tipo") String tipo, @PathVariable("nome") String nome) {
		
		Response<Page<User>> response = new Response<Page<User>>();
		Page<User> users = service.findByTipoAndEmpresaNome(page, count, tipo, service.userFromRequest(request).getEmpresa().getId(), nome);
		response.setData(users);
		
		return ResponseEntity.ok().body(response);
	}
	
	
	@GetMapping("/search/{tipo}/empresa")
	public ResponseEntity<List<User>> listByTipoUserAndEmpresaId(HttpServletRequest request,
			@PathVariable("tipo") String tipo){
		return ResponseEntity.ok().body(service.findByTipoAndEmpresa(tipo, service.userFromRequest(request).getEmpresa().getId()));
	}
	
	@GetMapping("/search/{instrutorId}")
	public ResponseEntity<Response<List<UserDTO>>> listAlunosByInstrutorId(HttpServletRequest request,
			@PathVariable("instrutorId") String instrutorId){
		
		Response<List<UserDTO>> response = new Response<List<UserDTO>>();
		List<UserDTO> users = service.findByTipoAndInstrutor(instrutorId, service.userFromRequest(request).getEmpresa().getId());
		response.setData(users);
		
		return ResponseEntity.ok().body(response);
	}
	
	@GetMapping("/alunosAtivos")
	public ResponseEntity<Response<List<User>>> listByAtivoEmpresa(HttpServletRequest request){
		Response<List<User>> response = new Response<List<User>>();
		response.setData(service.findByAtivoandEmpresa(service.userFromRequest(request).getEmpresa().getId()));
		
		return ResponseEntity.ok().body(response);
	}
	
}