package org.LTT.web.controller;

import org.LTT.persistence.dao.*;
import org.LTT.persistence.model.Local;
import org.LTT.persistence.model.LocalUser;
import org.LTT.persistence.model.Role;
import org.LTT.persistence.model.User;
import org.LTT.security.ActiveUserStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

@Controller
public class LocalController {
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

    @RequestMapping(value = "/local", method = RequestMethod.GET)
    public String local(Model model) {
        model.addAttribute("locals", localRepository.findAll());
        return "local";
    }

    @RequestMapping(value = "/addlocal", method = RequestMethod.POST)
    public String addlocal(Model model, @RequestParam String localname) {
        Local local = new Local();
        local.setLocalname(localname);
        local.setCreated(new Date());
        local.setModified(new Date());
        local.setStatus(true);
        localRepository.save(local);
        return "local";
    }

    @RequestMapping(value = "/deletelocal", method = RequestMethod.POST)
    public String deletelocal(Model model, @RequestParam long id) {
        Local local = localRepository.findOne(id);
        if (local != null) {
            localRepository.delete(local);
        }

        return "local";
    }

    @RequestMapping(value = "editlocal", method = RequestMethod.POST)
    public String editlocal(Model model, @RequestParam String localname, @RequestParam long id) {
        Local local = localRepository.findOne(id);
        local.setLocalname(localname);
        localRepository.save(local);
        return "local";
    }

    @RequestMapping(value = "/localuser", method = RequestMethod.GET)
    public String localuser(Model model) {
        Role role = roleReposity.findByName("ROLE_INTERSHIP");
        long roleid = role.getId();
        model.addAttribute("userlist", userRepository.findByEnabledAndLocalIdIsNullAndRoleId(true, roleid));
        model.addAttribute("locals", localRepository.findAll());
        model.addAttribute("localusers", localUserRepository.findByStatus(true));
        return "localuser";
    }

    @RequestMapping(value = "/addlocaluser", method = RequestMethod.POST)
    public String addlocaluser(Model model, @RequestParam long userid, @RequestParam long localid) {
        Local local = localRepository.findOne(localid);
        User user = userRepository.findOne(userid);
        String username = user.getFirstName() + " " + user.getLastName();
        LocalUser localUser = new LocalUser();
        localUser.setCreated(new Date());
        localUser.setModifed(new Date());
        localUser.setUserId(userid);
        localUser.setLocalId(localid);
        localUser.setStatus(true);
        localUser.setLocalName(local.getLocalname());
        localUser.setUserName(username);
        localUserRepository.save(localUser);
        user.setLocalId(localid);
        userRepository.save(user);
        return "localuser";
    }

    @RequestMapping(value = "/deletelocaluser", method = RequestMethod.POST)
    public String deletelocaluser(@RequestParam long id) {

        LocalUser localUser = localUserRepository.findOne(id);
        localUser.setStatus(false);
        long userid = localUser.getUserId();
        User user = userRepository.findOne(userid);
        user.setLocalId(null);
        userRepository.save(user);
        return "localuser";
    }

    @RequestMapping(value = "/editlocaluser/{id}", method = RequestMethod.GET)
    public String editlocaluser(Model model, @PathVariable("id") long id) {
        System.out.println("  editlocaluser         000 333");
        LocalUser localUser = localUserRepository.findOne(id);
        model.addAttribute("localuseredit", localUser);
        model.addAttribute("selecteduser", userRepository.findOne(localUser.getUserId()));
        model.addAttribute("selectedlocal", localRepository.findOne(localUser.getLocalId()));
        model.addAttribute("locals", localRepository.findAll());
        return "editlocaluser";
    }


    @RequestMapping(value = "/saveeditlocaluser", method = RequestMethod.POST)
    public String saveeditlocaluser(Model model, @RequestParam long id, @RequestParam long localid) {
        LocalUser localUser = localUserRepository.findOne(id);
        if (localUser != null) {
            localUser.setLocalId(localid);
            localUserRepository.save(localUser);
        }
        return "localuser";
    }
}
