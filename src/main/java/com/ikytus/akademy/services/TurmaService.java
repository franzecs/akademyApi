package com.ikytus.akademy.services;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ikytus.akademy.domain.Turma;
import com.ikytus.akademy.domain.User;
import com.ikytus.akademy.domain.enums.DiaEnum;
import com.ikytus.akademy.repository.TurmaRepository;
import com.ikytus.akademy.repository.UserRepository;
import com.ikytus.akademy.services.exception.ObjectNotFoundException;

@Service
public class TurmaService {

	@Autowired
	private TurmaRepository turmaRepository;

	@Autowired
	private UserRepository userRepository;

	public Page<Turma> findAll(int page, int count) {
		return turmaRepository.findAll(this.pages(page, count));
	}

	public Page<Turma> findByEmpresa(int page, int count, String empresaId) {
		Page<Turma> turmas = this.turmaRepository.findByEmpresaIdOrderByDiaAscHorarioAsc(this.pages(page, count), empresaId);
		List<User> alunos = userRepository.findAll();
		for (Turma t : turmas) {
			for (User a : alunos) {
				for (Turma ta : a.getTurmas()) {
					if (ta.getId().equals(t.getId())) {
						t.getAlunos().addAll(Arrays.asList(a));
					}
				}
			}
		}
		return turmas;
	}

	public Page<Turma> findByInstrutor(int page, int count, String instrutorId) {
		Page<Turma> turmas = this.turmaRepository.findByInstrutorIdOrderByDiaAscHorarioAsc(this.pages(page, count), instrutorId);
		List<User> alunos = userRepository.findAll();
		for (Turma t : turmas) {
			for (User a : alunos) {
				for (Turma ta : a.getTurmas()) {
					if (ta.getId().equals(t.getId())) {
						t.getAlunos().addAll(Arrays.asList(a));
					}
				}
			}
		}
		return turmas;
	}

	public List<Turma> findListByInstrutor(String instrutorId) {
		List<Turma> turmas = this.turmaRepository.findByInstrutorIdOrderByDiaAscHorarioAsc(instrutorId);

		List<User> alunos = userRepository.findAll();
		for (Turma t : turmas) {
			for (User a : alunos) {
				for (Turma ta : a.getTurmas()) {
					if (ta.getId().equals(t.getId())) {
						t.getAlunos().addAll(Arrays.asList(a));
					}
				}
			}
		}

		return turmas;
	}
	
	
	
	public List<Turma> findByDia(DiaEnum dia){
		return turmaRepository.findByDia(dia);
	}

	public List<Turma> listByEmpresa(String empresaId) {		
		List<Turma> turmas = this.turmaRepository.findByEmpresaIdOrderByDiaAscHorarioAsc(empresaId);
		List<User> alunos = userRepository.findAll();
		for (Turma t : turmas) {
			for (User a : alunos) {
				for (Turma ta : a.getTurmas()) {
					if (ta.getId().equals(t.getId())) {
						t.getAlunos().addAll(Arrays.asList(a));
					}
				}
			}
		}
		return turmas;
	}
	
	public List<Turma> listByEmpresaHorario(String empresaId) {		
		List<Turma> turmas = this.turmaRepository.findByEmpresaIdOrderByInstrutorAscHorarioAsc(empresaId);
		List<User> alunos = userRepository.findAll();
		for (Turma t : turmas) {
			for (User a : alunos) {
				for (Turma ta : a.getTurmas()) {
					if (ta.getId().equals(t.getId())) {
						t.getAlunos().addAll(Arrays.asList(a));
					}
				}
			}
		}
		return turmas;
	}
	
	public List<Turma> listTurmasEmpresa(String empresaId) {		
		List<Turma> turmas = this.turmaRepository.findByEmpresaIdOrderByDiaAscHorarioAsc(empresaId);
		return turmas;
	}
	
	
	public List<Turma> listByEmpresaDia(String empresaId, DiaEnum dia){
		return this.turmaRepository.findByEmpresaIdAndDiaOrderByHorarioAsc(empresaId, dia);
	}
	
	public Turma findById(String id) {
		Optional<Turma> turma = turmaRepository.findById(id);

		List<User> alunos = userRepository.findAll();

		for (User a : alunos) {
			for (Turma t : a.getTurmas()) {
				
				if (t.getId().equals(id)) {
					turma.get().getAlunos().addAll(Arrays.asList(a));
				}
			}
		}

		return turma.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}

	public Turma createOrUpdate(Turma turma) {
		return this.turmaRepository.save(turma);
	}

	public void delete(String id) {
		findById(id);
		turmaRepository.deleteById(id);
	}

	public Pageable pages(int page, int count) {
		return PageRequest.of(page, count);
	}
}
