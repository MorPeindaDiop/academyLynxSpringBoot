package it.jac.lynx.dto;

import java.util.Date;
import java.util.HashMap;

import org.springframework.beans.BeanUtils;

import it.jac.lynx.entity.Candidate;
import lombok.Data;

@Data
public class CandidateDTO {

	private int id;

	private String name;

	private String surname;
	
	private int idSeniority;
	
	private Date dataTest;

	private int score;

	private int time;

	public static CandidateDTO build(Candidate candidate) {

		CandidateDTO result = new CandidateDTO();
		BeanUtils.copyProperties(candidate, result);

		return result;
	}
	
}
