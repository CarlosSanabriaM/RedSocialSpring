package com.uniovi.tests.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PO_LoginView extends PO_NavView {

	/**
	 * Espera a que se cargue el formulario de login y lo rellena con los datos indicados
	 * 
	 * @param driver
	 * @param usernamep
	 * @param passwordp
	 */
	static public void fillForm(WebDriver driver, String usernamep, String passwordp) {
		
		// Si ya está cargado el formulario, no espera, y si no está cargado, espera a que se cargue
		checkElement(driver, "id", "buttonSubmit");
		
		WebElement dni = driver.findElement(By.name("username"));
		dni.click();
		dni.clear();
		dni.sendKeys(usernamep);
		
		WebElement name = driver.findElement(By.name("password"));
		name.click();
		name.clear();
		name.sendKeys(passwordp);
		
		// Pulsar el boton de Alta.
		By boton = By.id("buttonSubmit");
		driver.findElement(boton).click();
	}
	
	/**
	 * Va al formulario de login y lo rellena con los datos indicados
	 * 
	 * @param driver: apuntando al navegador abierto actualmente
	 * @param usernamep: valor para el campo username
	 * @param passwordp: valor para el campo password
	 */
	static public void goToLoginAndfillForm(WebDriver driver, String usernamep, String passwordp) {
		PO_HomeView.clickLinkAndCheckElement(driver, "aLogin", "id", "buttonSubmit");
		PO_LoginView.fillForm(driver, usernamep, passwordp);
	}

	/**
	 * Va al formulario de login, lo rellena con los datos indicados y comprueba que entra correctamente
	 * 
	 * @param driver: apuntando al navegador abierto actualmente
	 * @param usernamep: valor para el campo username
	 * @param passwordp: valor para el campo password
	 */
	static public void goToLoginFillFormAndCheckWasOk(WebDriver driver, String usernamep, String passwordp) {
		goToLoginAndfillForm(driver, usernamep, passwordp);
		// Comprobamos que entramos en la pagina privada del usuario
		PO_View.checkElement(driver, "text", "Usuario autenticado: " + usernamep);
	}
	
	/**
	 * Va al formulario de login, lo rellena con los datos indicados y 
	 * comprueba que se produce un error en el idioma indicado
	 * 
	 * @param errorKey: clave del error en el fichero de propiedades
	 * @param language: idioma en el que se va a mostrar el error
	 */
	static public void goToLoginFillFormAndCheckWasWrong(WebDriver driver, String usernamep, 
			String passwordp,int language) {
		
		goToLoginAndfillForm(driver, usernamep, passwordp);
		// Comprobamos que volvemos a la pagina de login y se muestra un error en el idioma indicado
		PO_View.checkKey(driver, "Error.login", language);
	}
	
}
