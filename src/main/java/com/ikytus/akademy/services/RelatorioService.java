package com.ikytus.akademy.services;

import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ikytus.akademy.domain.Plano;
import com.ikytus.akademy.domain.Turma;
import com.ikytus.akademy.domain.User;
import com.ikytus.akademy.domain.models.Dia;
import com.ikytus.akademy.domain.models.Frequencia;
import com.ikytus.akademy.security.UserService;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
public class RelatorioService {

	@Autowired
	private UserService userService;
	
	@Autowired
	private PlanoService planoService;
	
	@Autowired
	private TurmaService turmaService;
	
	private List<Dia> dias = new ArrayList<>();
	

	public byte[] relPlanos(HttpServletRequest request) throws Exception {

		List<Plano> planos = planoService.listByEmpresa(userService.userFromRequest(request).getEmpresa().getId());
		
		Map<String, Object> parametros = new HashMap<>();
		parametros.put("REPORT_LOCALE", new Locale("pt", "BR"));

		InputStream inputStream = this.getClass().getResourceAsStream("/relatorios/relPlanos.jasper");

		JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, parametros,
				new JRBeanCollectionDataSource(planos));

		return JasperExportManager.exportReportToPdf(jasperPrint);
	}
	
	public byte[] relFrequencia(HttpServletRequest request) throws Exception {

		List<Frequencia> frequencias = new ArrayList<>();
		List<Turma> turmas = turmaService.listByEmpresaHorario(userService.userFromRequest(request).getEmpresa().getId());
		
		for (Turma t :  turmas) {
			for(User aluno :  t.getAlunos()) {
				Frequencia f = new Frequencia(t.getInstrutor().getNome(), t.getHorario(), t.getDia(), aluno.getNome());
				frequencias.addAll(Arrays.asList(f));
			}
		}
		
		geraDias();
		
		Map<String, Object> parametros = new HashMap<>();
		parametros.put("REPORT_LOCALE", new Locale("pt", "BR"));
		for(Dia dia: dias) {
			parametros.put(dia.getDiaStr(), dia.getSem());
		}

		InputStream inputStream = this.getClass().getResourceAsStream("/relatorios/relFrequencia.jasper");

		JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, parametros,
				new JRBeanCollectionDataSource(frequencias));

		return JasperExportManager.exportReportToPdf(jasperPrint);
	}
	
	
	
	public void geraDias() throws ParseException {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("America/Sao_Paulo"));
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		
		int mes = cal.get(Calendar.MONTH)+1;
		int ano = cal.get(Calendar.YEAR);
		
		for (int i=1; i<32; i++) {
			Date data = sdf.parse(i +"/" + mes +"/"+ano);
			cal.setTime(data);
			Dia dia = new Dia(i,cal.get(Calendar.DAY_OF_WEEK));
			dias.addAll(Arrays.asList(dia));
		}
		
	}
}
