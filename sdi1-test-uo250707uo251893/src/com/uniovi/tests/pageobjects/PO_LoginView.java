package com.uniovi.tests.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PO_LoginView extends PO_NavView {

	static public void fillForm(WebDriver driver, String usernamep, String passwordp) {
		
		WebElement dni = driver.findElement(By.name("username"));
		dni.click();
		dni.clear();
		dni.sendKeys(usernamep);
		
		WebElement name = driver.findElement(By.name("password"));
		name.click();
		name.clear();
		name.sendKeys(passwordp);
		
		// Pulsar el boton de Alta.
		By boton = By.className("btn");
		driver.findElement(boton).click();
	}
	
	static public void goToLoginAndfillForm(WebDriver driver, String usernamep, String passwordp) {
		// Vamos al formulario de logueo.
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario
		PO_LoginView.fillForm(driver, usernamep, passwordp);
	}

	static public void goToLoginFillFormAndCheckWasOk(WebDriver driver, String usernamep, String passwordp) {
		goToLoginAndfillForm(driver, usernamep, passwordp);
		// Comprobamos que entramos en la pagina privada del usuario
		PO_View.checkElement(driver, "text", usernamep);
	}
	
	static public void goToLoginFillFormAndCheckWasWrong(WebDriver driver, String usernamep, String passwordp) {
		goToLoginAndfillForm(driver, usernamep, passwordp);
		// COmprobamos que volvemos a la pagina de login
		PO_View.checkElement(driver, "text", "Identifícate");
	}
	
}
