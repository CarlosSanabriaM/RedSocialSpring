package com.uniovi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uniovi.entities.Post;
import com.uniovi.entities.User;
import com.uniovi.repositories.PostsRepository;

@Service
public class PostsService {

	@Autowired
	private PostsRepository postsRepository;
	
	@Autowired
	private LoggerService loggerService;
	
	public Post addPost(Post post) {
		Post saved = postsRepository.save(post);
		loggerService.newPostCreated(post);
		return saved;
	}

	public List<Post> getPostsForUser(User userInSession) {
		return postsRepository.findAllByUser(userInSession);
	}
	
}
