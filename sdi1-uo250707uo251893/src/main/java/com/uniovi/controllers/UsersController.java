package com.uniovi.controllers;

import java.security.Principal;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
import com.uniovi.services.InsertSampleDataService;
import com.uniovi.services.LoggerService;
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
	private InsertSampleDataService insertSampleDataService;
	
	@Autowired
	private HttpSession httpSession;
	
	@Autowired
	private LoggerService loggerService;
	
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
		
		user.setRole(rolesService.getRoles()[0]); // Los usuarios registrados desde signup tienen role public
		usersService.addUser(user);
		
		loggerService.newUserHasSignedUp(user.getEmail());
		
		// Nada mas registrarse le hacemos que esté logeado
		securityService.autoLogin(user.getEmail(), user.getPasswordConfirm());	
		return "redirect:/user/list";
	}
	
	@RequestMapping("/login")
	public String login(@RequestParam(required=false) String error) {
		
		String urlLogin = (String) httpSession.getAttribute("login");
		if(urlLogin != null && urlLogin.equals("/admin/login") && error != null) {
			loggerService.errorByCredentialsInAdminLogin();
			return "redirect:/admin/login?error=credentials";
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
		User userInSession = usersService.getUserByEmail(principal.getName());
		String role = userInSession.getRole();
		String email = userInSession.getEmail();
		
		if(urlLogin!= null && urlLogin.equals("/admin/login") && !role.equals("ROLE_ADMIN")) {
			securityService.logoutUserInSession();
			
			loggerService.errorByRoleInAdminLogin(email);
			
			return "redirect:/admin/login?error=role";
		} 
		
		// Si accede desde un login, es que acaba de iniciar sesión
		if(urlLogin!= null) {
			loggerService.userHasLoggedInFrom(email, urlLogin);
			// Eliminamos el atributo de la sesión, ya que cuando acceda a /user/list
			// de nuevo, ya no va a venir de ningún formulario de login
			httpSession.removeAttribute("login");
		}
		
		Page<User> users;
		if (searchText != null && !searchText.isEmpty()) {
			users = usersService.searchUsersByEmailAndName(pageable, searchText);
			loggerService.userListSearchByEmailAndName(email, searchText, users);
		} else {
			users = usersService.getUsers(pageable);
			loggerService.userList(email, users);
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
		
		loggerService.userListUpdated(principal.getName(), users);
		
		return "user/list :: tableUsersAndPagination";
	}
	
	@RequestMapping("/user/delete/{id}")
	public String deleteUser(@PathVariable Long id, Principal principal) {	
		String emailUserDeleted = usersService.getUser(id).getEmail();
		usersService.deleteUser(id);
		
		loggerService.userDeleted(principal.getName(), id, emailUserDeleted);
		
		return "redirect:/user/list";
	}
	
	@RequestMapping("/admin/restart")
	public String restartDB(Principal principal) {
		insertSampleDataService.deleteAllAndInsertAgain();
		
		loggerService.databaseRestarted(principal.getName());
		
		return "admin/restarted";
	}
	
	@RequestMapping("/user/friends")
	public String getFriends(Pageable pageable, Principal principal, Model model) {
		Page<User> users = usersService.getFriends(pageable, principal.getName());
		
		model.addAttribute("usersList", users.getContent());
		model.addAttribute("page", users);
		
		return "/user/friends";
	}
	
}
