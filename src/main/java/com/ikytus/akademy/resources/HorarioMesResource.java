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

import com.ikytus.akademy.domain.HorarioMes;
import com.ikytus.akademy.domain.models.Response;
import com.ikytus.akademy.security.UserService;
import com.ikytus.akademy.services.HorarioMesService;

@RestController
@RequestMapping(value = "/horariomes")
@CrossOrigin(origins = "*")
public class HorarioMesResource {

	@Autowired
	private HorarioMesService service;

	@Autowired
	private UserService userService;

	@GetMapping("/{ano}/{mes}")
	public ResponseEntity<Response<List<HorarioMes>>> listAllByEmpresa(HttpServletRequest request,
			@PathVariable int ano, @PathVariable int mes) {

		Response<List<HorarioMes>> response = new Response<List<HorarioMes>>();
		Sort sort = Sort.by("dia").ascending().and(Sort.by("horario").ascending());

		List<HorarioMes> horarioMes = service.findAll(userService.userFromRequest(request).getEmpresa().getId(), ano,
				mes, sort);
		response.setData(horarioMes);
		return ResponseEntity.ok(response);
	}

	@GetMapping("/{ano}/{mes}/{instrutorId}")
	public ResponseEntity<Response<List<HorarioMes>>> listAllByEmpresa(HttpServletRequest request,
			@PathVariable int ano, @PathVariable int mes, @PathVariable String instrutorId) {

		Response<List<HorarioMes>> response = new Response<List<HorarioMes>>();
		Sort sort = Sort.by("dia").ascending().and(Sort.by("horario").ascending());

		List<HorarioMes> horarioMes = service.findAllbyInstrutor(
				userService.userFromRequest(request).getEmpresa().getId(), ano, mes, instrutorId, sort);

		response.setData(horarioMes);

		return ResponseEntity.ok(response);
	}

	@PostMapping
	public ResponseEntity<Response<HorarioMes>> insert(HttpServletRequest request, @RequestBody HorarioMes horarioMes,
			BindingResult result) {
		horarioMes.setEmpresa(userService.userFromRequest(request).getEmpresa());
		HorarioMes horarioMesNew = service.createOrUpdate(horarioMes);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(horarioMesNew.getId())
				.toUri();
		return ResponseEntity.created(uri).build();
	}

	@PutMapping("/{id}")
	public ResponseEntity<Response<HorarioMes>> update(@RequestBody HorarioMes horarioMes, @PathVariable String id) {
		Response<HorarioMes> response = new Response<HorarioMes>();
		horarioMes.setId(id);
		response.setData(service.createOrUpdate(horarioMes));
		return ResponseEntity.ok(response);
	}

	@GetMapping(value = "{id}")
	public ResponseEntity<Response<HorarioMes>> findById(@PathVariable("id") String id) {
		Response<HorarioMes> response = new Response<HorarioMes>();
		HorarioMes horarioMes = service.findById(id);
		if (horarioMes == null) {
			response.getErrors().add("Register not found id:" + id);
			return ResponseEntity.badRequest().body(response);
		}
		response.setData(horarioMes);
		return ResponseEntity.ok(response);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable String id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

}