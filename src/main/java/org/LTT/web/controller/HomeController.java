package org.LTT.web.controller;

import org.LTT.persistence.dao.RoleRepository;
import org.LTT.persistence.dao.UserInterface;
import org.LTT.persistence.dao.UserRepository;
import org.LTT.persistence.model.Role;
import org.LTT.persistence.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;
import java.util.Locale;

@Controller
public class HomeController {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    UserInterface userService;
    
    @Autowired 
    private RoleRepository roleReposity;
    
    @Autowired
    UserRepository userRepository;

    @RequestMapping( "/")
    public String say1(Model model,Principal principal) {
    	try {
    		System.out.println(" useruseruser == nnn "+principal.getName());
        	User user =userService.findUserByEmail(principal.getName());
        	long roleId= user.getRoleId();
        	System.out.println( "  roleId       "+roleId);
        	Role role = roleReposity.findOne(roleId);
        	String rolename =role.getName();
        	model.addAttribute("checkrole",rolename);
        	System.out.println( "  rolename       "+rolename);
		} catch (Exception e) {
			// TODO: handle exception
		}
        return "index";
    }

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String say(Model model,Principal principal) {
    	try {
    		System.out.println(" useruseruser == nnn "+principal.getName());
        	User user =userService.findUserByEmail(principal.getName());
        	long roleId= user.getRoleId();
        	Role role = roleReposity.findOne(roleId);
        	String rolename =role.getName();
        	model.addAttribute("checkrole",rolename);
        	
		} catch (Exception e) {
			// TODO: handle exception
		}
        return "index";
    }
    
    
    @RequestMapping(value = "/homepageinternship", method = RequestMethod.GET)
    public String homeinternship(Model model,Principal principal) {
    	try {
    		System.out.println(" useruseruser == nnn "+principal.getName());
        	User user =userService.findUserByEmail(principal.getName());
        	long roleId= user.getRoleId();
        	Role role = roleReposity.findOne(roleId);
        	String rolename =role.getName();
        	model.addAttribute("checkrole",rolename);
        	
		} catch (Exception e) {
			// TODO: handle exception
		}
        return "homepageinternship";
    }

    @RequestMapping(value = "/users",method = RequestMethod.GET)
    public String getAllUser( Model model){
        model.addAttribute("user",userRepository.findAll());

        return "users";
    }

}
