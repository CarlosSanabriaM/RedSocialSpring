package com.uniovi.controllers;

import java.security.Principal;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.uniovi.entities.Post;
import com.uniovi.entities.User;
import com.uniovi.services.PostsService;
import com.uniovi.services.UsersService;
import com.uniovi.validators.PostAddFormValidator;

@Controller
public class PostsController {

	@Autowired
	private PostsService postsService;
	
	@Autowired
	private UsersService usersService;
	
	@Autowired
	private PostAddFormValidator postAddFormValidator;
	
	@RequestMapping("/post/add")
	public String add(Model model) {
		model.addAttribute("post", new Post());
		return "post/add";
	}
	
	@RequestMapping(value = "/post/add", method = RequestMethod.POST)
	public String add(@ModelAttribute @Validated Post post, BindingResult result,
			Principal principal) {
		
		postAddFormValidator.validate(post, result);
		if(result.hasErrors()) {
			return "post/add";
		}
		
		String emailUserInSession = principal.getName();
		User userInSession = usersService.getUserByEmail(emailUserInSession); 
		
		post.setUser(userInSession);
		post.setDate(new Date()); // fecha actual
		postsService.addPost(post);
		
		return "redirect:/post/list/user/" + userInSession.getId();
	}
	
}
