package it.jac.lynx.entity;

import java.io.Serializable;

import lombok.Data;

@Data
public class PkUserSkill implements Serializable {
	
	//private User idUser;

	//private Question idSkill;

	private int idUser;

	private int idSkill;

}