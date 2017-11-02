package org.LTT.web.controller;

import org.LTT.persistence.dao.RoleRepository;
import org.LTT.persistence.dao.UniversityRepository;
import org.LTT.persistence.dao.UserInterface;
import org.LTT.persistence.dao.UserRepository;
import org.LTT.persistence.model.Role;
import org.LTT.persistence.model.University;
import org.LTT.persistence.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;
import java.util.Date;

@Controller
public class UniversityController {
	@Autowired
	private UniversityRepository universityRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	UserInterface userService;

	@Autowired
	private RoleRepository roleReposity;

	@RequestMapping(value = "/adduniversity", method = RequestMethod.POST)
	@ResponseBody
	public String adduniversity(Model model, @RequestParam String nameUniver) {

		University uni = new University();
		uni.setNameUniver(nameUniver);
		uni.setStatus(true);
		uni.setCreateDate(new Date());
		uni.setModifileDate(new Date());
		universityRepository.save(uni);
		return "university";
	}
	@RequestMapping(value = "/university", method = RequestMethod.GET)
	public String say(Model model, Principal principal) {
		model.addAttribute("universitylistall", universityRepository.findByStatus(true));

		try {

			User user = userService.findUserByEmail(principal.getName());
			long roleId = user.getRoleId();
			Role role = roleReposity.findOne(roleId);
			String rolename = role.getName();
			model.addAttribute("checkrole", rolename);

		} catch (Exception e) {
			// TODO: handle exception
		}
		return "university";
	}
	@RequestMapping(value = "/edituniver", method = RequestMethod.GET)
	public String edituniversity(Model model, @RequestParam long Id) {
			model.addAttribute("edituniver", universityRepository.findOne(Id));
		return "edituniver";
	}

	@RequestMapping(value = "/saveuniversity", method = RequestMethod.POST)
	@ResponseBody
	public String saveedituniversity(@RequestParam long id, @RequestParam String nameUniver) {

		University university = universityRepository.findOne(id);
			if (university != null) {
			university.setNameUniver(nameUniver);
			universityRepository.save(university);
			return "ok";
		} else {
			return "";
		}

	}

	@RequestMapping(value = "delete-logic-university", method = RequestMethod.POST)
	public String deletelogic(Model model, @RequestParam long Id) {
		System.out.println("deletelogicuniversity  000000000  ");
		University uni = universityRepository.findOne(Id);
		System.out.println("deletelogicuniversity  uni   " + uni);
		if (uni != null) {
			uni.setStatus(false);
			universityRepository.save(uni);
		} else {

			return "";
		}
		System.out.println("uni  00000000 = " + uni);
		return "university";
	}

}
