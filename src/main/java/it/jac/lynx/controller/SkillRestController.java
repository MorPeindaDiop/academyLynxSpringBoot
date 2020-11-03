package it.jac.lynx.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.jac.lynx.dto.Response;
import it.jac.lynx.entity.Skill;
import it.jac.lynx.service.SkillService;


@RestController
@RequestMapping("/rest/skill")
public class SkillRestController {
	private static Logger log = LoggerFactory.getLogger(SkillRestController.class);

	@Autowired
	private SkillService skillService;

	@PostMapping("/create")
	public Response<?> createSkill(
			@RequestParam String description) {

		log.info("Ricevuta richiesta di creazione nuovo prodotto");

		log.info("Rilevato utente di creazione");
		/* Con le tre righe sopra prendiamo le informazioni inerenti all'utente che sono necessarie
		 * per settare l'attributo creationUser e successivamente updateUser
		 */

		Skill skill = new Skill();
		skill.setDescription(description);
		log.info("Rilevata data di creazione");

		//this.skillService.createSkill(skill);

		return skillService.createSkill(skill);
	}

}
