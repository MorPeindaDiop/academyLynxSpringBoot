package it.jac.lynx.service;

import static org.junit.Assert.assertEquals;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import it.jac.lynx.entity.Candidate;



@SpringBootTest
public class CandidateServiceTest {

	@Autowired
	private CandidateService candidateService;
	
	@Test
	public void createCandidateTest() {
		Candidate candidate = new Candidate();
		candidate.setIdSeniority(3);
		candidate.setName("pippo");
		candidate.setSurname("pluto");
		assertEquals(true, candidateService.createCandidate(candidate).isResultTest());
	}
	
	@Test 
	public void deleteCandidateByIdTest() {	
			assertEquals("Candidate eliminata.", candidateService.deleteCandidateById(2).getResult());
	}
	
	@Test
	public void findAllCandidateTest() {
		assertEquals(true, candidateService.findAllCandidates().isResultTest());
	}
	
	@Test
	public void findCandidateByIdTest() {
		assertEquals(true, candidateService.findCandidateById(1).isResultTest());
	}
	
	@Test
	public void findCandidatesByIdSeniority() {
		assertEquals(true, candidateService.findCandidatesByidSeniority(3).isResultTest());
	}
	
}