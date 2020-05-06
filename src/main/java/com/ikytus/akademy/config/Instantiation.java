package com.ikytus.akademy.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.ikytus.akademy.domain.Empresa;
import com.ikytus.akademy.domain.Plano;
import com.ikytus.akademy.domain.Turma;
import com.ikytus.akademy.domain.User;
import com.ikytus.akademy.domain.enums.DiaEnum;
import com.ikytus.akademy.domain.enums.Perfil;
import com.ikytus.akademy.domain.models.Aluno;
import com.ikytus.akademy.domain.models.Endereco;
import com.ikytus.akademy.domain.models.Instrutor;
import com.ikytus.akademy.dto.EmpresaDTO;
import com.ikytus.akademy.dto.InstrutorDTO;
import com.ikytus.akademy.dto.PlanoDTO;
import com.ikytus.akademy.repository.EmpresaRepository;
import com.ikytus.akademy.repository.PlanoRepository;
import com.ikytus.akademy.repository.TurmaRepository;
import com.ikytus.akademy.repository.UserRepository;

//@Configuration 
public class Instantiation implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private EmpresaRepository empresaRepository;

	@Autowired
	private PlanoRepository planoRepository;

	@Autowired
	private TurmaRepository turmaRepository;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Override
	public void run(String... args) throws Exception {

		/*SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("America/Sao_Paulo"));

		userRepository.deleteAll();
		empresaRepository.deleteAll();
		planoRepository.deleteAll();
		turmaRepository.deleteAll();

		Endereco ender = new Endereco("61648-050", "Av. de Contorno Oeste", "Bloco 30 Apto 22A", "Nova Metrópole",
				"Caucaia", "CE", "405");

		Empresa emp1 = new Empresa(null, "Ikytus Sistemas", "03.729.627/0001-76", ender, "(85)988951038",
				"ikytussistemas@gmail.com", "Matriz", new EmpresaDTO(), "");
		Empresa emp2 = new Empresa(null, "Teste Sistemas", "03.729.627/0001-76", ender, "(85)988951038",
				"testesistemas0@gmail.com", "Matriz", new EmpresaDTO(), "");

		empresaRepository.saveAll(Arrays.asList(emp1, emp2));

		Empresa emp3 = new Empresa(null, "Teste filial_1", "03.729.627/0001-76", ender, "(85)988951038",
				"testesistemas1@gmail.com", "Filial", new EmpresaDTO(emp1), "");
		Empresa emp4 = new Empresa(null, "Teste filial_2", "03.729.627/0001-76", ender, "(85)988951038",
				"testesistemas2@gmail.com", "Filial", new EmpresaDTO(emp1), "");
		Empresa emp5 = new Empresa(null, "Teste filial_3", "03.729.627/0001-76", ender, "(85)988951038",
				"testesistemas3@gmail.com", "Filial", new EmpresaDTO(emp2), "");

		empresaRepository.saveAll(Arrays.asList(emp3, emp4, emp5));

		User franze = new User(null, "Franzé Silva", new Date(), sdf.parse("02/05/1979"), "franzecs@gmail.com",
				passwordEncoder.encode("123456"), "3212-5932", "988951038", true, ender, "Administrador",
				new EmpresaDTO(emp1), "../../../assets/img/prod.jpg", "20000100", "61649864353");
		franze.addPerfil(Perfil.ADMIN_SISTEMA);
		User alana = new User(null, "Alana", new Date(), sdf.parse("02/05/1979"), "alana@gmail.com",
				passwordEncoder.encode("123456"), "3212-5932", "988951038", true, ender, "Aluno", new EmpresaDTO(emp1),
				"../../../assets/img/prod.jpg", "20000100", "61649864353");
		alana.addPerfil(Perfil.ADMIN_EMPRESA);

		Instrutor inst1 = new Instrutor(null, "Instrutor1", new Date(), sdf.parse("02/05/1979"), "inst1@gmail.com",
				passwordEncoder.encode("123456"), "3212-5932", "988951038", true, ender, "Instrutor",
				new EmpresaDTO(emp1), "../../../assets/img/prod.jpg", "20000100", "61649864353");
		inst1.addPerfil(Perfil.INSTRUTOR);
		Instrutor inst2 = new Instrutor(null, "Instrutor2", new Date(), sdf.parse("02/05/1979"), "inst2@gmail.com",
				passwordEncoder.encode("123456"), "3212-5932", "988951038", true, ender, "Instrutor",
				new EmpresaDTO(emp1), "../../../assets/img/prod.jpg", "20000100", "61649864353");
		inst2.addPerfil(Perfil.INSTRUTOR);

		System.out.println(franze.getDataNascimento());

		userRepository.saveAll(Arrays.asList(franze, alana, inst1, inst2));

		Plano p1 = new Plano(null, "Mensal 2", 250.00, "duas aulas por semana", new EmpresaDTO(emp1));

		Plano p2 = new Plano(null, "Mensal 3", 270.80, "três aulas por semana", new EmpresaDTO(emp1));

		planoRepository.saveAll(Arrays.asList(p1, p2));

		Turma t1 = new Turma(null, DiaEnum.SEGUNDA, "08:00 - 09:00", "normal", new EmpresaDTO(emp1),
				new InstrutorDTO(inst1));
		Turma t2 = new Turma(null, DiaEnum.TERCA, "08:00 - 09:00", "normal", new EmpresaDTO(emp1),
				new InstrutorDTO(inst1));
		Turma t3 = new Turma(null, DiaEnum.QUARTA, "08:00 - 09:00", "normal", new EmpresaDTO(emp1),
				new InstrutorDTO(inst2));
		Turma t4 = new Turma(null, DiaEnum.SEGUNDA, "13:00 - 14:00", "normal", new EmpresaDTO(emp1),
				new InstrutorDTO(inst1));

		turmaRepository.saveAll(Arrays.asList(t1, t2, t3, t4));

		userRepository.saveAll(Arrays.asList(inst1, inst2));

		Aluno aluno1 = new Aluno(null, "Franzé Silva", new Date(), sdf.parse("02/05/1979"), "aluno1@gmail.com",
				passwordEncoder.encode("123456"), "3212-5932", "988951038", true, ender, "Aluno", new EmpresaDTO(emp1),
				"../../../assets/img/prod.jpg", "20000100", "61649864353");
		aluno1.addPerfil(Perfil.ALUNO);
		aluno1.setPlano(new PlanoDTO(p1));
		aluno1.setSexo("Masculino");
		aluno1.setPeso("104");
		aluno1.setDiaPagamento("5");
		aluno1.setObs("teste de aluno asdasdasjdasdj");
		aluno1.getTurmas().addAll(Arrays.asList(t1, t2));

		userRepository.saveAll(Arrays.asList(aluno1));
*/
	}
}
