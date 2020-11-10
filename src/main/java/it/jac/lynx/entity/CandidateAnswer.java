package it.jac.lynx.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import it.jac.lynx.pk.PkCandidateAnswer;
import lombok.Data;

@Entity
@Table(name = "candidate_answer")
@IdClass(PkCandidateAnswer.class)
@Data
public class CandidateAnswer {

	@Id
	@Column(name = "id_candidate")
	private int idCandidate;

	@Id
	@Column(name = "id_question")
	private int idQuestion;

	@Column(name = "answer")
	private boolean answer;
	
	@Override
	public String toString() {
		return "Candidate Answer [id candidate=" + idCandidate + ", id question=" + idQuestion + ", answer=" + answer +  "]";
	}
	
}
