package org.LTT.web.controller;

import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.LTT.persistence.dao.*;
import org.LTT.persistence.model.PeriodTimesheet;
import org.LTT.persistence.model.RegistrationPeriod;
import org.LTT.persistence.model.Role;
import org.LTT.persistence.model.Timesheet;
import org.LTT.persistence.model.User;
import org.LTT.security.ActiveUserStore;
import org.LTT.service.IAdminPeriodSheet;
import org.LTT.service.IAdminRegistrationPeriod;
import org.LTT.service.ITimesheetService;
import org.LTT.service.IUserService;
import org.LTT.validation.TimeValidation;
import org.LTT.web.viewmodel.MessageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class TimeSheetController {

	@Autowired
	private ITimesheetService iTimesheetService;

	@Autowired
	private IAdminRegistrationPeriod iAdminRegistrationPeriod;

	@Autowired
	private IAdminPeriodSheet iAdminPeriodSheet;

	@Autowired
	ActiveUserStore activeUserStore;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private CompanyCardRepository companycardrepository;

	@Autowired
	private UniversityRepository universityRepository;

	@Autowired
	private RoleRepository roleReposity;

	@Autowired
	private ReviewsRepository reviewsRepository;

	@Autowired
	IUserService userService;

	@Autowired
	private TimesheetRepository timesheetRepository;

	@Autowired
	AssignInToMentorRepository assignInToMentorRepository;

	@Autowired
	private LocalRepository localRepository;

	@Autowired
	private LocalUserRepository localUserRepository;

	@Autowired
	PeriodTimesheetRepository periodTimesheetRepository;

	@Autowired
	ResgistrationPeriodReposition resgistrationPeriodReposition;

	private ObjectError error;

	@RequestMapping(value = "/admintimesheet", method = RequestMethod.GET)
	public String admintimesheet(Model model) {
		Role role = roleReposity.findByName("ROLE_INTERSHIP");
		long roleid = role.getId();
		model.addAttribute("listUserTimesheet", userRepository.findByTimesheetIsNullAndEnabledAndRoleId(true, roleid));
		model.addAttribute("Listtimesheet", timesheetRepository.findByStatus(true));
		return "adminTimesheet";
	}

	@RequestMapping(value = "/add-timesheet", method = RequestMethod.POST)
	public String addtimesheet(@Validated Timesheet timesheet, BindingResult bindingResult,
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
			iTimesheetService.addtimesheet(timesheet);
		}
		return "redirect:/admintimesheet";

	}

	@RequestMapping(value = "/admindeletetimesheet", method = RequestMethod.POST)
	public String admindeletetimesheet(Model model, @RequestParam long id) {

		try {
			Timesheet timesh = timesheetRepository.findOne(id);
			if (timesh != null) {
				timesh.setStatus(false);
				timesheetRepository.save(timesh);
				long userid = timesh.getUserid();

				User user = userRepository.findOne(userid);
				user.setTimesheet(null);
				userRepository.save(user);
			}
		} catch (Exception e) {

		}

		return "adminTimesheet";
	}

	@RequestMapping(value = "/save-registration-period", method = RequestMethod.POST)
	public String saveRegistrationperiod(@Validated RegistrationPeriod registrationPeriod, BindingResult bindingResult,
			RedirectAttributes redirectAttributes) {
		if (bindingResult.hasErrors()) {
			MessageModel messageModel = new MessageModel();
			messageModel.setTypeMessage(MessageModel.TYPE_MESSAGE_ERROR);
			for (ObjectError error : bindingResult.getAllErrors()) {
				String msg = error.getObjectName() + ":" + error.getDefaultMessage();
				messageModel.getMessages().add(msg);
			}
			redirectAttributes.addFlashAttribute("message", messageModel);

		} else if ((TimeValidation.validation(registrationPeriod, bindingResult, redirectAttributes))) {
			iAdminRegistrationPeriod.adminRegistration(registrationPeriod);
			MessageModel messageModel = new MessageModel();
			messageModel.setTypeMessage(MessageModel.TYPE_MESSAGE_ERROR);
			messageModel.getMessages().add("Success");
			redirectAttributes.addFlashAttribute("message", messageModel);
		}

		return "redirect:/list-period-timesheet";
	}

	@RequestMapping(value = "/admin-save-registration-edit", method = RequestMethod.POST)
	public String saveEditRegistrationPeriodTimesheet(@Validated RegistrationPeriod registrationPeriod,
			BindingResult bindingResult, RedirectAttributes redirectAttributes) {
		RegistrationPeriod checkexit = iAdminRegistrationPeriod.findbyid(registrationPeriod.getId());
		if (checkexit == null) {
			MessageModel messageModel = new MessageModel();
			messageModel.setTypeMessage(MessageModel.TYPE_MESSAGE_ERROR);
			messageModel.getMessages().add("NOT FOUND Registration Period");
			System.out.println(" 88888888888883333 ");
			redirectAttributes.addFlashAttribute("message", messageModel);
		} else if ((TimeValidation.validation(registrationPeriod, bindingResult, redirectAttributes))) {
			System.out.println(
					"555  = " + (TimeValidation.validation(registrationPeriod, bindingResult, redirectAttributes)));
			System.out.println(" registrationPeriod   9999 " + registrationPeriod);
			iAdminRegistrationPeriod.editRegistrationPeriod(registrationPeriod);
			MessageModel messageModel = new MessageModel();
			messageModel.setTypeMessage(MessageModel.TYPE_MESSAGE_ERROR);
			messageModel.getMessages().add("Success");
			redirectAttributes.addFlashAttribute("message", messageModel);
		}
		return "redirect:/list-period-timesheet";
	}

	// delete
	@RequestMapping(value = "/delete-registration-period-timesheet", method = RequestMethod.POST)
	public String deleteRegistrationPeriodTimesheet(@Validated RegistrationPeriod registrationPeriod,
			BindingResult bindingResult, RedirectAttributes redirectAttributes) {
		RegistrationPeriod reg = resgistrationPeriodReposition.findByStatusAndId(true, registrationPeriod.getId());
		if (reg == null) {
			MessageModel messageModel = new MessageModel();
			messageModel.setTypeMessage(MessageModel.TYPE_MESSAGE_ERROR);
			messageModel.getMessages().add("NOT FOUND Registration ");
			redirectAttributes.addFlashAttribute("message", messageModel);
		} else if (reg != null) {
			reg.setStatus(false);
			resgistrationPeriodReposition.save(reg);
		}
		return "redirect:/list-period-timesheet";
	}

	@RequestMapping(value = "/edit-registration-time/{id}", method = RequestMethod.GET)
	public String editRegistrationTime(Model model, Principal principal, @PathVariable("id") long id) {
		PeriodTimesheet periodTimesheet = periodTimesheetRepository.findOne(id);
		RegistrationPeriod registrationPeriod = resgistrationPeriodReposition.findOne(id);
		PeriodTimesheet periodTimesheet2 = periodTimesheetRepository.findOne(registrationPeriod.getPeriodId());
		model.addAttribute("Timesheetregis", periodTimesheet2);
		model.addAttribute("userid", userRepository.findByEnabled(true));
		model.addAttribute("registrationPeriod", registrationPeriod);
		try {
			User user = userService.findUserByEmail(principal.getName());
			long roleId = user.getRoleId();
			Role role = roleReposity.findOne(roleId);
			String rolename = role.getName();
			model.addAttribute("checkrole", rolename);

		} catch (Exception e) {
		}
		return "editAdminRehistrationPeriod";
	}

	// period edit

	@RequestMapping(value = "/save-edit-period-timesheet", method = RequestMethod.POST)
	public String saveEditPeriodTimesheet(@Validated PeriodTimesheet periodTimesheet, BindingResult bindingResult,
			RedirectAttributes redirectAttributes) {
		System.out.println("save-edit-period-timesheet  ");
		PeriodTimesheet checkexit = iAdminPeriodSheet.findbyid(periodTimesheet.getId());
		if (checkexit == null) {
			MessageModel messageModel = new MessageModel();
			messageModel.setTypeMessage(MessageModel.TYPE_MESSAGE_ERROR);
			messageModel.getMessages().add("NOT FOUND  Period");
			System.out.println(" 88888888888883333 ");
			redirectAttributes.addFlashAttribute("message", messageModel);
		} else if (bindingResult.hasErrors()) {
			MessageModel messageModel = new MessageModel();
			messageModel.setTypeMessage(MessageModel.TYPE_MESSAGE_ERROR);
			for (ObjectError error : bindingResult.getAllErrors()) {
				String msg = error.getObjectName() + ":" + error.getDefaultMessage();
				messageModel.getMessages().add(msg);
			}
			redirectAttributes.addFlashAttribute("message", messageModel);

		} else if ((TimeValidation.validationDate(periodTimesheet, bindingResult, redirectAttributes))) {
			iAdminPeriodSheet.editPeriod(periodTimesheet);
			MessageModel messageModel = new MessageModel();
			messageModel.setTypeMessage(MessageModel.TYPE_MESSAGE_ERROR);
			messageModel.getMessages().add("Success");
			redirectAttributes.addFlashAttribute("message", messageModel);
		}
		return "redirect:/period-timesheet";
	}

	@RequestMapping(value = "/period-timesheet", method = RequestMethod.GET)
	public String periodtimesheet(Model model, Principal principal) {
		model.addAttribute("listperiod", periodTimesheetRepository.findByStatus(true));
		try {
			User user = userService.findUserByEmail(principal.getName());
			long roleId = user.getRoleId();
			Role role = roleReposity.findOne(roleId);
			String rolename = role.getName();
			model.addAttribute("checkrole", rolename);

		} catch (Exception e) {
		}
		return "listPeriodTimesheet";
	}

	@RequestMapping(value = "/new-period-timesheet", method = RequestMethod.GET)
	public String addperiodtimesheet(Model model, Principal principal) {
		try {
			User user = userService.findUserByEmail(principal.getName());
			long roleId = user.getRoleId();
			Role role = roleReposity.findOne(roleId);
			String rolename = role.getName();
			model.addAttribute("checkrole", rolename);

		} catch (Exception e) {
		}
		return "periodtimesheet";
	}

	// period

	@RequestMapping(value = "/add-period-timesheet", method = RequestMethod.POST)
	public String savePeriodTimesheet(@Validated PeriodTimesheet periodTimesheet, BindingResult bindingResult,
			RedirectAttributes redirectAttributes) {
		if (bindingResult.hasErrors()) {
			MessageModel messageModel = new MessageModel();
			messageModel.setTypeMessage(MessageModel.TYPE_MESSAGE_ERROR);
			for (ObjectError error : bindingResult.getAllErrors()) {
				String msg = error.getObjectName() + ":" + error.getDefaultMessage();
				messageModel.getMessages().add(msg);
			}
			redirectAttributes.addFlashAttribute("message", messageModel);

		} else if ((TimeValidation.validationDate(periodTimesheet, bindingResult, redirectAttributes))) {
			// iAdminRegistrationPeriod.adminRegistration(registrationPeriod);
			iAdminPeriodSheet.adminPriod(periodTimesheet);
			MessageModel messageModel = new MessageModel();
			messageModel.setTypeMessage(MessageModel.TYPE_MESSAGE_ERROR);
			messageModel.getMessages().add("Success");
			redirectAttributes.addFlashAttribute("message", messageModel);
		}

		return "redirect:/period-timesheet";

	}

	@RequestMapping(value = "/delete-periodtimesheet", method = RequestMethod.POST)
	public String deleteperiodtimesheet(@Validated PeriodTimesheet periodTimesheet, BindingResult bindingResult,
			RedirectAttributes redirectAttributes) {

		PeriodTimesheet period = periodTimesheetRepository.findByStatusAndId(true, periodTimesheet.getId());
		if (period == null) {
			MessageModel messageModel = new MessageModel();
			messageModel.setTypeMessage(MessageModel.TYPE_MESSAGE_ERROR);
			messageModel.getMessages().add("NOT FOUND PeriodTimesheet ");
			redirectAttributes.addFlashAttribute("message", messageModel);

		} else if (period != null) {
			System.out.println("period  period.getId() 8 ===== " + period.getId());
			period.setStatus(false);
			periodTimesheetRepository.save(period);

		}

		return "redirect:/period-timesheet";
	}

	@RequestMapping(value = "/editperiodtimesheet/{id}", method = RequestMethod.GET)
	public String editperiodtimesheet(Model model, @PathVariable("id") long id, Principal principal) {
		PeriodTimesheet periodTimesheet = periodTimesheetRepository.findOne(id);
		model.addAttribute("editperi", periodTimesheet);
		try {
			User user = userService.findUserByEmail(principal.getName());
			long roleId = user.getRoleId();
			Role role = roleReposity.findOne(roleId);
			String rolename = role.getName();
			model.addAttribute("checkrole", rolename);

		} catch (Exception e) {
		}
		return "editperiodtimesheet";
	}

	@RequestMapping(value = "/choisePeriodTimesheet/{id}", method = RequestMethod.GET)
	public String getperiodtimesheet(Model model, @PathVariable("id") long id, Principal principal) {
		PeriodTimesheet periodTimesheet = periodTimesheetRepository.findOne(id);
		model.addAttribute("Timesheet", periodTimesheet);
		model.addAttribute("userid", userRepository.findByEnabled(true));
		model.addAttribute("listperiod", periodTimesheetRepository.findAll());
		try {
			User user = userService.findUserByEmail(principal.getName());
			long roleId = user.getRoleId();
			Role role = roleReposity.findOne(roleId);
			String rolename = role.getName();
			model.addAttribute("checkrole", rolename);

		} catch (Exception e) {
		}
		return "adminRehistrationPeriod";
	}

	@RequestMapping(value = "/list-period-timesheet", method = RequestMethod.GET)
	public String listperiodtimesheet(Model model, Principal principal) {
		model.addAttribute("listperiod", periodTimesheetRepository.findByStatus(true));
		model.addAttribute("listregistration", resgistrationPeriodReposition.findByStatus(true));
		try {
			User user = userService.findUserByEmail(principal.getName());
			long roleId = user.getRoleId();
			Role role = roleReposity.findOne(roleId);
			String rolename = role.getName();
			model.addAttribute("checkrole", rolename);

		} catch (Exception e) {
		}
		return "adminGetPeriodTimesheet";
	}

}
