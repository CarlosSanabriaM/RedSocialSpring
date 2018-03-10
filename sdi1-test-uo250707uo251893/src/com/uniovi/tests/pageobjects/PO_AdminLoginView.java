package com.uniovi.tests.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PO_AdminLoginView extends PO_NavView {

	/**
	 * Espera a que se cargue el formulario de login del administrador y lo rellena con los datos indicados
	 * 
	 * @param driver: apuntando al navegador abierto actualmente
	 * @param usernamep: valor para el campo username
	 * @param passwordp: valor para el campo password
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
	 * Va al formulario de login del administrador y lo rellena con los datos indicados
	 * 
	 * @param driver: apuntando al navegador abierto actualmente
	 * @param usernamep: valor para el campo username
	 * @param passwordp: valor para el campo password
	 */
	static public void goToAdminLoginAndfillForm(WebDriver driver, String usernamep, String passwordp) {
		// Vamos a /login y esperamos a que cargue el enlace a /admin/login
		PO_HomeView.clickLinkAndCheckElement(driver, "aLogin", "id", "aAdminLogin");
		// Clickamos el enlace /admin/login y cuando cargue, rellenamos el formulario
		PO_HomeView.clickLinkAndCheckElement(driver, "aAdminLogin", "id", "buttonSubmit");
		PO_AdminLoginView.fillForm(driver, usernamep, passwordp);
	}

	/**
	 * Va al formulario de login del administrador, lo rellena con los datos indicados y 
	 * comprueba que entra correctamente
	 * 
	 * @param driver: apuntando al navegador abierto actualmente
	 * @param usernamep: valor para el campo username
	 * @param passwordp: valor para el campo password
	 */
	static public void goToAdminLoginFillFormAndCheckWasOk(WebDriver driver, String usernamep, String passwordp) {
		PO_AdminLoginView.goToAdminLoginAndfillForm(driver, usernamep, passwordp);
		// Comprobamos que entramos en la pagina privada del administrador
		PO_View.checkElement(driver, "text", "Usuario autenticado: " + usernamep);
	}
	
	/**
	 * Va al formulario de login del administrador, lo rellena con los datos indicados y 
	 * comprueba que se produce el error indicado en el idioma indicado
	 * 
	 * @param errorKey: clave del error en el fichero de propiedades
	 * @param language: idioma en el que se va a mostrar el error
	 */
	static public void goToAdminLoginFillFormAndCheckWasWrong(WebDriver driver, String usernamep, 
			String passwordp, String errorKey, int language) {
		
		PO_AdminLoginView.goToAdminLoginAndfillForm(driver, usernamep, passwordp);
		// Comprobamos que volvemos a la pagina de login y se muestra el error indicado en el idioma indicado
		PO_View.checkKey(driver, errorKey, language);
	}
	
}
