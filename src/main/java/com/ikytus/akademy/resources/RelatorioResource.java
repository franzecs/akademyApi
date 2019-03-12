package com.ikytus.akademy.resources;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ikytus.akademy.domain.FluxoCaixa;
import com.ikytus.akademy.services.RelatorioService;

@RestController
@RequestMapping(value="/relatorios")
@CrossOrigin(origins="*")
public class RelatorioResource {
	
	@Autowired
	private RelatorioService relatorioService;
	
	
	@GetMapping("/planos")
	public ResponseEntity<byte[]> relPlanos(HttpServletRequest request) throws Exception {
		
		byte[] rel = relatorioService.relPlanos(request);
				
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_PDF_VALUE)
				.body(rel);
	}
	
	@GetMapping("/alunos")
	public ResponseEntity<byte[]> relAlunos(HttpServletRequest request) throws Exception {
		
		byte[] rel = relatorioService.relPlanos(request);
				
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_PDF_VALUE)
				.body(rel);
	}
	
	@GetMapping("/alunos/frequencia")
	public ResponseEntity<byte[]> relAlunosFrequencia(HttpServletRequest request) throws Exception {
		
		byte[] rel = relatorioService.relFrequencia(request);
				
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_PDF_VALUE)
				.body(rel);
	}
	
	@PostMapping("/fluxoCaixa")
	public ResponseEntity<byte[]> relFluxoCaixa(HttpServletRequest request, @RequestBody FluxoCaixa fluxo) throws Exception {
		
		byte[] rel = relatorioService.relFluxoCaixa(request, fluxo);
				
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_PDF_VALUE)
				.body(rel);
	}
	
	
}
