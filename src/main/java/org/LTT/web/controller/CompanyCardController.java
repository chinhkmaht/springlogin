package org.LTT.web.controller;

import org.LTT.persistence.dao.CompanyCardRepository;
import org.LTT.persistence.dao.RoleRepository;
import org.LTT.persistence.dao.UserRepository;
import org.LTT.persistence.model.CompanyCard;
import org.LTT.persistence.model.RegistrationPeriod;
import org.LTT.persistence.model.Role;
import org.LTT.persistence.model.University;
import org.LTT.persistence.model.User;
import org.LTT.service.IAdminCardService;
import org.LTT.service.IUserService;
import org.LTT.validation.TimeValidation;
import org.LTT.web.viewmodel.MessageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.Arrays;
import java.util.Date;

@Controller
public class CompanyCardController {
	@Autowired
	private CompanyCardRepository companyCardRepository;

	@Autowired
	IAdminCardService iAdminCardSevice;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	IUserService userService;

	@Autowired
	private RoleRepository roleReposity;
	private ObjectError error;

	@RequestMapping(value = "/companycard", method = RequestMethod.GET)
	public String say(Model model, Principal principal) {
		model.addAttribute("companyCard", companyCardRepository.findByStatus(true));

		model.addAttribute("userall", userRepository.findByCompanycardIdIsNullAndEnabled(true));

		try {
			System.out.println(" useruseruser == nnn " + principal.getName());
			User user = userService.findUserByEmail(principal.getName());
			long roleId = user.getRoleId();
			Role role = roleReposity.findOne(roleId);
			String rolename = role.getName();
			model.addAttribute("checkrole", rolename);

		} catch (Exception e) {
			// TODO: handle exception
		}
		return "companycard";
	}

	@RequestMapping(value = "/addcompanycard", method = RequestMethod.POST)
	public String saveNewCompanyCard(@Validated CompanyCard companyCard, BindingResult bindingResult,
			RedirectAttributes redirectAttributes) {
		if (bindingResult.hasErrors()) {
			MessageModel messageModel = new MessageModel();
			messageModel.setTypeMessage(MessageModel.TYPE_MESSAGE_ERROR);
			for (ObjectError error : bindingResult.getAllErrors()) {
				String msg = error.getObjectName() + ":" + error.getDefaultMessage();
				messageModel.getMessages().add(msg);
			}
			redirectAttributes.addFlashAttribute("message", messageModel);
		} else {
			iAdminCardSevice.addNewCard(companyCard);
			MessageModel messageModel = new MessageModel();
			messageModel.setTypeMessage(MessageModel.TYPE_MESSAGE_ERROR);
			messageModel.getMessages().add("Success");
			redirectAttributes.addFlashAttribute("message", messageModel);
		}
		return "redirect:/companycard";
	}

	@RequestMapping(value = "/save-edit-companycard", method = RequestMethod.POST)
	@ResponseBody
	public String saveedituniversity(@RequestParam long id, @RequestParam String name) {
		CompanyCard companyCard = companyCardRepository.findOne(id);
		System.out.println("3333333333   ");
		if (companyCard != null) {
			companyCard.setName(name);
			companyCardRepository.save(companyCard);
			return "ok";
		} else {
			return "";
		}
	}

	@RequestMapping(value = "/deletelogiccompanycard", method = RequestMethod.POST)
	public String deletelogic(Model model, @RequestParam long Id) {
		System.out.println("deletelogicuniversity  000000000  ");
		CompanyCard companyCard = companyCardRepository.findOne(Id);
		System.out.println("deletelogicuniversity  uni   " + companyCard);
		if (companyCard != null) {
			companyCard.setStatus(false);
			companyCardRepository.save(companyCard);
			User user = userRepository.findOne(companyCard.getUserId());
			user.setCompanycardId(null);
			userRepository.save(user);
		}
		return "companycard";
	}

}
