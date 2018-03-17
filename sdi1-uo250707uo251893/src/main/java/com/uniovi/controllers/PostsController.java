package com.uniovi.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.Principal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.uniovi.entities.Post;
import com.uniovi.entities.User;
import com.uniovi.services.LoggerService;
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
	
	@Autowired
	private LoggerService loggerService;
	
	@RequestMapping("/post/add")
	public String add(Model model) {		
		model.addAttribute("post", new Post());
		return "post/add";
	}
	
	@RequestMapping(value = "/post/add", method = RequestMethod.POST)
	public String addPost(@ModelAttribute @Validated Post post, BindingResult result,
			Principal principal, @RequestParam("image") MultipartFile image) {
		
		postAddFormValidator.validate(post, result);
		if(result.hasErrors()) {
			return "post/add";
		}
		
		String emailUserInSession = principal.getName();
		User userInSession = usersService.getUserByEmail(emailUserInSession); 
		
		post.setUser(userInSession);
		post.setDate(new Date()); // fecha actual
		post.setContainsImage(!image.isEmpty());
		Post saved = postsService.addPost(post);
		
		if(!image.isEmpty()) {
			saveImage(image,result,saved);
			if(result.hasErrors()) {
				return "post/add";				
			}
		}
		
		return "redirect:/post/list";
	}
	
	private void saveImage(MultipartFile image,BindingResult result,Post post) {
		try {
			InputStream is = image.getInputStream();
			Files.copy(is, Paths.get("src/main/resources/static/img/" + post.getId() + ".jpg"), StandardCopyOption.REPLACE_EXISTING);
		}catch(IOException e) {
			e.printStackTrace();
			result.addError(new ObjectError("image", "Error.post.add.image"));
		}
	}

	@RequestMapping("/post/list")
	public String getList(Principal principal, Model model) {
		String emailUserInSession = principal.getName();
		User userInSession = usersService.getUserByEmail(emailUserInSession); 
		
		List<Post> posts = postsService.getPostsForUser(userInSession);
		model.addAttribute("postsList", posts);
		loggerService.userListHisPosts(emailUserInSession, posts);
		
		return "post/list";
	}
	
	@RequestMapping("/post/list/{id}")
	public String getPostsOf(@PathVariable Long id, Principal principal, Model model) {
		User user = usersService.getUser(id);
		User userInSession = usersService.getUserByEmail(principal.getName());
		
		if(!user.getFriends().contains(userInSession))
			return "redirect:/";
		
		List<Post> posts = postsService.getPostsForUser(user);
		model.addAttribute("postsList", posts);
		loggerService.userListHisPosts(user.getEmail(), posts);
		
		return "post/list";
	}
	
}
