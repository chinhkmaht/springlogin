package org.LTT.web.controller;

import org.LTT.persistence.dao.*;
import org.LTT.persistence.model.PeriodTimesheet;
import org.LTT.persistence.model.Reviews;
import org.LTT.persistence.model.Role;
import org.LTT.persistence.model.User;
import org.LTT.security.ActiveUserStore;
import org.LTT.validation.TimeValidation;
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
public class ReviewController {

    @Autowired
    ActiveUserStore activeUserStore;

    @Autowired
    ReviewInterface ireviewsevice;
    
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
    UserInterface userService;

   
    @Autowired
    AssignInToMentorRepository assignInToMentorRepository;


    @Autowired
    private LocalRepository localRepository;

    @Autowired
    private LocalUserRepository localUserRepository;

    @Autowired
    PeriodTimesheetRepository periodTimesheetRepository;
    
	private ObjectError error;

    @RequestMapping(value = "/reviewsinternship/{id}", method = RequestMethod.GET)
    public String reviewsinternship(Model model, Principal principal, @PathVariable("id") long id) {
        Role rolebyname = roleReposity.findByName("ROLE_INTERSHIP");
        long roleIdIntership = rolebyname.getId();
        model.addAttribute("userintership", userRepository.findByEnabledAndRoleId(true, roleIdIntership));
        System.out.println("   00000000000   reviewsRepository.findByUserIdReview    " + reviewsRepository.findByUserIdReviewAndStatus(id, true));
        model.addAttribute("reviews", reviewsRepository.findByUserIdReviewAndStatus(id, true));
        model.addAttribute("userid", id);
        try {
            User user = userService.findUserByEmail(principal.getName());
            long userId = user.getId();
            System.out.println("userId  = " + userId);
            model.addAttribute("userreview", userId);
            long roleId = user.getRoleId();
            Role role = roleReposity.findOne(roleId);
            String rolename = role.getName();
            model.addAttribute("checkrole", rolename);
        } catch (Exception e) {
        }
        return "reviewsinternship";
    }

    @RequestMapping(value = "/editeviews", method = RequestMethod.POST)
    @ResponseBody
    public String savereview(@RequestParam String note, @RequestParam long id) {
        System.out.println(" id reviews   " + id);
        System.out.println(" note reviews   " + note);
        Reviews review = reviewsRepository.findOne(id);
        if (review != null) {
            review.setNote(note);
        }
        return "reviewsinternship";
    }

    @RequestMapping(value = "/reviewsmentor/{id}", method = RequestMethod.GET)
    public String reviewsmentor(Model model, Principal principal, @PathVariable("id") long id) {
        Role rolebyname = roleReposity.findByName("ROLE_USER");
        long roleIdmentor = rolebyname.getId();
        model.addAttribute("userintership", userRepository.findByEnabledAndRoleId(true, roleIdmentor));
        System.out.println("   00000000000   reviewsRepository.findByUserIdReview    " + reviewsRepository.findByUserIdReviewAndStatus(id, true));
        model.addAttribute("reviews", reviewsRepository.findByUserIdReviewAndStatus(id, true));
        model.addAttribute("userid", id);
        try {
            User user = userService.findUserByEmail(principal.getName());
            long userId = user.getId();
            System.out.println("userId  = " + userId);
            model.addAttribute("userreview", userId);
            long roleId = user.getRoleId();
            Role role = roleReposity.findOne(roleId);
            String rolename = role.getName();
            model.addAttribute("checkrole", rolename);
        } catch (Exception e) {
        }
        return "reviewsmentor";
    }

    @RequestMapping(value = "/deletelogicreview", method = RequestMethod.POST)
    public String Say(Model model, @RequestParam long id) {
        try {
            Reviews review = reviewsRepository.findOne(id);
            if (review != null) {
                review.setStatus(false);
                reviewsRepository.save(review);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return "reviewsinternship";
    }
    
	@RequestMapping(value = "/add-reviews-intership", method = RequestMethod.POST)
	public String savePeriodTimesheet(@Validated Reviews reviews, BindingResult bindingResult,
			RedirectAttributes redirectAttributes) {
		long userid = reviews.getUserIdReview();
		String url = "redirect:/listinternship";
		if (bindingResult.hasErrors()) {
			MessageModel messageModel = new MessageModel();
			messageModel.setTypeMessage(MessageModel.TYPE_MESSAGE_ERROR);
			for (ObjectError error : bindingResult.getAllErrors()) {
				String msg = error.getObjectName() + ":" + error.getDefaultMessage();
				messageModel.getMessages().add(msg);
			}
			System.out.println("  nooooooooooooooooo ");
			redirectAttributes.addFlashAttribute("message", messageModel);
			url ="redirect:/reviewsinternship/"+userid;

		} else if ((TimeValidation.validationDateReview(reviews, bindingResult, redirectAttributes))){
			// iAdminRegistrationPeriod.adminRegistration(registrationPeriod);
			ireviewsevice.addReviewsinternship(reviews);
			
			System.out.println("  ireviewsevice.addReviewsinternship(reviews)  ");
			MessageModel messageModel = new MessageModel();
			messageModel.setTypeMessage(MessageModel.TYPE_MESSAGE_ERROR);
			messageModel.getMessages().add("Success");
			redirectAttributes.addFlashAttribute("message", messageModel);
		}else if (!(TimeValidation.validationDateReview(reviews, bindingResult, redirectAttributes))){
			url ="redirect:/reviewsinternship/"+userid;
		}
		return url;
	}
	@RequestMapping(value = "/listinternship", method = RequestMethod.GET)
	public String listallmentor(Model model, Principal principal) {
		model.addAttribute("univer", universityRepository.findByStatus(true));
		Role rolebyname = roleReposity.findByName("ROLE_INTERSHIP");
		long roleIdIntership = rolebyname.getId();
		model.addAttribute("userintership", userRepository.findByEnabledAndRoleId(true, roleIdIntership));
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
		return "listinternship";
	}
	

//    @RequestMapping(value = "/reviewuserresult", method = RequestMethod.POST)
//    public String say(Model model, @RequestParam long rankid, @RequestParam String rank, @RequestParam long useridreview, @RequestParam long userid, @RequestParam String formdate, @RequestParam String todate, @RequestParam String note) {
//        System.out.println("rankid reviewuserresultreviewuserresult " + rankid);
//        String pattern = "yyyy-MM-dd";
//        SimpleDateFormat format = new SimpleDateFormat(pattern);
//        Reviews reivew = new Reviews();
//        try {
//            User user = userRepository.findOne(userid);
//            User userreviews = userRepository.findOne(useridreview);
//            Date reviewsForm = format.parse(formdate);
//            Date reviewTo = format.parse(todate);
//            reivew.setModified(new Date());
//            reivew.setCreated(new Date());
//            reivew.setRank(rank);
//            reivew.setNote(note);
//            reivew.setRankId(rankid);
//            reivew.setStatus(true);
//            reivew.setReviewsForm(reviewsForm);
//            reivew.setReviewTo(reviewTo);
//            reivew.setUserIdrv(userid);
//            reivew.setUserIdReview(useridreview);
//            reivew.setUsername(user.getFirstName() + " " + user.getLastName());
//            reivew.setUserReviewName(userreviews.getFirstName() + " " + userreviews.getLastName());
//            reviewsRepository.save(reivew);
//        } catch (Exception e) {
//            // TODO: handle exception
//            System.out.println(e);
//        }
//
//        return "reviewsinternship";
//    }
}
