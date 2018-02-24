package com.uniovi.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.*;

@Component
public class SignUpFormValidator implements Validator {

	@Override
	public boolean supports(Class<?> arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void validate(Object arg0, Errors arg1) {
		// TODO Auto-generated method stub
		
	}
	
}