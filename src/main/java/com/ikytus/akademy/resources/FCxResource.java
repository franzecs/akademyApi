package com.ikytus.akademy.resources;

import java.net.URI;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.ikytus.akademy.domain.FCx;
import com.ikytus.akademy.domain.models.Response;
import com.ikytus.akademy.services.FCxService;

@RestController
@RequestMapping(value = "/fcx")
@CrossOrigin(origins = "*")
public class FCxResource {

	@Autowired
	private FCxService service;

	@SuppressWarnings("static-access")
	@PostMapping
	public ResponseEntity<Response<FCx>> insert(HttpServletRequest request, @RequestBody FCx fluxoCaixa,
			BindingResult result) {

		Response<FCx> response = new Response<FCx>();

		FCx fluxoNew = service.createOrUpdate(fluxoCaixa);
		response.setData(fluxoNew);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(fluxoNew.getId())
				.toUri();
		return ResponseEntity.created(uri).build().ok(response);
	}

	@GetMapping
	public ResponseEntity<Response<List<FCx>>> listAll(HttpServletRequest request) {
		Response<List<FCx>> response = new Response<List<FCx>>();
		List<FCx> planos = service.findAll();
		response.setData(planos);
		return ResponseEntity.ok(response);
	}

	@GetMapping("/{ano}/{mes}")
	public ResponseEntity<Response<List<FCx>>> listByAnoMes(HttpServletRequest request, @PathVariable int ano,
			@PathVariable int mes) {

		Response<List<FCx>> response = new Response<List<FCx>>();

		List<FCx> fluxo = service.findBy(ano, mes);
		response.setData(fluxo);
		return ResponseEntity.ok(response);
	}

	@GetMapping(value = "{id}")
	public ResponseEntity<Response<FCx>> findById(@PathVariable("id") String id, HttpServletRequest request) {
		Response<FCx> response = new Response<FCx>();

		FCx fluxo = service.findById(id);
		if (fluxo == null) {
			response.getErrors().add("Register not found id:" + id);
			return ResponseEntity.badRequest().body(response);
		}
		response.setData(fluxo);
		return ResponseEntity.ok(response);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Response<FCx>> update(@RequestBody FCx fluxoCaixa, @PathVariable String id) {
		Response<FCx> response = new Response<FCx>();
		fluxoCaixa.setId(id);
		response.setData(service.createOrUpdate(fluxoCaixa));
		return ResponseEntity.ok(response);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable String id, HttpServletRequest request) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}