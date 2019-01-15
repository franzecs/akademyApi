package com.ikytus.akademy.security;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.ikytus.akademy.domain.Empresa;
import com.ikytus.akademy.domain.Turma;
import com.ikytus.akademy.domain.User;
import com.ikytus.akademy.dto.UserDTO;
import com.ikytus.akademy.repository.EmpresaRepository;
import com.ikytus.akademy.repository.UserRepository;
import com.ikytus.akademy.services.EmpresaService;
import com.ikytus.akademy.services.exception.ObjectNotFoundException;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private EmpresaRepository empresaRepository;
	
	@Autowired
	private EmpresaService empresaService;	
			
	@Autowired
    protected JWTUtil jwtTokenUtil;
	
	public Page<User> findAll(int page, int count) {
		Pageable pages = PageRequest.of(page, count);
		return this.userRepository.findAll(pages);
	}
	
	public User findByEmail(String email) {
		return this.userRepository.findByEmail(email);
	}
	
	public User findById(String id) {
		
		return userRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}
	
	public Page<User> findByTipoAndEmpresa(int page, int count, String tipo, String empresaId) {
		Pageable pages = PageRequest.of(page, count);
		return userRepository.findBytipoUserAndEmpresaIdOrderByNome(pages,tipo, empresaId);
	}
	
	public Page<User> findByTipoAndEmpresaNome(int page, int count, String tipo, String empresaId, String nome) {
		Pageable pages = PageRequest.of(page, count);
		return userRepository.findBytipoUserAndEmpresaIdAndNomeContainingIgnoreCase(pages,tipo, empresaId, nome);
	}
	
		
	public List<User> findByTipoAndEmpresa(String tipo, String empresaId) {
		return userRepository.findBytipoUserAndEmpresaId(tipo, empresaId);
	}
	
	public List<UserDTO> findByTipoAndInstrutor(String instrutorId, String empresaId) {	
		List<User> alunosInstrutor = new ArrayList<>();
		for(User u : this.findByTipoAndEmpresa("Aluno", empresaId)) {
			for(Turma t : u.getTurmas()) {
				if(t.getInstrutor().getId().equals(instrutorId)) {
					if(!alunosInstrutor.contains(u))
					alunosInstrutor.addAll(Arrays.asList(u));
				}
			}
		}
		List<UserDTO> listDTO = alunosInstrutor.stream().map(x -> new UserDTO(x)).collect(Collectors.toList());
		return listDTO;
	}
	
	public User createOrUpdate (User user) {
		user.setNome(user.getNome().toUpperCase());
		return userRepository.save(user);
	}
						
	public void delete(String id) {
		findById(id);
		userRepository.deleteById(id);
	}
		
	public User update(User user) {
		User newUser = findById(user.getId());
		updateData(newUser, user);
		return userRepository.save(newUser);
	}
	
	public User updateStatus(String status, String id) {
		User newUser = findById(id);
				
		if(status.equals("true")) {
			newUser.setAtivo(true);
		}else {
			newUser.setAtivo(false);
		}
		
		return userRepository.save(newUser);
	}
	
	public User updateURLPerfil(String url, String id) {
		
		User newUser = findById(id);
		newUser.setUrl_perfil(url);
		return userRepository.save(newUser);
	}
	
	public void updateData(User newUser, User user) {
		if(user.getSenha() != null) {
			newUser.setSenha(user.getSenha());
		}
		newUser.setNome(user.getNome());
		newUser.setEmail(user.getEmail());
		newUser.setEmpresa(user.getEmpresa());
		newUser.setEndereco(user.getEndereco());
		newUser.setAtivo(user.isAtivo());
		newUser.setUrl_perfil(user.getUrl_perfil());
	}
	
	/*public User fromDTO(UserDTO userDto) {
		return new User(userDto.getId(), userDto.getNome(), userDto.getEmail(), "",
				userDto.getIsAtivo(), userDto.getEndereco(), userDto.getEmpresa(), userDto.getUrl_perfil());
	}*/
	
	public List<Empresa> empresasFindByUser(String id){
			List<Empresa> empresas = new ArrayList<>();
			empresas.add(empresaService.findById(id));
			empresas.addAll(empresaRepository.findByMatrizId(id));
		return empresas;
	}
	
	public User userFromRequest(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        String email = jwtTokenUtil.getUsername(token.substring(7));
        return findByEmail(email);
    }
	
	public static UserSS authenticated() {
		try {
			return (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		}
		catch (Exception e) {
			return null;
		}
	}
}