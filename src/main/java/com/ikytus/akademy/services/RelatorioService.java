package com.ikytus.akademy.services;

import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ikytus.akademy.domain.FluxoCaixa;
import com.ikytus.akademy.domain.ItemFluxoCaixa;
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
	private String mesAtual;
	private int anoAtual;

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
		List<User> instrutores = userService.findByTipoAndEmpresa("Instrutor", userService.userFromRequest(request).getEmpresa().getId());
		
		for (User I : instrutores) {
			List<User> alunosInstrutor = new ArrayList<>();
			List<Frequencia> freqTemp = new ArrayList<>();
			for(User A : userService.findByTipoAndEmpresa("Aluno", I.getEmpresa().getId())) {
				for(Turma t : A.getTurmas()) {
					if(t.getInstrutor().getId().equals(I.getId())) {
						if(!alunosInstrutor.contains(A)) {
							alunosInstrutor.addAll(Arrays.asList(A));
						}
					}
				}
			}
			
			for(User aluno: alunosInstrutor) {
				Frequencia f = new Frequencia(I.getNome(), null, null, null,null, aluno.getNome());
				for(Turma turma: aluno.getTurmas()) {
					if(turma.getInstrutor().getId().equals(I.getId())) {
						if(f.getHorario()== null) {
							f.setHorario(turma.getHorario());
							f.setSemana(turma.getDia());
						}else {
							if(f.getHorario().equals(turma.getHorario())) {
								if(f.getSemana2()==null) {
									f.setSemana2(turma.getDia());
								}else {
									f.setSemana3(turma.getDia());
								}
							}else {
							  Frequencia f2 = new Frequencia(I.getNome(), turma.getHorario(), turma.getDia(), null,null, aluno.getNome());
							  freqTemp.addAll(Arrays.asList(f2));
							}
						}
					}
				}
				freqTemp.addAll(Arrays.asList(f));
			}
			freqTemp.sort(Comparator.comparing(Frequencia::getHorario));
			frequencias.addAll(freqTemp);
		}
				
		geraDias();
						
		Map<String, Object> parametros = new HashMap<>();
		parametros.put("REPORT_LOCALE", new Locale("pt", "BR"));
		parametros.put("mes", mesAtual);
		parametros.put("ano", anoAtual);
		for (Dia dia : dias) {
			parametros.put(dia.getDiaStr(), dia.getSem());
			parametros.put("S" + dia.getDiaStr(), dia.getDiaSemana());
		}

		InputStream inputStream = this.getClass().getResourceAsStream("/relatorios/relFrequencia.jasper");

		JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, parametros,
				new JRBeanCollectionDataSource(frequencias));

		return JasperExportManager.exportReportToPdf(jasperPrint);
	}

	public byte[] relFluxoCaixa(HttpServletRequest request, FluxoCaixa fluxo) throws Exception {

		List<ItemFluxoCaixa> itens = fluxo.getItens();

		Map<String, Object> parametros = new HashMap<>();
		parametros.put("REPORT_LOCALE", new Locale("pt", "BR"));
		parametros.put("mes", geraMes(fluxo.getMes()));
		parametros.put("ano", fluxo.getAno());
		parametros.put("previsto", fluxo.getPrevisto());
		parametros.put("realizado", fluxo.getExecutado());

		InputStream inputStream = this.getClass().getResourceAsStream("/relatorios/relFluxoCaixa.jasper");

		JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, parametros,
				new JRBeanCollectionDataSource(itens));

		return JasperExportManager.exportReportToPdf(jasperPrint);
	}

	public void geraDias() throws ParseException {

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("America/Sao_Paulo"));

		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());

		int mes = cal.get(Calendar.MONTH) + 1;
		anoAtual = cal.get(Calendar.YEAR);

		mesAtual = geraMes(mes);

		for (int i = 1; i < 32; i++) {
			cal.set(Calendar.DAY_OF_MONTH, i);
			cal.set(Calendar.MONTH, mes - 1);
			cal.set(Calendar.YEAR, anoAtual);
			Dia dia = new Dia(i, cal.get(Calendar.DAY_OF_WEEK));
			dias.addAll(Arrays.asList(dia));
		}

	}

	private String geraMes(int mes) {
		if (mes == 1) {
			return "Janeiro";
		}
		if (mes == 2) {
			return "Fevereiro";
		}
		if (mes == 3) {
			return "Março";
		}
		if (mes == 4) {
			return "Abril";
		}
		if (mes == 5) {
			return "Maio";
		}
		if (mes == 6) {
			return "Junho";
		}
		if (mes == 7) {
			return "Julho";
		}
		if (mes == 8) {
			return "Agosto";
		}
		if (mes == 9) {
			return "Setembro";
		}
		if (mes == 10) {
			return "Outubro";
		}
		if (mes == 11) {
			return "Novembro";
		}
		if (mes == 12) {
			return "Dezembro";
		} else {
			return "mês invalido";
		}
	}
}
