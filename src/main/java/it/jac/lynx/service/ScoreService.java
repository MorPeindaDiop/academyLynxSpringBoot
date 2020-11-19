package it.jac.lynx.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.jac.lynx.dto.CandidateAnswerDTO;
import it.jac.lynx.dto.CandidateDTO;
import it.jac.lynx.dto.CandidateResponseDTO;
import it.jac.lynx.dto.QuestionDTO;
import it.jac.lynx.dto.Response;
import it.jac.lynx.entity.CandidateAnswer;

@Service
public class ScoreService {
	private static Logger log = LoggerFactory.getLogger(ScoreService.class);

	@Autowired
	private QuestionService questionService;

	@Autowired
	private CandidateAnswerService candidateAnswerService;

	@Autowired
	private CandidateService candidateService;	

	public Response<List<CandidateAnswerDTO>> setCandidateResponse(List<CandidateResponseDTO> lista, int idCandidate){

		Response<List<CandidateAnswerDTO>> response = new Response <List<CandidateAnswerDTO>>();

		List<CandidateAnswerDTO> listaReturn= new ArrayList<CandidateAnswerDTO>();

		try {

			for (CandidateResponseDTO candidateResponseDTO : lista) {

				CandidateAnswer ca = new CandidateAnswer();
				QuestionDTO qDTO=questionService.findQuestionById(candidateResponseDTO.getIdQuestion()).getResult();
				ca.setIdCandidate(idCandidate);
				ca.setIdQuestion(qDTO.getId());

				switch (qDTO.getType()) {
				case "aperta":
					ca.setAnswer(candidateResponseDTO.getCandidateResponse().equalsIgnoreCase(qDTO.getCorrectAnswerText()) ? true : false);
					break;
				case "vf":
					Boolean answer = candidateResponseDTO.getCandidateResponse().equalsIgnoreCase("true") ? true : false;
					ca.setAnswer(answer ? true : false);
					break;
				case "crocette":
					ca.setAnswer(candidateResponseDTO.getCandidateResponse().equalsIgnoreCase(qDTO.getCorrectAnswerText()) ? true : false);
					break;
				}

				candidateAnswerService.createCandidateAnswer(ca);
				listaReturn.add(CandidateAnswerDTO.build(ca));

			}

			response.setResult(listaReturn);
			response.setResultTest(true);

		} catch (Exception e ) {

			response.setError("Nessun elemento trovato.");

		}

		return response;

	}

	public Response<CandidateDTO> setScoreCandidate(int idCandidate) {

		Response<CandidateDTO> response = new Response <CandidateDTO>();

		List<CandidateAnswerDTO> candidateTest = new ArrayList<CandidateAnswerDTO>();

		double nCorrectAnswer = 0;

		double weightedScore = 0;

		double arithmeticScore = 0;

		double totalWeightedScoreTest = 0;

		try {

			CandidateDTO candidate = candidateService.findCandidateById(idCandidate).getResult();

			candidateTest = candidateAnswerService.findCandidateAnswerByIdCandidate(idCandidate).getResult();

			for (CandidateAnswerDTO answer: candidateTest) {

				QuestionDTO question = questionService.findQuestionById(answer.getIdQuestion()).getResult();

				totalWeightedScoreTest += question.getDifficulty();
				log.info(totalWeightedScoreTest + " totalWeightedScoreTes nel for");

				if (answer.isAnswer()) {

					nCorrectAnswer += 1;

					weightedScore += question.getDifficulty();
					log.info(weightedScore + " weightedScore nel for");

				}

			}

			log.info(candidateTest.size() + " candidateTest.size()");
			log.info(arithmeticScore + " arithmeticScore");
			log.info(nCorrectAnswer + " nCorrectAnswer");
			arithmeticScore =  (( nCorrectAnswer / (double) candidateTest.size() ) * 100);
			log.info(arithmeticScore + " arithmeticScore ricalcolato");
			weightedScore =  (( weightedScore / totalWeightedScoreTest ) * 100);
			log.info(totalWeightedScoreTest + " totalWeightedScoreTest");
			log.info(weightedScore + " weightedScore ricalcolato");

			candidateService.setCandidateScoreAndTime(candidate.getId(), (int) nCorrectAnswer, (int) weightedScore, (int) arithmeticScore, 50);

			response.setResult(candidate);
			response.setResultTest(true);

		} catch (Exception e ) {

			response.setError("Nessun elemento trovato.");

		}

		return response;

	}

}
