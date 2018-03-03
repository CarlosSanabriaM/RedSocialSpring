package com.uniovi.controllers;

import java.security.Principal;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.uniovi.entities.User;
import com.uniovi.services.RolesService;
import com.uniovi.services.SecurityService;
import com.uniovi.services.UsersService;
import com.uniovi.validators.SignUpFormValidator;

@Controller
public class UsersController {

	@Autowired
	private UsersService usersService;
	
	@Autowired
	private SecurityService securityService;
	
	@Autowired
	private SignUpFormValidator signUpFormValidator;
	
	@Autowired
	private RolesService rolesService;
	
	@Autowired
	private HttpSession httpSession;
	
	@RequestMapping("/signup")
	public String signup(Model model) {
		model.addAttribute("user", new User());
		return "signup";
	}

	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public String signup(@ModelAttribute @Validated User user, BindingResult result, Model model) {
		signUpFormValidator.validate(user, result);
		if(result.hasErrors()) {
			return "signup";
		}
		
		user.setRole(rolesService.getRoles()[0]); // Los usuarios registrados desde signup tienen role public TODO - cambiar!, debe ser una entidad
		usersService.addUser(user);
		securityService.autoLogin(user.getEmail(), user.getPasswordConfirm()); // Nada mas registrarse le hacemos que esté logeado
		return "redirect:user/list";
	}
	
	@RequestMapping("/login")
	public String login(@RequestParam(required=false) String error) {
		
		String urlLogin = (String) httpSession.getAttribute("login");
		if(urlLogin != null && urlLogin.equals("/admin/login") && error != null) {
			return "redirect:admin/login?error=credentials";
		}
		
		httpSession.setAttribute("login", "/login");
		return "login";
	}
	
	@RequestMapping("/admin/login")
	public String adminLogin() {
		httpSession.setAttribute("login", "/admin/login");
		return "admin/login";
	}
	
	@RequestMapping("/user/list")
	public String getListado(Model model, Pageable pageable, Principal principal,
			@RequestParam(value="", required=false) String searchText) {
		
		String urlLogin = (String) httpSession.getAttribute("login");
		String role = usersService.getUserByEmail(principal.getName()).getRole();
		
		if(urlLogin.equals("/admin/login") && !role.equals("ROLE_ADMIN")) {
			SecurityContextHolder.clearContext();
			return "redirect://localhost:8090/admin/login?error=role"; //TODO -  ESTO NO ES DEL todo CORRECTO, pero es que si no me retorna a una relativa --> /user/admin/login
		} 
		
		if(urlLogin.equals("/login") && !role.equals("ROLE_PUBLIC")) {
			SecurityContextHolder.clearContext();
			return "redirect://localhost:8090/login?error";
		}
		
		Page<User> users;
		if (searchText != null && !searchText.isEmpty()) {
			users = usersService.searchUsersByEmailAndName(pageable, searchText);
		} else {
			users = usersService.getUsers(pageable);
		}
		model.addAttribute("usersList", users.getContent());
		model.addAttribute("page", users);
		
		return "user/list";
	}
	
	@RequestMapping("/user/list/update")
	public String updateListado(Model model, Pageable pageable, Principal principal) {		
		Page<User> users = usersService.getUsers(pageable);
		model.addAttribute("usersList", users.getContent());
		model.addAttribute("page", users);
		
		return "user/list :: tableUsersAndPagination";
	}
	
	@RequestMapping("/user/delete/{id}")
	public String deleteUser(@PathVariable Long id) {	
		usersService.deleteUser(id);
		return "redirect:/user/list";
	}
	
}
