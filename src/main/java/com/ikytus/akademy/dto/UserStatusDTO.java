package com.ikytus.akademy.dto;

import java.io.Serializable;

import com.ikytus.akademy.domain.User;

public class UserStatusDTO implements Serializable {	
	private static final long serialVersionUID = 1L;
	
	private String id;
    private Boolean isAtivo;
	
	public UserStatusDTO() {
	}
	
	public UserStatusDTO(User user) {
		this.id = user.getId();
		this.isAtivo = user.isAtivo();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Boolean getIsAtivo() {
		return isAtivo;
	}

	public void setIsAtivo(Boolean isAtivo) {
		this.isAtivo = isAtivo;
	}
}
