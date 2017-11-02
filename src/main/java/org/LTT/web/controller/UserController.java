package org.LTT.web.controller;

import org.LTT.persistence.dao.*;
import org.LTT.persistence.model.*;
import org.LTT.security.ActiveUserStore;
import org.LTT.web.dto.UserDto;
import org.LTT.web.viewmodel.MessageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class UserController {

	@Autowired
	ActiveUserStore activeUserStore;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UniversityRepository universityRepository;

	@Autowired
	private RoleRepository roleReposity;

	@Autowired
	private ReviewsRepository reviewsRepository;

	@Autowired
	UserInterface userService;

	@Autowired
	AssignInToMentorRepository assignInToMentorRepository;

	@Autowired
	private LocalRepository localRepository;
	private ObjectError error;

	@RequestMapping(value = "/searchusertop", method = RequestMethod.POST)
	public String search1(Model model, @RequestParam String userfirtname, @RequestParam String userlastname) {

		String redirectUrl = "/searchusertop";
		return "redirect:" + redirectUrl;
		// return "searchusertop";
	}

	@RequestMapping(value = "/userlist", method = RequestMethod.GET)
	public String listalluser(Model model, Principal principal) {
		model.addAttribute("userlist", userRepository.findByEnabled(true));
		model.addAttribute("checkrole", "");
		model.addAttribute("univer", universityRepository.findByStatus(true));
		try {
			System.out.println(" useruseruser == nnn " + principal.getName());
			User user = userService.findUserByEmail(principal.getName());
			long roleId = user.getRoleId();
			Role role = roleReposity.findOne(roleId);
			String rolename = role.getName();
			model.addAttribute("checkrole", rolename);
			System.out.println(" role =   =    " + role.getName());
			System.out.println("   user.getRoles()    " + user.getRoles());
		} catch (Exception e) {
		}
		return "users";
	}

	@RequestMapping(value = "/listmentor", method = RequestMethod.GET)
	public String listmentor(Model model, Principal principal) {
		model.addAttribute("univer", universityRepository.findByStatus(true));
		Role rolebyname = roleReposity.findByName("ROLE_USER");
		long roleIdmentor = rolebyname.getId();
		model.addAttribute("usermentor", userRepository.findByEnabledAndRoleId(true, roleIdmentor));
		try {
			User user = userService.findUserByEmail(principal.getName());
			long roleId = user.getRoleId();
			Role role = roleReposity.findOne(roleId);
			String rolename = role.getName();
			model.addAttribute("checkrole", rolename);

		} catch (Exception e) {
		}

		return "listmentor";
	}

	@RequestMapping(value = "/deletelogicuser", method = RequestMethod.POST)
	public String delete(@Validated User user, BindingResult bindingResult,
			RedirectAttributes redirectAttributes) {
		User userCheckExit = userRepository.findByEnabledAndId(true, user.getId());
		if (userCheckExit != null) {
			userCheckExit.setEnabled(false);
			userRepository.save(userCheckExit);
		
		} else if (userCheckExit == null) {
			MessageModel messageModel = new MessageModel();
			messageModel.setTypeMessage(MessageModel.TYPE_MESSAGE_ERROR);
			messageModel.getMessages().add("NOT FOUND ");
			redirectAttributes.addFlashAttribute("message", messageModel);
		}
		return "redirect:/userlist";
	}

	@RequestMapping(value = "/editlastname", method = RequestMethod.POST)
	public String editablelastname(Model model, @RequestParam long id, @RequestParam String name) {

		User user = userRepository.findOne(id);
		if (user != null) {
			user.setLastName(name);
			userRepository.save(user);
		}
		return "";
	}

	@RequestMapping(value = "/editfirtname", method = RequestMethod.POST)
	public String editablefirtname(Model model, @RequestParam long id, @RequestParam String name) {
		User user = userRepository.findOne(id);
		if (user != null) {
			user.setFirstName(name);
			userRepository.save(user);
		}
		return "";
	}

}
