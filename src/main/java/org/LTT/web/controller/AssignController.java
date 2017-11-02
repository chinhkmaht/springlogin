package org.LTT.web.controller;

import org.LTT.persistence.dao.*;
import org.LTT.persistence.model.AssignInToM;
import org.LTT.persistence.model.Role;
import org.LTT.persistence.model.User;
import org.LTT.security.ActiveUserStore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.Date;

@Controller
public class AssignController {
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
    UserInterface userService;

   
    @Autowired
    AssignInToMentorRepository assignInToMentorRepository;


    @Autowired
    private LocalRepository localRepository;

    @Autowired
    private LocalUserRepository localUserRepository;

    @Autowired
    PeriodTimesheetRepository periodTimesheetRepository;

    @RequestMapping(value = "/assign", method = RequestMethod.GET)
    public String Assign(Model model,Principal principal) {
        Role role = roleReposity.findByName("ROLE_INTERSHIP");
        Role rolementor = roleReposity.findByName("ROLE_USER");
        long rolemenotrid = rolementor.getId();
        long roleid = role.getId();
        model.addAttribute("userinter", userRepository.findByAssignToIsLessThanAndEnabledAndRoleId(1, true, roleid));
        model.addAttribute("usermentor", userRepository.findByEnabledAndRoleId(true, rolemenotrid));
        model.addAttribute("assignall", assignInToMentorRepository.findByStatus(true));
        try {
			User user = userService.findUserByEmail(principal.getName());
			long roleId = user.getRoleId();
			Role rolecheck = roleReposity.findOne(roleId);
			String rolename = rolecheck.getName();
			model.addAttribute("checkrole", rolename);

		} catch (Exception e) {
		}
        return "assignInterToMentor";
    }

    @RequestMapping(value = "/save-assign", method = RequestMethod.POST)
    public String SaveAssign(Model model, @RequestParam long userinter, @RequestParam long usermentor) {
        try {
            User userinternship = userRepository.findOne(userinter);
            User usermentorcheck = userRepository.findOne(usermentor);
            AssignInToM assignInToM = new AssignInToM();
            assignInToM.setUserInter(userinter);
            assignInToM.setUserMentor(usermentor);
            assignInToM.setFirtnameinter(userinternship.getFirstName());
            assignInToM.setLastnameinter(userinternship.getLastName());
            assignInToM.setLastnamementor(usermentorcheck.getLastName());
            assignInToM.setFirtnamementor(usermentorcheck.getFirstName());
            assignInToM.setStatus(true);
            assignInToM.setCreateDate(new Date());
            assignInToM.setModifileDate(new Date());
            assignInToMentorRepository.save(assignInToM);

            userinternship.setAssignTo(usermentor);
            userRepository.save(userinternship);

        } catch (Exception e) {

        }
        return "assignInterToMentor";
    }
    @RequestMapping(value = "/delete-assign", method = RequestMethod.POST)
    public String deleteassign(Model model, @RequestParam long id) {
        try {
            AssignInToM assgin = assignInToMentorRepository.findOne(id);
            System.out.println(" assgin  = " + assgin);
            if (assgin != null) {
                assgin.setStatus(false);
                assignInToMentorRepository.save(assgin);
                long userinter = assgin.getUserInter();

                System.out.println("userinter  = " + userinter);
                User user = userRepository.findOne(userinter);
                user.setAssignTo(0);
                System.out.println("user   =  " + user);
                userRepository.save(user);
            }

        } catch (Exception e) {

        }
        return "";
    }

}
