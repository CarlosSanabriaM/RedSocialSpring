package com.uniovi.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.uniovi.entities.Post;
import com.uniovi.entities.User;

@Component
public class PostAddFormValidator implements Validator {

	@Override
	public boolean supports(Class<?> aClass) {
		return User.class.equals(aClass);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Post post = (Post) target;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title", "Error.empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "text", "Error.empty");
		
		if (post.getTitle().length() < 3 || post.getTitle().length() > 30) {
			errors.rejectValue("title", "Error.post.add.title.length");
		}
		
		if (post.getText().length() < 3 || post.getText().length() > 500) {
			errors.rejectValue("text", "Error.post.add.text.length");
		}
		
	}

}
